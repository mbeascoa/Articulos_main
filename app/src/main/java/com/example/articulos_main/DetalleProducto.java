package com.example.articulos_main;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleProducto extends AppCompatActivity {

    private static final String TAG = DetalleProducto.class.getSimpleName();
    private TextView coddet, nomdet, desdet, predet ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        coddet = (TextView) findViewById(R.id.et_codigo_detalle);
        nomdet = (TextView) findViewById(R.id.et_nombre_detalle);
        desdet = (TextView) findViewById(R.id.et_descripcion_detalle);
        predet = (TextView) findViewById(R.id.et_precio_detalle);



        //Recogemos los parámetros enviados por el primer Activity a través del método getExras
        Bundle bundle = getIntent().getExtras();
        int codigoId = bundle.getInt("NUMEROPROD");
        String codigo = String.valueOf(codigoId);
        Log.i(TAG, "onCreate Detalle Producto  : " + codigo);
        leerdetalle(codigo);

    }



    public void leerdetalle(String codigo) {
        try {

            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBTienda", null, 1);
            SQLiteDatabase db = usdbh.getReadableDatabase();


            String[] args = new String[]{codigo};
            String sql = "Select * from Productos where codigopro =?";
            Cursor c = db.rawQuery(sql, args);
            c.moveToFirst();

            coddet.setText(c.getString(c.getColumnIndexOrThrow("codigopro")));
            nomdet.setText(c.getString(c.getColumnIndexOrThrow("nombre")));
            desdet.setText(c.getString(c.getColumnIndexOrThrow("descripcion")));
            predet.setText(c.getString(c.getColumnIndexOrThrow("precio")));

            db.close();
        } catch (Exception e) {
            Log.d(TAG, "ERROR: " + e.toString());
        }
    }


    public void cerrarVentana(View view) {
        finish();
    }
}