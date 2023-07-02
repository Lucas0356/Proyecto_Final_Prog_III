package Personajes;

import java.time.LocalDate;
public class Elfo extends Personaje{
    private final byte poderMagico = 8; // Constante con valor de poder mágico igual a 7
    public Elfo(Raza raza, String nombre, String apodo, LocalDate fechaNacimiento, short edad) {
        super(raza, nombre, apodo, fechaNacimiento, edad,
                (byte) 100, // salud
                ".img",    // Imagen
                (byte) 7, // Velocidad
                (byte) 4, // Destreza
                (byte) 1, // Nivel
                (byte) 3, // Armadura
                (byte) 7, // Resistencia mágica
                (byte) 6); // Evasión
    }
}