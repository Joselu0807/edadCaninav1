package es.davante.edadcanina;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;


public class MainActivity extends AppCompatActivity {

    // Objeto generado por DataBinding para acceder a las vistas sin findViewById
    private ActivityMainBinding binding;
    // Creo el modelo para delegar en él toda la validación y el cálculo
    private EdadCaninaModelo modelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        modelo = new EdadCaninaModelo();
// Conecto el modelo con el binding para que la vista observe sus cambios sola
        binding.setModelo(modelo);

        // Dejo solo la orden de calcular; los errores los pinta ahora el @BindingAdapter desde el XML
        binding.btnCalc.setOnClickListener(view -> modelo.calcular());
0031
    } // <-- Esta llave cierra el onCreate()
}
