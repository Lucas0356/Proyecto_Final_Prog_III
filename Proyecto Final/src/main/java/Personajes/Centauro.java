package Personajes;

import java.time.LocalDate;
public class Centauro extends Personaje{
    private final byte fuerza = 7; // Constante con valor de fuerza igual a 7
    public Centauro(Raza raza, String nombre, String apodo, LocalDate fechaNacimiento, short edad) {
        super(raza, nombre, apodo, fechaNacimiento, edad,
                (byte) 100, // salud
                ".img",    // Imagen
                (byte) 8, // Velocidad
                (byte) 3, // Destreza
                (byte) 1, // Nivel
                (byte) 5, // Armadura
                (byte) 4, // Resistencia mágica
                (byte) 4); // Evasión
    }
    public byte calcularAtaque() {
        byte ataque = (byte) (getDestreza() * fuerza * getNivel());
        return ataque;
    }
}