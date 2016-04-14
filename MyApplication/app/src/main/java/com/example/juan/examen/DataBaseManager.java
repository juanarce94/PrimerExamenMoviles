package com.example.juan.examen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Juan on 4/10/2016.
 */
public class DataBaseManager {

    //General Strings
    public static final String AVES_TABLE_NAME = "aves";
    public static final String USERS_TABLE_NAME = "users";
    public static final String ID = "id";
    
    //Ave Strings
    public static final String AVES_NOMBRE_COMUN = "nombre_co";
    public static final String AVES_NOMBRE_CIENTIFICO = "nombre_ci";
    public static final String AVES_DESCRIPCION = "descripcion";
    public static final String AVES_GENERALIDADES = "generalidades";

    
    //User Strings
    public static final String USER_NAME = "name";
    public static final String USER_PASSWORD = "password";


    //Attributes
    public static SQLiteDatabase avesDataBase;
    private DataBaseHelper dbHelper;

    public static String newAvesTable = "create table if not exists " + AVES_TABLE_NAME +
                " (" + ID + " integer primary key autoincrement, " +
                AVES_NOMBRE_COMUN + " text not null, " +
                AVES_NOMBRE_CIENTIFICO + " text not null, " +
                AVES_DESCRIPCION + " text not null, " +
                AVES_GENERALIDADES + " text not null);";

    public static String newUsersTable = "create table if not exists " + USERS_TABLE_NAME +
                " (" + ID + " integer primary key autoincrement, " +
                USER_NAME + " text not null, " +
                USER_PASSWORD + " text not null);";

    public DataBaseManager(Context context, String name) {
        dbHelper = new DataBaseHelper(context, name);
        avesDataBase =  dbHelper.getWritableDatabase();
        //context.deleteDatabase("EXAMEN_DATA_BASE");
    }

    public void deleteTable(String tableName){

        avesDataBase.execSQL("DELETE FROM " + tableName + " ;");

    }
    public void dropTable(String tableName){

        avesDataBase.execSQL("drop table " + tableName + " ;");

    }

    public ArrayList<String> getData(String tableName){
        ArrayList<String> output = new ArrayList<>();
        String[] columns = new String[] {ID,USER_NAME,USER_PASSWORD};
        Cursor cursor = avesDataBase.query(tableName, columns, null, null, null, null, null);

        int iID = cursor.getColumnIndex(ID);
        int iName = cursor.getColumnIndex(USER_NAME);
        int iPassword = cursor.getColumnIndex(USER_PASSWORD);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            output.add(cursor.getString(iID) + " " + cursor.getString(iName) + " " + cursor.getString(iPassword));
        }
        return output;
    }
    public static ArrayList<String> getAves(){
        ArrayList<String> output = new ArrayList<>();
        String[] columns = new String[] {ID,AVES_NOMBRE_COMUN,AVES_NOMBRE_CIENTIFICO,AVES_DESCRIPCION,AVES_GENERALIDADES};
        Cursor cursor = avesDataBase.query(AVES_TABLE_NAME,columns,null,null,null,null,null);

        int iID = cursor.getColumnIndex(ID);
        int iNombreCo = cursor.getColumnIndex(AVES_NOMBRE_COMUN);
        int iNombreCi = cursor.getColumnIndex(AVES_NOMBRE_CIENTIFICO);
        int iDescripcion = cursor.getColumnIndex(AVES_DESCRIPCION);
        int iGeneralidades = cursor.getColumnIndex(AVES_GENERALIDADES);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            output.add(cursor.getString(iID) + " " +
                       cursor.getString(iNombreCo) + " " +
                       cursor.getString(iNombreCi) + " " +
                       cursor.getString(iDescripcion) + " " +
                       cursor.getString(iGeneralidades));
        }

        return output;
    }
    public static Ave getAve(String id){

        String[] columns = new String[] {ID,AVES_NOMBRE_COMUN,AVES_NOMBRE_CIENTIFICO,AVES_DESCRIPCION,AVES_GENERALIDADES};
        Cursor cursor = avesDataBase.query(AVES_TABLE_NAME,columns,null,null,null,null,null);

        int iID = cursor.getColumnIndex(ID);
        int iNombreCo = cursor.getColumnIndex(AVES_NOMBRE_COMUN);
        int iNombreCi = cursor.getColumnIndex(AVES_NOMBRE_CIENTIFICO);
        int iDescripcion = cursor.getColumnIndex(AVES_DESCRIPCION);
        int iGeneralidades = cursor.getColumnIndex(AVES_GENERALIDADES);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            if(cursor.getString(iID).equals(id)){
                return  new Ave(cursor.getString(iNombreCo),
                                 cursor.getString(iNombreCi),
                                 cursor.getString(iDescripcion),
                                 cursor.getString(iGeneralidades));
            }
        }

        return new Ave("","","","");
    }
    public boolean logRequest(String name, String password){

        String[] columns = new String[] {ID,USER_NAME,USER_PASSWORD};
        Cursor cursor = avesDataBase.query(USERS_TABLE_NAME, columns, null, null, null, null, null);

        int iName = cursor.getColumnIndex(USER_NAME);
        int iPassword = cursor.getColumnIndex(USER_PASSWORD);

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){

            if(cursor !=  null)
                if(name.equals(cursor.getString(iName)) && password.equals(cursor.getString(iPassword)))
                    return true;
        }
        return false;
    }

    public static void insertDB(Object data, String tableName){
        switch (tableName){
            case AVES_TABLE_NAME:
                insertAves((Ave)data);
                return;
            case USERS_TABLE_NAME:
                insertUsers((User)data);
                return;
        }
    }



    private static void insertAves(Ave ave){

        String insertion = "insert into " + AVES_TABLE_NAME + " (" +
                AVES_NOMBRE_COMUN + " , " +
                AVES_NOMBRE_CIENTIFICO + " , " +
                AVES_DESCRIPCION + " , " +
                AVES_GENERALIDADES +
                " ) values ( \"" +
                ave.nombreComun + "\" , \"" +
                ave.nombreCientifico + "\" , \"" +
                ave.descripcion + "\" , \"" +
                ave.generalidades + "\" );";

        avesDataBase.execSQL(insertion);

    }
    public static void deleteAves(String id){
        avesDataBase.delete(AVES_TABLE_NAME, ID + "=?", new String[]{id});
    }
    public static void updateAves(String id, Ave ave){

    String updateSql = "update " + AVES_TABLE_NAME + " set " +
            AVES_NOMBRE_COMUN + " = \"" + ave.nombreComun + "\" , " +
            AVES_NOMBRE_CIENTIFICO + " = \"" + ave.nombreCientifico + "\" , " +
            AVES_DESCRIPCION + " = \"" + ave.descripcion + "\" , " +
            AVES_GENERALIDADES + " = \"" + ave.generalidades +
            "\" where " + ID + " = \"" + id + "\";";
    avesDataBase.execSQL(updateSql);


    }

    private static void insertUsers(User user){
        String insertion = "insert into " + USERS_TABLE_NAME + " (" +
                USER_NAME + " , " +
                USER_PASSWORD +
                " ) values ( \"" +
                user.nombre + "\" , \"" +
                user.password + "\" );";
        avesDataBase.execSQL(insertion);
    }
    private static void deleteUsers(int id){
        avesDataBase.delete(USERS_TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
    }
    private static void updateUsers(int id, User user){
        String updateSql = "update " + USERS_TABLE_NAME + " set " +
                USER_NAME + " = \"" + user.nombre + "\" , " +
                USER_PASSWORD + " = \"" + user.password +
                "\" where " + ID + " = \"" + String.valueOf(id) + "\";";
        avesDataBase.execSQL(updateSql);
    }
}
