package Utilidades;

import java.io.*;
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

    // ------------------------------------------------------------------------
}
