package org.example.Logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvaluadorTest {

    @Test
    void prioridad() {
        assertEquals(-1, Evaluador.prioridad('0'));
        assertEquals(1, Evaluador.prioridad('-'));
        assertEquals(2, Evaluador.prioridad('*'));
        assertEquals(3, Evaluador.prioridad('^'));
    }

    @Test
    void infixToPostfix() {
        assertNotEquals("532*+", Evaluador.infixToPostfix("5+3*2"));
        assertEquals("5 3 2*+", Evaluador.infixToPostfix("5+3*2"));
    }

    @Test
    void evaluatePostfix() {
        assertEquals(11, Evaluador.evaluatePostfix("5 3 2*+"));
    }
}