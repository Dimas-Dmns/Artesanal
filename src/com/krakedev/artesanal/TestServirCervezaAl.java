
package com.krakedev.artesanal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestServirCervezaAl {

    // Tolerancia para comparar valores double
    private static final double DELTA = 0.001;

    @Test
    public void testServirCervezaConConstructorCompleto() {

        // Se utiliza el constructor que recibe la capacidad máxima
        Maquina maquina = new Maquina("Pilsener", "Cerveza rubia", 0.05, 5000);

        // Se llena la máquina y se sirven 100 ml
        maquina.llenarMaquina();

        double valor = maquina.servirCerveza(100);

        // Verifica que se descuente la cantidad servida
        assertEquals(4800, maquina.getCantidadActual(), DELTA);

        // Verifica que el valor a pagar sea 100 * 0.05
        assertEquals(5.0, valor, DELTA);
    }

    @Test
    public void testServirCervezaConConstructorSimple() {

        // Se utiliza el constructor que establece la capacidad predeterminada
        Maquina maquina = new Maquina("Club", "Cerveza negra", 0.10);

        // Se llena la máquina y se sirven 200 ml
        maquina.llenarMaquina();

        double valor = maquina.servirCerveza(200);

        // Verifica que se descuenten correctamente los mililitros
        assertEquals(9700, maquina.getCantidadActual(), DELTA);

        // Verifica el valor a pagar
        assertEquals(20.0, valor, DELTA);
    }

    @Test
    public void testServirTodaLaCervezaDisponible() {

        // Prepara una máquina con 4900 ml disponibles
        Maquina maquina = new Maquina("Pilsener", "Cerveza rubia", 0.05, 5000);
        maquina.llenarMaquina();

        double valor = maquina.servirCerveza(4900);

        // Verifica que se pueda servir toda la cerveza disponible
        assertEquals(0, maquina.getCantidadActual(), DELTA);

        // Verifica el valor correspondiente a los 4900 ml
        assertEquals(245.0, valor, DELTA);
    }

    @Test
    public void testServirCervezaSinCantidadSuficiente() {

        // Prepara una máquina con 4900 ml disponibles
        Maquina maquina = new Maquina("Pilsener", "Cerveza rubia", 0.05, 5000);
        maquina.llenarMaquina();

        double cantidadInicial = maquina.getCantidadActual();

        // Se solicita más cerveza de la que está disponible
        double valor = maquina.servirCerveza(5000);

        // Verifica que no se haya servido cerveza
        assertEquals(cantidadInicial, maquina.getCantidadActual(), DELTA);

        // Verifica que el valor retornado sea cero
        assertEquals(0, valor, DELTA);
    }

    @Test
    public void testServirCervezaConMaquinaVacia() {

        // Se crea una máquina que inicialmente no tiene cerveza
        Maquina maquina = new Maquina("Club", "Cerveza negra", 0.10, 5000);

        // Se intenta servir cerveza sin haber llenado la máquina
        double valor = maquina.servirCerveza(100);

        // Verifica que la cantidad actual siga siendo cero
        assertEquals(0, maquina.getCantidadActual(), DELTA);

        // Verifica que no se cobre ningún valor
        assertEquals(0, valor, DELTA);
    }

    @Test
    public void testServirCantidadExactaDisponible() {

        // Prepara una máquina con 4900 ml disponibles
        Maquina maquina = new Maquina("Club", "Cerveza negra", 0.10, 5000);
        maquina.llenarMaquina();

        // Se solicita exactamente toda la cerveza disponible
        double valor = maquina.servirCerveza(4900);

        // Verifica que la máquina quede vacía
        assertEquals(0, maquina.getCantidadActual(), DELTA);

        // Verifica el valor total a pagar
        assertEquals(490.0, valor, DELTA);
    }

    @Test
    public void testServirCeroMililitros() {

        // Prepara una máquina con cerveza disponible
        Maquina maquina = new Maquina("Pilsener", "Cerveza rubia", 0.05, 5000);
        maquina.llenarMaquina();

        double cantidadInicial = maquina.getCantidadActual();

        // Se solicita servir cero mililitros
        double valor = maquina.servirCerveza(0);

        // Verifica que la cantidad disponible no cambie
        assertEquals(cantidadInicial, maquina.getCantidadActual(), DELTA);

        // Verifica que el valor a pagar sea cero
        assertEquals(0, valor, DELTA);
    }
}
