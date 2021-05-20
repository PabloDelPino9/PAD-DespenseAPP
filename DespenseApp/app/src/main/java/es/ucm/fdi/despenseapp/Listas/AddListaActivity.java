package es.ucm.fdi.despenseapp.Listas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import es.ucm.fdi.despenseapp.Despensas.AddDespensaActivity;
import es.ucm.fdi.despenseapp.Despensas.Despensa;
import es.ucm.fdi.despenseapp.Despensas.InterfazDespensasActivity;
import es.ucm.fdi.despenseapp.Despensas.ListaDeDespensas;
import es.ucm.fdi.despenseapp.MainActivity;
import es.ucm.fdi.despenseapp.Productos.InterfazProductosActivity;
import es.ucm.fdi.despenseapp.Productos.ProductoComprado;
import es.ucm.fdi.despenseapp.R;

public class AddListaActivity extends AppCompatActivity {

    EditText nombre;
    Button boton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_lista);
        nombre = (EditText) findViewById(R.id.editTextTextPersonName);
        boton = (Button) findViewById(R.id.button5);
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
                    case R.id.navigation_lists:
                        startActivity(new Intent(getApplicationContext(), InterfazListasActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navigation_products:
                        startActivity(new Intent(getApplicationContext(), InterfazProductosActivity.class));
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
                confirmacion = new AlertDialog.Builder(AddListaActivity.this);
                confirmacion.setTitle("Mensaje de confirmación");
                confirmacion.setMessage("¿Esta seguro que desea crear la lista?");
                confirmacion.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addProduct();
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
    }
    public void addProduct(){
        Lista nuevo = new Lista(nombre.getText().toString(), new HashMap<Integer, ProductoComprado>());
        if(nuevo.getNombreLista().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Introduzca Nombre",Toast.LENGTH_SHORT).show();
        } else {
            Serializable extras = getIntent().getSerializableExtra("item");
            int posicion = (int) extras;
            ListaDeDespensas.getListaDeDespensas().getListaDespensas().get(posicion).getListas().add(nuevo);
            Intent i = new Intent(this, ListasDespensaActivity.class );
            i.putExtra("item", posicion);
            startActivity(i);
        }
    }
}
