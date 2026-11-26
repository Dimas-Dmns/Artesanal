package com.krakedev.artesanal.testUnit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.krakedev.artesanal.Maquina;

public class TestRecargarJUnit {

    @Test
    public void testRecargaExitosa() {
        Maquina rubia = new Maquina("MAQ-009", "Pilsener", "Cerveza", 0.02, 8000);
        boolean resultado = rubia.recargarCerveza(3000);
        assertTrue(resultado);
        assertEquals(3000, rubia.getCantidadActual(), 0.0001);
    }

    @Test
    public void testRecargaFallida() {
        Maquina negra = new Maquina("MAQ-010", "Club", "Cerveza club", 0.03, 8000);
        negra.recargarCerveza(7000);
        // 7000 + 3000 = 10000, pero el límite ahora es 8000 - 200 = 7800
        // Por eso esta recarga DEBE FALLAR
        boolean resultado = negra.recargarCerveza(3000);
        assertFalse(resultado);
        // La cantidad debe quedar en 7000, no en 3000, porque no se recargó nada
        assertEquals(7000, negra.getCantidadActual(), 0.0001);
    }
}