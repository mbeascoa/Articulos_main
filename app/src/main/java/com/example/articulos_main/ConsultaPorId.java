package com.example.articulos_main;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConsultaPorId extends AppCompatActivity {
    private EditText consulxid;
    private TextView nomcxi, codcxi, descxi,precxi;
    private static final String TAG = com.example.articulos_main.MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_por_id);
        codcxi = (TextView) findViewById(R.id.et_codigo_consultaporid);
        nomcxi = (TextView) findViewById(R.id.et_nombre_consultaporid);
        descxi = (TextView) findViewById(R.id.et_descripcion_consultaporid);
        precxi= (TextView) findViewById(R.id.et_precio_consultaporid);

        consulxid = (EditText) findViewById(R.id.et_consultaporid);


    }


    public void consultarDatos(View view) {
        try {
            String id = consulxid.getText().toString();
            //Escondemos el teclado al pulsa el boton consultar
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(consulxid.getWindowToken(), 0);

            com.example.articulos_main.BaseDatosHelper usdbh = new com.example.articulos_main.BaseDatosHelper(this, "DBTienda", null, 1);
            SQLiteDatabase db = usdbh.getReadableDatabase();


            String[] args = new String[]{id};
            String sql = "Select * from Productos where codigopro =?";
            Cursor c = db.rawQuery(sql, args);
            c.moveToFirst();

            codcxi.setText(c.getString(c.getColumnIndexOrThrow("codigopro")));
            nomcxi.setText(c.getString(c.getColumnIndexOrThrow("nombre")));
            descxi.setText(c.getString(c.getColumnIndexOrThrow("descripcion")));
            precxi.setText(c.getString(c.getColumnIndexOrThrow("precio")));

            db.close();
        } catch (Exception e) {
            Log.d(TAG, "ERROR: " + e.toString());
        }
    }

    public void cerrarVentana(View view) {
        finish();
    }
}