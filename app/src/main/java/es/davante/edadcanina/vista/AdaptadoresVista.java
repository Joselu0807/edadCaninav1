package es.davante.edadcanina.vista;

import android.content.Context;
import android.widget.EditText;
import androidx.databinding.BindingAdapter;
import es.davante.edadcanina.R;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;

// Creo esta clase dentro del paquete vista para traducir los estados del modelo
// a mensajes visuales sin ensucian ni el modelo ni la Activity.
public final class AdaptadoresVista {

    // Pongo el constructor privado porque esta clase solo tiene métodos estáticos
    private AdaptadoresVista() {
    }

    // Este adaptador crea el atributo app:errorValidacion para el XML y pinta o limpia
    // el error del EditText automáticamente cada vez que cambia validacion en el modelo.
    @BindingAdapter("errorValidacion")
    public static void mostrarErrorValidacion(EditText campo, Validacion validacion) {
        if (validacion == null || validacion == Validacion.SIN_CALCULAR || validacion == Validacion.OK) {
            campo.setError(null);
            return;
        }

        Context contexto = campo.getContext();
        String mensaje;

        switch (validacion) {
            case VACIO:
                mensaje = contexto.getString(R.string.error_vacio);
                break;
            case NO_NUMERICO:
                mensaje = contexto.getString(R.string.error_no_numero);
                break;
            case FUERA_DE_RANGO:
                mensaje = contexto.getString(
                        R.string.error_rango,
                        EdadCaninaModelo.EDAD_MINIMA,
                        EdadCaninaModelo.EDAD_MAXIMA
                );
                break;
            default:
                mensaje = null;
                break;
        }

        campo.setError(mensaje);
        if (mensaje != null) {
            campo.requestFocus();
        }
    }
}