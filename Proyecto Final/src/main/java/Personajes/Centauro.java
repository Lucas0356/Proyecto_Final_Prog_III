package Personajes;

public class Centauro extends Personaje{

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
        byte fuerza = 8; // Constante con valor de fuerza igual a 8
        return (byte) (getDestreza() * fuerza * getNivel());
    }
    // ------------------------------------------------------------------------
}