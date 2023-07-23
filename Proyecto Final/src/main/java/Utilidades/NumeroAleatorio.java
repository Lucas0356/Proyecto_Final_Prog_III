package Utilidades;

import java.util.Random;

public class NumeroAleatorio {
    public static byte generarNumeroAleatorio(int rango) {
        // Crear una instancia de la clase Random
        Random random = new Random();

        // Generar un número aleatorio entre 1 y el número proporcionado
        int numeroAleatorio = random.nextInt(rango) + 1;

        // Devolver el número aleatorio generado
        return (byte) numeroAleatorio;
    }
}