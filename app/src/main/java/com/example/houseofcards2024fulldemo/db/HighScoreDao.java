package com.example.houseofcards2024fulldemo.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface HighScoreDao {

    @Query("SELECT * FROM HighScoreEntity ORDER BY score DESC LIMIT 10")
    List<HighScoreEntity> getTopTenScores();

    @Query("SELECT * FROM HighScoreEntity WHERE synced_with_server = 0")
    List<HighScoreEntity> getUnsyncedScores();

    @Insert
    void saveHighScore(HighScoreEntity score);

    @Update
    void updateHighScore(HighScoreEntity score);

    @Query("DELETE FROM HighScoreEntity WHERE synced_with_server = 1")
    void deleteSyncedScores();
}
