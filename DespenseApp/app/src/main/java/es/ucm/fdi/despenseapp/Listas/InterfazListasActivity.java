package es.ucm.fdi.despenseapp.Listas;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.ucm.fdi.despenseapp.Despensas.AdaptadorDespensaLista;
import es.ucm.fdi.despenseapp.Despensas.AddDespensaActivity;
import es.ucm.fdi.despenseapp.Despensas.Despensa;
import es.ucm.fdi.despenseapp.Despensas.DespensaActivity;
import es.ucm.fdi.despenseapp.Despensas.InterfazDespensasActivity;
import es.ucm.fdi.despenseapp.Despensas.ListaDeDespensas;
import es.ucm.fdi.despenseapp.MainActivity;
import es.ucm.fdi.despenseapp.Productos.InterfazProductosActivity;
import es.ucm.fdi.despenseapp.R;

public class InterfazListasActivity extends AppCompatActivity {
    ListView listView;
    private L_AdaptadorDespensaLista adaptador;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_despensas);
        listView = (ListView) findViewById(R.id.products_list);
        llenarItems();
        System.out.println("Estoy en InterfazListasActivity");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?>adapter, View v, int position, long id) {
                Despensa item = (Despensa) adapter.getItemAtPosition(position);
                Intent i = new Intent(InterfazListasActivity.this, ListasDespensaActivity.class );
                System.out.println("POSICION: " + position);
                i.putExtra("Despensa", position);
                startActivity(i);
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
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_products:
                        startActivity(new Intent(getApplicationContext(), InterfazProductosActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_lists:
                        return true;
                }
                return false;
            }
        });
    }

    private void llenarItems(){
        adaptador = new L_AdaptadorDespensaLista(this, ListaDeDespensas.getListaDeDespensas().getListaDespensas());
        listView.setAdapter(adaptador);
    }

    public void addProductButton(View v){
        Intent i = new Intent(this, AddDespensaActivity.class );
        startActivity(i);
    }

}
