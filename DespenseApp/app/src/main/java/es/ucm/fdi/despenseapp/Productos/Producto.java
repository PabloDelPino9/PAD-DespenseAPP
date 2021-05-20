package es.ucm.fdi.despenseapp.Productos;

import java.io.Serializable;

public class Producto implements Serializable {
    private int imgProducto;
    private String nombreProducto;
    private double precioAprox;
    private int idProd;

    public Producto(int imgFoto, String titulo, double precio, int producto) {
        this.imgProducto = imgFoto;
        this.nombreProducto = titulo;
        this.precioAprox = precio;
        this.idProd = producto;
    }

    public int getImgProducto() {
        return imgProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecioAprox() {
        return precioAprox;
    }

    public String toString(){
        return nombreProducto + " (" + precioAprox + " €)";
    }

    public int getIdProd() {
        return idProd;
    }
}
