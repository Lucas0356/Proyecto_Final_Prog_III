package Personajes;

import java.time.LocalDate;
public class Elfo extends Personaje{
    private final byte poderMagico = 9; // Constante con valor de poder mágico igual a 9
    public Elfo(Raza raza, String nombre, String apodo, LocalDate fechaNacimiento, short edad) {
        super(raza, nombre, apodo, fechaNacimiento, edad,
                (byte) 100, // salud
                ".img",    // Imagen
                (byte) 7, // Velocidad
                (byte) 5, // Destreza
                (byte) 1, // Nivel
                (byte) 4, // Armadura
                (byte) 7); // Resistencia mágica
    }

    @Override
    public byte calcularPoderDeDisparo() {
        byte PD = (byte) (getDestreza() * poderMagico * getNivel());
        return PD;
    }
}