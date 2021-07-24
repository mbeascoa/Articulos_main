package com.example.articulos_main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BaseDatosHelper extends SQLiteOpenHelper {
   /*Sentencia SQL para crear la tabla de Productos
   Se evidencia en el código anterior la creación de una tabla denominada Productos, almacenando la sentencia SQL,
    como un String en la variable sqlCreate, luego esta varible será implementada por el método execSQL, que recibe
    el String de la sentencia SQL a ejecutar, por medio de la implementación de la variable db de tipo SQLiteDatabase,
    que se usa en los dos métodos anteriores.
    */


       String sqlCreate = "CREATE TABLE Productos (codigopro INTEGER,  nombre TEXT,  descripcion TEXT, precio INTEGER)";

    public BaseDatosHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
//NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
//      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
//      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
//      a la nueva, por lo que este método debería ser más elaborado.
//Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Productos");
//Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}