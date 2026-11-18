package es.davante.edadcanina;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import es.davante.edadcanina.controlador.EdadCaninaControlador;
import es.davante.edadcanina.databinding.ActivityMainBinding;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

public class MainActivity extends AppCompatActivity {
    // Uso claves constantes para guardar en el Bundle solo los datos mínimos al girar la pantalla
    private static final String CLAVE_EDAD_TEXTO = "edad_texto";
    private static final String CLAVE_CALCULADO = "calculado";
    private ActivityMainBinding binding;
    // Creo el modelo para delegar en él toda la validación y el cálculo
    private EdadCaninaModelo modelo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        modelo = new EdadCaninaModelo();
// Restauro el estado antes de enlazar el modelo con el binding para que la vista
        // arranque ya con el resultado o el error que había antes de girar
        if (savedInstanceState != null) {
            modelo.setEdadTexto(savedInstanceState.getString(CLAVE_EDAD_TEXTO, ""));
            if (savedInstanceState.getBoolean(CLAVE_CALCULADO, false)) {
                modelo.calcular();
            }
        }
        binding.setModelo(modelo);

        // Creo el controlador y lo enlazo con el XML; ya no queda ningún listener en la Activity
        EdadCaninaControlador controlador = new EdadCaninaControlador(modelo);
        binding.setControlador(controlador);
    }
        @Override
        protected void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);
            // Guardo solo el texto introducido y si ya se había pulsado Calcular; con esos dos
            // datos mínimos puedo reconstruir tanto el resultado como cualquier error tras girar
            outState.putString(CLAVE_EDAD_TEXTO, modelo.getEdadTexto());
            outState.putBoolean(CLAVE_CALCULADO, modelo.getValidacion() != Validacion.SIN_CALCULAR);
        }
}

