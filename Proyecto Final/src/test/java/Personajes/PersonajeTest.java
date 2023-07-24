package Personajes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonajeTest {

    @Test
    @DisplayName("Prueba recibir daño y limitar salud a mínimo cero")
    public void recibirDanioLimitarSaludAMinimoCero() {
        Personaje personaje = new Elfo("Legolas", "Link", "01-01-2000");
        System.out.println("Daño a recibir: " + 120);
        System.out.println("Salud del elfo: " + personaje.getSalud());
        personaje.recibirDanio((byte) 120, false);
        System.out.println("Salud despues de recibir el daño: " + personaje.getSalud());
        assertEquals(0, personaje.getSalud());
    }
    @Test
    @DisplayName("Prueba calcular poder de defensa y verificar resultado")
    public void calcularPoderDeDefensaCalculoCorrecto() {
        Personaje personaje;
        byte poderDeDefensaEsperado;

        // Centauro:
        personaje = new Centauro("Pedro", "Pepito", "01-01-2000");
        poderDeDefensaEsperado = (byte) (5 * 8); // Armadura * Velocidad
        assertEquals(poderDeDefensaEsperado, personaje.calcularPoderDeDefensa());

        // Elfo:
        personaje = new Elfo("Legolas", "Link", "01-01-2000");
        poderDeDefensaEsperado = (byte) (4 * 7); // Armadura * Velocidad
        assertEquals(poderDeDefensaEsperado, personaje.calcularPoderDeDefensa());

        // Golem:
        personaje = new Golem("Juan", "Geodude", "01-01-2000");
        poderDeDefensaEsperado = (byte) (10 * 2); // Armadura * Velocidad
        assertEquals(poderDeDefensaEsperado, personaje.calcularPoderDeDefensa());

        // Humano:

        personaje = new Humano("Lucas", "Lukitas", "06-05-2003");
        poderDeDefensaEsperado = (byte) (4 * 6); // Armadura * Velocidad
        assertEquals(poderDeDefensaEsperado, personaje.calcularPoderDeDefensa());

        // Orco:
        personaje = new Orco("Grug", "Gruco", "01-01-2000");
        poderDeDefensaEsperado = (byte) (7 * 4); // Armadura * Velocidad
        assertEquals(poderDeDefensaEsperado, personaje.calcularPoderDeDefensa());
    }
    @Test
    @DisplayName("Prueba calcular poder de resistencia mágica y verificar resultado")
    public void calcularPoderDeResistenciaMagicaCalculoCorrecto() {
        Personaje personaje;
        byte poderDeResistenciaMagicaEsperado;

        // Centauro:
        personaje = new Centauro("Pedro", "Pepito", "01-01-2000");
        poderDeResistenciaMagicaEsperado = (byte) (4 * 8); // Resistencia mágica * Velocidad
        assertEquals(poderDeResistenciaMagicaEsperado, personaje.calcularPoderDeResistenciaMagica());

        // Elfo:
        personaje = new Elfo("Legolas", "Link", "01-01-2000");
        poderDeResistenciaMagicaEsperado = (byte) (7 * 7); // Resistencia mágica * Velocidad
        assertEquals(poderDeResistenciaMagicaEsperado, personaje.calcularPoderDeResistenciaMagica());

        // Golem:
        personaje = new Golem("Juan", "Geodude", "01-01-2000");
        poderDeResistenciaMagicaEsperado = (byte) (6 * 2); // Resistencia mágica * Velocidad
        assertEquals(poderDeResistenciaMagicaEsperado, personaje.calcularPoderDeResistenciaMagica());

        // Humano:
        personaje = new Humano("Lucas", "Lukitas", "01-01-2000");
        poderDeResistenciaMagicaEsperado = (byte) (4 * 6); // Resistencia mágica * Velocidad
        assertEquals(poderDeResistenciaMagicaEsperado, personaje.calcularPoderDeResistenciaMagica());

        // Orco:
        personaje = new Orco("Grug", "Gruco", "01-01-2000");
        poderDeResistenciaMagicaEsperado = (byte) (2 * 4); // Resistencia mágica * Velocidad
        assertEquals(poderDeResistenciaMagicaEsperado, personaje.calcularPoderDeResistenciaMagica());
    }
    @Test
    @DisplayName("Prueba recibir daño y verificar si el personaje está vivo")
    public void recibirDanioVerificarSiEstaVivo() {
        Personaje personaje = new Humano("Carlos", "Carlitos", "01-01-2000");
        assertTrue(personaje.estaVivo());

        // Recibir daño suficiente para que la salud sea 0
        personaje.recibirDanio((byte) 100, false);
        assertFalse(personaje.estaVivo());
    }
    @Test
    @DisplayName("Prueba atacar y verificar que hizo daño")
    public void realizarAtaqueVerificarElDanio(){
        Personaje atacante = new Humano("Carlos", "Carlitos", "01-01-2000");
        Personaje defensor = new Elfo("Legolas", "Elfo de los Bosques", "01-01-2000");

        byte danio = atacante.realizarAtaque(defensor);
        defensor.recibirDanio(danio, false);

        assertEquals(100 - danio, defensor.getSalud());
    }
}