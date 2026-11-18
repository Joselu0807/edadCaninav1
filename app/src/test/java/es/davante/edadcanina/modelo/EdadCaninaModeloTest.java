package es.davante.edadcanina.modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// Pruebas unitarias para comprobar que mi modelo calcula y valida bien sin depender del emulador
public class EdadCaninaModeloTest {

    private EdadCaninaModelo modelo;

    @Before
    public void setUp() {
        // Preparo un modelo limpio antes de cada test para que una prueba no afecte a la siguiente
        modelo = new EdadCaninaModelo();
    }

    @Test
    public void edadValida_calculaMultiplicandoPorSiete() {
        modelo.setEdadTexto("6");
        assertEquals(Validacion.OK, modelo.calcular());
        assertEquals(42, modelo.getEdadHumana());
    }

    @Test
    public void limitesValidos_unoYTreintaSonAceptados() {
        modelo.setEdadTexto("1");
        assertEquals(Validacion.OK, modelo.calcular());
        assertEquals(7, modelo.getEdadHumana());

        modelo.setEdadTexto("30");
        assertEquals(Validacion.OK, modelo.calcular());
        assertEquals(210, modelo.getEdadHumana());
    }

    @Test
    public void entradaVaciaOConEspacios_devuelveVacio() {
        modelo.setEdadTexto("");
        assertEquals(Validacion.VACIO, modelo.calcular());
        assertEquals(0, modelo.getEdadHumana());

        modelo.setEdadTexto("   ");
        assertEquals(Validacion.VACIO, modelo.calcular());
    }

    @Test
    public void textoNoNumerico_devuelveNoNumerico() {
        modelo.setEdadTexto("abc");
        assertEquals(Validacion.NO_NUMERICO, modelo.calcular());
        assertEquals(0, modelo.getEdadHumana());
    }

    @Test
    public void edadCeroONegativa_devuelveFueraDeRango() {
        modelo.setEdadTexto("0");
        assertEquals(Validacion.FUERA_DE_RANGO, modelo.calcular());

        modelo.setEdadTexto("-4");
        assertEquals(Validacion.FUERA_DE_RANGO, modelo.calcular());
    }

    @Test
    public void edadMayorDeTreinta_devuelveFueraDeRango() {
        modelo.setEdadTexto("31");
        assertEquals(Validacion.FUERA_DE_RANGO, modelo.calcular());
    }

    @Test
    public void cambiarEntradaTrasCalcular_caducaResultadoAnterior() {
        modelo.setEdadTexto("6");
        modelo.calcular();
        assertEquals(Validacion.OK, modelo.getValidacion());

        // Si cambio el texto después de calcular, compruebo que el resultado anterior caduca
        modelo.setEdadTexto("8");
        assertEquals(Validacion.SIN_CALCULAR, modelo.getValidacion());
        assertEquals(0, modelo.getEdadHumana());
    }
    @Test
        public void calcular_avisaALosObservadoresCuandoCambianLosDatos() {
            final boolean[] avisoEdad = {false};
            final boolean[] avisoValidacion = {false};

            // Me suscribo al modelo igual que hace DataBinding por debajo para comprobar que notifica
            modelo.addOnPropertyChangedCallback(new androidx.databinding.Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(androidx.databinding.Observable sender, int propertyId) {
                    if (propertyId == es.davante.edadcanina.BR.edadHumana) {
                        avisoEdad[0] = true;
                    } else if (propertyId == es.davante.edadcanina.BR.validacion) {
                        avisoValidacion[0] = true;
                    }
                }
            });

            modelo.setEdadTexto("6");
            modelo.calcular();

            assertTrue(avisoEdad[0]);
            assertTrue(avisoValidacion[0]);
        }
    }
