package es.davante.edadcanina;

import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import es.davante.edadcanina.databinding.ActivityMainBinding;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final int FACTOR_CANINO = 7;
    private static final int EDAD_MINIMA = 1;
    private static final int EDAD_MAXIMA = 30;

    // Objeto generado por DataBinding para acceder a las vistas sin findViewById
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Implementar el boton (setOnClickListener)
        binding.btnCalc.setOnClickListener(view -> calcularEdad());
    } // <-- Esta llave cierra el onCreate()

    private void mostrarError(String mensaje) {
        binding.ageInput.setError(mensaje);
        binding.ageInput.requestFocus();
        binding.resultText.setText("");
    }

    private void calcularEdad() {
        String ageString =binding.ageInput.getText().toString().trim();

        if (ageString.isEmpty()) {
            mostrarError(getString(R.string.error_vacio));
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException e) {
            mostrarError(getString(R.string.error_no_numero));
            return;
        }

        if (age < EDAD_MINIMA || age > EDAD_MAXIMA) {
            mostrarError(getString(R.string.error_rango, EDAD_MINIMA, EDAD_MAXIMA));
            return;
        }

        binding.ageInput.setError(null);
        int result = age * FACTOR_CANINO;
        binding.resultText.setText(getString(R.string.tu_edad_format, result));
    }
}
