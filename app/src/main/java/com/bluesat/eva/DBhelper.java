package com.bluesat.eva;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "location.sql";
    public static final String TABLE = "eva";
    public static final String COLOMN_ALTITUDE = "altitude";
    public static final String COLOMN_LONGTITUDE = "longtitude";
    public static final String COLOMN_LATITUDE = "latitude";
    public static final String COLOMN_TIME = "time";

    public DBhelper(Context c) {
        super(c, new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), DATABASE_NAME).getAbsolutePath() ,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + TABLE +
                "(" + COLOMN_ALTITUDE + " INTEGER,"+
                COLOMN_LATITUDE + " INTEGER,"+
                COLOMN_LONGTITUDE + " INTEGER,"+
                COLOMN_TIME +" STRING" +
                ")";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE);
        onCreate(db);
    }

    public void insertLocation(double altitude, double longtitude, double latitude, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLOMN_ALTITUDE,altitude);
        cv.put(COLOMN_LONGTITUDE,longtitude);
        cv.put(COLOMN_LATITUDE,latitude);
        cv.put(COLOMN_TIME,time);
        db.insert(TABLE,null,cv);

    }


}
