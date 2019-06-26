package com.efsalokyay.bayrakquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Veritabani extends SQLiteOpenHelper {

    public Veritabani(Context context) {
        super(context, "bayrakquiz.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS bayraklar (bayrak_id INTEGER PRIMARY KEY AUTOINCREMENT, bayrak_ad TEXT, bayrak_resim TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS bayraklar");
        onCreate(db);
    }
}
