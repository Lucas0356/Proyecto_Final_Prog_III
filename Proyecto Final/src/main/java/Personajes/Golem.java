package Personajes;

import java.time.LocalDate;

public class Golem extends Personaje{
    private byte cargaAtaque; // 1 a 3 - Tarda 3 turnos en cargar su ataque
    private final byte fuerza = 10; // Constante con valor de fuerza igual a 10
    public Golem(Raza raza, String nombre, String apodo, LocalDate fechaNacimiento, short edad) {
        super(raza, nombre, apodo, fechaNacimiento, edad,
                (byte) 100, // salud
                ".img",    // Imagen
                (byte) 2, // Velocidad
                (byte) 1, // Destreza
                (byte) 1, // Nivel
                (byte) 10, // Armadura
                (byte) 6, // Resistencia mágica
                (byte) 5); // Evasión
    }
}
