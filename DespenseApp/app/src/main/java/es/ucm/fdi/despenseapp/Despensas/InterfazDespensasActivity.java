package es.ucm.fdi.despenseapp.Despensas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Map;

import es.ucm.fdi.despenseapp.Listas.InterfazListasActivity;
import es.ucm.fdi.despenseapp.Listas.L_AdaptadorDespensaLista;
import es.ucm.fdi.despenseapp.MainActivity;
import es.ucm.fdi.despenseapp.Productos.InterfazProductosActivity;
import es.ucm.fdi.despenseapp.Productos.ListaDeProductos;
import es.ucm.fdi.despenseapp.Productos.Producto;
import es.ucm.fdi.despenseapp.R;

public class InterfazDespensasActivity extends AppCompatActivity {
    ListView listView;
    SearchView search;
    private AdaptadorDespensaLista adaptador;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfaz_despensas);
        listView = (ListView) findViewById(R.id.products_list);
        search = (SearchView) findViewById(R.id.search);
        llenarItems();
        ImageButton delete= (ImageButton) findViewById(R.id.imageButton2);

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

    }
    private void llenarItems(){
        adaptador = new AdaptadorDespensaLista(this, ListaDeDespensas.getListaDeDespensas().getListaDespensas());
        listView.setAdapter(adaptador);
    }

    public void addProductButton(View v){
        Intent i = new Intent(this, AddDespensaActivity.class );
        startActivity(i);
    }
}
