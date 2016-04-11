package com.example.juan.examen;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Juan on 4/10/2016.
 */
public class AvesDataBaseManager {

    private static final String TABLE_NAME = "aves";
    private static final String AVES_ID = "id";
    private static final String AVES_NOMBRE_COMUN = "nombre_co";
    private static final String AVES_NOMBRE_CIENTIFICO = "nombre_ci";
    private static final String AVES_DESCRIPCION = "descripcion";
    private static final String AVES_GENERALIDADES = "generalidades";

    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    public static final String CREATE_TABLE = "create table " + TABLE_NAME +
            " (" + AVES_ID + " integer primary key autoincrement, " +
            AVES_NOMBRE_COMUN + " text not null, " +
            AVES_NOMBRE_CIENTIFICO + "text not null, " +
            AVES_DESCRIPCION + " text not null, " +
            AVES_GENERALIDADES + " text not null;";


    public AvesDataBaseManager(Context context) {
        dbHelper = new DataBaseHelper(context);
        db =  dbHelper.getWritableDatabase();

    }

    public void insert(Ave ave){

        String insertion = "insert into " + TABLE_NAME + " (" +
                AVES_NOMBRE_COMUN +
                AVES_NOMBRE_CIENTIFICO +
                AVES_DESCRIPCION +
                AVES_GENERALIDADES +
                " ) values ( " +
                ave.nombreComun +
                ave.nombreCientifico +
                ave.descripcion +
                ave.generalidades + " );";

        db.execSQL(insertion);

    }

    public void delete(int id){
        db.delete(TABLE_NAME, AVES_ID + "=?", new String[]{String.valueOf(id)});
    }



    public void update(int id, Ave ave){
        
    String updateSql = "update " + TABLE_NAME + " set " +
            AVES_NOMBRE_COMUN + " = " + ave.nombreComun + " , " +
            AVES_NOMBRE_CIENTIFICO + " = " + ave.nombreCientifico + " , " +
            AVES_DESCRIPCION + " = " + ave.descripcion + " , " +
            AVES_GENERALIDADES + " = " + ave.generalidades +
            " where " + AVES_ID + " = " + String.valueOf(id);
    db.execSQL(updateSql);


    }

}
