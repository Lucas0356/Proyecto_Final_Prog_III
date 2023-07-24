import Partida.*;
import Personajes.*;
import Utilidades.*;
import Utilidades.NumeroAleatorio;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Verificar si el archivo de la partida existe, y si no, crearlo
        ManejoLogs.verificarArchivo();

        boolean reiniciar = true;

        while (reiniciar) {
            Partida partidaJuego = new Partida();
            int opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    partidaManual(partidaJuego);
                    break;
                case 2:
                    partidaRapida(partidaJuego);
                    break;
                case 3:
                    leerLogs();
                    break;
                case 4:
                    borrarLogs();
                    break;
                case 5:
                    mostrarInformacionJuego();
                    break;
                case 0:
                    borrarConsola();
                    System.out.println("\n¡Gracias por jugar!");
                    reiniciar = false;
                    break;
                default:
                    System.out.println("\nNo ingresó una opción válida.\n");
                    break;
            }
        }
    }
    // ------------------------------------------------------------------------
    private static void partidaManual(Partida partida) {
        System.out.println("\nUsted eligió partida manual.");
        final int NUM_PERSONAJES_POR_JUGADOR = 3;

        for (int i = 0; i < NUM_PERSONAJES_POR_JUGADOR * 2; i++) {
            System.out.println("\n----------------------------------------------------------------");
            if (i % 2 == 0) {
                System.out.println("Defina el personaje número " + (i / 2 + 1) + " del Jugador 1");
                partida.agregarPersonajeJugador1(crearPersonaje());
            } else {
                System.out.println("Defina el personaje número " + (i / 2 + 1) + " del Jugador 2");
                partida.agregarPersonajeJugador2(crearPersonaje());
            }
        }

        partida.iniciarPartida();
    }
    private static void partidaRapida(Partida partida) {
        System.out.println("\nUsted eligió partida rápida.");
        System.out.println("Por favor espere mientras se crean los personajes...");
        final int NUM_PERSONAJES_POR_JUGADOR = 3;

        for (int contador = 0; contador < NUM_PERSONAJES_POR_JUGADOR * 2; contador++) {
            Personaje personaje = crearPersonajeAleatorio();
            if (contador % 2 == 0) {
                partida.agregarPersonajeJugador1(personaje);
            } else {
                partida.agregarPersonajeJugador2(personaje);
            }
        }
        partida.iniciarPartida();
    }
    private static void leerLogs() {
        System.out.println("\nUsted eligió leer partidas anteriores.");
        seleccionarPartidaParaLectura();
    }
    private static void borrarLogs() {
        System.out.println("\nUsted eligió borrar partidas anteriores.");
        if (preguntarSiBorrar()){
            ManejoLogs.borrarLogs();
        }
    }
    private static void mostrarInformacionJuego() {
        System.out.println("================================================================================");
        System.out.println("              LA CONQUISTA DEL TRONO DE HIERRO - JUEGO DE ROL");
        System.out.println("================================================================================");
        System.out.println("¡Bienvenido a La Conquista del Trono de Hierro, un emocionante juego de rol para");
        System.out.println("2 jugadores! Cada jugador dispondrá de 3 valientes personajes para la batalla, cada");
        System.out.println("uno con habilidades únicas y estadísticas especiales.");

        // Información sobre los personajes disponibles
        System.out.println("Los personajes disponibles son:");
        System.out.println(" - Los valientes humanos y los poderosos centauros, quienes son los más equilibrados,");
        System.out.println("   poseen estadísticas balanceadas que los hacen versátiles en el combate.");
        System.out.println(" - Los feroces orcos, que pueden enfurecerse después de recibir 2 ataques,");
        System.out.println("   infligiendo 50% más de daño en el siguiente turno.");
        System.out.println(" - Los ágiles elfos, expertos en el poder mágico, capaces de derrotar a personajes");
        System.out.println("   altamente defensivos con facilidad, debido a que no usan la fuerza física.");
        System.out.println(" - Los imponentes golems, quienes deben prepararse durante 3 turnos para realizar un");
        System.out.println("   ataque devastador.");

        System.out.println("Cada vez que un personaje derrote a un oponente en combate, recibirá un nivel extra,");
        System.out.println("haciendo que sus ataques sean aún más poderosos.");
        System.out.println("Tu misión en este fascinante mundo de fantasía es enfrentarte a otro jugador y");
        System.out.println("decidir quién será el digno heredero del Trono de Hierro, convirtiéndose en el");
        System.out.println("monarca del reino.");
        System.out.println("El destino de esta tierra está en tus manos. ¡Buena suerte en tu aventura épica!");
        System.out.println("================================================================================");

        // Mostrar links de las cartas de los personajes
        Partida.continuar("\nPulse enter para ver las cartas de los personajes: ");
        System.out.println("Carta Humano: https://i.ibb.co/HP77GtJ/humano.png");
        System.out.println("Carta Centauro: https://i.ibb.co/yW0bY2L/centauro.png");
        System.out.println("Carta Elfo: https://i.ibb.co/kcKBrdx/elfo.png");
        System.out.println("Carta Golem: https://i.ibb.co/28q4Qz3/golem.png");
        System.out.println("Carta Orco: https://i.ibb.co/8YMm8xS/orco.png");

        Partida.continuar("\nPulse enter para volver al menú principal: ");
    }
    // ------------------------------------------------------------------------

    // Creación de personaje --------------------------------------------------
    private static Personaje crearPersonaje() {
        Raza raza = seleccionarRaza();
        String nombre = ingresarNombre();
        String apodo = ingresarApodo();
        String fechaDeNacimiento = ingresarFechaNacimiento();
        switch (raza) {
            case Humano:
                return new Humano(nombre,apodo,fechaDeNacimiento);
            case Orco:
                return new Orco(nombre,apodo,fechaDeNacimiento);
            case Elfo:
                return new Elfo(nombre,apodo,fechaDeNacimiento);
            case Centauro:
                return new Centauro(nombre,apodo,fechaDeNacimiento);
            case Golem:
                return new Golem(nombre,apodo,fechaDeNacimiento);
        }
        return null;
    }
    private static Raza seleccionarRaza() {
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
    private static String ingresarNombre() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del personaje: ");
        String nombre = scanner.nextLine();

        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío. Por favor, ingréselo nuevamente.");
            return ingresarNombre(); // Llamada recursiva para solicitar el nombre nuevamente
        }
        return nombre;
    }
    private static String ingresarApodo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el apodo del personaje: ");
        String apodo = scanner.nextLine();

        if (apodo.trim().isEmpty()) {
            System.out.println("El apodo no puede estar vacío. Por favor, ingréselo nuevamente.");
            return ingresarNombre(); // Llamada recursiva para solicitar el apodo nuevamente
        }
        return apodo;
    }
    private static String ingresarFechaNacimiento() {
        Scanner scanner = new Scanner(System.in);
        String fechaString;

        do {
            System.out.println("Ingrese su fecha de nacimiento (formato: DD-MM-YYYY): ");
            fechaString = scanner.nextLine();

            try {
                int edad = Personaje.calcularEdad(fechaString);
                if (edad > 300) {
                    System.out.println("La edad ingresada supera el límite máximo de 300 años. Por favor, ingrésela nuevamente.");
                } else {
                    return fechaString;
                }
            } catch (Exception e) {
                System.out.println("Formato de fecha incorrecto. Por favor, ingrésela nuevamente.");
            }
        } while (true);
    }
    // ------------------------------------------------------------------------

    // Creación de personaje aleatoria  ---------------------------------------
    private static Personaje crearPersonajeAleatorio() {
        Raza raza = razaAleatoria();
        String[] datosPersonajeAleatorio = API.obtenerDatosPersonajeAleatorio();
        String nombre = datosPersonajeAleatorio[0];
        String apodo = datosPersonajeAleatorio[1];
        String fechaDeNacimiento = datosPersonajeAleatorio[2];

        switch (raza) {
            case Humano:
                return new Humano(nombre,apodo,fechaDeNacimiento);
            case Orco:
                return new Orco(nombre,apodo,fechaDeNacimiento);
            case Elfo:
                return new Elfo(nombre,apodo,fechaDeNacimiento);
            case Centauro:
                return new Centauro(nombre,apodo,fechaDeNacimiento);
            case Golem:
                return new Golem(nombre,apodo,fechaDeNacimiento);
        }
        return null;
    }
    private static Raza razaAleatoria() {
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
    private static int mostrarMenu() {
        System.out.println("================================================");
        System.out.println("------- LA CONQUISTA DEL TRONO DE HIERRO -------");
        System.out.println("      [1] Iniciar partida manual");
        System.out.println("      [2] Iniciar partida rápida");
        System.out.println("      [3] Leer partidas anteriores");
        System.out.println("      [4] Borrar partidas anteriores");
        System.out.println("      [5] Mostrar información del juego");
        System.out.println("      [0] Salir");
        System.out.println("================================================");

        System.out.print("\nIngrese una opción: ");

        Scanner scanner = new Scanner(System.in);

        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de leer el número
            return opcion;
        } catch (InputMismatchException e) {
            System.out.println("\nNo ingresó una opción válida.\n");
            scanner.nextLine();
            return -1; // Retorna -1 para indicar que hubo un error en la selección
        }
    }
    private static void borrarConsola() {
        // Simula el borrado de la consola imprimiendo una serie de líneas en blanco
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    private static boolean preguntarSiBorrar() {
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
    private static void seleccionarPartidaParaLectura() {
        byte numeroPartida;
        // Mostrar lista de partidas disponibles y retornar la cantidad de las mismas

        byte cantidadDePartidas = ManejoLogs.mostrarListaPartidas();
        if (cantidadDePartidas != 0) { // Verificamos que existan partidas
            System.out.println("0. [Para salir]");
            try {
                Scanner scanner = new Scanner(System.in);
                do {
                    // Preguntar al usuario qué partida desea leer
                    System.out.print("\nIngrese el número de partida que desea leer: ");
                    numeroPartida = scanner.nextByte();
                } while (numeroPartida > cantidadDePartidas || numeroPartida < 0); // Verificar que esté en el rango
                // Leer la partida seleccionada
                if (numeroPartida == 0){
                    return;
                }
                ManejoLogs.leerPartida(numeroPartida);
            } catch (InputMismatchException e) {
                System.out.println("\nDato ingresado inválido. Por favor, vuelva a intentarlo.\n");
            }
        }
    }
    // ------------------------------------------------------------------------
}