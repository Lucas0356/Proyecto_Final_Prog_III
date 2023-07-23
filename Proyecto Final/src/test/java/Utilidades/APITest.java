package Utilidades;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class APITest {
    @Test
    @DisplayName("Prueba obtener datos de personaje aleatorio")
    void testObtenerDatosPersonajeAleatorio() {
        // Ejecutar el método a probar
        String[] datosPersonaje = API.obtenerDatosPersonajeAleatorio();

        // Verificar el resultado
        assertNotNull(datosPersonaje, "Los datos del personaje no deben ser nulos");
        assertEquals(3, datosPersonaje.length, "Debe haber 3 datos en el arreglo");
        assertNotNull(datosPersonaje[0], "El nombre no debe ser nulo");
        assertNotNull(datosPersonaje[1], "El apodo no debe ser nulo");
        assertTrue(datosPersonaje[2].matches("\\d{2}-\\d{2}-\\d{4}"), "La fecha de nacimiento debe tener un formato válido");

        // Verificar que el nombre no esté en la lista de nombres no deseados
        String[] nombresNoDeseados = {"Mr.", "Dr.", "Mrs.", "Ms.", "Miss", "Prof."};
        for (String noDeseado : nombresNoDeseados) {
            assertNotEquals(noDeseado, datosPersonaje[0], "El nombre no debe ser un nombre no deseado");
        }

        // Imprimir los datos obtenidos
        System.out.println("Datos del personaje aleatorio:");
        System.out.println("Nombre: " + datosPersonaje[0]);
        System.out.println("Apodo: " + datosPersonaje[1]);
        System.out.println("Fecha de Nacimiento: " + datosPersonaje[2]);
    }
}
