package Personajes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class HumanoTest {

    @Test
    @DisplayName("Prueba realizar un ataque")
    void realizarAtaque() {
        // Creamos personajes ficticios para la prueba
        Personaje personajeAtacante = new Humano(Raza.Humano, "Lucas", "Lukitas",
                "06-05-2003", (short) 20);
        Personaje personajeDefensor = new Elfo(Raza.Elfo, "Juan", "Juancito",
                "25-10-1998", (short) 26);

        // Ejecutar el método a probar

        // Verificar
    }
}