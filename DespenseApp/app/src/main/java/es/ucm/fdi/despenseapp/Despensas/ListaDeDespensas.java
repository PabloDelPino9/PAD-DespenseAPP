package es.ucm.fdi.despenseapp.Despensas;

import java.util.ArrayList;

import es.ucm.fdi.despenseapp.Utils;

public class ListaDeDespensas {
    private ArrayList<Despensa> listaDespensas;
    private static ListaDeDespensas miLista;
    private ArrayList<Despensa> valoresIniciales = new ArrayList<Despensa>();
    public static ListaDeDespensas getListaDeDespensas(){
        if(miLista == null) {
            miLista = new ListaDeDespensas();
        }
        return miLista;
    }

    private ListaDeDespensas() {
        this.listaDespensas = Utils.despensasIniciales();
    }

    public ArrayList<Despensa> getListaDespensas() {
        return listaDespensas;
    }

    public void setListaDespensas(ArrayList<Despensa> listaProductos) {
        this.listaDespensas = listaProductos;
    }

    public void addProduct(Despensa nuevo){
        listaDespensas.add(nuevo);
    }
}
