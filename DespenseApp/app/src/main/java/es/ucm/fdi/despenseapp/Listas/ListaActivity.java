package es.ucm.fdi.despenseapp.Listas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import es.ucm.fdi.despenseapp.Despensas.AdaptadorDespensaListaProductos;
import es.ucm.fdi.despenseapp.Despensas.Despensa;
import es.ucm.fdi.despenseapp.Despensas.DespensaActivity;
import es.ucm.fdi.despenseapp.Despensas.InterfazDespensasActivity;
import es.ucm.fdi.despenseapp.Despensas.ListaDeDespensas;
import es.ucm.fdi.despenseapp.MainActivity;
import es.ucm.fdi.despenseapp.Productos.InterfazProductosActivity;
import es.ucm.fdi.despenseapp.Productos.ListaDeProductos;
import es.ucm.fdi.despenseapp.Productos.Producto;
import es.ucm.fdi.despenseapp.R;

public class ListaActivity extends AppCompatActivity {
    ListView listView;
    TextView nombreLista;
    Button botonCompra;
    private L_AdaptadorListaProductos adaptador;
    private Lista lista;
    Spinner sp;
    private int cont = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_lista);
        listView = (ListView) findViewById(R.id.products_list);
        nombreLista = (TextView) findViewById(R.id.textView);
        botonCompra = (Button) findViewById(R.id.button6);
        sp = (Spinner)findViewById(R.id.spinner3);
        getDetalleLista();
        Button irDespensa = (Button) findViewById(R.id.button7);

        nombreLista.setText(lista.getNombreLista());
        adaptador = new L_AdaptadorListaProductos(this, lista.getListaProductos(), (int) getIntent().getSerializableExtra("idDespensa"), (int) getIntent().getSerializableExtra("idLista"));
        listView.setAdapter(adaptador);
        Map<Integer, Producto> list = ListaDeProductos.getListaDeProductos().getListaProductos();
        System.out.println("Contador: " + cont);

        if(!list.containsKey(-1)){
            Producto aux = new Producto(0, "Elija un producto", 0.0, 0);
            list.put(0, aux);
            cont++;
        }
        Collection values = list.values();
        List<Producto> list1 = new ArrayList<Producto>(values);
        ArrayAdapter adapter= new ArrayAdapter<Producto>(this,android.R.layout.simple_spinner_item, list1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0)
                {
                    addProducto(position);
                    System.out.println("Llego" + position);
                    finish();
                    startActivity(getIntent());
                    //list.remove(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                list.remove(0);
            }
        });

        BottomNavigationView navigation = findViewById(R.id.bottomNavigationView);
        navigation.setSelectedItemId(R.id.navigation_home);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_despensa:
                        startActivity(new Intent(getApplicationContext(), InterfazDespensasActivity.class));
                        overridePendingTransition(0,0);                    case R.id.navigation_products:
                        startActivity(new Intent(getApplicationContext(), InterfazProductosActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_lists:
                        startActivity(new Intent(getApplicationContext(), InterfazListasActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        botonCompra.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder confirmacion;
                confirmacion = new AlertDialog.Builder(ListaActivity.this);
                confirmacion.setTitle("Mensaje de confirmación");
                confirmacion.setMessage("¿Volcar los productos marcados a la despensa?");
                confirmacion.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        comprar();
                    }
                });
                confirmacion.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                confirmacion.show();
            }
        });

        irDespensa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                irADespensa();
            }
        });
    }

    public void getDetalleLista() {
        //Aquí recogemos y tratamos los parámetros
        Serializable extras = getIntent().getSerializableExtra("idLista");
        int idLista = (int) extras;
        extras = getIntent().getSerializableExtra("idDespensa");
        int idDespensa = (int) extras;
        this.lista = ListaDeDespensas.getListaDeDespensas().getListaDespensas().get(idDespensa).getListas().get(idLista);
    }

    public void addProducto(int pos) {
        Serializable extras = getIntent().getSerializableExtra("idLista");
        int idLista = (int) extras;
        extras = getIntent().getSerializableExtra("idDespensa");
        int idDespensa = (int) extras;
        this.lista = ListaDeDespensas.getListaDeDespensas().getListaDespensas().get(idDespensa).getListas().get(idLista);
        lista.addProducto(pos);
        ListaDeDespensas ld = ListaDeDespensas.getListaDeDespensas();
        ld.getListaDespensas().get(idDespensa).getListas().set(idLista, lista);
        ListaDeDespensas.getListaDeDespensas().setListaDespensas(ld.getListaDespensas());
    }

    public void comprar() {
        Serializable extras = getIntent().getSerializableExtra("idLista");
        int idLista = (int) extras;
        extras = getIntent().getSerializableExtra("idDespensa");
        int idDespensa = (int) extras;
        ArrayList<Integer> keyList = new ArrayList<Integer>(adaptador.listaMarcados.keySet());
        for(int x = 0; x < keyList.size(); x++){
            if (adaptador.listaMarcados.get(keyList.get(x))){
                if(lista.getListaProductos().get(keyList.get(x)).getCantidad() > 0){
                    ListaDeDespensas.getListaDeDespensas().getListaDespensas().get(idDespensa).addProducto(keyList.get(x),lista.getListaProductos().get(keyList.get(x)).getCantidad());
                    System.out.println("Cantidad comprada: " + lista.getListaProductos().get(keyList.get(x)).getCantidad());
                    lista.eliminarProducto(keyList.get(x));
                }
            }
        }
        adaptador.notifyDataSetChanged();
    }

    public void irADespensa(){
        int idDespensa = (int) getIntent().getSerializableExtra("idDespensa");
        Intent i = new Intent(this, DespensaActivity.class );
        i.putExtra("item", idDespensa);
        startActivity(i);
    }
}
