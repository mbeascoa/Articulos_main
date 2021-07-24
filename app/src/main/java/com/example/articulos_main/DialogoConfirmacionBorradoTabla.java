package com.example.articulos_main;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DialogoConfirmacionBorradoTabla extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.alarma)
                .setTitle("¿Desea confirmar el borrado de la tabla Productos de la base de datos DBTienda?")
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((BorrarTabla)getActivity()).accionAceptar();
                            }
                        }
                )
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((BorrarTabla)getActivity()).accionCancelar();
                            }
                        }
                )
                .create();
    }
}