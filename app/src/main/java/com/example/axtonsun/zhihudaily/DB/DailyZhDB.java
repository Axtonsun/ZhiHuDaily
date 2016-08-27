package com.example.axtonsun.zhihudaily.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.axtonsun.zhihudaily.Bean.Stories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AxtonSun on 2016/8/21.
 */
public class DailyZhDB {

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private static  DailyZhDB mDailyZhDB;

    private DailyZhDB(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();//前提1
    }

    public synchronized static DailyZhDB getInstance(Context context) {
        if (mDailyZhDB == null) {
            mDailyZhDB = new DailyZhDB(context);
        }
        return mDailyZhDB;
    }

    public void saveFavourite(Stories stories) {//保存
        if (stories != null) {
            ContentValues values = new ContentValues();//前提2
            values.put(DBHelper.COLUMN_NEWS_ID, stories.getId());
            values.put(DBHelper.COLUMN_NEWS_TITLE, stories.getTitle());
            values.put(DBHelper.COLUMN_NEWS_IMAGE, stories.getImages());
            db.insert(DBHelper.TABLE_NAME, null, values);
        }
    }

    public List<Stories> loadFavourite() {
        List<Stories> favouriteList = new ArrayList<Stories>();
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {//移动到第一行
            do {
                Stories stories = new Stories();
                stories.setId(cursor.getInt(1));
                stories.setTitle(cursor.getString(2));
                favouriteList.add(stories);
            } while (cursor.moveToNext());//一直移动到下一行
        }
        cursor.close();//关闭游标
        return favouriteList;
    }

    public boolean isFavourite(Stories stories) {//查询
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, DBHelper.COLUMN_NEWS_ID + " = ?", new String[]{stories.getId() + ""}, null, null, null);
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    public void deleteFavourite(Stories stories) {//删除
        if (stories != null) {
            db.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_NEWS_ID + " = ?", new String[]{stories.getId() + ""});
        }
    }

    public synchronized void closeDB() {
        if (mDailyZhDB != null) {
            db.close();
        }
    }

}
