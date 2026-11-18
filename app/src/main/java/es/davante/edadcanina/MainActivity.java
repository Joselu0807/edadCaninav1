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

        // Implementar el boton (setOnClickListener)
        binding.btnCalc.setOnClickListener(view -> {
            modelo.setEdadTexto(binding.ageInput.getText().toString());
            Validacion validacion = modelo.calcular();
            pintarResultado(validacion);
        });
    } // <-- Esta llave cierra el onCreate()

    private void mostrarError(String mensaje) {
        binding.ageInput.setError(mensaje);
        binding.ageInput.requestFocus();
        binding.resultText.setText("");
    }

    // Traduzco el estado que me devuelve el modelo al texto correspondiente en pantalla
    private void pintarResultado(Validacion validacion) {
        switch (validacion) {
            case OK:
                binding.ageInput.setError(null);
                binding.resultText.setText(getString(R.string.tu_edad_format, modelo.getEdadHumana()));
                break;
            case VACIO:
                mostrarError(getString(R.string.error_vacio));
                break;
            case NO_NUMERICO:
                mostrarError(getString(R.string.error_no_numero));
                break;
            case FUERA_DE_RANGO:
                mostrarError(getString(R.string.error_rango, EdadCaninaModelo.EDAD_MINIMA, EdadCaninaModelo.EDAD_MAXIMA));
                break;
            default:
                binding.ageInput.setError(null);
                binding.resultText.setText("");
                break;
        }
    }
}