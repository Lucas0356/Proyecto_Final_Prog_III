package Personajes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonajeTest {

    @Test
    @DisplayName("Prueba generar la efectividad de disparo")
    void generarEfectividadDeDisparoTest() {
        // Crear un personaje ficticio para la prueba
        Personaje personajePrueba = new Humano(Raza.Humano, "Lucas", "Lukitas",
                LocalDate.parse("2000-05-06"), (short) 20);

        // Ejecutar el método a probar
        byte ED = personajePrueba.generarEfectividadDeDisparo();

        // Verificar que el número generado esté dentro del rango esperado (1 a 100)
        assertTrue(ED >= 1 && ED <= 100, "El número generado debe estar dentro del rango de 1 a 100");

        // Imprimir el número generado
        System.out.println("El valor de efectividad de disparo es de: " + ED);
    }

    @Test
    @DisplayName("Prueba calcular el valor de ataque")
    void calcularValorDeAtaqueTest() {
        // Crear un personaje ficticio para la prueba
        Personaje personajeAtacante = new Humano(Raza.Humano, "Lucas", "Lukitas",
                LocalDate.parse("2000-05-06"), (short) 20);
        Personaje personajeDefensor = new Elfo(Raza.Elfo, "Juan", "Juancito",
                LocalDate.parse("1997-10-25"), (short) 26);

        // Ejecutar el método a probar
        personajeAtacante.realizarAtaque(personajeDefensor);

        // Verificar

    }
}