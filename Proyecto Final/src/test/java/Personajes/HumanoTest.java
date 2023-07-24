package Personajes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumanoTest {

    @Test
    @DisplayName("Prueba para calcular el poder de disparo en todos los niveles")
    void calcularPoderDeDisparoTest() {
        Humano humano = new Humano("Lucas", "Lukitas", "01-01-2000");

        // Siendo nivel 1
        byte poderDeDisparoEsperado = (byte) (5 * 7); // Destreza * fuerza * nivel (1)
        assertEquals(poderDeDisparoEsperado, humano.calcularPoderDeDisparo());

        // Siendo nivel 2
        humano.aumentarNivel();
        poderDeDisparoEsperado = (byte) (5 * 7 * 2); // Destreza * fuerza * nivel (2)
        assertEquals(poderDeDisparoEsperado, humano.calcularPoderDeDisparo());

        // Siendo nivel 3
        humano.aumentarNivel();
        poderDeDisparoEsperado = (byte) (5 * 7 * 3); // Destreza * fuerza * nivel (3)
        assertEquals(poderDeDisparoEsperado, humano.calcularPoderDeDisparo());

        // Siendo nivel 4 (max)
        humano.aumentarNivel();
        poderDeDisparoEsperado = (byte) (5 * 7 * 4); // Destreza * fuerza * nivel (4)
        assertEquals(poderDeDisparoEsperado, humano.calcularPoderDeDisparo());
    }
}