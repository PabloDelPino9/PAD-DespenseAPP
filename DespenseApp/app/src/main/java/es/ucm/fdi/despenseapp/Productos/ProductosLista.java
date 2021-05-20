package es.ucm.fdi.despenseapp.Productos;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ucm.fdi.despenseapp.R;

public class ProductosLista extends AppCompatActivity {
    private TextView tvTitulo, precio;
    private ImageView imgFoto;
    private Producto item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_element);
        item = (Producto) getIntent().getSerializableExtra("item");
        if (item != null){
            tvTitulo = findViewById(R.id.tvTitulo);
            precio = findViewById(R.id.tvPrecio);
            imgFoto = findViewById(R.id.imgFoto);

            tvTitulo.setText(item.getNombreProducto());
            precio.setText(Double.toString(item.getPrecioAprox()));
            imgFoto.setImageResource(item.getImgProducto());
        }
    }
}
