import Partida.*;
import Personajes.*;
import Utilidades.*;
import Utilidades.NumeroAleatorio;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean reiniciar = true;
        Scanner scanner = new Scanner(System.in);
        // Verificar si el archivo de la partida existe, y si no, crearlo
        ManejoLogs.verificarArchivo();

        while (reiniciar) {
            Partida partidaJuego = new Partida();
            System.out.println("==========================================");
            System.out.println("---------------- RPG GAME ----------------");
            System.out.println("     [1] Iniciar partida manual");
            System.out.println("     [2] Iniciar partida rápida");
            System.out.println("     [3] Leer partidas anteriores");
            System.out.println("     [4] Borrar partidas anteriores");
            System.out.println("     [0] Salir");
            System.out.println("==========================================");

            System.out.print("\nIngrese una opción: ");

            try {
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        PartidaManual(partidaJuego);
                        break;
                    case 2:
                        PartidaRapida(partidaJuego);
                        break;
                    case 3:
                        LeerLogs();
                        break;
                    case 4:
                        BorrarLogs();
                        break;
                    case 0:
                        BorrarConsola();
                        System.out.println("\n¡Gracias por jugar!");
                        reiniciar = false;
                        break;
                    default:
                        System.out.println("\nNo ingresó una opción válida.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nNo ingresó una opción válida.\n");
                scanner.nextLine();
            }
        }
    }

    // ------------------------------------------------------------------------
    static void PartidaManual(Partida partida) {
        System.out.println("\nUsted eligió partida manual.");
        final int NUM_PERSONAJES_POR_JUGADOR = 3;

        for (int i = 0; i < NUM_PERSONAJES_POR_JUGADOR * 2; i++) {
            System.out.println("\n----------------------------------------------------------------");
            if (i % 2 == 0) {
                System.out.println("Defina el personaje número " + (i / 2 + 1) + " del Jugador 1");
                partida.agregarPersonajeJugador1(CrearPersonaje());
            } else {
                System.out.println("Defina el personaje número " + (i / 2 + 1) + " del Jugador 2");
                partida.agregarPersonajeJugador2(CrearPersonaje());
            }
        }

        partida.iniciarPartida();
    }
    static void PartidaRapida(Partida partida) {
        System.out.println("\nUsted eligió partida rápida.");
        System.out.println("Por favor espere mientras se crean los personajes...");
        final int NUM_PERSONAJES_POR_JUGADOR = 3;

        for (int contador = 0; contador < NUM_PERSONAJES_POR_JUGADOR * 2; contador++) {
            Personaje personaje = CrearPersonajeAleatorio();
            if (contador % 2 == 0) {
                partida.agregarPersonajeJugador1(personaje);
            } else {
                partida.agregarPersonajeJugador2(personaje);
            }
        }
        partida.iniciarPartida();
    }
    static void LeerLogs() {
        System.out.println("\nUsted eligió leer partidas anteriores.");
        seleccionarPartidaParaLectura();
    }
    static void BorrarLogs() {
        System.out.println("\nUsted eligió borrar partidas anteriores.");
        if (preguntarSiBorrar()){
            ManejoLogs.borrarLogs();
        }
    }
    // ------------------------------------------------------------------------

    // Creación de personaje --------------------------------------------------
    static Personaje CrearPersonaje() {
        Raza raza = SeleccionarRaza();
        String nombre = IngresarNombre();
        String apodo = IngresarApodo();
        String fechaDeNacimiento = IngresarFechaNacimiento();
        short edad = CalcularEdad(fechaDeNacimiento);
        switch (raza) {
            case Humano:
                return new Humano(raza,nombre,apodo,fechaDeNacimiento,edad);
            case Orco:
                return new Orco(raza,nombre,apodo,fechaDeNacimiento,edad);
            case Elfo:
                return new Elfo(raza,nombre,apodo,fechaDeNacimiento,edad);
            case Centauro:
                return new Centauro(raza,nombre,apodo,fechaDeNacimiento,edad);
            case Golem:
                return new Golem(raza,nombre,apodo,fechaDeNacimiento,edad);
        }
        return null;
    }
    static Raza SeleccionarRaza() {
        Scanner scanner = new Scanner(System.in);
        String opcion = "";
        Raza raza = null;

        while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4") && !opcion.equals("5")) {
            System.out.println("\nSeleccione la raza del personaje: ");
            System.out.println("[1] Humano");
            System.out.println("[2] Orco");
            System.out.println("[3] Elfo");
            System.out.println("[4] Centauro");
            System.out.println("[5] Golem");
            System.out.println("Ingrese una opción: ");
            opcion = scanner.nextLine();
        }
        switch (opcion) {
            case "1":
                raza = Raza.Humano;
                break;
            case "2":
                raza = Raza.Orco;
                break;
            case "3":
                raza = Raza.Elfo;
                break;
            case "4":
                raza = Raza.Centauro;
                break;
            case "5":
                raza = Raza.Golem;
                break;
        }
        return raza;
    }
    static String IngresarNombre() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del personaje: ");
        String nombre = scanner.nextLine();

        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío. Por favor, ingréselo nuevamente.");
            return IngresarNombre(); // Llamada recursiva para solicitar el nombre nuevamente
        }
        return nombre;
    }
    static String IngresarApodo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el apodo del personaje: ");
        String apodo = scanner.nextLine();

        if (apodo.trim().isEmpty()) {
            System.out.println("El apodo no puede estar vacío. Por favor, ingréselo nuevamente.");
            return IngresarNombre(); // Llamada recursiva para solicitar el apodo nuevamente
        }
        return apodo;
    }
    public static String IngresarFechaNacimiento() {
        Scanner scanner = new Scanner(System.in);
        String fechaString;
        do {
            System.out.println("Ingrese su fecha de nacimiento (formato: DD-MM-YYYY): ");
            fechaString = scanner.nextLine();
            LocalDate fechaDeNacimiento = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if (fechaDeNacimiento != null) {
                int edad = CalcularEdad(fechaString);
                if (edad > 300) {
                    System.out.println("La edad ingresada supera el límite máximo de 300 años. Por favor, ingrésela nuevamente.");
                } else {
                    return fechaString;
                }
            }
        } while (true);
    }
    // ------------------------------------------------------------------------

    // Creación de personaje aleatoria  ---------------------------------------
    static Personaje CrearPersonajeAleatorio() {
        Raza raza = razaAleatoria();
        String[] datosPersonajeAleatorio = API.obtenerDatosPersonajeAleatorio();
        String nombre = datosPersonajeAleatorio[0];
        String apodo = datosPersonajeAleatorio[1];
        String fechaDeNacimiento = datosPersonajeAleatorio[2];

        short edad = CalcularEdad(fechaDeNacimiento);
        switch (raza) {
            case Humano:
                return new Humano(raza,nombre,apodo,fechaDeNacimiento,edad);
            case Orco:
                return new Orco(raza,nombre,apodo,fechaDeNacimiento,edad);
            case Elfo:
                return new Elfo(raza,nombre,apodo,fechaDeNacimiento,edad);
            case Centauro:
                return new Centauro(raza,nombre,apodo,fechaDeNacimiento,edad);
            case Golem:
                return new Golem(raza,nombre,apodo,fechaDeNacimiento,edad);
        }
        return null;
    }
    static Raza razaAleatoria() {
        Raza raza = null;
        byte numeroAleatorio = NumeroAleatorio.generarNumeroAleatorio(5);
        switch (numeroAleatorio) {
            case 1:
                raza = Raza.Humano;
                break;
            case 2:
                raza = Raza.Orco;
                break;
            case 3:
                raza = Raza.Elfo;
                break;
            case 4:
                raza = Raza.Centauro;
                break;
            case 5:
                raza = Raza.Golem;
                break;
        }
        return raza;
    }
    // ------------------------------------------------------------------------

    // Otros métodos auxiliares, utilidades, impresiones, etc. ----------------
    static void BorrarConsola() {
        // Simula el borrado de la consola imprimiendo una serie de líneas en blanco
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    static short CalcularEdad(String fechaDeNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaNacimiento = LocalDate.parse(fechaDeNacimiento);
        Period edad = Period.between(fechaNacimiento, fechaActual);
        return (short) edad.getYears();
    }
    static boolean preguntarSiBorrar() {
        Scanner scanner = new Scanner(System.in);
        String opcion = "";

        while (!opcion.equals("1") && !opcion.equals("0")) {
            System.out.println("\n¿Está seguro de que desea borrar todas las partidas anteriores?");
            System.out.println("[1] Sí");
            System.out.println("[0] Volver al menú");
            System.out.println("Ingrese una opción: ");
            opcion = scanner.nextLine();
        }
        return opcion.equals("1"); // Si es 1 devolverá true
    }
    static void seleccionarPartidaParaLectura(){
        // Mostrar lista de partidas disponibles
        if (ManejoLogs.mostrarListaPartidas()){
            // Preguntar al usuario qué partida desea leer
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nIngrese el número de partida que desea leer: ");
            int numeroPartida = scanner.nextInt();

            // Leer la partida seleccionada
            ManejoLogs.leerPartida(numeroPartida);
        }
    }
    // ------------------------------------------------------------------------
}