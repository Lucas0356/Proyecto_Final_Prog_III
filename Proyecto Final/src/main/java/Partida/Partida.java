package Partida;

import Personajes.*;
import Utilidades.NumeroAleatorio;
import Utilidades.ManejoLogs;

import java.util.Scanner;

public class Partida {
    private static final int NUM_PERSONAJES = 3;
    private static final int NUM_RONDAS = 7;
    private final Personaje[] jugador1 = new Personaje[3];
    private final Personaje[] jugador2 = new Personaje[3];
    private byte rondaActual = 0;

    // Métodos públicos para interactuar con la partida -----------------------
    public void agregarPersonajeJugador1(Personaje personaje) {
        for (byte contador = 0; contador < jugador1.length; contador++) {
            if (jugador1[contador] == null) {
                jugador1[contador] = personaje;
                break;
            }
        }
    }
    public void agregarPersonajeJugador2(Personaje personaje) {
        for (byte contador = 0; contador < jugador2.length; contador++) {
            if (jugador2[contador] == null) {
                jugador2[contador] = personaje;
                break;
            }
        }
    }
    public void iniciarPartida() {
        ManejoLogs.nuevaPartidaFecha();
        imprimirPersonajes();
        continuar("\nPulse enter para comenzar la partida: ");
        String ganador = ronda("Comenzar");
        if (ganador.equals("JUGADOR 1")){
            imprimirGanador(ganador, jugador1);
        } else {
            imprimirGanador(ganador, jugador2);
        }
        continuar("Pulse enter para volver al menú principal: ");
    }
    public Personaje[] getJugador1() {
        return jugador1;
    }
    public Personaje[] getJugador2() {
        return jugador2;
    }

    // ------------------------------------------------------------------------

    // Sección de lógica de la partida ----------------------------------------
    private String ronda(String ganador) { // Método que representa una ronda de la partida
        while (!todosMuertos(jugador1) && !todosMuertos(jugador2)) {
            // Bucle hasta que algún jugador haya perdido todos sus personajes

            // Seleccionar los personajes para la ronda
            Personaje personajeJ1 = sortearPersonaje(jugador1);
            Personaje personajeJ2 = sortearPersonaje(jugador2);

            rondaActual++;
            imprimirRonda(personajeJ1.getRazaYapodo(), personajeJ2.getRazaYapodo());

            // Definir el jugador que comienza la ronda
            byte num;
            if (ganador.equals("Jugador 2 ganó")) {
                num = 1;
                imprimirJugadorQueInicia("Jugador 1");
            } else if (ganador.equals("Jugador 1 ganó")) {
                num = 2;
                imprimirJugadorQueInicia("Jugador 2");
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
                    personajeJ1.realizarAtaque(personajeJ2);
                    ataquesRealizadosJugador1++;

                    personajeJ2.realizarAtaque(personajeJ1);
                    ataquesRealizadosJugador2++;
                } else {
                    // Comienza jugador 2
                    personajeJ2.realizarAtaque(personajeJ1);
                    ataquesRealizadosJugador2++;

                    personajeJ1.realizarAtaque(personajeJ2);
                    ataquesRealizadosJugador1++;
                }
            }
            // Determinar si hay un ganador de la ronda y actualizar el ganador si es necesario
            if (!personajeJ1.estaVivo()) {
                bonificacionNivel(personajeJ2);
                ronda("Jugador 2 ganó");
            } else if (!personajeJ2.estaVivo()) {
                bonificacionNivel(personajeJ1);
                ronda("Jugador 1 ganó");
            } else {
                ronda("empate");
            }
        }
        if (todosMuertos(jugador1)) { // Retornamos el ganador de la partida
            return ("JUGADOR 2");
        } else {
            return ("JUGADOR 1");
        }
    }
    private boolean todosMuertos(Personaje[] jugador) {
        for (Personaje personaje : jugador) {
            if (personaje.estaVivo()) {
                return false; // Si encuentra un personaje vivo, retorna false
            }
        }
        return true; // Si no encontró personajes vivos, retorna true
    }
    // ------------------------------------------------------------------------

    // Sección de lógica de sorteos y aleatoriedad ----------------------------
    private byte sortearJugador() {
        byte num = NumeroAleatorio.generarNumeroAleatorio(2);
        // Se le proporciona 1 o 2, que será el jugador que comienza
        ManejoLogs.recibirLogPartida("-----------------------------------------------------------------------------");
        if (num == 1) {
            ManejoLogs.recibirLogPartida("El sistema sorteó al Jugador 1 para iniciar la ronda");
        } else {
            ManejoLogs.recibirLogPartida("El sistema sorteó al Jugador 2 para iniciar la ronda");
        }
        ManejoLogs.recibirLogPartida("-----------------------------------------------------------------------------");
        return num;
    }
    private Personaje sortearPersonaje(Personaje[] personajes) {
        int num;
        do {
            num = NumeroAleatorio.generarNumeroAleatorio(3);
        } while (!personajes[num - 1].estaVivo()); // Se repite el proceso mientras el personaje seleccionado esté muerto
        return personajes[num - 1];
    }

    // ------------------------------------------------------------------------

    // Otros métodos auxiliares, utilidades, impresiones, etc. ----------------
    private void bonificacionNivel(Personaje personaje){
        ManejoLogs.recibirLogPartida("------------------------------------------------------------------------------");
        ManejoLogs.recibirLogPartida("El " + personaje.getRazaYapodo() + " ha recibido una bonificación");
        personaje.aumentarNivel();
        ManejoLogs.recibirLogPartida("por derrotar a su oponente, ahora es nivel " + personaje.getNivel());
        ManejoLogs.recibirLogPartida("------------------------------------------------------------------------------");
    }
    public static void continuar(String texto) {
        if (ManejoLogs.getGuardar()){
            // Imprime un mensaje en la consola y espera a que el usuario presione la tecla Enter para continuar
            Scanner scanner = new Scanner(System.in);
            System.out.println(texto);
            scanner.nextLine();
        }
    }
    private void imprimirPersonajes() {
        for (int contador = 0; contador < NUM_PERSONAJES; contador++) {
            if (jugador1[contador] != null) {
                ManejoLogs.recibirLogPartida("\n========================================");
                ManejoLogs.recibirLogPartida("         Personaje " + (contador + 1) + " [jugador 1]");
                ManejoLogs.recibirLogPartida("========================================");
                ManejoLogs.recibirLogPartida(String.valueOf(jugador1[contador]));
                ManejoLogs.recibirLogPartida("========================================\n");
                continuar("Pulse enter para continuar: ");
            }
            if (jugador2[contador] != null) {
                ManejoLogs.recibirLogPartida("========================================");
                ManejoLogs.recibirLogPartida("         Personaje " + (contador + 1) + " [jugador 2]");
                ManejoLogs.recibirLogPartida("========================================");
                ManejoLogs.recibirLogPartida(String.valueOf(jugador2[contador]));
                ManejoLogs.recibirLogPartida("========================================");
                if (contador + 1 != 3) {
                    //Para que no aparezca con el último personaje, puesto que ahí ponemos otro texto.
                    continuar("Pulse enter para continuar: ");
                }
            }
        }
    }
    private void imprimirRonda(String nombrePJ1, String nombrePJ2) {
        ManejoLogs.recibirLogPartida("\n=============================================================================");
        ManejoLogs.recibirLogPartida("                                    RONDA " + rondaActual);
        ManejoLogs.recibirLogPartida("=============================================================================");

        ManejoLogs.recibirLogPartida("\n-----------------------------------------------------------------------------");
        ManejoLogs.recibirLogPartida("El sistema eligió al personaje " + nombrePJ1 + " para el jugador 1");
        ManejoLogs.recibirLogPartida("El sistema eligió al personaje " + nombrePJ2 + " para el jugador 2");
        ManejoLogs.recibirLogPartida("-----------------------------------------------------------------------------");
    }
    private void imprimirJugadorQueInicia(String jugador) {
        ManejoLogs.recibirLogPartida("-----------------------------------------------------------------------------");
        ManejoLogs.recibirLogPartida("El " + jugador + " iniciará la ronda debido a haber perdido la anterior");
        ManejoLogs.recibirLogPartida("-----------------------------------------------------------------------------");
    }
    private void imprimirGanador(String ganador, Personaje[] jugador) {
        ManejoLogs.recibirLogPartida("\n------------------------------------------------------------------------------");
        ManejoLogs.recibirLogPartida("==============================================================================");
        ManejoLogs.recibirLogPartida("                 ¡FELICIDADES " + ganador + " POR TU VICTORIA!");
        ManejoLogs.recibirLogPartida("==============================================================================");
        ManejoLogs.recibirLogPartida("          HAS DEMOSTRADO TU ENORME VALOR Y TE CORONASTE COMO CAMPEÓN.");
        ManejoLogs.recibirLogPartida("             TU IMPRESIONANTE VICTORIA MARAVILLÓ A LOS PUEBLERINOS");
        ManejoLogs.recibirLogPartida("                     AHORA ELLOS TE VEN COMO EL NUEVO REY.");
        ManejoLogs.recibirLogPartida("        DESPUÉS DE MUCHO TIEMPO, EL TRONO DE HIERRO TIENE NUEVO DUEÑO");
        ManejoLogs.recibirLogPartida("                      QUE TU GLORIA RESUENE EN LOS CIELOS,");
        ManejoLogs.recibirLogPartida("                       Y TU LEYENDA PERDURE EN EL TIEMPO!");
        ManejoLogs.recibirLogPartida("==============================================================================");
        ManejoLogs.recibirLogPartida("Imagen del trono de hierro: https://i.ibb.co/RC9vTJj/tronodehierro.jpg");
        continuar("\nPulse enter para ver el cuadro de honor de los guerreros: ");

        for (int i = 0; i < NUM_PERSONAJES; i++) {
            ManejoLogs.recibirLogPartida(jugador[i].imprimirEnVictoria(i));
        }
    }
    // ------------------------------------------------------------------------
}