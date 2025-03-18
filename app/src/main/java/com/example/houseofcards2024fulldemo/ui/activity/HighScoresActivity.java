package com.example.houseofcards2024fulldemo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.houseofcards2024fulldemo.R;
import com.example.houseofcards2024fulldemo.db.AppDb;
import com.example.houseofcards2024fulldemo.db.HighScoreDao;
import com.example.houseofcards2024fulldemo.db.HighScoreEntity;
import com.example.houseofcards2024fulldemo.ui.adapter.HighScoreAdapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HighScoresActivity extends AppCompatActivity {

    public static Intent getIntent(Activity activity) {
        Intent intent = new Intent(activity, HighScoresActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ListView lvScores = findViewById(R.id.lv_scores);

        AppDb db = AppDb.getDb(this);
        HighScoreDao dao = db.highScoreDao();
        List<HighScoreEntity> scores = dao.getTopTenScores();

        lvScores.setAdapter(new HighScoreAdapter(this, scores));
    }
}
