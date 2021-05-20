package es.ucm.fdi.despenseapp.Listas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import es.ucm.fdi.despenseapp.Listas.*;
import es.ucm.fdi.despenseapp.Productos.ListaDeProductos;
import es.ucm.fdi.despenseapp.Productos.Producto;
import es.ucm.fdi.despenseapp.Productos.ProductoComprado;

public class Lista implements Serializable {
    private String nombreLista;
    private Map<Integer, ProductoComprado> listaProductos;

    public Lista(String titulo, Map<Integer, ProductoComprado> listaProductos) {
        this.nombreLista = titulo;
        this.listaProductos = listaProductos;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public Map<Integer, ProductoComprado> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(Map<Integer, ProductoComprado> listaProductos) {
        this.listaProductos = listaProductos;
    }


    public void upCantidad(int position) {
        listaProductos.get(position).setCantidad(listaProductos.get(position).getCantidad() + 1);
    }

    public void downCantidad(int position) {
        if (listaProductos.get(position).getCantidad() != 0) {
            listaProductos.get(position).setCantidad(listaProductos.get(position).getCantidad() - 1);
        }
    }

    public void eliminarProducto(int position) {
        listaProductos.remove(position);
    }

    public void addProducto(int posicion) {
        Producto seleccionado = ListaDeProductos.getListaDeProductos().getListaProductos().get(posicion);
        ProductoComprado add = new ProductoComprado(seleccionado);
        listaProductos.put(posicion, add);
    }
}