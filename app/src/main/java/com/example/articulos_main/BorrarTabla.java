package com.example.articulos_main;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BorrarTabla extends AppCompatActivity {
    private TextView resultadoBorrarTabla;
    private static final String TAG = BorrarTabla.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_tabla);
        resultadoBorrarTabla = (TextView) findViewById(R.id.tv_resultadoborrartabla);
        }


    public void borrarTabla(View view) {
        BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBTienda", null, 1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS Productos");
        resultadoBorrarTabla.setText("Se ha borrado correctamente la tabla ");
        db.close();
    }

    public void cerrarVentana(View view) {
        finish();
    }

}