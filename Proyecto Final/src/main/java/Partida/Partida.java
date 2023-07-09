package Partida;

import Personajes.*;
import java.util.Scanner;

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
        for (int i = 0; i < 3; i++) {
            if (jugador2[i] != null) {
                System.out.println("\n========================================");
                System.out.println("         Personaje " + (i+1) + " [jugador 1]");
                System.out.println("========================================");
                System.out.println(jugador1[i]);
                System.out.println("========================================\n");
                continuar("Pulse enter para continuar: ");
            }
            if (jugador2[i] != null) {
                System.out.println("========================================");
                System.out.println("         Personaje " + (i+1) + " [jugador 2]");
                System.out.println("========================================");
                System.out.println(jugador2[i]);
                System.out.println("========================================");
                continuar("Pulse enter para continuar: ");
            }
        }
    }
    public void iniciarPartida(){
        continuar("\nPulse enter para comenzar la partida: ");
        byte num = NumeroAleatorio.generarNumeroAleatorio(2);
        // Se le proporciona 1 o 2, que será el jugador que comienza.
        if (num == 1) {
            System.out.println("Comienza el jugador 1");
        } else{
            System.out.println("Comienza el jugador 2");
        }
        continuar("Pulse enter para continuar: ");
    }

    private void continuar(String texto){
        Scanner scanner = new Scanner(System.in);
        System.out.println(texto);
        scanner.nextLine();
    }
}