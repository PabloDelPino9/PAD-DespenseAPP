package es.ucm.fdi.despenseapp.Despensas;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ucm.fdi.despenseapp.Productos.ProductoComprado;
import es.ucm.fdi.despenseapp.R;

public class ProductosListaDespensa extends AppCompatActivity {
    private TextView tvNombre, tvCantidad;
    private ImageButton up, down;
    private ImageView img;
    private ProductoComprado item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_productos_lista);
        item = (ProductoComprado) getIntent().getSerializableExtra("item");
        if (item != null){
            tvNombre = findViewById(R.id.tvTitulo);
            tvCantidad = findViewById(R.id.textView7);

            tvNombre.setText(item.getNombreProducto());
            img.setImageAlpha(item.getImgProducto());
            tvCantidad.setText(Integer.toString(item.getCantidad()));
        }
    }
}
