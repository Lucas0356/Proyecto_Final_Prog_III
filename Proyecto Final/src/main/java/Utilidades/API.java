package Utilidades;

import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;  // Importar la clase Scanner para leer datos de la API

public class API {
    public static String[] obtenerDatosPersonajeAleatorio() {
        String[] datos = new String[3];  // Crear un arreglo para almacenar los datos del personaje
        try {
            String nombre;
            String apodo;
            String fechaConFormato;
            do {
                JSONObject jsonObject = obtenerDatosAPI();  // Obtener los datos del personaje desde la API
                String nombreCompleto = jsonObject.getString("name");  // Obtener el nombre completo del objeto JSON
                // Extraer el primer nombre del nombre completo
                String[] partesNombre = nombreCompleto.split("\\s+");  // Dividir el nombre completo en partes separadas por espacios
                nombre = partesNombre[0];  // Obtener el primer nombre
                apodo = jsonObject.getString("username");  // Obtener el apodo del personaje desde el objeto JSON
                String fechaNacimiento = jsonObject.getString("birth_data");  // Obtener la fecha de nacimiento desde el objeto JSON
                LocalDate fechaDeNacimiento = LocalDate.parse(fechaNacimiento);
                fechaConFormato = fechaDeNacimiento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } while (esNombreNoDeseado(nombre));  // Repetir hasta obtener un nombre deseado
            // Establecer los datos obtenidos en el arreglo
            datos[0] = nombre;
            datos[1] = apodo;
            datos[2] = fechaConFormato;
        } catch (Exception e) {
            // En caso de excepción, establecer datos genéricos
            datos[0] = "Sin nombre";
            datos[1] = "Sin apodo";
            datos[2] = "01-01-2000";
        }
        return datos;  // Devolver los datos del personaje
    }

    // Método privado para obtener los datos de la API
    private static JSONObject obtenerDatosAPI() throws IOException {
        URL url = new URL("https://api.namefake.com/");  // Crear una instancia de la clase URL con la dirección de la API
        try (Scanner scanner = new Scanner(url.openStream())) {  // Abrir un flujo de entrada desde la URL
            StringBuilder response = new StringBuilder();  // Crear un StringBuilder para almacenar la respuesta de la API
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());  // Leer cada línea de la respuesta y agregarla al StringBuilder
            }
            return new JSONObject(response.toString());  // Crear un objeto JSONObject a partir de la respuesta
        }
    }

    // Método privado para verificar si un nombre no deseado se encuentra en la lista
    private static boolean esNombreNoDeseado(String nombre) {
        String[] nombresNoDeseados = {"Mr.", "Dr.", "Mrs.", "Ms.", "Miss", "Prof."};  // Lista de nombres no deseados
        for (String noDeseado : nombresNoDeseados) {
            if (nombre.equals(noDeseado)) {  // Comprobar si el nombre coincide con alguno de los nombres no deseados
                return true;  // Devolver true si se encuentra un nombre no deseado
            }
        }
        return false;  // Devolver false si no se encuentra ningún nombre no deseado
    }
}
