package com.example.sonia.uf1evidencia;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyBBDD extends SQLiteOpenHelper{
    //creamos dos variables estaticas para enumerar la base de datos y darle nombre
    public static final String DATABASE_NAME = "MIBASEDEDATOS.db";
    public int DATABASE_VERSION = 1;
    //definimos una tabla con columnas en la base de datos
    public static final String TABLA_PALABRAS = "palabras";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_NOMBRE = "nombre";
    //creamos la tabla y especificamos su contenido
    private static final String SQL_CREAR = "create table "
            + TABLA_PALABRAS + "(" + COLUMNA_ID
            + " integer primary key autoincrement, " + COLUMNA_NOMBRE
            + " text not null);";
    public MyBBDD(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate (SQLiteDatabase db) {
        //teniendo sql crear solo nos falta usarlo
        db.execSQL(SQL_CREAR);

    }
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void agregar(MyBBDD) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMNA_NOMBRE, nombre);

        db.insert(TABLA_PALABRAS, null,values);
        db.close();

    }
    print()
}
