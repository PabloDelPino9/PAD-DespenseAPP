package es.ucm.fdi.despenseapp.Listas;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.ucm.fdi.despenseapp.Despensas.Despensa;
import es.ucm.fdi.despenseapp.Despensas.ListaDeDespensas;
import es.ucm.fdi.despenseapp.Productos.ProductoComprado;
import es.ucm.fdi.despenseapp.R;

public class L_AdaptadorListaProductos extends BaseAdapter {
    private Map<Integer, ProductoComprado> listEntidad;
    private Context context;
    private LayoutInflater inflater;
    public int idDespensa;
    public int idLista;
    public Map<Integer, Boolean> listaMarcados = new HashMap<Integer, Boolean>();

    public L_AdaptadorListaProductos(Context context, Map<Integer, ProductoComprado> listEntidad, int item, int lista) {
        this.context = context;
        this.listEntidad = listEntidad;
        this.idDespensa = item;
        this.idLista = lista;
    }

    @Override
    public int getCount() {
        return listEntidad.size();
    }

    @Override
    public Object getItem(int position) {
        ArrayList<Integer> keyList = new ArrayList<Integer>(listEntidad.keySet());
        int key = keyList.get(position);
        return listEntidad.get(key);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // OBTENER EL OBJETO POR CADA ITEM A MOSTRAR
        final ProductoComprado entidad = (ProductoComprado) getItem(position);
        System.out.println(position);
        // CREAMOS E INICIALIZAMOS LOS ELEMENTOS DEL ITEM DE LA LISTA
        convertView = LayoutInflater.from(context).inflate(R.layout.l_productos_lista, null);
        TextView tvNombre = (TextView) convertView.findViewById(R.id.tvTitulo);
        TextView tvCantidad = (TextView) convertView.findViewById(R.id.textView7);
        ImageView imgProd = (ImageView) convertView.findViewById(R.id.imgFoto);
        CheckBox check = (CheckBox) convertView.findViewById(R.id.checkBox);
        // LLENAMOS LOS ELEMENTOS CON LOS VALORES DE CADA ITEM
        tvNombre.setText(entidad.getNombreProducto());
        String num = "" + entidad.getCantidad();
        tvCantidad.setText(num);
        imgProd.setImageResource(entidad.getImgProducto());
        //Handle buttons and add onClickListeners
        ImageButton up= (ImageButton)convertView.findViewById(R.id.imageButton);
        ImageButton down= (ImageButton)convertView.findViewById(R.id.imageButton4);
        ImageButton delete= (ImageButton) convertView.findViewById(R.id.imageButton5);

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    listaMarcados.put(entidad.getIdProd(),true);
                } else {
                    listaMarcados.remove(entidad.getIdProd());
                }
            }
        });

        up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ArrayList<Despensa> lista = ListaDeDespensas.getListaDeDespensas().getListaDespensas();
                if (lista.get(idDespensa).getListas().get(idLista).getListaProductos().containsKey(entidad.getIdProd())) {
                    lista.get(idDespensa).getListas().get(idLista).upCantidad(entidad.getIdProd());
                    notifyDataSetChanged();
                }
            }
        });
        down.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ArrayList<Despensa> lista = ListaDeDespensas.getListaDeDespensas().getListaDespensas();
                if (lista.get(idDespensa).getListas().get(idLista).getListaProductos().containsKey(entidad.getIdProd())) {
                    if (listEntidad.get(entidad.getIdProd()).getCantidad() != 0) {
                        listEntidad.get(entidad.getIdProd()).setCantidad(listEntidad.get(entidad.getIdProd()).getCantidad() - 1);
                    }
                    notifyDataSetChanged();
                }
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
                        ArrayList<Despensa> lista = ListaDeDespensas.getListaDeDespensas().getListaDespensas();
                        if (lista.get(idDespensa).getListas().get(idLista).getListaProductos().containsKey(entidad.getIdProd())) {
                            if(lista.get(idDespensa).getListas().get(idLista).getListaProductos().get(entidad.getIdProd()).getCantidad() > 0){
                                Toast.makeText(context, "No se puede eliminar un producto no agotado", Toast.LENGTH_LONG).show();
                            } else {
                                lista.get(idDespensa).getListas().get(idLista).eliminarProducto(entidad.getIdProd());
                                ListaDeDespensas.getListaDeDespensas().setListaDespensas(lista);
                                notifyDataSetChanged();
                                System.out.println("DELETE");
                            }
                        }
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
}
