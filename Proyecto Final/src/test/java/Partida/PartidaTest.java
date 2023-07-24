package Partida;

import Personajes.*;

import Utilidades.ManejoLogs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartidaTest {
    private Partida partida;
    @Test
    @DisplayName("Prueba agregar personajes a los jugadores")
    void agregarPersonajeJugadorTest() {
        partida = new Partida();
        // Creamos los personajes de ambos jugadores
        Personaje personajeJ1N1 = new Elfo("Legolas", "Elfo de los Bosques", "01-01-2000");
        Personaje personajeJ2N1 = new Humano("Carlos", "Carlitos", "02-02-2000");
        Personaje personajeJ1N2 = new Centauro("Pedro", "Pepito", "01-01-2000");
        Personaje personajeJ2N2 = new Golem("Juan", "Geodude", "01-01-2000");
        Personaje personajeJ1N3 = new Orco("Grug", "Gruco", "01-01-2000");
        Personaje personajeJ2N3 = new Humano("Lucas", "Lukitas", "06-05-2003");

        // Agregamos los personajes del jugador N1
        partida.agregarPersonajeJugador1(personajeJ1N1);
        partida.agregarPersonajeJugador1(personajeJ1N2);
        partida.agregarPersonajeJugador1(personajeJ1N3);

        // Verificamos que sean los mismos
        assertEquals(personajeJ1N1, partida.getJugador1()[0]);
        assertEquals(personajeJ1N2, partida.getJugador1()[1]);
        assertEquals(personajeJ1N3, partida.getJugador1()[2]);

        // Agregamos los personajes del jugador N2
        partida.agregarPersonajeJugador2(personajeJ2N1);
        partida.agregarPersonajeJugador2(personajeJ2N2);
        partida.agregarPersonajeJugador2(personajeJ2N3);

        // Verificamos que sean los mismos
        assertEquals(personajeJ2N1, partida.getJugador2()[0]);
        assertEquals(personajeJ2N2, partida.getJugador2()[1]);
        assertEquals(personajeJ2N3, partida.getJugador2()[2]);
    }
    @Test
    @DisplayName("Prueba partida completa")
    void testPartidaCompleta() {
        ManejoLogs.setGuardarFalse();
        partida = new Partida();
        // Creamos los personajes de ambos jugadores
        Personaje personajeJ1N1 = new Elfo("Legolas", "Elfo de los Bosques", "01-01-2000");
        Personaje personajeJ1N2 = new Centauro("Pedro", "Pepito", "01-01-2000");
        Personaje personajeJ1N3 = new Orco("Grug", "Gruco", "01-01-2000");

        Personaje personajeJ2N1 = new Humano("Carlos", "Carlitos", "02-02-2000");
        Personaje personajeJ2N2 = new Golem("Juan", "Geodude", "01-01-2000");
        Personaje personajeJ2N3 = new Humano("Lucas", "Lukitas", "06-05-2003");

        // Agregamos los personajes del jugador N1
        partida.agregarPersonajeJugador1(personajeJ1N1);
        partida.agregarPersonajeJugador1(personajeJ1N2);
        partida.agregarPersonajeJugador1(personajeJ1N3);

        // Agregamos los personajes del jugador N2
        partida.agregarPersonajeJugador2(personajeJ2N1);
        partida.agregarPersonajeJugador2(personajeJ2N2);
        partida.agregarPersonajeJugador2(personajeJ2N3);

        // Iniciar partida
        partida.iniciarPartida();

        // Verificar que se haya determinado un ganador correctamente
        Personaje[] jugador1 = partida.getJugador1();
        Personaje[] jugador2 = partida.getJugador2();

        int cantidadVivosJugador1 = 0;
        int cantidadVivosJugador2 = 0;

        // Contar la cantidad de personajes vivos de cada jugador al final de la partida
        for (Personaje personaje : jugador1) {
            if (personaje.estaVivo()) {
                cantidadVivosJugador1++;
            }
        }
        for (Personaje personaje : jugador2) {
            if (personaje.estaVivo()) {
                cantidadVivosJugador2++;
            }
        }

        // Verificar que todos los personajes de un jugador estén muertos y el otro jugador tenga al menos un personaje vivo
        if (cantidadVivosJugador1 == 0) {
            assertEquals(0, cantidadVivosJugador1);
            assertTrue(cantidadVivosJugador2 > 0);
        } else {
            assertTrue(cantidadVivosJugador1 > 0);
            assertEquals(0, cantidadVivosJugador2);
        }
    }
}
