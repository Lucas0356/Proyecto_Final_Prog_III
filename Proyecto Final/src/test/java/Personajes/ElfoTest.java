package Personajes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElfoTest {
    @Test
    @DisplayName("Prueba para calcular el poder de disparo en todos los niveles")
    void calcularPoderDeDisparoTest() {
        Elfo elfo = new Elfo("Legolas", "Link", "01-01-2000");

        // Siendo nivel 1
        byte poderDeDisparoEsperado = (byte) (5 * 9); // Destreza * poder mágico * nivel (1)
        assertEquals(poderDeDisparoEsperado, elfo.calcularPoderDeDisparo());

        // Siendo nivel 2
        elfo.aumentarNivel();
        poderDeDisparoEsperado = (byte) (5 * 9 * 2); // Destreza * poder mágico * nivel (2)
        assertEquals(poderDeDisparoEsperado, elfo.calcularPoderDeDisparo());

        // Siendo nivel 3
        elfo.aumentarNivel();
        poderDeDisparoEsperado = (byte) (5 * 9 * 3); // Destreza * poder mágico * nivel (3)
        assertEquals(poderDeDisparoEsperado, elfo.calcularPoderDeDisparo());

        // Siendo nivel 4 (max)
        elfo.aumentarNivel();
        poderDeDisparoEsperado = (byte) (5 * 9 * 4); // Destreza * poder mágico * nivel (4)
        assertEquals(poderDeDisparoEsperado, elfo.calcularPoderDeDisparo());
    }
}