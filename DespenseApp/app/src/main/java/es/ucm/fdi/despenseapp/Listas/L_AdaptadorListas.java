package es.ucm.fdi.despenseapp.Listas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import es.ucm.fdi.despenseapp.Despensas.Despensa;
import es.ucm.fdi.despenseapp.Despensas.DespensaActivity;
import es.ucm.fdi.despenseapp.Productos.ListaDeProductos;
import es.ucm.fdi.despenseapp.Productos.Producto;
import es.ucm.fdi.despenseapp.R;

public class L_AdaptadorListas extends BaseAdapter {

    private ArrayList<Lista> listEntidad;
    private Context context;
    private LayoutInflater inflater;
    private int idDespensa;
    public L_AdaptadorListas(Context context, ArrayList<Lista> listEntidad, int idDespensa) {
        this.context = context;
        this.listEntidad = listEntidad;
        this.idDespensa = idDespensa;
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
        final Lista entidad = (Lista) getItem(position);

        // CREAMOS E INICIALIZAMOS LOS ELEMENTOS DEL ITEM DE LA LISTA
        convertView = LayoutInflater.from(context).inflate(R.layout.lists_element, null);
        TextView tvNombre = (TextView) convertView.findViewById(R.id.textView3);
        TextView tvCantidad = (TextView) convertView.findViewById(R.id.textView4);
        ImageButton delete = (ImageButton) convertView.findViewById(R.id.imageButton2);

        // LLENAMOS LOS ELEMENTOS CON LOS VALORES DE CADA ITEM
        tvNombre.setText(entidad.getNombreLista());
        String num = "Nº de productos: " + entidad.getListaProductos().size();
        tvCantidad.setText(num);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ListaActivity.class);
                i.putExtra("idDespensa", idDespensa);
                i.putExtra("idLista", position);

                context.startActivity(i);
                System.out.println("A !!");
            }
        });
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder confirmacionDelete;
                confirmacionDelete = new AlertDialog.Builder(context);
                confirmacionDelete.setTitle("Mensaje de confirmación");
                confirmacionDelete.setMessage("¿Esta seguro que desea eliminar la lista?");
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
        return convertView;
    }
}
