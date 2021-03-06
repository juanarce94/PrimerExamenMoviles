package com.example.juan.examen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juan on 4/10/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private  final static int DB_SCHEME_VERSION = 1;

    public DataBaseHelper(Context context, String dataBaseName) {
        super(context, dataBaseName ,null,DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseManager.newAvesTable);
        db.execSQL(DataBaseManager.newUsersTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
