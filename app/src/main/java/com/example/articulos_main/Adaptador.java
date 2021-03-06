package com.example.articulos_main;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>  {

    private List<Productos> listaProductos;
    int posicionseleccionada = 0;
    private static final String TAG = Adaptador.class.getSimpleName();

    public Adaptador(List<Productos> ListaProducto) {

        this.listaProductos = ListaProducto;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_datos, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int codigopro = listaProductos.get(position).getCodigoProd();
        holder.txtcodigo.setText(String.valueOf(codigopro));

        String nomb = listaProductos.get(position).getNombre();
        holder.txtnombre.setText(nomb);


        int preci = listaProductos.get(position).getPrecio();
        holder.txtprecio.setText(String.valueOf(preci));

        holder.cardview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                posicionseleccionada = position;

                if (posicionseleccionada == position) {
                    holder.txtnombre.setTextColor(Color.RED);


                } else {
                    holder.txtnombre.setTextColor(Color.DKGRAY);
                }
                notifyDataSetChanged();
                //Notificamos cambios para que el contenedr se entere y refresque los datos
                Intent i = new Intent(holder.itemView.getContext(), DetalleProducto.class);

                i.putExtra("NUMEROPROD", listaProductos.get(position).getCodigoProd());
                holder.itemView.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtnombre, txtprecio, txtcodigo;
        private CardView cardview;
        public ViewHolder(View v) {
            super(v);
            txtcodigo = (TextView) v.findViewById(R.id.txtCodigo);
            txtnombre=(TextView) v.findViewById(R.id.txtNombre);
            txtprecio=(TextView) v.findViewById(R.id.txtPrecioVariable);
            cardview = (CardView) v.findViewById(R.id.cv1);

        }
    }

}