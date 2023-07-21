package Personajes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class HumanoTest {

    @Test
    @DisplayName("Prueba realizar un ataque")
    void realizarAtaque() {
        // Creamos personajes ficticios para la prueba
        Personaje personajeAtacante = new Humano(Raza.Humano, "Lucas", "Lukitas",
                LocalDate.parse("2000-05-06"), (short) 20);
        Personaje personajeDefensor = new Elfo(Raza.Elfo, "Juan", "Juancito",
                LocalDate.parse("1997-10-25"), (short) 26);

        // Ejecutar el método a probar
        personajeAtacante.realizarAtaque(personajeDefensor);

        System.out.println(personajeDefensor);

        // Verificar
    }
}