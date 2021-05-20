package es.ucm.fdi.despenseapp.Despensas;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ucm.fdi.despenseapp.R;

public class DespensasLista extends AppCompatActivity {
    private TextView tvNombre, tvNprod;
    private Despensa item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_element);
        item = (Despensa) getIntent().getSerializableExtra("item");
        if (item != null){
            tvNombre = findViewById(R.id.textView3);
            tvNprod = findViewById(R.id.textView4);

            tvNombre.setText(item.getNombreDespensa());
            tvNprod.setText(Double.toString(item.getListaProductos().size()));
        }
    }


}
