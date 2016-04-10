package com.example.juan.examen;

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

    public static final String CREATE_TABLE = "create table " + TABLE_NAME +
            " (" + AVES_ID + " integer primary key autoincrement, " +
            AVES_NOMBRE_COMUN + "text not null, " +
            AVES_NOMBRE_CIENTIFICO + "text not null, " +
            AVES_DESCRIPCION + "text not null, " +
            AVES_GENERALIDADES + "text not null;";



}
