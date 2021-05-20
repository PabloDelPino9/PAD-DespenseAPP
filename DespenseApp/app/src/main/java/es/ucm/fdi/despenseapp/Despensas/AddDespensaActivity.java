package es.ucm.fdi.despenseapp.Despensas;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import es.ucm.fdi.despenseapp.Listas.InterfazListasActivity;
import es.ucm.fdi.despenseapp.Listas.Lista;
import es.ucm.fdi.despenseapp.MainActivity;
import es.ucm.fdi.despenseapp.Productos.AddProductActivity;
import es.ucm.fdi.despenseapp.Productos.InterfazProductosActivity;
import es.ucm.fdi.despenseapp.Productos.ProductoComprado;
import es.ucm.fdi.despenseapp.R;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AddDespensaActivity extends AppCompatActivity {

    EditText nombre;
    Button boton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_despensa);
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
                confirmacion = new AlertDialog.Builder(AddDespensaActivity.this);
                confirmacion.setTitle("Mensaje de confirmación");
                confirmacion.setMessage("¿Esta seguro que desea crear la despensa?");
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
        Despensa nuevo = new Despensa(nombre.getText().toString(), new HashMap<Integer, ProductoComprado>() , new ArrayList<Lista>());
        if(nuevo.getNombreDespensa().isEmpty()) {
            Toast.makeText(getApplicationContext(),"Introduzca Nombre",Toast.LENGTH_SHORT).show();
        } else {
            ListaDeDespensas.getListaDeDespensas().addProduct(nuevo);
            Intent i = new Intent(this, InterfazDespensasActivity.class );
            startActivity(i);
        }
    }
}
