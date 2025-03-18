package com.example.houseofcards2024fulldemo.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListResponse {

    @SerializedName("items")
    private List<HighScore> scores;

    public ListResponse() {
    }

    public List<HighScore> getScores() {
        return scores;
    }
}
