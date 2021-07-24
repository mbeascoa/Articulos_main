package com.example.articulos_main;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ConsultaPorNombre extends AppCompatActivity {
    private EditText consulxnombre;
    private TextView codcxn, nomcxn, descxn, precxn, conta;
    List<Productos> datosProductos = new  ArrayList<>();
    private int contador = 0;
    private int indice1 = 0;
    private int indice2 = 0;
    private int vuelta = 0;
    private static final String TAG = GestorProductos.class.getSimpleName();
    private Button consultar, otraconsulta, siguiente, anterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_por_nombre);

        codcxn = (TextView) findViewById(R.id.et_codigo_consultapornombre);
        nomcxn = (TextView) findViewById(R.id.et_nombre_consultapornombre);
        descxn = (TextView) findViewById(R.id.et_descripcion_cosultapornombre);
        precxn = (TextView) findViewById(R.id.et_precio_cosultapornombre);

        consulxnombre = (EditText) findViewById(R.id.et_cosultapornombre);
        conta= (TextView)findViewById(R.id.tv_contador);

        consultar= (Button) findViewById(R.id.btn_consultarpornombre);
        otraconsulta = (Button) findViewById(R.id.btn_otraconsulta_cosultapornombre);
        siguiente = (Button) findViewById(R.id.btn_siguiente);
        anterior = (Button) findViewById(R.id.btn_anterior);
        siguiente.setVisibility(View.INVISIBLE);
        anterior.setVisibility(View.INVISIBLE);


    }


    public void consultarDatos(View view ) {
        try {

            //Ocultamos el teclado al presionar el boton
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(consulxnombre.getWindowToken(), 0);

            String nomb = consulxnombre.getText().toString();
            consultar.setVisibility(View.GONE);
            //consultar.setVisibility(View.INVISIBLE);

                BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBTienda", null, 1);
                SQLiteDatabase db = usdbh.getReadableDatabase();


                String[] args = new String[]{nomb};
                String sql = "Select * from Productos where nombre =?";
                Cursor c = db.rawQuery(sql, args);

                if (c.moveToFirst()) {

                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        int codigopro = Integer.parseInt(c.getString(c.getColumnIndexOrThrow("codigopro")));
                        String nombre = c.getString(c.getColumnIndexOrThrow("nombre"));
                        String descripcion = c.getString(c.getColumnIndexOrThrow("descripcion"));
                        int precio = Integer.parseInt(c.getString(c.getColumnIndexOrThrow("precio")));

                        Productos emp = new Productos();
                        emp.setCodigoProd(codigopro);
                        emp.setNombre(nombre);
                        emp.setDescripcion(descripcion);
                        emp.setPrecio(precio);
                        Log.i(TAG, "creado producto ");

                        datosProductos.add(emp);
                    } while (c.moveToNext());

                    if (datosProductos.size()<=1) {
                        conta.setText(" Se ha encontrado un único registro con ese nombre ");

                    } else {
                        siguiente.setVisibility(View.VISIBLE);
                        anterior.setVisibility(View.VISIBLE);
                    }
                    visibilidad(true);
                    c.moveToFirst();
                    obtener(c);
                    indice1 = contador + 1;
                    indice2 = datosProductos.size();
                    conta.setText(" Registro " + indice1 + " de un total de " + indice2 +" productos");
                } else {
                    visibilidad(false);
                    conta.setText(" No se encontaron Registros");
                }

            } catch(Exception e){
                Log.d(TAG, "ERROR: " + e.toString());
            }
        }


        public void visibilidad(Boolean bool) {
        if( bool) {
            codcxn.setVisibility(View.VISIBLE);
            nomcxn.setVisibility(View.VISIBLE);
            descxn.setVisibility(View.VISIBLE);
            precxn.setVisibility(View.VISIBLE);
        } else {
            codcxn.setVisibility(View.GONE);
            nomcxn.setVisibility(View.GONE);
            descxn.setVisibility(View.GONE);
            precxn.setVisibility(View.GONE);
        }
    }

    //  pasar al siguiente registro
    public void siguiente(View view) {
        try {
            String nomb = consulxnombre.getText().toString();

            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBTienda", null, 1);
            SQLiteDatabase db = usdbh.getReadableDatabase();

            String[] args = new String[]{nomb};
            String sql = "Select * from Productos where nombre =?";
            Cursor c = db.rawQuery(sql, args);

            vuelta +=1;
            if( vuelta <= 0) {
                vuelta = 1;
            }
            if (vuelta >= datosProductos.size()+1) {
                vuelta = 1;
            }

            if (c.moveToFirst()) {
             if ((vuelta <= (datosProductos.size())) && (vuelta>0 )) {
                    c.moveToFirst();
                    for (int i=2; i<=vuelta ; i++) {
                            c.moveToNext();
                        }
                    obtener(c);
                    }


                    conta.setText(" Registro "+ vuelta+" de un total de "+ indice2 + "productos");


            } else {
                conta.setText(" No se encontaron Registros");
                visibilidad(false);
            }

        } catch (Exception e){
                    Log.d(TAG, "ERROR: " + e.toString());
                }

    }


    //pasar al anterior registro

    public void anterior(View view) {
        try {
            String nomb = consulxnombre.getText().toString();
            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBTienda", null, 1);
            SQLiteDatabase db = usdbh.getReadableDatabase();

            String[] args = new String[]{nomb};
            String sql = "Select * from Productos where nombre =?";
            Cursor c = db.rawQuery(sql, args);

            vuelta-=1;
            if (c.moveToFirst()) {
                if (vuelta >=datosProductos.size()) {
                    vuelta = datosProductos.size()-1;
                }
                if( vuelta == 0) {
                    vuelta = datosProductos.size();
                }
                if ((vuelta <= datosProductos.size()) && (vuelta>0 )) {
                    for (int i=2; i<=vuelta ; i++) {
                        c.moveToNext();
                    }
                    obtener(c);
                    conta.setText(" Registro "+ vuelta+" de un total de "+ indice2);
                }
            }
        } catch (Exception e){
            Log.d(TAG, "ERROR: " + e.toString());
        }

    }

    private void obtener(Cursor c){
        codcxn.setText(c.getString(c.getColumnIndexOrThrow("codigopro")));
        nomcxn.setText(c.getString(c.getColumnIndexOrThrow("nombre")));
        descxn.setText(c.getString(c.getColumnIndexOrThrow("descripcion")));
        precxn.setText(c.getString(c.getColumnIndexOrThrow("precio")));
    }


    public void cerrarVentana(View view) {
        finish();
    }


    public void otraconsulta(View view) {
        consultar.setVisibility(View.VISIBLE);
        conta.setText(" " );

    }
}