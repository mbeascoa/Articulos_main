package com.example.articulos_main;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class BorrarProducto extends AppCompatActivity {
    private static final String TAG = BorrarProducto.class.getSimpleName();
    private EditText consulxid;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_producto);
        consulxid = (EditText) findViewById(R.id.et_codigoempborraremp);
        resultado = (TextView) findViewById(R.id.tv_resultado_borrar_empleado);


    }


    public void borrarDato(View view ) {
    mostrarDialogoConfirmacionBorrado(view);
    }


    public void mostrarDialogoConfirmacionBorrado(View view) {
        DialogoConfirmacionBorrado confirmacion  = new DialogoConfirmacionBorrado();
        confirmacion.show(getFragmentManager(), "Cuadro confirmación borrado");
    }

    public void accionAceptar() {
        try {
            mensajePersonalizado("Borrando Registro, gracias");
            String id = consulxid.getText().toString();
            com.example.articulos_main.BaseDatosHelper usdbh = new com.example.articulos_main.BaseDatosHelper(this, "DBTienda", null, 1);
            SQLiteDatabase db = usdbh.getWritableDatabase();
            String tabla = "Productos";
            String whereClause = "codigopro=?";
            String[] whereArgs = new String[]{id};
            db.delete(tabla, whereClause, whereArgs);
            db.close();
            resultado.setText("Registro borrado , muchas gracias");
            Log.i(TAG, "Registro borrado , muchas gracias");

                Toast.makeText(this, "Registro dado de baja correctamente", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Log.d(TAG, "ERROR: " + e.toString());

        }

    }

    public void accionCancelar() {
        mensajePersonalizado("Cancelando borrado del producto");
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