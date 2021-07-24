package com.example.articulos_main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Modificar extends AppCompatActivity {
    private EditText consulxid,  nommod, codmod, desmod, premod;
    private Button consultarxid, salir, modificar;
    private TextView resultado;
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
        resultado = (TextView) findViewById(R.id.txtResultadoModificar);

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
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        inputMethodManager.hideSoftInputFromWindow(premod.getWindowToken(), 0);

        mostrarDialogoConfirmacionModificacion(view);
    }


    public void borrarDato(View view ) {
        mostrarDialogoConfirmacionModificacion(view);
    }


    public void mostrarDialogoConfirmacionModificacion(View view) {
        DialogoConfirmacionModificacion confirmacion  = new DialogoConfirmacionModificacion();
        confirmacion.show(getFragmentManager(), "Cuadro confirmación modificar");
    }

    public void accionAceptar() {
        try {
            mensajePersonalizado("Modificando  Registro, gracias");
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
                resultado.setText("Registro modificado correctamente , muchas gracias");
                Log.i(TAG, "Registro modificado , muchas gracias");

                Toast.makeText(this, "Registro modificado correctamente", Toast.LENGTH_LONG).show();

                db.close();
            }catch(Exception e) {
                Log.d(TAG, "ERROR: " + e.toString());
            }


        } catch (Exception e) {
            Log.d(TAG, "ERROR: " + e.toString());

        }

    }

    public void accionCancelar() {
        mensajePersonalizado("Cancelando la modificación  del producto");
    }

    public void mensajePersonalizado(String opcion) {
        Toast mensaje = new Toast(getApplicationContext());

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.capa_toast,
                (ViewGroup) findViewById(R.id.lytLayout));

        TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
        txtMsg.setText(opcion);

        mensaje.setDuration(Toast.LENGTH_SHORT);
        mensaje.setView(layout);
        mensaje.show();
    }


    public void cerrarVentana(View view) {
        finish();
    }
}