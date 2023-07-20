package Utilidades;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumeroAleatorioTest {
    @Test
    @DisplayName("Prueba generar número aleatorio dentro del rango")
    void testGenerarNumeroAleatorio() {
        byte rango = 3;
        byte num = NumeroAleatorio.generarNumeroAleatorio(rango);

        // Verificar que el número generado esté dentro del rango esperado (1 a rango)
        assertTrue(num >= 1 && num <= rango, "El número generado debe estar dentro del rango de 1 a " + rango);

        // Imprimir el número generado
        System.out.println("El número aleatorio (de 1 a " + rango + ") es: " + num);
    }
}
