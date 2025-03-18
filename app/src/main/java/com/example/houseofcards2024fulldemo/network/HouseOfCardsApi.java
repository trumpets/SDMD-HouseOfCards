package com.example.houseofcards2024fulldemo.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HouseOfCardsApi {

    static final String BASE_URL = "https://city-201617.appspot.com/_ah/api/highscores/v1/";

    static final String SCORE_URL = "score";

    @GET(SCORE_URL)
    Call<ListResponse> getHighScores();

    @POST(SCORE_URL)
    Call<Void> createHighScore(@Body HighScore highscore);
}
