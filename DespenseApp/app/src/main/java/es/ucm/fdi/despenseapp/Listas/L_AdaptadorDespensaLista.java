package es.ucm.fdi.despenseapp.Listas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import es.ucm.fdi.despenseapp.Despensas.Despensa;
import es.ucm.fdi.despenseapp.R;

/**
 * Created by Elvis on 27 abr 2017.
 */

public class L_AdaptadorDespensaLista extends BaseAdapter {
    private ArrayList<Despensa> listEntidad;
    private Context context;
    private LayoutInflater inflater;

    public L_AdaptadorDespensaLista(Context context, ArrayList<Despensa> listEntidad) {
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
        convertView = LayoutInflater.from(context).inflate(R.layout.l_lists_element_despensas, null);
        TextView tvNombre = (TextView) convertView.findViewById(R.id.textView3);
        TextView tvCantidad = (TextView) convertView.findViewById(R.id.textView4);

        // LLENAMOS LOS ELEMENTOS CON LOS VALORES DE CADA ITEM
        tvNombre.setText(entidad.getNombreDespensa());
        String num = "Nº de listas: " + entidad.getListas().size();
        tvCantidad.setText(num);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ListasDespensaActivity.class);
                i.putExtra("item", position);
                context.startActivity(i);
                System.out.println("A !!");
            }
        });

        return convertView;
    }
}
