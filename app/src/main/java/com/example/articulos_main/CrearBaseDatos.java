package com.example.articulos_main;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CrearBaseDatos extends AppCompatActivity {


    private TextView resultado;
    private static final String TAG = com.example.articulos_main.MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_base_datos);
        resultado = (TextView) findViewById(R.id.tv_tablecreada);

        try {
            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBTienda", null, 1);
            //Abrimos la base de datos 'Productos' en modo escritura
            SQLiteDatabase db = usdbh.getWritableDatabase();

            if (db != null) {
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("codigoemp", 1);
                nuevoRegistro.put("nombre", "Miguel");
                nuevoRegistro.put("apellido", "Beascoa");
                nuevoRegistro.put("oficio", "Ingeniero");
                nuevoRegistro.put("direccion", "Arturo Soria");
                nuevoRegistro.put("fechaalta", "10/12/2021");
                nuevoRegistro.put("salario", 250000);
                nuevoRegistro.put("comision", 2500);
                nuevoRegistro.put("numerodepartamento", 10);

                //Insertamos el registro en la base de datos
                // primer registro de la base de datos, Productos
                //segundo parametro, siempre nulo menos en los autoincrementales

                db.insert("Productos", null, nuevoRegistro);
                //El segundo parámetro lo obviaremos por el momento ya que tan sólo se
                //hace necesario en casos muy puntuales
                //(por ejemplo para poder insertar registros completamente vacíos)


                this.resultado.setText("ALTA CORRECTA");
                Toast.makeText(this, "Un resgistro inicial dado de alta correctamente", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Log.d(TAG, "ERROR: " + e.toString());
        }
    }

    public void cerrarVentana(View view) {
        finish();
    }


}