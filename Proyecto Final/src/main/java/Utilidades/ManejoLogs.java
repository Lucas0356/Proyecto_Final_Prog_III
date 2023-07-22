package Utilidades;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManejoLogs {

    // Atributos --------------------------------------------------------------
    private static final String logs = "logs.txt";
    // ------------------------------------------------------------------------

    // Métodos ----------------------------------------------------------------
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
                e.printStackTrace();
            }
        }
    }
    public static void recibirLogPartida(String log) {
        System.out.println(log); // Mostramos por consola el log de la partida antes de guardarlo
        escribirLog(log);
    }
    private static void escribirLog(String log){
        try {
            FileWriter fileWriter = new FileWriter(logs, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Escribir el log formateado en el archivo
            bufferedWriter.write(log);
            bufferedWriter.newLine(); // Agregamos una línea en blanco para separar cada registro
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void nuevaPartidaFecha(){
        // Obtener la fecha y hora actual
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        // log con la fecha y hora para mostrar de cuando es la partida
        String logFecha = "\n[Partida iniciada el " + fechaHoraActual.format(formatoFechaHora) + "]\n";

        // Escribir el log formateado en el archivo
        escribirLog(logFecha);
    }
    // ------------------------------------------------------------------------
}
