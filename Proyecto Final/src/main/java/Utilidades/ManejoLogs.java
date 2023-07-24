package Utilidades;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManejoLogs {

    // Atributos --------------------------------------------------------------
    private static final String logs = "logs.txt";
    private static boolean guardar = true; // Para que al hacer tests no se escriban en el archivo logs.txt
    // ------------------------------------------------------------------------

    // Métodos públicos para interactuar con la clase -------------------------
    public static void verificarArchivo() {
        // Creamos un objeto de tipo File para representar el archivo "logs.txt"
        File archivo = new File(logs);

        // Verificamos si el archivo existe en el directorio de trabajo
        if (!archivo.exists()) {
            try {
                // Si el archivo no existe, intentamos crearlo con el método createNewFile()
                // El método createNewFile() devuelve true si se crea exitosamente, o false si no se pudo crear
                boolean creadoExitosamente = archivo.createNewFile();

                // Mostramos un mensaje para indicar si se creó el archivo correctamente en caso de no existir
                if (creadoExitosamente) {
                    System.out.println("Archivo de logs creado exitosamente.");
                }
            } catch (IOException e) {
                // Manejo de la excepción si ocurre un error durante la creación del archivo
                System.out.println("Ha ocurrido un error durante la creación del archivo: " + e.getMessage());
            }
        }
    }
    public static void recibirLogPartida(String log) {
        if (guardar){
            System.out.println(log);
            escribirLog(log);
        }
    }
    public static void nuevaPartidaFecha(){
        if (guardar){
            // Obtener la fecha y hora actual
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            // log con la fecha y hora para mostrar de cuando es la partida
            String logFecha = "\n[Partida iniciada el " + fechaHoraActual.format(formatoFechaHora) + "]\n";

            // Escribir el log formateado en el archivo
            escribirLog(logFecha);
        }
    }
    public static byte mostrarListaPartidas() {
        byte partidasEncontradas = 0;
        try {
            FileReader fileReader = new FileReader(logs);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linea;
            byte numeroPartida = 1;

            System.out.println("Lista de partidas disponibles:\n");
            while ((linea = bufferedReader.readLine()) != null) {
                if (linea.startsWith("[Partida iniciada el ")) {
                    System.out.println(numeroPartida + ". " + linea);
                    numeroPartida++;
                    partidasEncontradas++;
                }
            }
            bufferedReader.close();

            if (partidasEncontradas == 0) {
                System.out.println("No se encontraron partidas registradas.\n");
            }
        } catch (IOException e) {
            // Manejo de la excepción si ocurre un error durante la lectura del archivo
            System.out.println("Ha ocurrido un error al intentar leer el archivo 'logs.txt': " + e.getMessage());
        }
        return partidasEncontradas; // Nos devuelve la cantidad de partidas
    }
    public static void leerPartida(int numeroPartida) {
        try {
            // Abrimos el archivo "logs.txt" para lectura
            BufferedReader bufferedReader = new BufferedReader(new FileReader(logs));

            // Buscamos y mostramos el contenido de la partida solicitada
            boolean encontrada = mostrarContenidoPartida(bufferedReader, numeroPartida);

            bufferedReader.close();

            // Si no se encontró la partida, mostramos un mensaje informativo
            if (!encontrada) {
                System.out.println("\nLa partida con el número " + numeroPartida + " no fue encontrada.\n");
            }
        } catch (IOException e) {
            // Manejo de la excepción si ocurre un error durante la lectura del archivo
            System.out.println("Ha ocurrido un error al intentar leer el archivo 'logs.txt': " + e.getMessage());
        }
    }
    public static void borrarLogs(){
        try {
            // Abrimos el archivo "logs.txt" para escritura, creando uno nuevo en blanco
            // append: false --> cualquier contenido previo en el archivo será borrado.
            FileWriter fileWriter = new FileWriter(logs, false);

            // Utilizamos BufferedWriter para escribir en el archivo de manera eficiente
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribimos una línea vacía para borrar el contenido del archivo
            // Como el archivo se abrió en modo de escritura sin append (false)
            // el contenido anterior se reemplazará por esta línea vacía.
            bufferedWriter.write("");

            // Cerramos el BufferedWriter
            bufferedWriter.close();

            System.out.println("Todas las partidas han sido borradas.\n");
        } catch (IOException e) {
            // Manejo de la excepción si ocurre un error durante la escritura del archivo
            System.out.println("Ha ocurrido un error al intentar borrar el archivo 'logs.txt': " + e.getMessage());
        }
    }
    public static void setGuardarFalse() {
        guardar = false; // Para que al hacer tests no se escriban en el archivo logs.txt
    }
    public static boolean getGuardar() {
        return guardar;
    }
    // ------------------------------------------------------------------------

    // Métodos privados complementarios----------------------------------------
    private static void escribirLog(String log){
        try {
            // Abrimos el archivo "logs.txt" para escritura
            // append: true --> Los nuevos datos se agregarán al final del archivo.
            FileWriter fileWriter = new FileWriter(logs, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribir el log en el archivo
            bufferedWriter.write(log);

            // Agregamos una línea en blanco para separar cada registro
            bufferedWriter.newLine();

            // Cerramos el BufferedWriter
            bufferedWriter.close();
        } catch (IOException e) {
            // Manejo de la excepción si ocurre un error durante la escritura del archivo
            System.out.println("Ha ocurrido un error al intentar la escritura en el archivo: " + e.getMessage());
        }
    }
    private static boolean mostrarContenidoPartida(BufferedReader bufferedReader, int numeroPartida) throws IOException {
        String linea;
        boolean encontrada = false;
        int partidaActual = 0;

        while ((linea = bufferedReader.readLine()) != null) {
            if (linea.startsWith("[Partida iniciada el ")) {
                partidaActual++;
                if (partidaActual == numeroPartida) {
                    encontrada = true;
                    System.out.println("\nContenido de la Partida " + numeroPartida + ":");
                } else if (encontrada) {
                    // Si ya se encontró la partida deseada, se detiene el bucle
                    break;
                }
            } else if (encontrada) {
                // Si encontramos una línea que no inicia con "[Partida iniciada el", y ya se encontró
                // la partida deseada, la imprimimos, ya que pertenece a la partida buscada.
                System.out.println(linea);
            }
        }
        return encontrada;
    }
    // ------------------------------------------------------------------------
}
