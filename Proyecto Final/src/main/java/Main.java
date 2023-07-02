import Partida.*;
import Personajes.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        boolean reiniciar = true;
        Scanner scanner = new Scanner(System.in);
        Partida partidaJuego = new Partida();

        while (reiniciar) {
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
                        PartidaManual();
                        break;
                    case 2:
                        Partida partida = PartidaRapida(partidaJuego);
                        partida.imprimirPersonajes();
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

    static void PartidaManual() {
        System.out.println("\nUsted eligió partida manual.");
        CrearPersonaje();
    }

    static Partida PartidaRapida(Partida partida) {
        System.out.println("\nUsted eligió partida rápida.");
        int contador = 0;
        while (contador < 6) {
            Personaje personaje = CrearPersonajeAleatorio();
            if (contador == 0 || contador == 2 || contador == 4) {
                partida.agregarPersonajeJugador1(personaje);
            } else{
                partida.agregarPersonajeJugador2(personaje);
            }
            contador++;
        }
        return partida;
    }

    static void LeerLogs() {
        System.out.println("\nUsted eligió leer partidas anteriores.");
    }

    static void BorrarLogs() {
        System.out.println("\nUsted eligió borrar partidas anteriores.");
    }

    static void BorrarConsola() {
        // Simula el borrado de la consola imprimiendo una serie de líneas en blanco
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // Creación de personaje ------------------------------------------------
    static void CrearPersonaje() {
        Raza raza = SeleccionarRaza();
        String nombre = IngresarNombre();
        String apodo = IngresarApodo();
        LocalDate fechaDeNacimiento = IngresarFechaNacimiento();
        short edad = CalcularEdad(fechaDeNacimiento);
        switch (raza) {
            case Humano:
                System.out.println("Humano");
                break;
            case Orco:
                System.out.println("orco");
                break;
            case Elfo:
                System.out.println("elfo");
                break;
            case Centauro:
                System.out.println("centauro");
                break;
            case Golem:
                System.out.println("Golem");
                break;
        }
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
    public static LocalDate IngresarFechaNacimiento() {
        Scanner scanner = new Scanner(System.in);
        String fechaString;
        do {
            System.out.println("Ingrese su fecha de nacimiento (formato: YYYY-MM-DD): ");
            fechaString = scanner.nextLine();
            try {
                LocalDate fechaNacimiento = LocalDate.parse(fechaString);
                int edad = CalcularEdad(fechaNacimiento);

                if (edad> 300) {
                    System.out.println("La edad ingresada supera el límite máximo de 300 años. Por favor, ingrésela nuevamente.");
                } else {
                    return fechaNacimiento;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Fecha de nacimiento inválida. Por favor, ingrésela nuevamente.");
            }
        } while (true);
    }
    static short CalcularEdad(LocalDate fechaDeNacimiento){
        LocalDate fechaActual = LocalDate.now();
        Period edad = Period.between(fechaDeNacimiento, fechaActual);
        return (short) edad.getYears();
    }

    // ------------------------------------------------------------------------

    // Creación de personaje aleatoria  ---------------------------------------
    static Personaje CrearPersonajeAleatorio() {
        Raza raza = razaAleatoria();
        String nombre = API.obtenerDatosPersonajeAleatorio()[0];
        String apodo = API.obtenerDatosPersonajeAleatorio()[1];
        LocalDate fechaDeNacimiento = LocalDate.parse(API.obtenerDatosPersonajeAleatorio()[2]);
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
}
