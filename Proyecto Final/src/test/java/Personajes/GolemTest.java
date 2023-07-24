package Personajes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GolemTest {

    @Test
    @DisplayName("Prueba para calcular el poder de disparo en todos los niveles")
    void calcularPoderDeDisparoTest() {
        Golem golem = new Golem("Juan", "Geodude", "01-01-2000");

        // Siendo nivel 1
        byte poderDeDisparoEsperado = (byte) (4 * 20); // Destreza * fuerza * nivel (1)
        assertEquals(poderDeDisparoEsperado, golem.calcularPoderDeDisparo());

        // Siendo nivel 2
        golem.aumentarNivel();
        poderDeDisparoEsperado = (byte) (4 * 20 * 2); // Destreza * fuerza * nivel (2)
        assertEquals(poderDeDisparoEsperado, golem.calcularPoderDeDisparo());

        // Siendo nivel 3
        golem.aumentarNivel();
        poderDeDisparoEsperado = (byte) (4 * 20 * 3); // Destreza * fuerza * nivel (3)
        assertEquals(poderDeDisparoEsperado, golem.calcularPoderDeDisparo());

        // Siendo nivel 4 (max)
        golem.aumentarNivel();
        poderDeDisparoEsperado = (byte) (4 * 20 * 4); // Destreza * fuerza * nivel (4)
        assertEquals(poderDeDisparoEsperado, golem.calcularPoderDeDisparo());
    }

    @Test
    @DisplayName("Prueba para verificar si el Golem tarda 3 turnos en atacar")
    void verificarCargaAtaque() {
        Golem golem = new Golem("Juan", "Geodude", "01-01-2000");
        byte turno;
        boolean estaCargando;

        for (turno = 1; turno <= 3; turno++){
            // Método en la clase partida que se utiliza antes de atacar para actualizar el estado de la carga
            estaCargando = golem.golemEstaCargandoAtaque();
            System.out.println("Turno " + turno + ": ¿Está cargando? " + estaCargando);
        }

        // Verificar que el golem pueda atacar al tercer turno
        estaCargando = golem.golemEstaCargandoAtaque();
        System.out.println("Turno " + turno + ": ¿Está cargando? " + estaCargando);
        assertFalse(estaCargando);

        // Verificar que el golem no pueda atacar en el cuarto turno
        estaCargando = golem.golemEstaCargandoAtaque();
        turno++;
        System.out.println("Turno " + turno + ": ¿Está cargando? " + estaCargando);
        assertTrue(estaCargando);
    }
}