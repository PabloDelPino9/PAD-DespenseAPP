package es.ucm.fdi.despenseapp.Despensas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.Map;

import es.ucm.fdi.despenseapp.Productos.ListaDeProductos;
import es.ucm.fdi.despenseapp.Productos.Producto;
import es.ucm.fdi.despenseapp.R;

/**
 * Created by Elvis on 27 abr 2017.
 */

public class AdaptadorDespensaLista extends BaseAdapter {
    private ArrayList<Despensa> listEntidad;
    private Context context;
    private LayoutInflater inflater;

    public AdaptadorDespensaLista(Context context, ArrayList<Despensa> listEntidad) {
        this.context = context;
        this.listEntidad = listEntidad;
    }

    @Override
    public int getCount() {
        return listEntidad.size();
    }

    @Override
    public Object getItem(int position) {
        return listEntidad.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // OBTENER EL OBJETO POR CADA ITEM A MOSTRAR
        final Despensa entidad = (Despensa) getItem(position);

        // CREAMOS E INICIALIZAMOS LOS ELEMENTOS DEL ITEM DE LA LISTA
        convertView = LayoutInflater.from(context).inflate(R.layout.lists_element, null);
        TextView tvNombre = (TextView) convertView.findViewById(R.id.textView3);
        TextView tvCantidad = (TextView) convertView.findViewById(R.id.textView4);
        ImageButton delete = (ImageButton) convertView.findViewById(R.id.imageButton2);
        // LLENAMOS LOS ELEMENTOS CON LOS VALORES DE CADA ITEM
        tvNombre.setText(entidad.getNombreDespensa());
        String num = "Nº de productos: " + entidad.getListaProductos().size();
        tvCantidad.setText(num);

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
                        listEntidad.remove(position);
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
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DespensaActivity.class);
                i.putExtra("item", position);
                context.startActivity(i);
                System.out.println("A !!");
            }
        });

        return convertView;
    }
}
