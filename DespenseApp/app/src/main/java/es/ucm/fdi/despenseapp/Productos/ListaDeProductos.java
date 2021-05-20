package es.ucm.fdi.despenseapp.Productos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.ucm.fdi.despenseapp.Utils;

public class ListaDeProductos {
    private Map<Integer, Producto> listaProductos = new HashMap<Integer, Producto>();
    private static ListaDeProductos miLista;
    private static int contadorId = 1;
    private ArrayList<Producto> valoresIniciales = new ArrayList<Producto>();
    public static ListaDeProductos getListaDeProductos(){
        if(miLista == null) {
            miLista = new ListaDeProductos();
        }
        return miLista;
    }

    private ListaDeProductos() {
        listaProductos = Utils.productosIniciales();
        contadorId = 16;
        System.out.println("CARGADOS LOS DATOS INICIALES");
    }

    public Map<Integer, Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(Map<Integer, Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void addProduct(Producto nuevo, int id){
        if (!listaProductos.containsKey(id)){
            listaProductos.put(id, nuevo);
            contadorId++;
        } else {
            System.out.println("NO ENTRAAAAA");
        }
    }

    public int getNextId() {
        return contadorId;
    }

}
