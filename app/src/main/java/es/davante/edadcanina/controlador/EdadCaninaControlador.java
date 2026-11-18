package es.davante.edadcanina.controlador;

import es.davante.edadcanina.modelo.EdadCaninaModelo;

// Separo las acciones del usuario en el Controlador para que ni la Vista tenga lógica
// ni el Controlador conozca ningún componente visual de Android.
public class EdadCaninaControlador {

    private final EdadCaninaModelo modelo;

    public EdadCaninaControlador(EdadCaninaModelo modelo) {
        this.modelo = modelo;
    }

    // Traduzco el clic del botón Calcular en una orden directa hacia el modelo
    public void onCalcularClick() {
        modelo.calcular();
    }

    // Traduzco el clic del botón Limpiar para reiniciar todos los datos del modelo
    public void onLimpiarClick() {
        modelo.limpiar();
    }
}