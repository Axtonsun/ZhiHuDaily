package com.example.axtonsun.zhihudaily.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AxtonSun on 2016/8/21.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "daily_news.db";
    public static final String TABLE_NAME = "daily_news_fav";
    public static final int DB_VERSION = 1;
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NEWS_ID = "news_id";
    public static final String COLUMN_NEWS_TITLE = "news_title";
    public static final String COLUMN_NEWS_IMAGE = "news_image";

    public static final String DATABASE_CREATE
            = " CREATE TABLE " + TABLE_NAME
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NEWS_ID + " INTEGER UNIQUE, "
            + COLUMN_NEWS_TITLE + " TEXT, "
            + COLUMN_NEWS_IMAGE + " TEXT);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //如果是数据库中有这个表，先drop掉，然后create表，然后再进行数据插入
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
