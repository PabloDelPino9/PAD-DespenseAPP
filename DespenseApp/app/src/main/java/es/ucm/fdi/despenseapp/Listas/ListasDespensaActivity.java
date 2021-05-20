package es.ucm.fdi.despenseapp.Listas;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

import es.ucm.fdi.despenseapp.Despensas.AdaptadorDespensaLista;
import es.ucm.fdi.despenseapp.Despensas.AddDespensaActivity;
import es.ucm.fdi.despenseapp.Despensas.Despensa;
import es.ucm.fdi.despenseapp.Despensas.DespensaActivity;
import es.ucm.fdi.despenseapp.Despensas.InterfazDespensasActivity;
import es.ucm.fdi.despenseapp.Despensas.ListaDeDespensas;
import es.ucm.fdi.despenseapp.MainActivity;
import es.ucm.fdi.despenseapp.Productos.InterfazProductosActivity;
import es.ucm.fdi.despenseapp.R;

public class ListasDespensaActivity extends AppCompatActivity {
    ListView listView;
    TextView tv;
    private L_AdaptadorListas adaptador;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_listas_en_despensa);
        listView = (ListView) findViewById(R.id.products_list);
        tv = (TextView) findViewById(R.id.textView);

        llenarItems();

        BottomNavigationView navigation = findViewById(R.id.bottomNavigationView);
        Button irDespensa = (Button) findViewById(R.id.button8);
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
                        startActivity(new Intent(getApplicationContext(), InterfazListasActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        irDespensa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                irADespensa();
            }
        });
    }

    private void llenarItems(){
        Serializable extras = getIntent().getSerializableExtra("item");
        int posicion = (int) extras;
        adaptador = new L_AdaptadorListas(this, ListaDeDespensas.getListaDeDespensas().getListaDespensas().get(posicion).getListas(), posicion);
        listView.setAdapter(adaptador);
        tv.setText(ListaDeDespensas.getListaDeDespensas().getListaDespensas().get(posicion).getNombreDespensa());
    }

    public void addProductButton(View v){
        Intent i = new Intent(this, AddListaActivity.class );
        Serializable extras = getIntent().getSerializableExtra("item");
        int posicion = (int) extras;
        i.putExtra("item", posicion);
        startActivity(i);
    }

    public void onResume() {
        super.onResume();
        System.out.println(ListaDeDespensas.getListaDeDespensas().getListaDespensas().get(1).getListaProductos().size());
        llenarItems();
    }

    public void irADespensa(){
        int idDespensa = (int) getIntent().getSerializableExtra("item");
        Intent i = new Intent(this, DespensaActivity.class );
        i.putExtra("item", idDespensa);
        startActivity(i);
    }
}
