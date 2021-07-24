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
    private EditText consulxid,  nommod, apemod, ofimod, dirmod,salmod, commod,numdepmod, fechamod;
    private Button consultarxid, salir, modificar;
    private static final String TAG = Modificar.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        consulxid = (EditText) findViewById(R.id.et_modificar);

        nommod = (EditText) findViewById(R.id.et_nombre_modificar);
        apemod = (EditText) findViewById(R.id.et_apellido_modificar);
        ofimod = (EditText) findViewById(R.id.et_descripcion_modificar);
        dirmod = (EditText) findViewById(R.id.et_direccion_modificar);
        salmod=(EditText)findViewById(R.id.et_salario_modificar);
        commod = (EditText) findViewById(R.id.et_comision_modificar);
        numdepmod = (EditText) findViewById(R.id.et_numero_departamento_modificar);
        fechamod = (EditText) findViewById(R.id.et_fechamodificar);
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
            String sql = "Select * from Empleados where codigoemp =?";
            Cursor c = db.rawQuery(sql, args);
            c.moveToFirst();

            nommod.setText(c.getString(c.getColumnIndexOrThrow("nombre")));
            apemod.setText(c.getString(c.getColumnIndexOrThrow("apellido")));
            ofimod.setText(c.getString(c.getColumnIndexOrThrow("oficio")));
            dirmod.setText(c.getString(c.getColumnIndexOrThrow("direccion")));
            salmod.setText(c.getString(c.getColumnIndexOrThrow("salario")));
            commod.setText(c.getString(c.getColumnIndexOrThrow("comision")));
            numdepmod.setText(c.getString(c.getColumnIndexOrThrow("numerodepartamento")));
            fechamod.setText(c.getString(c.getColumnIndexOrThrow("fechaalta")));
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
            String nom = nommod.getText().toString();
            String ape = apemod.getText().toString();
            String ofi = ofimod.getText().toString();
            String dir = dirmod.getText().toString();
            String sal = salmod.getText().toString();
            String com = commod.getText().toString();
            String numdep = numdepmod.getText().toString();
            String fecha = fechamod.getText().toString();

            ContentValues actualizaReg = new ContentValues();
            actualizaReg.put("nombre", nom);
            actualizaReg.put("apellido", ape);
            actualizaReg.put("oficio", ofi);
            actualizaReg.put("direccion", dir);
            actualizaReg.put("salario", sal);
            actualizaReg.put("comision", com);
            actualizaReg.put("numerodepartamento", numdep);
            actualizaReg.put("fechaalta", fecha);
            //Actualizamos el registroenla base de datos
            String[] args = new String[]{id};
            db.update("Empleados", actualizaReg, "codigoemp =?", args);


            db.close();
        }catch(Exception e) {
             Log.d(TAG, "ERROR: " + e.toString());
        }
}

    public void cerrarVentana(View view) {
        finish();
    }
}