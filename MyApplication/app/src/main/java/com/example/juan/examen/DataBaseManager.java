package com.example.juan.examen;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Juan on 4/10/2016.
 */
public class DataBaseManager {

    //General Strings
    public static final String AVES_TABLE_NAME = "aves";
    public static final String USERS_TABLE_NAME = "users";
    private static final String ID = "id";
    
    //Ave Strings
    private static final String AVES_NOMBRE_COMUN = "nombre_co";
    private static final String AVES_NOMBRE_CIENTIFICO = "nombre_ci";
    private static final String AVES_DESCRIPCION = "descripcion";
    private static final String AVES_GENERALIDADES = "generalidades";

    
    //User Strings
    private static final String USER_NAME = "name";
    private static final String USER_PASSWORD = "password";


    //Attributes
    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    public static String newAvesTable = "create table " + AVES_TABLE_NAME +
                " (" + ID + " integer primary key autoincrement, " +
                AVES_NOMBRE_COMUN + " text not null, " +
                AVES_NOMBRE_CIENTIFICO + "text not null, " +
                AVES_DESCRIPCION + " text not null, " +
                AVES_GENERALIDADES + " text not null);";

    public static String newUsersTable = "create table " + USERS_TABLE_NAME +
                " (" + ID + " integer primary key autoincrement, " +
                USER_NAME + " text not null, " +
                USER_PASSWORD + " text not null);";

    public DataBaseManager(Context context, String name) {
        dbHelper = new DataBaseHelper(context, name);
        db =  dbHelper.getWritableDatabase();
    }
    
    public void insertDB(Object data, String tableName){
        switch (tableName){
            case AVES_TABLE_NAME:
                insertAves((Ave)data);
                return;
            case USERS_TABLE_NAME:
                insertUsers((User)data);
                return;
        }
    }
    public void daleteDB(int id, String tableName){
        switch (tableName){
            case AVES_TABLE_NAME:
                deleteAves(id);
                return;
            case USERS_TABLE_NAME:
                deleteUsers(id);
                return;
        }
    }
    public void updateDB(Object data, int id, String tableName){
        switch (tableName){
            case AVES_TABLE_NAME:
                updateAves(id, (Ave) data);
                return;
            case USERS_TABLE_NAME:
                updateUsers(id, (User) data);
                return;
        }
    }

    private void insertAves(Ave ave){

        String insertion = "insert into " + AVES_TABLE_NAME + " (" +
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
    private void deleteAves(int id){
        db.delete(AVES_TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
    }
    private void updateAves(int id, Ave ave){

    String updateSql = "update " + AVES_TABLE_NAME + " set " +
            AVES_NOMBRE_COMUN + " = " + ave.nombreComun + " , " +
            AVES_NOMBRE_CIENTIFICO + " = " + ave.nombreCientifico + " , " +
            AVES_DESCRIPCION + " = " + ave.descripcion + " , " +
            AVES_GENERALIDADES + " = " + ave.generalidades +
            " where " + ID + " = " + String.valueOf(id);
    db.execSQL(updateSql);


    }

    private void insertUsers(User user){
        String insertion = "insert into " + USERS_TABLE_NAME + " (" +
                USER_NAME +
                USER_PASSWORD +
                " ) values ( " +
                user.nombre +
                user.password + " );";
        db.execSQL(insertion);
    }
    private void deleteUsers(int id){
        db.delete(USERS_TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
    }
    private void updateUsers(int id, User user){
        String updateSql = "update " + USERS_TABLE_NAME + " set " +
                USER_NAME + " = " + user.nombre + " , " +
                USER_PASSWORD + " = " + user.password +
                " where " + ID + " = " + String.valueOf(id);
        db.execSQL(updateSql);
    }
}
