package com.info.bitirmeprojesi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VeritabaniYardimcisi extends SQLiteOpenHelper {
    public VeritabaniYardimcisi(@Nullable Context context) {
        super(context, "chart.sql", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE chart (chart_id INTEGER PRIMARY KEY AUTOINCREMENT,chart_name TEXT, chart_count INTEGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS room (room_id INTEGER PRIMARY KEY AUTOINCREMENT,room_no INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS chart");
        db.execSQL("DROP TABLE IF EXISTS room");
        onCreate(db);
    }
}
