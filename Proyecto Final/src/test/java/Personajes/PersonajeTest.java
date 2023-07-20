package Personajes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonajeTest {

    @Test
    @DisplayName("Prueba generar la efectividad de disparo")
    void efectividadDeDisparoTest() {
        // Crear un personaje ficticio para la prueba
        Personaje personajePrueba = new Humano(Raza.Humano, "Lucas", "Lukitas",
                LocalDate.parse("2000-05-06"), (short) 20);

        // Ejecutar el método a probar
        byte ED = personajePrueba.efectividadDeDisparo();

        // Verificar que el número generado esté dentro del rango esperado (1 a 100)
        assertTrue(ED >= 1 && ED <= 100, "El número generado debe estar dentro del rango de 1 a 100");

        // Imprimir el número generado
        System.out.println("El porcentaje de efectividad de disparo es de: " + ED + "%");
    }
}