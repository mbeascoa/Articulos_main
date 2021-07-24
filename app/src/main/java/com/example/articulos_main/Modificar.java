package com.example.articulos_main;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Modificar extends AppCompatActivity {
    private EditText consulxid,  nommod, codmod, desmod, premod;
    private Button consultarxid, salir, modificar;
    private static final String TAG = Modificar.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        consulxid = (EditText) findViewById(R.id.et_modificar);

        codmod = (EditText) findViewById(R.id.et_codigo_modificar);
        nommod = (EditText) findViewById(R.id.et_nombre_modificar);
        desmod = (EditText) findViewById(R.id.et_descripcion_modificar);
        premod = (EditText) findViewById(R.id.et_precio_modificar);

        consultarxid = (Button) findViewById(R.id.btn_consultarid_modificar);
        modificar = (Button) findViewById(R.id.btn_consultar_modificar);
        salir = (Button) findViewById(R.id.btn_salir_modificar);

    }

    public void consultarDatos(View view) {
        try {
            String id = consulxid.getText().toString();

            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBTienda", null, 1);
            SQLiteDatabase db = usdbh.getWritableDatabase();


            String[] args = new String[]{id};
            String sql = "Select * from Productos where codigopro =?";
            Cursor c = db.rawQuery(sql, args);
            c.moveToFirst();

            codmod.setText(c.getString(c.getColumnIndexOrThrow("codigopro")));
            nommod.setText(c.getString(c.getColumnIndexOrThrow("nombre")));
            desmod.setText(c.getString(c.getColumnIndexOrThrow("descripcion")));
            premod.setText(c.getString(c.getColumnIndexOrThrow("precio")));

            db.close();
        } catch (Exception e){
            Log.d(TAG, "ERROR: " + e.toString());
        }

    }

    public void actualizarDato(View view) {

        try {
            String id = consulxid.getText().toString();
            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBTienda", null, 1);
            SQLiteDatabase db = usdbh.getWritableDatabase();
            String cod = codmod.getText().toString();
            String nom = nommod.getText().toString();
            String des = desmod.getText().toString();
            String pre = premod.getText().toString();


            ContentValues actualizaReg = new ContentValues();
            actualizaReg.put("codigopro", cod);
            actualizaReg.put("nombre", nom);
            actualizaReg.put("descripcion", des);
            actualizaReg.put("precio", pre);

            //Actualizamos el registroenla base de datos
            String[] args = new String[]{id};
            db.update("Productos", actualizaReg, "codigopro =?", args);


            db.close();
        }catch(Exception e) {
             Log.d(TAG, "ERROR: " + e.toString());
        }
}

    public void cerrarVentana(View view) {
        finish();
    }
}