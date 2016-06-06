package com.ackerman.j.gavin.zootrack.Config.DbConstants;

import android.database.sqlite.SQLiteOpenHelper;

import com.ackerman.j.gavin.zootrack.Repository.Impl.AnimalRepositoryImpl;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ackerman.j.gavin.zootrack.Repository.AnimalRepository;

/**
 * Created by hashcode on 2016/06/02.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = DBHelper.class.getSimpleName();

    public DBHelper(Context context) {
        super(context, DbConstants.DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String animal = " CREATE TABLE " + AnimalRepositoryImpl.TABLE_NAME
                + "("
                + AnimalRepositoryImpl.COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + AnimalRepositoryImpl.COLUMN_NAME + " TEXT  NOT NULL , "
                + AnimalRepositoryImpl.COLUMN_COUNTRY + " TEXT NOT NULL, "
                + AnimalRepositoryImpl.COLUMN_AGE+ " INTEGER NOT NULL "
                + AnimalRepositoryImpl.COLUMN_SPECIES + " TEXT NOT NULL "
                + AnimalRepositoryImpl.COLUMN_STOCK + " INTEGER NOT NULL, "
                + AnimalRepositoryImpl.COLUMN_TYPE + " TEXT NOT NULL "
                + AnimalRepositoryImpl.COLUMN_FOODNAME + " TEXT NOT NULL "
                + AnimalRepositoryImpl.COLUMN_PRICE + " REAL NOT NULL "
                + ")";


        // Database creation sql statement


        Log.d(TAG, "onCreate with SQL: " + animal);


        db.execSQL(animal);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + AnimalRepositoryImpl.TABLE_NAME);

        onCreate(db);
    }
}
