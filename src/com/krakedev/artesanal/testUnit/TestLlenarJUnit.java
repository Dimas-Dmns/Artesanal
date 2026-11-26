package com.krakedev.artesanal.testUnit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.krakedev.artesanal.Maquina;

public class TestLlenarJUnit {

    @Test
    public void testLlenarMaquina() {
        Maquina rubia = new Maquina("MAQ-008", "Pilsener", "Cerveza", 0.02, 8000);
        rubia.llenarMaquina();
        // Antes era 8000 - 100 = 7900, ahora es 8000 - 200 = 7800
        assertEquals(7800, rubia.getCantidadActual(), 0.0001);
    }
}