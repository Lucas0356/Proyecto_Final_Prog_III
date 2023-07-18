package Partida;

import Personajes.*;
import java.util.Scanner;

public class Partida {
    private static final int NUM_PERSONAJES = 3;
    private static final int NUM_RONDAS = 7;
    private Personaje[] jugador1 = new Personaje[3];
    private Personaje[] jugador2 = new Personaje[3];
    private byte rondaActual = 0;

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
        for (int i = 0; i < NUM_PERSONAJES; i++) {
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
        while(!todosMuertos(jugador1) && !todosMuertos(jugador2)){
            ronda();
        }
        System.out.println("JUEGO TERMINADO");
    }

    public void ronda(){
        Personaje personajeJ1 = sortearPersonaje(jugador1);
        Personaje personajeJ2 = sortearPersonaje(jugador2);

        rondaActual++;
        System.out.println("Ronda " + rondaActual);

        System.out.println("El sistema eligió al personaje " + personajeJ1.getRaza() + " para el jugador 1");
        System.out.println("El sistema eligió al personaje " + personajeJ2.getRaza() + " para el jugador 2");

        continuar("\nPulse enter para comenzar la ronda: ");
        byte num = sortearJugador(); // Sorteo quien comienza

        int ataquesRealizadosJugador1 = 0;
        int ataquesRealizadosJugador2 = 0;

        while ((ataquesRealizadosJugador1 < NUM_RONDAS && ataquesRealizadosJugador2 < NUM_RONDAS)
                && personajeJ1.estaVivo() && personajeJ2.estaVivo()) {
            if (num == 1) {
                // Comienza jugador 1
                realizarAtaque(personajeJ1, personajeJ2);
                ataquesRealizadosJugador1++;

                if (personajeJ2.estaVivo()){
                    realizarAtaque(personajeJ2, personajeJ1);
                    ataquesRealizadosJugador2++;
                }

            } else {
                // Comienza jugador 2
                realizarAtaque(personajeJ2, personajeJ1);
                ataquesRealizadosJugador2++;

                if (personajeJ1.estaVivo()) {
                    realizarAtaque(personajeJ1, personajeJ2);
                    ataquesRealizadosJugador1++;
                }
            }
        }
    }

    private void continuar(String texto){
        Scanner scanner = new Scanner(System.in);
        System.out.println(texto);
        scanner.nextLine();
    }
    public byte sortearJugador(){
        byte num = NumeroAleatorio.generarNumeroAleatorio(2);
        // Se le proporciona 1 o 2, que será el jugador que comienza
        if (num == 1) {
            System.out.println("El sistema sorteó al Jugador 1 para iniciar la ronda");
        } else{
            System.out.println("El sistema sorteó al Jugador 2 para iniciar la ronda");
        }
        continuar("\nPulse enter para continuar: ");
        return num;
    }
    public Personaje sortearPersonaje(Personaje[] personajes) {
        int num;
        do {
            num = NumeroAleatorio.generarNumeroAleatorio(3);
        } while (!personajes[num - 1].estaVivo());
        return personajes[num - 1];
    }
    public boolean todosMuertos(Personaje[] jugador) {
        for (Personaje personaje : jugador) {
            if (personaje.estaVivo()) {
                return false; // Si encuentra un personaje vivo, retorna false
            }
        }
        return true; // Si no encontró personajes vivos, retorna true
    }
    private void realizarAtaque(Personaje atacante, Personaje defensor) {
        System.out.println(atacante.getRaza() + " '" + atacante.getApodo() + "' ataca a " +
                defensor.getRaza() + " '" + defensor.getApodo() + "'");
        byte ataque = atacante.calcularAtaque();
        defensor.recibirDaño(ataque);
        System.out.println("Le ha provocado " + ataque + " de daño. " + defensor.getApodo() +
                " queda con " + defensor.getSalud() + " de salud.");
        continuar("\nPulse enter para continuar: ");
    }
}