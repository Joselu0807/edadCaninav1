package es.davante.edadcanina.modelo;

// Uso un enum con los posibles estados en vez de devolver textos directamente,
// así no ato el modelo a Android ni a ningún idioma concreto.
public enum Validacion {
    SIN_CALCULAR,
    OK,
    VACIO,
    NO_NUMERICO,
    FUERA_DE_RANGO
}