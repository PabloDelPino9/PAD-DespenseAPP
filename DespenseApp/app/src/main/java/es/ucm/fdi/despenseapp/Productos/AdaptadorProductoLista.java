package es.ucm.fdi.despenseapp.Productos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.despenseapp.Despensas.Despensa;
import es.ucm.fdi.despenseapp.Despensas.ListaDeDespensas;
import es.ucm.fdi.despenseapp.R;

/**
 * Created by Elvis on 27 abr 2017.
 */

public class AdaptadorProductoLista extends BaseAdapter implements Filterable {
    private Map<Integer, Producto> listEntidad;
    private Map<Integer, Producto> listEntidadFiltered;
    private Context context;
    private LayoutInflater inflater;
    private ItemFilter mFilter = new ItemFilter();

    public AdaptadorProductoLista(Context context, Map<Integer, Producto> listEntidad) {
        this.context = context;
        this.listEntidad = listEntidad;
        this.listEntidadFiltered = listEntidad;
    }

    @Override
    public int getCount() {
        return listEntidadFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        ArrayList<Integer> keyList = new ArrayList<Integer>(listEntidadFiltered.keySet());
        int key = keyList.get(position);
        return listEntidadFiltered.get(key);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // OBTENER EL OBJETO POR CADA ITEM A MOSTRAR
        final Producto entidad = (Producto) getItem(position);
        // CREAMOS E INICIALIZAMOS LOS ELEMENTOS DEL ITEM DE LA LISTA
        convertView = LayoutInflater.from(context).inflate(R.layout.products_element, null);
        ImageView imgFoto = (ImageView) convertView.findViewById(R.id.imgFoto);
        TextView tvTitulo = (TextView) convertView.findViewById(R.id.tvTitulo);
        TextView tvContenido = (TextView) convertView.findViewById(R.id.tvPrecio);
        ImageButton delete= (ImageButton) convertView.findViewById(R.id.imageButton3);

        // LLENAMOS LOS ELEMENTOS CON LOS VALORES DE CADA ITEM
        imgFoto.setImageResource(entidad.getImgProducto());
        tvTitulo.setText(entidad.getNombreProducto());
        tvContenido.setText(new Double(entidad.getPrecioAprox()).toString());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProductosLista.class);
                i.putExtra("item", entidad);
                context.startActivity(i);
                System.out.println("Click !!");
            }
        });
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder confirmacionDelete;
                confirmacionDelete = new AlertDialog.Builder(context);
                confirmacionDelete.setTitle("Mensaje de confirmación");
                confirmacionDelete.setMessage("¿Esta seguro que desea eliminar el producto?");
                confirmacionDelete.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<Integer, Producto> lista = ListaDeProductos.getListaDeProductos().getListaProductos();
                        lista.remove(entidad.getIdProd());
                        ListaDeProductos.getListaDeProductos().setListaProductos(lista);
                        notifyDataSetChanged();
                    }
                });
                confirmacionDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                confirmacionDelete.show();
            }
        });
        return convertView;
    }

    private void Alert(){
        AlertDialog.Builder confirmacionDelete;
        confirmacionDelete = new AlertDialog.Builder(context);
        confirmacionDelete.setTitle("Mensaje de confirmación");
        confirmacionDelete.setMessage("¿Esta seguro que desea eliminar el producto?");
        confirmacionDelete.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        confirmacionDelete.setNegativeButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

    private void eliminarPorducto(){

    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final Map<Integer, Producto> list = listEntidad;

            int count = list.size();
            final ArrayList<Producto> nlist = new ArrayList<Producto>(count);

            Producto filterableString ;
            for (Map.Entry<Integer, Producto> entry : list.entrySet()) {
                filterableString = list.get(entry.getKey());
                if (filterableString.getNombreProducto().toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }            }
            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Map<Integer, Producto> aux = new HashMap<Integer, Producto>();
            ArrayList<Producto> aux2 = (ArrayList<Producto>) results.values;
            for(int x = 0; x < results.count; x++){
                aux.put(aux2.get(x).getIdProd(), aux2.get(x));
            }
            listEntidadFiltered = aux;
            notifyDataSetChanged();
        }

    }
}
