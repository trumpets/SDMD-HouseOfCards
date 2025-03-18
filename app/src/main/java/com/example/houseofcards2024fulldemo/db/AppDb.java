package com.example.houseofcards2024fulldemo.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {HighScoreEntity.class}, version = 1)
public abstract class AppDb extends RoomDatabase {

    public static AppDb getDb(Context context) {
        return Room.databaseBuilder(context, AppDb.class, "house_of_cards.db")
                .allowMainThreadQueries()
                .build();
    }

    public abstract HighScoreDao highScoreDao();
}
