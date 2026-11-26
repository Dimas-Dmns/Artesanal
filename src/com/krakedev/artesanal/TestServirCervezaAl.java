package com.krakedev.artesanal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestServirCervezaAl {

    // Tolerancia para comparar valores double
    private static final double DELTA = 0.001;

    @Test
    public void testServirCervezaConConstructorCompleto() {

        // Se utiliza el constructor que recibe la capacidad máxima
        Maquina maquina = new Maquina("MAQ-001", "Pilsener", "Cerveza rubia", 0.05, 5000);

        // Se llena la máquina: 5000 - 200 = 4800, se sirven 100 ml
        maquina.llenarMaquina();

        double valor = maquina.servirCerveza(100);

        // 4800 - 100 = 4700
        assertEquals(4700, maquina.getCantidadActual(), DELTA);

        // Verifica que el valor a pagar sea 100 * 0.05
        assertEquals(5.0, valor, DELTA);
    }

    @Test
    public void testServirCervezaConConstructorSimple() {

        // Se utiliza el constructor que establece la capacidad predeterminada 10000
        Maquina maquina = new Maquina("MAQ-002", "Club", "Cerveza negra", 0.10);

        // Se llena: 10000 - 200 = 9800, se sirven 200 ml
        maquina.llenarMaquina();

        double valor = maquina.servirCerveza(200);

        // 9800 - 200 = 9600
        assertEquals(9600, maquina.getCantidadActual(), DELTA);

        // Verifica el valor a pagar
        assertEquals(20.0, valor, DELTA);
    }

    @Test
    public void testServirTodaLaCervezaDisponible() {

        // Prepara una máquina con 4800 ml disponibles (5000 - 200)
        Maquina maquina = new Maquina("MAQ-003", "Pilsener", "Cerveza rubia", 0.05, 5000);
        maquina.llenarMaquina();

        double valor = maquina.servirCerveza(4800);

        // Verifica que se pueda servir toda la cerveza disponible
        assertEquals(0, maquina.getCantidadActual(), DELTA);

        // Verifica el valor correspondiente a los 4800 ml: 4800 * 0.05 = 240
        assertEquals(240.0, valor, DELTA);
    }

    @Test
    public void testServirCervezaSinCantidadSuficiente() {

        // Prepara una máquina con 4800 ml disponibles
        Maquina maquina = new Maquina("MAQ-004", "Pilsener", "Cerveza rubia", 0.05, 5000);
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
        Maquina maquina = new Maquina("MAQ-005", "Club", "Cerveza negra", 0.10, 5000);

        // Se intenta servir cerveza sin haber llenado la máquina
        double valor = maquina.servirCerveza(100);

        // Verifica que la cantidad actual siga siendo cero
        assertEquals(0, maquina.getCantidadActual(), DELTA);

        // Verifica que no se cobre ningún valor
        assertEquals(0, valor, DELTA);
    }

    @Test
    public void testServirCantidadExactaDisponible() {

        // Prepara una máquina con 4800 ml disponibles
        Maquina maquina = new Maquina("MAQ-006", "Club", "Cerveza negra", 0.10, 5000);
        maquina.llenarMaquina();

        // Se solicita exactamente toda la cerveza disponible
        double valor = maquina.servirCerveza(4800);

        // Verifica que la máquina quede vacía
        assertEquals(0, maquina.getCantidadActual(), DELTA);

        // Verifica el valor total a pagar: 4800 * 0.10 = 480
        assertEquals(480.0, valor, DELTA);
    }

    @Test
    public void testServirCeroMililitros() {

        // Prepara una máquina con cerveza disponible
        Maquina maquina = new Maquina("MAQ-007", "Pilsener", "Cerveza rubia", 0.05, 5000);
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