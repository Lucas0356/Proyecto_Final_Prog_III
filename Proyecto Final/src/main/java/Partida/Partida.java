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
            if (jugador1[i] != null) {
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
                if (i+1 != 3){ //Para que no aparezca con el último personaje, puesto que ahí ponemos otro texto.
                    continuar("Pulse enter para continuar: ");
                }
            }
        }
    }
    public void iniciarPartida(){
        imprimirPersonajes();
        continuar("\nPulse enter para comenzar la partida: ");
        String ganador = ronda("empezar");
        System.out.println("JUEGO TERMINADO");
        System.out.println("El ganador es: " + ganador);
        continuar("Pulse enter para volver al menú principal: ");
    }

    public String ronda(String ganador){
        while(!todosMuertos(jugador1) && !todosMuertos(jugador2)) {
            Personaje personajeJ1 = sortearPersonaje(jugador1);
            Personaje personajeJ2 = sortearPersonaje(jugador2);
            byte num;

            rondaActual++;
            System.out.println("\n=====================================================");
            System.out.println("                    RONDA " + rondaActual);
            System.out.println("=====================================================");

            System.out.println("\n----------------------------------------------------------------");
            System.out.println("El sistema eligió al personaje " + personajeJ1.getRaza() + " para el jugador 1");
            System.out.println("El sistema eligió al personaje " + personajeJ2.getRaza() + " para el jugador 2");
            System.out.println("----------------------------------------------------------------");

            if (ganador.equals("Jugador 2 ganó")) {
                num = 1;
                System.out.println("----------------------------------------------------------------");
                System.out.println("El Jugador 1 iniciará la ronda debido a haber perdido la anterior");
                System.out.println("----------------------------------------------------------------");
            } else if (ganador.equals("Jugador 1 ganó")) {
                num = 2;
                System.out.println("----------------------------------------------------------------");
                System.out.println("El Jugador 2 iniciará la ronda debido a haber perdido la anterior");
                System.out.println("----------------------------------------------------------------");
            } else {
                num = sortearJugador(); // Sorteo quien comienza
            }

            continuar("\nPulse enter para comenzar la ronda: ");


            int ataquesRealizadosJugador1 = 0;
            int ataquesRealizadosJugador2 = 0;

            while ((ataquesRealizadosJugador1 < NUM_RONDAS && ataquesRealizadosJugador2 < NUM_RONDAS)
                    && personajeJ1.estaVivo() && personajeJ2.estaVivo()) {
                if (num == 1) {
                    // Comienza jugador 1
                    realizarAtaque(personajeJ1, personajeJ2);
                    ataquesRealizadosJugador1++;

                    if (personajeJ2.estaVivo()) {
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
            if (!personajeJ1.estaVivo()) { // Llamado recursivo hasta que no se cumplan condiciones del bucle.
                ronda("Jugador 2 ganó");
            } else if (!personajeJ2.estaVivo()) {
                ronda("Jugador 1 ganó");
            } else {
                ronda("empate");
            }
        }
        if (todosMuertos(jugador1)) { // Retornamos el ganador de la partida
            return ("Jugador 2");
        } else {
            return ("Jugador 1");
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
        System.out.println("----------------------------------------------------------------");
        if (num == 1) {
            System.out.println("El sistema sorteó al Jugador 1 para iniciar la ronda");
        } else{
            System.out.println("El sistema sorteó al Jugador 2 para iniciar la ronda");
        }
        System.out.println("----------------------------------------------------------------");
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
        System.out.println("----------------------------------------------------------------");
        System.out.println(atacante.getRaza() + " '" + atacante.getApodo() + "' ataca a " +
                defensor.getRaza() + " '" + defensor.getApodo() + "'");
        byte ataque = atacante.calcularAtaque();
        defensor.recibirDaño(ataque);
        System.out.println("Le ha provocado " + ataque + " de daño. " + defensor.getApodo() +
                " queda con " + defensor.getSalud() + " de salud.");
        System.out.println("-----------------------------------------------------------------");
        continuar("\nPulse enter para continuar: ");
    }
}