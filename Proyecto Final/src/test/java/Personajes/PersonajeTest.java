package Personajes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonajeTest {

    @Test
    @DisplayName("Prueba recibir daño y limitar salud a mínimo cero")
    public void recibirDaño_LimitarSaludAMinimoCero() {
        Personaje personaje = new Elfo(Raza.Elfo, "Legolas", "Elfo de los Bosques", "01/01/2000", (short) 23);
        System.out.println("Daño a recibir: " + 120);
        System.out.println("Salud del elfo: " + personaje.getSalud());
        personaje.recibirDaño((byte) 120);
        System.out.println("Salud despues de recibir el daño: " + personaje.getSalud());
        assertEquals(0, personaje.getSalud());
    }

    @Test
    @DisplayName("Prueba calcular poder de defensa")
    public void calcularPoderDeDefensa_CalculoCorrecto() {
        Personaje personaje = new Golem(Raza.Golem, "Juan", "Geodude", "01/01/2000", (short) 23);
        byte poderDeDefensaEsperado = (byte) (10 * 2); // Armadura * Velocidad
        System.out.println(poderDeDefensaEsperado + " " + personaje.calcularPoderDeDefensa());
        assertEquals(poderDeDefensaEsperado, personaje.calcularPoderDeDefensa());
    }

}