package es.davante.edadcanina.modelo;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import es.davante.edadcanina.BR;

// Aquí meto toda la lógica de validación y cálculo sin importar nada de android.*,
// así puedo probarla luego con JUnit sin tener que arrancar el emulador.
public class EdadCaninaModelo extends BaseObservable{

    // Dejo los límites públicos para que la vista los use al montar el mensaje de error sin repetir números
    public static final int EDAD_MINIMA = 1;
    public static final int EDAD_MAXIMA = 30;
    private static final int FACTOR_CANINO = 7;

    private String edadTexto = "";
    private int edadHumana = 0;
    private Validacion validacion = Validacion.SIN_CALCULAR;
@Bindable
    public String getEdadTexto() {
        return edadTexto;
    }

    public void setEdadTexto(String nuevoTexto) {
        if (nuevoTexto == null) {
            nuevoTexto = "";
        }
        // Si el usuario ha escrito lo mismo que ya había, salgo para no hacer trabajo de más
        if (nuevoTexto.equals(this.edadTexto)) {
            return;
        }
        this.edadTexto = nuevoTexto;
        notifyPropertyChanged(BR.edadTexto);

        // Uso los setters privados para que avisen a DataBinding y se oculte el resultado viejo
        setEdadHumana(0);
        setValidacion(Validacion.SIN_CALCULAR);
    }
    @Bindable
    public int getEdadHumana() {
        return edadHumana;
    }
    // Dejo este setter privado porque solo el propio modelo debe poder cambiar el resultado
    private void setEdadHumana(int nuevaEdadHumana) {
        if (this.edadHumana == nuevaEdadHumana) {
            return;
        }
        this.edadHumana = nuevaEdadHumana;
        notifyPropertyChanged(BR.edadHumana);
    }
    @Bindable
    public Validacion getValidacion() {
        return validacion;
    }
    // Dejo este setter privado para que el estado de validación solo lo decida calcular()
    private void setValidacion(Validacion nuevaValidacion) {
        if (this.validacion == nuevaValidacion) {
            return;
        }
        this.validacion = nuevaValidacion;
        notifyPropertyChanged(BR.validacion);
    }
    // Valido lo que ha escrito el usuario y calculo los años humanos si todo está correcto
    public Validacion calcular() {
        String limpio = edadTexto.trim();

        if (limpio.isEmpty()) {
            setEdadHumana(0);
            setValidacion(Validacion.VACIO);
            return validacion;
        }

        int edadPerro;
        try {
            edadPerro = Integer.parseInt(limpio);
        } catch (NumberFormatException e) {
            setEdadHumana(0);
            setValidacion(Validacion.NO_NUMERICO);
            return validacion;
        }

        if (edadPerro < EDAD_MINIMA || edadPerro > EDAD_MAXIMA) {
            setEdadHumana(0);
            setValidacion( Validacion.FUERA_DE_RANGO);
            return validacion;
        }

        setEdadHumana(edadPerro * FACTOR_CANINO);
        setValidacion(Validacion.OK);
        return validacion;

    }
    public void limpiar() {
        setEdadTexto("");
    }
}