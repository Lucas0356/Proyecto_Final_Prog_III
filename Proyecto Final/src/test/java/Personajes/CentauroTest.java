package Personajes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentauroTest {
    @Test
    @DisplayName("Prueba para calcular el poder de disparo en todos los niveles")
    void calcularPoderDeDisparoTest() {
        Centauro centauro = new Centauro("Pedro", "Pepito", "01-01-2000");

        // Siendo nivel 1
        byte poderDeDisparoEsperado = (byte) (3 * 8); // Destreza * fuerza * nivel (1)
        assertEquals(poderDeDisparoEsperado, centauro.calcularPoderDeDisparo());

        // Siendo nivel 2
        centauro.aumentarNivel();
        poderDeDisparoEsperado = (byte) (3 * 8 * 2); // Destreza * fuerza * nivel (2)
        assertEquals(poderDeDisparoEsperado, centauro.calcularPoderDeDisparo());

        // Siendo nivel 3
        centauro.aumentarNivel();
        poderDeDisparoEsperado = (byte) (3 * 8 * 3); // Destreza * fuerza * nivel (3)
        assertEquals(poderDeDisparoEsperado, centauro.calcularPoderDeDisparo());

        // Siendo nivel 4 (max)
        centauro.aumentarNivel();
        poderDeDisparoEsperado = (byte) (3 * 8 * 4); // Destreza * fuerza * nivel (4)
        assertEquals(poderDeDisparoEsperado, centauro.calcularPoderDeDisparo());
    }
}