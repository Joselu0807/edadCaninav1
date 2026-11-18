package es.davante.edadcanina.vista;

import android.content.Context;
import android.widget.EditText;
import androidx.databinding.BindingAdapter;
import es.davante.edadcanina.R;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

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
    // Interfaz sencilla para enlazar la tecla Listo del teclado desde el XML sin tocar la Activity
    public interface AlPulsarListo {
        void onListo();
    }

    // Este adaptador captura la tecla de acción del teclado (actionDone), oculta el teclado
    // y lanza el cálculo en el controlador igual que si pulsara el botón Calcular.
    @BindingAdapter("alPulsarListo")
    public static void configurarTeclaListo(EditText campo, AlPulsarListo accion) {
        if (accion == null) {
            campo.setOnEditorActionListener(null);
            return;
        }

        campo.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                accion.onListo();
                InputMethodManager imm = (InputMethodManager) v.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return true;
            }
            return false;
        });
    }
}