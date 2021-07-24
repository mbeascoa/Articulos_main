package com.example.articulos_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GestorProductos extends AppCompatActivity {

  private Button btncrear, btnconsultar, btnalta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btncrear = (Button)findViewById(R.id.btn_crearbasedatos);
        btnconsultar= (Button) findViewById(R.id.btn_consultar_productos);
        btnalta = (Button) findViewById(R.id.btn_altaproductos);

    }

    public void alta(View view){
        Intent i = new Intent (this, AltaProducto.class);
        startActivity(i);

    }

    public void consulta(View view){
        Intent i = new Intent (this, ConsultaProducto.class);
        startActivity(i);

    }
    public void crearBaseDatos(View view){
        Intent i = new Intent(this,CrearBaseDatos.class );
        startActivity(i);
    }

    public void consultaporId(View view){
        Intent i = new Intent ( this, ConsultaPorId.class);
        startActivity(i);
    }
    public void consultapornombre(View view){
        Intent i = new Intent ( this, ConsultaPorNombre.class);
        startActivity(i);
    }

    public void modificar(View view){
        Intent i = new Intent( this, Modificar.class);
        startActivity(i);
    }

    public void borrarempleado(View view){
        Intent i = new Intent( this, BorrarProducto.class);
        startActivity(i);
    }
    public void borrartabla(View view){
        Intent i = new Intent( this, BorrarTabla.class);
        startActivity(i);
    }
}
