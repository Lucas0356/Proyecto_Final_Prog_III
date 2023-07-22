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
                    comprobarAtaque(personajeJ1, personajeJ2);
                    ataquesRealizadosJugador1++;

                    comprobarAtaque(personajeJ2, personajeJ1);
                    ataquesRealizadosJugador2++;
                } else {
                    // Comienza jugador 2
                    comprobarAtaque(personajeJ2, personajeJ1);
                    ataquesRealizadosJugador2++;

                    comprobarAtaque(personajeJ1, personajeJ2);
                    ataquesRealizadosJugador1++;
                }
            }
            // Determinar si hay un ganador de la ronda y actualizar el ganador si es necesario
            if (!personajeJ1.estaVivo()) {
                ronda("Jugador 2 ganó");
            } else if (!personajeJ2.estaVivo()) {
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
    private void comprobarAtaque(Personaje atacante, Personaje defensor) {
        if (atacante.estaVivo()) {
            if (atacante.getRaza() == Raza.Golem) {
                Golem golem = (Golem) atacante; // Convertir atacante a tipo Golem para acceder a su método
                if (golem.golemEstaCargandoAtaque()) {
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("El Golem '" + atacante.getApodo() + "' esta cargando su ataque...");
                    System.out.println("----------------------------------------------------------------");
                    continuar("\nPulse enter para continuar: ");
                } else {
                    realizarAtaque(atacante, defensor);
                }
            } else {
                realizarAtaque(atacante, defensor);
            }
        }
    }
    private void realizarAtaque(Personaje atacante, Personaje defensor) {
        System.out.println("----------------------------------------------------------------");
        System.out.println(atacante.getRaza() + " '" + atacante.getApodo() + "' ataca a " +
                defensor.getRaza() + " '" + defensor.getApodo() + "'");
        byte ataque = atacante.realizarAtaque(defensor);
        defensor.recibirDaño(ataque);
        System.out.println("Le ha provocado " + ataque + " de daño. " + defensor.getApodo() +
                " queda con " + defensor.getSalud() + " de salud.");
        System.out.println("-----------------------------------------------------------------");

        verificarEnfurecimientoOrco(defensor); // Habilidad del orco

        continuar("\nPulse enter para continuar: ");
    }
    private void verificarEnfurecimientoOrco(Personaje defensor) {
        if (defensor.getRaza() == Raza.Orco) {
            Orco orco = (Orco) defensor; // Convertir defensor a tipo Orco para acceder a su método
            orco.incrementarAtaquesRecibidos();
            if (orco.getAtaquesRecibidos() >= 2) {
                orco.activarFerocidad();
                orco.resetearAtaquesRecibidos();
                System.out.println("----------------------------------------------------------------");
                System.out.println("¡El Orco '" + orco.getApodo() + "' se ha enfurecido y hará más daño\nen el próximo ataque!");
                System.out.println("----------------------------------------------------------------");
            }
        }
    }

    // ------------------------------------------------------------------------

    // Sección de lógica de sorteos y aleatoriedad ----------------------------
    private byte sortearJugador() {
        byte num = NumeroAleatorio.generarNumeroAleatorio(2);
        // Se le proporciona 1 o 2, que será el jugador que comienza
        System.out.println("----------------------------------------------------------------");
        if (num == 1) {
            System.out.println("El sistema sorteó al Jugador 1 para iniciar la ronda");
        } else {
            System.out.println("El sistema sorteó al Jugador 2 para iniciar la ronda");
        }
        System.out.println("----------------------------------------------------------------");
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
    private void continuar(String texto) {
        // Imprime un mensaje en la consola y espera a que el usuario presione la tecla Enter para continuar
        Scanner scanner = new Scanner(System.in);
        System.out.println(texto);
        scanner.nextLine();
    }
    private void imprimirPersonajes() {
        for (int i = 0; i < NUM_PERSONAJES; i++) {
            if (jugador1[i] != null) {
                ManejoLogs.recibirLogPartida("\n========================================");
                ManejoLogs.recibirLogPartida("         Personaje " + (i + 1) + " [jugador 1]");
                ManejoLogs.recibirLogPartida("========================================");
                ManejoLogs.recibirLogPartida(String.valueOf(jugador1[i]));
                ManejoLogs.recibirLogPartida("========================================\n");
                continuar("Pulse enter para continuar: ");
            }
            if (jugador2[i] != null) {
                ManejoLogs.recibirLogPartida("========================================");
                ManejoLogs.recibirLogPartida("         Personaje " + (i + 1) + " [jugador 2]");
                ManejoLogs.recibirLogPartida("========================================");
                ManejoLogs.recibirLogPartida(String.valueOf(jugador2[i]));
                ManejoLogs.recibirLogPartida("========================================");
                if (i + 1 != 3) { //Para que no aparezca con el último personaje, puesto que ahí ponemos otro texto.
                    continuar("Pulse enter para continuar: ");
                }
            }
        }
    }
    private void imprimirRonda(String nombrePJ1, String nombrePJ2) {
        System.out.println("\n=====================================================");
        System.out.println("                      RONDA " + rondaActual);
        System.out.println("=====================================================");

        System.out.println("\n----------------------------------------------------------------");
        System.out.println("El sistema eligió al personaje " + nombrePJ1 + " para el jugador 1");
        System.out.println("El sistema eligió al personaje " + nombrePJ2 + " para el jugador 2");
        System.out.println("----------------------------------------------------------------");
    }
    private void imprimirJugadorQueInicia(String jugador) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("El " + jugador + " iniciará la ronda debido a haber perdido la anterior");
        System.out.println("----------------------------------------------------------------");
    }
    private void imprimirGanador(String ganador, Personaje[] jugador) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("================================================================");
        System.out.println("           ¡FELICIDADES " + ganador + " POR TU VICTORIA!");
        System.out.println("================================================================");
        System.out.println("   HAS DEMOSTRADO TU ENORME VALOR Y TE CORONASTE COMO CAMPEÓN.");
        System.out.println("    TU IMPRESIONANTE VICTORIA MARAVILLÓ A LOS PUEBLERINOS");
        System.out.println("            AHORA ELLOS TE VEN COMO EL NUEVO REY.");
        System.out.println(" DESPUÉS DE MUCHO TIEMPO, EL TRONO DE HIERRO TIENE NUEVO DUEÑO");
        System.out.println("             QUE TU GLORIA RESUENE EN LOS CIELOS,");
        System.out.println("              Y TU LEYENDA PERDURE EN EL TIEMPO!");
        System.out.println("================================================================");
        continuar("\nPulse enter para ver el cuadro de honor de los guerreros: ");

        for (int i = 0; i < NUM_PERSONAJES; i++) {
            System.out.println(jugador[i].imprimirEnVictoria(i));
        }
    }
    // ------------------------------------------------------------------------
}