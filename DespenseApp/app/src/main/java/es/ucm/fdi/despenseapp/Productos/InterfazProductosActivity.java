package es.ucm.fdi.despenseapp.Productos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.ucm.fdi.despenseapp.Despensas.InterfazDespensasActivity;
import es.ucm.fdi.despenseapp.Listas.InterfazListasActivity;
import es.ucm.fdi.despenseapp.MainActivity;
import es.ucm.fdi.despenseapp.R;

public class InterfazProductosActivity extends AppCompatActivity {
    ListView listView;
    SearchView search;
    private AdaptadorProductoLista adaptador;
    private ArrayList<Producto> arrayEntidad = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfaz_productos);
        listView = (ListView) findViewById(R.id.products_list);
        search = (SearchView) findViewById(R.id.search);
        llenarItems();
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
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_products:
                        return true;
                    case R.id.navigation_lists:
                        startActivity(new Intent(getApplicationContext(), InterfazListasActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adaptador.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adaptador.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void llenarItems(){
        Map<Integer, Producto> listaProductos = ListaDeProductos.getListaDeProductos().getListaProductos();
        if(listaProductos.containsKey(0)){
            listaProductos.remove(0);
        }
        if (listaProductos.isEmpty()){
            adaptador = new AdaptadorProductoLista(this, new HashMap<Integer, Producto>());

        } else {
            System.out.println("Cantidad de productos: " + listaProductos.size());
            adaptador = new AdaptadorProductoLista(this, listaProductos);
        }
        listView.setAdapter(adaptador);
    }

    public void addProductButton(View v){
        Intent i = new Intent(this, AddProductActivity.class );
        startActivity(i);
    }
}
