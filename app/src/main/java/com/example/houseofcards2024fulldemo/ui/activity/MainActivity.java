package com.example.houseofcards2024fulldemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.houseofcards2024fulldemo.domain.Game;
import com.example.houseofcards2024fulldemo.domain.House;
import com.example.houseofcards2024fulldemo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Game game;

    private TextView tvPoints;
    private TextView tvNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();

        tvPoints = findViewById(R.id.tv_points);
        tvNumber = findViewById(R.id.tv_number);

        refreshDisplay();

        setupButtons();
    }

    private void refreshDisplay() {
        tvPoints.setText(String.valueOf(game.getPlayerPoints()));
        tvNumber.setText(String.valueOf(game.getNumber()));
    }

    private void setupButtons() {
        Button btnAlpha = findViewById(R.id.btn_alpha);
        btnAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleHouseClicked(btnAlpha, 0, getString(R.string.alpha));
            }
        });

        Button btnBravo = findViewById(R.id.btn_bravo);
        btnBravo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleHouseClicked(btnBravo, 1, getString(R.string.bravo));
            }
        });

        Button btnCharlie = findViewById(R.id.btn_charlie);
        btnCharlie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleHouseClicked(btnCharlie, 2, getString(R.string.charlie));
            }
        });

        Button btnDelta = findViewById(R.id.btn_delta);
        btnDelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleHouseClicked(btnDelta, 3, getString(R.string.delta));
            }
        });
    }

    private void handleHouseClicked(Button btnHouse, int houseIndex, String houseName) {
        House selectedHouse = game.assignToHouse(houseIndex);
        btnHouse.setText(houseName + " " + selectedHouse.getCurrentSum());
        if (selectedHouse.isClosed()) {
            btnHouse.setEnabled(false);
        }

        refreshDisplay();

        if (game.isGameOver()) {
            Intent intent = GameOverActivity.getIntent(this, game.getPlayerPoints());
            startActivity(intent);

            finish();
        }
    }
}
