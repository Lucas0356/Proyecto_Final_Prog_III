package Personajes;

import java.time.LocalDate;
public class Centauro extends Personaje{

    // Atributos --------------------------------------------------------------
    private final byte fuerza = 8; // Constante con valor de fuerza igual a 8
    // ------------------------------------------------------------------------

    // Constructor ------------------------------------------------------------
    public Centauro(Raza raza, String nombre, String apodo, String fechaNacimiento, short edad) {
        super(raza, nombre, apodo, fechaNacimiento, edad,
                (byte) 100, // salud
                ".img",    // Imagen
                (byte) 8, // Velocidad
                (byte) 3, // Destreza
                (byte) 1, // Nivel
                (byte) 5, // Armadura
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