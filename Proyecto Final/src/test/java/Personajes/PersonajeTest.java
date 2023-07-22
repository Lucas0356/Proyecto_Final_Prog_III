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
                "06-05-2003", (short) 20);

        // Ejecutar el método a probar
        byte ED = personajePrueba.generarEfectividadDeDisparo();

        // Verificar que el número generado esté dentro del rango esperado (1 a 100)
        assertTrue(ED >= 1 && ED <= 100, "El número generado debe estar dentro del rango de 1 a 100");

        // Imprimir el número generado
        System.out.println("El valor de efectividad de disparo es de: " + ED);
    }
}