package com.example.articulos_main;

public class Productos {
    private int mCodigoProd,mPrecio;
    private String mNombre, mDescripcion;

    public Productos() {
    }


    public Productos(int mCodigoProd, String mNombre, String mDescripcion, int mPrecio) {
        this.mCodigoProd= mCodigoProd;
        this.mNombre = mNombre;
        this.mDescripcion = mDescripcion;
        this.mPrecio = mPrecio;
    }


    public int getCodigoProd() {
        return this.mCodigoProd;
    }

    public void setCodigoProd(int mCodigoProd) {
        this.mCodigoProd = mCodigoProd;
    }

    public int getPrecio() {
        return this.mPrecio;
    }

    public void setPrecio(int mPrecio) {
        this.mPrecio = mPrecio;
    }

    public String getNombre() {
        return this.mNombre;
    }

    public void setNombre(String mNombre) {
        this.mNombre = mNombre;
    }

    public String getDescripcion() {
        return this.mDescripcion;
    }

    public void setDescripcion(String mDescripcion) {
        this.mDescripcion = mDescripcion;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "Codigo Producto = " + mCodigoProd +
                ", Precio= " + mPrecio +
                ", Nombre ='" + mNombre + '\'' +
                ", Descripcion='" + mDescripcion + '\'' +
                '}';
    }
}