package Personajes;

import java.time.LocalDate;
public class Humano extends Personaje{

    // Atributos --------------------------------------------------------------
    private final byte fuerza = 7; // Constante con valor de fuerza igual a 7
    // ------------------------------------------------------------------------

    // Constructor ------------------------------------------------------------
    public Humano(Raza raza, String nombre, String apodo, String fechaNacimiento, short edad) {
        super(raza, nombre, apodo, fechaNacimiento, edad,
                (byte) 100, // salud
                ".img",    // Imagen
                (byte) 6, // Velocidad
                (byte) 5, // Destreza
                (byte) 1, // Nivel
                (byte) 4, // Armadura
                (byte) 4); // Resistencia mágica
    }
    // ------------------------------------------------------------------------

    // Método de cálculo de poder de disparo ----------------------------------
    @Override
    public byte calcularPoderDeDisparo() {
        byte PD = (byte) (getDestreza() * fuerza * getNivel());
        return PD;
    }
    // ------------------------------------------------------------------------
}
