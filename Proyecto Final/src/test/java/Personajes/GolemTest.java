package Personajes;

import Utilidades.ManejoLogs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GolemTest {

    @Test
    @DisplayName("Probar que no se pueda atacar con un personaje muerto")
    public void verificarSiAtacaMuerto() {
        Personaje atacante = new Golem("Juan", "Geodude", "01-01-2000");
        Personaje defensor = new Elfo("Legolas", "Elfo de los Bosques", "01-01-2000");

        // Matamos al personaje
        atacante.recibirDanio((byte) 100);

        // Intentamos realizar el ataque
        atacante.realizarAtaque(defensor);

        // Si la vida del defensor es 100, significa que el atacante no pudo atacar
        assertEquals(100, defensor.getSalud());
    }
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
        ManejoLogs.setGuardarFalse();
        Golem golem = new Golem("Juan", "Geodude", "01-01-2000");
        Personaje defensor = new Elfo("Legolas", "Elfo de los Bosques", "01-01-2000");
        byte turno;
        byte estadoCargando;

        System.out.println("Cuando está en 0 puede atacar.");
        for (turno = 1; turno <= 3; turno++){
            // Realizamos 3 turnos para que cargue el ataque
            golem.realizarAtaque(defensor);
            estadoCargando = golem.getCargaAtaque();
            System.out.println("Turno " + turno + ": Estado de la carga: " + estadoCargando);

            // Verificar que el golem no pueda atacar en 3 turnos
            assertTrue(estadoCargando > 0);
        }

        // Verificar que el golem pueda atacar luego de pasar 3 turnos cargando
        golem.realizarAtaque(defensor);
        estadoCargando = golem.getCargaAtaque();

        System.out.println("Turno " + turno + ": Estado de la carga: " + estadoCargando);
        assertEquals(0,estadoCargando);

        // Verificar que el golem no pueda atacar en el cuarto turno
        golem.realizarAtaque(defensor);
        estadoCargando = golem.getCargaAtaque();
        turno++;

        System.out.println("Turno " + turno + ": Estado de la carga: " + estadoCargando);
        assertTrue(estadoCargando > 0);
    }
}