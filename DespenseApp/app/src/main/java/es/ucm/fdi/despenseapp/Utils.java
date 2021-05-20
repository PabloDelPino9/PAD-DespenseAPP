package es.ucm.fdi.despenseapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.ucm.fdi.despenseapp.Despensas.Despensa;
import es.ucm.fdi.despenseapp.Listas.Lista;
import es.ucm.fdi.despenseapp.Productos.Producto;
import es.ucm.fdi.despenseapp.Productos.ProductoComprado;
import es.ucm.fdi.despenseapp.R;

public class Utils {


    public static  Map<Integer, Producto> productosIniciales () {
        Map<Integer, Producto> listaProductos = new HashMap<Integer, Producto>();

        listaProductos.put(1,new Producto(R.drawable.patatas, "Patatas 5kg", 1.2,1));
        listaProductos.put(2,new Producto(R.drawable.huevos, "Huevos 12uds", 6.2,2));
        listaProductos.put(3,new Producto(R.drawable.sal, "Sal 1 kg", 2.0,3));
        listaProductos.put(4, new Producto(R.drawable.tomates, "Tomates 3kg", 1.2,4));
        listaProductos.put(5, new Producto(R.drawable.atun, "Atún en conserva 500g", 6.8, 5));
        listaProductos.put(6, new Producto(R.drawable.naranjas, "Naranjas 3kg", 1.2, 6));
        listaProductos.put(7, new Producto(R.drawable.manzanas, "Manzanas 3kg", 1.2, 7));
        listaProductos.put(8, new Producto(R.drawable.peras, "Peras 3kg", 1.2, 8));
        listaProductos.put(9, new Producto(R.drawable.aceitunas, "Aceitunas 1kg", 1.2, 9));
        listaProductos.put(10, new Producto(R.drawable.lechuga, "Lechuga", 1.2, 10));
        listaProductos.put(11, new Producto(R.drawable.macarrones, "Macarrones 1kg", 1.2, 11));
        listaProductos.put(12, new Producto(R.drawable.azucar, "Azúcar 1kg", 1.2, 12));
        listaProductos.put(13, new Producto(R.drawable.galletas, "Galletas", 1.2, 13));
        listaProductos.put(14, new Producto(R.drawable.mermelada, "Mermelada", 1.2, 14));
        listaProductos.put(15, new Producto(R.drawable.leche, "Leche 1L", 1.2, 15));

        return listaProductos;
    }

    public static ArrayList<Despensa> despensasIniciales(){
        ArrayList<Despensa> valoresIniciales = new ArrayList<Despensa>();
        Despensa despensa1 = new Despensa("Casa", new HashMap<Integer, ProductoComprado>(), new ArrayList<Lista>());

        HashMap<Integer, ProductoComprado> a = new HashMap<Integer, ProductoComprado>();
        HashMap<Integer, ProductoComprado> d = new HashMap<Integer, ProductoComprado>();

        ProductoComprado a1 = new ProductoComprado(R.drawable.lechuga, "Lechuga", 1.2, 0,10);
        ProductoComprado a2 = new ProductoComprado(R.drawable.macarrones, "Macarrones 1kg", 1.2,1, 11);
        ProductoComprado a3 = new ProductoComprado(R.drawable.azucar, "Azúcar 1kg", 1.2,5, 12);
        ProductoComprado a4 = new ProductoComprado(R.drawable.galletas, "Galletas", 1.2,3, 13);

        a.put(a1.getIdProd(), a1);
        a.put(a2.getIdProd(), a2);
        a.put(a3.getIdProd(), a3);

        d.put(a4.getIdProd(), a4);

        ArrayList<Lista> c = new ArrayList<Lista>();
        Lista l = new Lista("Lista 1", a);
        Lista l2 = new Lista("Lista 2", new HashMap<Integer, ProductoComprado>());
        c.add(l);
        c.add(l2);
        Despensa despensa2 = new Despensa("Viaje amigos", d, c);

        valoresIniciales.add(despensa1);
        valoresIniciales.add(despensa2);
        return valoresIniciales;
    }



}
