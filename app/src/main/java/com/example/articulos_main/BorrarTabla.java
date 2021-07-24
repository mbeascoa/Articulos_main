package com.example.articulos_main;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
        mostrarDialogoConfirmacionBorrarTabla( view);
    }



    public void mostrarDialogoConfirmacionBorrarTabla(View view) {
        DialogoConfirmacionBorradoTabla confirmacion  = new DialogoConfirmacionBorradoTabla();
        confirmacion.show(getFragmentManager(), "Cuadro confirmación borrado");
    }

    public void accionAceptar() {
        try {
            mensajePersonalizado("Borrando Tabla Productos, gracias");
            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBTienda", null, 1);
            SQLiteDatabase db = usdbh.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS Productos");
            resultadoBorrarTabla.setText("Se ha borrado correctamente la tabla ");
            db.close();
            Log.i(TAG, "Tabla borrada , muchas gracias");

            Toast.makeText(this, "Tabla Productos borrada correctamente", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Log.d(TAG, "ERROR: " + e.toString());

        }

    }

    public void accionCancelar() {
        mensajePersonalizado("Cancelando borrado de la tabla productos de la base de datos DBTienda");
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