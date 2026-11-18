package es.davante.edadcanina.controlador;

import org.junit.Before;
import org.junit.Test;
import es.davante.edadcanina.modelo.EdadCaninaModelo;
import es.davante.edadcanina.modelo.Validacion;
import static org.junit.Assert.*;

// Pruebas unitarias para verificar que el controlador delega bien cada acción en el modelo
public class EdadCaninaControladorTest {

    private EdadCaninaModelo modelo;
    private EdadCaninaControlador controlador;

    @Before
    public void setUp() {
        modelo = new EdadCaninaModelo();
        controlador = new EdadCaninaControlador(modelo);
    }

    @Test
    public void onCalcularClick_ordenaAlModeloCalcular() {
        modelo.setEdadTexto("6");
        controlador.onCalcularClick();

        assertEquals(Validacion.OK, modelo.getValidacion());
        assertEquals(42, modelo.getEdadHumana());
    }

    @Test
    public void onLimpiarClick_reiniciaTodosLosDatosDelModelo() {
        modelo.setEdadTexto("6");
        controlador.onCalcularClick();

        // Al pulsar Limpiar, todo debe volver al estado inicial vacío
        controlador.onLimpiarClick();

        assertEquals("", modelo.getEdadTexto());
        assertEquals(0, modelo.getEdadHumana());
        assertEquals(Validacion.SIN_CALCULAR, modelo.getValidacion());
    }
}