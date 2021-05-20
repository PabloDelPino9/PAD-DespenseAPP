package es.ucm.fdi.despenseapp.Despensas;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import es.ucm.fdi.despenseapp.Listas.InterfazListasActivity;
import es.ucm.fdi.despenseapp.Listas.Lista;
import es.ucm.fdi.despenseapp.Listas.ListasDespensaActivity;
import es.ucm.fdi.despenseapp.MainActivity;
import es.ucm.fdi.despenseapp.Productos.InterfazProductosActivity;
import es.ucm.fdi.despenseapp.Productos.ListaDeProductos;
import es.ucm.fdi.despenseapp.Productos.Producto;
import es.ucm.fdi.despenseapp.Productos.ProductoComprado;
import es.ucm.fdi.despenseapp.R;

public class DespensaActivity extends AppCompatActivity {
    ListView listView;
    TextView nombreDespensa;
    private AdaptadorDespensaListaProductos adaptador;
    private Despensa despensa;
    Spinner sp;
    private int cont = 0;
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_despensa);
        listView = (ListView) findViewById(R.id.products_list);
        nombreDespensa = (TextView) findViewById(R.id.textView);
        sp = (Spinner)findViewById(R.id.spinner3);
        getDetalleDespensa();
        Button boton = (Button) findViewById(R.id.button6);
        Button botonListas = (Button) findViewById(R.id.button2);

        nombreDespensa.setText(despensa.getNombreDespensa());
        adaptador = new AdaptadorDespensaListaProductos(this, despensa.getListaProductos(), (int) getIntent().getSerializableExtra("item"));
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
                List<Integer> l = new ArrayList<Integer>(list.keySet());
                if(position != 0)
                {
                    addProducto(l.get(position), 0);
                    System.out.println("Llego" + l.get(position));
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
                        return true;
                    case R.id.navigation_products:
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

        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder confirmacion;
                confirmacion = new AlertDialog.Builder(DespensaActivity.this);
                confirmacion.setTitle("Mensaje de confirmación");
                confirmacion.setMessage("¿Esta seguro que desea crear la lista?");
                confirmacion.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        generarLista();;
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

        botonListas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                irAListas();
            }
        });
    }

    public void getDetalleDespensa() {
        //Aquí recogemos y tratamos los parámetros
        Serializable extras = getIntent().getSerializableExtra("item");
        int posicion = (int) extras;
        this.despensa = ListaDeDespensas.getListaDeDespensas().getListaDespensas().get(posicion);
    }

    public void addProducto(int pos, int cantidad) {
        Serializable extras = getIntent().getSerializableExtra("item");
        int posicion = (int) extras;
        this.despensa = ListaDeDespensas.getListaDeDespensas().getListaDespensas().get(posicion);
        ListaDeDespensas ld = ListaDeDespensas.getListaDeDespensas();
        ld.getListaDespensas().get(posicion).addProducto(pos, cantidad);
        ListaDeDespensas.getListaDeDespensas().setListaDespensas(ld.getListaDespensas());
        System.out.println("LD: " + ld.getListaDespensas().get(posicion).getListaProductos().get(pos));
        System.out.println("Lista: " + ListaDeDespensas.getListaDeDespensas().getListaDespensas().get(posicion).getListaProductos().get(pos).getNombreProducto());
        System.out.println("ENTROO");
    }

    public void generarLista() {
        Serializable extras = getIntent().getSerializableExtra("item");
        int posicion = (int) extras;
        Map<Integer, ProductoComprado>  lista = new HashMap<Integer, ProductoComprado>();
        for (Map.Entry<Integer, ProductoComprado> entry : despensa.getListaProductos().entrySet()) {
            if(entry.getValue().getCantidad() == 0) {
                lista.put(entry.getKey(), entry.getValue());
            }
        }
        String timeStamp = new SimpleDateFormat("dd/MM/yy-HH.mm").format(new Date());
        Lista listaNueva = new Lista( timeStamp, lista);
        ListaDeDespensas.getListaDeDespensas().getListaDespensas().get(posicion).getListas().add(listaNueva);
        Toast.makeText(getApplicationContext(),"Lista creada: " + timeStamp,Toast.LENGTH_SHORT).show();
    }

    public void irAListas(){
        int idDespensa = (int) getIntent().getSerializableExtra("item");
        Intent i = new Intent(this, ListasDespensaActivity.class );
        i.putExtra("item", idDespensa);
        startActivity(i);
    }
}
