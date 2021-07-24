package com.example.articulos_main;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ConsultaProducto extends AppCompatActivity {

    private RecyclerView miRecicler;
    private RecyclerView.Adapter miAdapter;
    List<Productos> listadoDeProductos = new ArrayList<>();

    private static final String TAG = ConsultaProducto.class.getSimpleName();
    List<Productos> datosProductos = new  ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_producto);
        // Buscamos el control para cargar los datos
        miRecicler = (RecyclerView) findViewById(R.id.RV1);

        // Añadimos que  el tamaño del RecyclerView no se cambiará.
        // que tiene hijos (items) que tienen anchura y altura fijas. Permite
        // que el RecyclerView optimice mejor
        miRecicler.setHasFixedSize(true);

        miRecicler.setLayoutManager(new LinearLayoutManager(this));
        //Especificamos el adaptador con la lista a visualizar
        listadoDeProductos = recuperarProductos();
        miAdapter = new Adaptador(listadoDeProductos);
        miRecicler.setAdapter(miAdapter);

    }

    private List<Productos> recuperarProductos() {

        try {
            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBTienda", null, 1);

            SQLiteDatabase db = usdbh.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM Productos", null);
            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
              //int mCodigoemp,mSalario, mComision,mNumeroDepartamento;
              // String mNombre, mApellido, mOficio, mDireccion,mFechaAlta;
                do {
                    int codigopro = Integer.parseInt(c.getString(c.getColumnIndexOrThrow("codigopro")));
                    String nombre= c.getString(c.getColumnIndexOrThrow("nombre"));
                    String descripcion= c.getString(c.getColumnIndexOrThrow("descripcion")) ;
                    int precio=Integer.parseInt(c.getString(c.getColumnIndexOrThrow("precio")));

                    Productos prod = new Productos(codigopro, nombre, descripcion, precio);
                    datosProductos.add(prod);
                    Log.d(TAG, prod.toString());
                    //Toast.makeText(this, emp.toString(), Toast.LENGTH_SHORT).show();
                } while (c.moveToNext());
            }

        } catch (Exception e) {
            System.out.println(e.toString());

        }
        return datosProductos;
    }


    public void cerrarVentana(View view) {
        finish();
    }


}
