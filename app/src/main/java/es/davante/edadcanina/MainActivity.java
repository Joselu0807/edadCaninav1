package es.davante.edadcanina;

import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


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

        binding.btnCalc.setOnClickListener(view -> {
            Validacion validacion = modelo.calcular();
            pintarErrorTemporal(validacion);
        });

    } // <-- Esta llave cierra el onCreate()

    // Traduzco el estado que me devuelve el modelo al texto correspondiente en pantalla
    // Pinto solo el error del EditText de forma temporal hasta moverlo al XML en el siguiente paso
    private void pintarErrorTemporal(Validacion validacion) {
        switch (validacion) {
            case VACIO:
                binding.ageInput.setError(getString(R.string.error_vacio));
                binding.ageInput.requestFocus();
                break;
            case NO_NUMERICO:
                binding.ageInput.setError(getString(R.string.error_no_numero));
                binding.ageInput.requestFocus();
                break;
            case FUERA_DE_RANGO:
                binding.ageInput.setError(getString(R.string.error_rango, EdadCaninaModelo.EDAD_MINIMA, EdadCaninaModelo.EDAD_MAXIMA));
                binding.ageInput.requestFocus();
                break;
            default:
                binding.ageInput.setError(null);
                break;
        }
    }
}