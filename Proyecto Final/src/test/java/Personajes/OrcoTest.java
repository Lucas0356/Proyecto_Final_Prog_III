package Personajes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrcoTest {

    @Test
    @DisplayName("Prueba para calcular el poder de disparo en todos los niveles")
    void calcularPoderDeDisparoTest() {
        Orco orco = new Orco("Grom", "Destructor", "10-07-2000");

        // Siendo nivel 1
        byte poderDeDisparoEsperado = (byte) (2 * 10); // Destreza * fuerza * nivel (1)
        assertEquals(poderDeDisparoEsperado, orco.calcularPoderDeDisparo());

        // Siendo nivel 2
        orco.aumentarNivel();
        poderDeDisparoEsperado = (byte) (2 * 10 * 2); // Destreza * fuerza * nivel (2)
        assertEquals(poderDeDisparoEsperado, orco.calcularPoderDeDisparo());

        // Siendo nivel 3
        orco.aumentarNivel();
        poderDeDisparoEsperado = (byte) (2 * 10 * 3); // Destreza * fuerza * nivel (3)
        assertEquals(poderDeDisparoEsperado, orco.calcularPoderDeDisparo());

        // Siendo nivel 4 (max)
        orco.aumentarNivel();
        poderDeDisparoEsperado = (byte) (2 * 10 * 4); // Destreza * fuerza * nivel (4)
        assertEquals(poderDeDisparoEsperado, orco.calcularPoderDeDisparo());

    }
    @Test
    @DisplayName("Prueba activar ferocidad después de recibir dos ataques")
    public void activarFerocidadDespuesDeRecibirDosAtaques() {
        Orco orco = new Orco("Grom", "Destructor", "10-07-2000");
        Personaje atacante = new Humano("Carlos", "Carlitos", "01-01-2000");

        // Verificar que la ferocidad esté desactivada al principio
        assertFalse(orco.getEstadoFerocidad());

        // Realizar un ataque al orco
        byte danio1 = atacante.realizarAtaque(orco);
        orco.recibirDanio(danio1, false);

        // Verificar que la ferocidad siga desactivada después de recibir un ataque
        assertFalse(orco.getEstadoFerocidad());

        // Realizar otro ataque al orco
        byte danio2 = atacante.realizarAtaque(orco);
        orco.recibirDanio(danio2, false);

        // Verificar que la ferocidad se haya activado después de recibir dos ataques
        assertTrue(orco.getEstadoFerocidad());
    }
}