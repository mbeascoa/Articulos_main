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

public class AltaProducto extends AppCompatActivity {

    private EditText mCodigo, mNombre, mDescripcion, mPrecio;
    private TextView tv_ResultadoAlta;
    boolean verificado = false;
    String codigopro, nombrepro, descripcionpro, preciopro;
    SQLiteDatabase db=null;
    private Validaciones objetoValidar;  //objeto de nuestra clase validaciones
    private static final String TAG = AltaProducto.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_producto);

        mCodigo = (EditText) findViewById(R.id.et_CodigoProducto_Alta);
        mNombre = (EditText) findViewById(R.id.et_Nombre_Alta);
        mDescripcion = (EditText) findViewById(R.id.et_Descripcion_Alta);
        mPrecio = (EditText) findViewById(R.id.et_Precio_Alta);
    }

        public void grabarBaseDatos(View view) {
            try {
                objetoValidar = new Validaciones();

                codigopro = mCodigo.getText().toString();
                nombrepro = mNombre.getText().toString();
                descripcionpro = mDescripcion.getText().toString();
                preciopro = mPrecio.getText().toString();


                //validamos que los editext no esten vacíos
                if (!objetoValidar.Vacio(mCodigo) && !objetoValidar.Vacio(mNombre)
                        && !objetoValidar.Vacio(mDescripcion) && !objetoValidar.Vacio(mPrecio))
                {
                    //validamos que el campo numeroAlta tiene un valor string que puede castearse a numero
                    if (!objetoValidar.isNumeric(codigopro) && !objetoValidar.isNumeric(preciopro)) {
                        Toast.makeText(this, "Introduzca un número , por favor", Toast.LENGTH_SHORT).show();
                    } else {
                        mostrarDialogoConfirmacionAlta(view);
                    }

                }
            } catch (Exception e) {
                Log.d(TAG, "ERROR: " + e.toString());
            }
        }

    public void mostrarDialogoConfirmacionAlta(View view) {
        DialogoConfirmacionAlta confirmacion  = new DialogoConfirmacionAlta();
        confirmacion.show(getFragmentManager(), "Cuadro confirmación alta");
    }

    public void accionAceptar() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(mPrecio.getWindowToken(), 0);

            mensajePersonalizado("Insertando Registro, gracias");
            BaseDatosHelper usdbh = new BaseDatosHelper(this, "DBTienda", null, 1);
            //Abrimos la base de datos 'DBTienda' en modo escritura
            db = usdbh.getWritableDatabase();

            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("codigopro", mCodigo.getText().toString());
            nuevoRegistro.put("nombre", mNombre.getText().toString());
            nuevoRegistro.put("descripcion", mDescripcion.getText().toString());
            nuevoRegistro.put("precio", mPrecio.getText().toString());


            //Insertamos el registro en la base de datos
            // primer parametro; tabla de la base de datos, Productos
            //segundo parametro, siempre nulo menos en los autoincrementales
            if (db != null) {
                db.insert("Productos", null, nuevoRegistro);
                //El segundo parámetro lo obviaremos por el momento ya que tan sólo se
                //hace necesario en casos muy puntuales
                //(por ejemplo para poder insertar registros completamente vacíos)

                this.tv_ResultadoAlta.setText("ALTA CORRECTA");
                Toast.makeText(this, "Registro dado de alta correctamente", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Log.d(TAG, "ERROR: " + e.toString());

        }

    }

    public void accionCancelar() {
        mensajePersonalizado("Cancelando Envio ");
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
