package com.example.houseofcards2024fulldemo.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HighScoreEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "score")
    private int score;

    @ColumnInfo(name = "synced_with_server")
    private boolean syncedWithServer;

    public HighScoreEntity() {
    }

    public HighScoreEntity(int score, boolean syncedWithServer) {
        this.score = score;
        this.syncedWithServer = syncedWithServer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isSyncedWithServer() {
        return syncedWithServer;
    }

    public void setSyncedWithServer(boolean syncedWithServer) {
        this.syncedWithServer = syncedWithServer;
    }
}
