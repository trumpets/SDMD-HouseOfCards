package com.example.houseofcards2024fulldemo.network;

import com.google.gson.annotations.SerializedName;

public class HighScore {
    @SerializedName("score")
    private int score;

    public HighScore() {
    }

    public HighScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
