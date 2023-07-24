package Personajes;

import Utilidades.ManejoLogs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrcoTest {

    @Test
    @DisplayName("Probar que no se pueda atacar con un personaje muerto")
    public void verificarSiAtacaMuerto() {
        Personaje atacante = new Orco("Grom", "Destructor", "10-07-2000");
        Personaje defensor = new Elfo("Legolas", "Elfo de los Bosques", "01-01-2000");

        // Matamos al personaje
        atacante.recibirDanio((byte) 100);

        // Intentamos realizar el ataque
        byte danio = atacante.realizarAtaque(defensor);

        // Si el daño retornado del ataque es 0, significa que no pudo atacar
        assertEquals(0, danio);
    }
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
        ManejoLogs.setGuardarFalse();
        Orco orco = new Orco("Grom", "Destructor", "10-07-2000");
        Personaje atacante = new Humano("Carlos", "Carlitos", "01-01-2000");

        // Verificar que la ferocidad esté desactivada al principio
        assertFalse(orco.getEstadoFerocidad());

        // Realizar un ataque al orco
        byte danio1 = atacante.calcularDanioAtaque(orco);
        System.out.println("Primer ataque al orco. Estado de su ferocidad: " + orco.getEstadoFerocidad());
        orco.recibirDanio(danio1);

        // Verificar que la ferocidad siga desactivada después de recibir un ataque
        assertFalse(orco.getEstadoFerocidad());

        // Realizar otro ataque al orco
        System.out.println("Segundo ataque al orco. Estado de su ferocidad: " + orco.getEstadoFerocidad());
        byte danio2 = atacante.calcularDanioAtaque(orco);
        orco.recibirDanio(danio2);

        // Verificar que la ferocidad se haya activado después de recibir dos ataques
        assertTrue(orco.getEstadoFerocidad());
        System.out.println("Estado de su ferocidad luego de los 2 ataques recibidos: " + orco.getEstadoFerocidad());

        // Verificar que la ferocidad se haya desactivado después de recibir atacar con ferocidad
        orco.realizarAtaque(atacante); // Orco ataca
        assertFalse(orco.getEstadoFerocidad());
        System.out.println("Estado de su ferocidad luego de atacar con la misma activada: " + orco.getEstadoFerocidad());
    }
}