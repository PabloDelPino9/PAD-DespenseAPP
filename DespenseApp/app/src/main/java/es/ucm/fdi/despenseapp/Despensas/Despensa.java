package es.ucm.fdi.despenseapp.Despensas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import es.ucm.fdi.despenseapp.Listas.Lista;
import es.ucm.fdi.despenseapp.Productos.ListaDeProductos;
import es.ucm.fdi.despenseapp.Productos.Producto;
import es.ucm.fdi.despenseapp.Productos.ProductoComprado;

public class Despensa implements Serializable {
    private String nombreDespensa;
    private Map<Integer, ProductoComprado> listaProductos;
    private ArrayList<Lista> listas;

    public Despensa(String titulo, Map<Integer, ProductoComprado> listaProductos, ArrayList<Lista> lista) {
        this.nombreDespensa = titulo;
        this.listaProductos = listaProductos;
        this.listas = lista;
    }

    public String getNombreDespensa() {
        return nombreDespensa;
    }

    public void setNombreDespensa(String nombreDespensa) {
        this.nombreDespensa = nombreDespensa;
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

    public void addProducto(int id, int cantidad) {
        System.out.println("pos: " + id + " cantidad: " + cantidad);
        if(listaProductos.containsKey(id)){
            ProductoComprado p = listaProductos.get(id);
            p.setCantidad(p.getCantidad() + cantidad);
            listaProductos.put(id, p);
            System.out.println("Entro porque existe");
        } else {
            Producto seleccionado = ListaDeProductos.getListaDeProductos().getListaProductos().get(id);
            ProductoComprado add = new ProductoComprado(seleccionado);
            add.setCantidad(cantidad);
            listaProductos.put(id, add);
            System.out.println("Entro porque no existe");
        }
    }

    public ArrayList<Lista> getListas() {
        return listas;
    }

    public void setListas(ArrayList<Lista> listas) {
        this.listas = listas;
    }
}