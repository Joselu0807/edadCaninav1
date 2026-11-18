package es.davante.edadcanina.modelo;

// Aquí meto toda la lógica de validación y cálculo sin importar nada de android.*,
// así puedo probarla luego con JUnit sin tener que arrancar el emulador.
public class EdadCaninaModelo {

    // Dejo los límites públicos para que la vista los use al montar el mensaje de error sin repetir números
    public static final int EDAD_MINIMA = 1;
    public static final int EDAD_MAXIMA = 30;
    private static final int FACTOR_CANINO = 7;

    private String edadTexto = "";
    private int edadHumana = 0;
    private Validacion validacion = Validacion.SIN_CALCULAR;

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

        // Si cambia la entrada, reinicio el resultado para que no se quede en pantalla el cálculo viejo
        this.edadHumana = 0;
        this.validacion = Validacion.SIN_CALCULAR;
    }

    public int getEdadHumana() {
        return edadHumana;
    }

    public Validacion getValidacion() {
        return validacion;
    }

    // Valido lo que ha escrito el usuario y calculo los años humanos si todo está correcto
    public Validacion calcular() {
        String limpio = edadTexto.trim();

        if (limpio.isEmpty()) {
            edadHumana = 0;
            validacion = Validacion.VACIO;
            return validacion;
        }

        int edadPerro;
        try {
            edadPerro = Integer.parseInt(limpio);
        } catch (NumberFormatException e) {
            edadHumana = 0;
            validacion = Validacion.NO_NUMERICO;
            return validacion;
        }

        if (edadPerro < EDAD_MINIMA || edadPerro > EDAD_MAXIMA) {
            edadHumana = 0;
            validacion = Validacion.FUERA_DE_RANGO;
            return validacion;
        }

        edadHumana = edadPerro * FACTOR_CANINO;
        validacion = Validacion.OK;
        return validacion;
    }
}