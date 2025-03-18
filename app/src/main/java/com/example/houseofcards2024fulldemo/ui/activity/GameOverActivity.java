package com.example.houseofcards2024fulldemo.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.houseofcards2024fulldemo.R;
import com.example.houseofcards2024fulldemo.Utils;
import com.example.houseofcards2024fulldemo.db.AppDb;
import com.example.houseofcards2024fulldemo.db.HighScoreDao;
import com.example.houseofcards2024fulldemo.db.HighScoreEntity;
import com.example.houseofcards2024fulldemo.network.HighScore;
import com.example.houseofcards2024fulldemo.network.HouseOfCardsApi;
import com.example.houseofcards2024fulldemo.network.ListResponse;

import java.io.IOException;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameOverActivity extends AppCompatActivity {

    private static final String EXTRA_FINAL_POINTS = "final.points";

    public static Intent getIntent(Activity activity, int finalPoints) {
        Intent intent = new Intent(activity, GameOverActivity.class);
        intent.putExtra(GameOverActivity.EXTRA_FINAL_POINTS, finalPoints);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        int finalPoints = intent.getIntExtra(EXTRA_FINAL_POINTS, 0);

        AppDb db = AppDb.getDb(this);
        HighScoreDao dao = db.highScoreDao();
        dao.saveHighScore(new HighScoreEntity(finalPoints, false));

        syncScoresWithServer(dao);

        setupUI(finalPoints);
    }

    private void syncScoresWithServer(HighScoreDao dao) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new SyncWithServerTask().execute(dao);
        } else {
            Log.d(Utils.LOG_TAG, "No network connection, unable to sync with server.");
        }
    }

    private void setupUI(int finalPoints) {
        TextView tvFinalPoints = findViewById(R.id.tv_final_points);
        tvFinalPoints.setText(getString(R.string.final_points) + " " + finalPoints);

        findViewById(R.id.btn_high_scores).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(HighScoresActivity.getIntent(GameOverActivity.this));
            }
        });

        findViewById(R.id.btn_play_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }

    private class SyncWithServerTask extends AsyncTask<HighScoreDao, Void, Void> {

        @Override
        protected Void doInBackground(HighScoreDao... highScoreDaos) {
            HighScoreDao dao = highScoreDaos[0];

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(HouseOfCardsApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            HouseOfCardsApi api = retrofit.create(HouseOfCardsApi.class);

            List<HighScoreEntity> unsyncedScores = dao.getUnsyncedScores();
            for (int i = 0; i < unsyncedScores.size(); i++) {
                HighScoreEntity dbScore = unsyncedScores.get(i);

                try {
                    Response<Void> response = api.createHighScore(new HighScore(dbScore.getScore())).execute();
                    if (response.isSuccessful()) {
                        Log.d(Utils.LOG_TAG, "Score synced with server " + dbScore.getScore());
                        dbScore.setSyncedWithServer(true);
                        dao.updateHighScore(dbScore);
                    } else {
                        Log.d(Utils.LOG_TAG, "Syncing high score failed with response code: " + response.code());
                    }
                } catch (IOException e) {
                    Log.d(Utils.LOG_TAG, "Syncing high score failed with msg: " + e.getMessage());
                }
            }

            try {
                Response<ListResponse> response = api.getHighScores().execute();
                if (response.isSuccessful()) {
                    Log.d(Utils.LOG_TAG, "Scores fetched from server " + response.code());

                    dao.deleteSyncedScores();

                    List<HighScore> serverScores = response.body().getScores();
                    for (int i = 0; i < serverScores.size(); i++) {
                        HighScore serverScore = serverScores.get(i);
                        dao.saveHighScore(new HighScoreEntity(serverScore.getScore(), true));
                    }
                } else {
                    Log.d(Utils.LOG_TAG, "Fetching high scores failed with response code: " + response.code());
                }

            } catch (IOException e) {
                Log.d(Utils.LOG_TAG, "Fetching high score failed with msg: " + e.getMessage());
            }

            return null;
        }
    }
}
