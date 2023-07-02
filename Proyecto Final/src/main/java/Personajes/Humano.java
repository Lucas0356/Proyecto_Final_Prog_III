package Personajes;

import java.time.LocalDate;
public class Humano extends Personaje{
    private final byte fuerza = 6; // Constante con valor de fuerza igual a 6
    public Humano(Raza raza, String nombre, String apodo, LocalDate fechaNacimiento, short edad) {
        super(raza, nombre, apodo, fechaNacimiento, edad,
                (byte) 100, // salud
                ".img",    // Imagen
                (byte) 6, // Velocidad
                (byte) 3, // Destreza
                (byte) 1, // Nivel
                (byte) 4, // Armadura
                (byte) 3, // Resistencia mágica
                (byte) 5); // Evasión
    }
}
