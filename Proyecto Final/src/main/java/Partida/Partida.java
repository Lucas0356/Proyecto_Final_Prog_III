package Partida;

import Personajes.*;
public class Partida {
    private Personaje[] jugador1 = new Personaje[3];
    private Personaje[] jugador2 = new Personaje[3];

    public void agregarPersonajeJugador1(Personaje personaje) {
        for (int i = 0; i < jugador1.length; i++) {
            if (jugador1[i] == null) {
                jugador1[i] = personaje;
                break;
            }
        }
    }
    public void agregarPersonajeJugador2(Personaje personaje) {
        for (int i = 0; i < jugador2.length; i++) {
            if (jugador2[i] == null) {
                jugador2[i] = personaje;
                break;
            }
        }
    }

    public void imprimirPersonajes() {
        System.out.println("Personajes del Jugador 1:");
        for (Personaje personaje : jugador1) {
            if (personaje != null) {
                System.out.println(personaje.toString());
            }
        }

        System.out.println("Personajes del Jugador 2:");
        for (Personaje personaje : jugador2) {
            if (personaje != null) {
                System.out.println(personaje.toString());
            }
        }
    }


}