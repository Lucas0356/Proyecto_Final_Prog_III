package Personajes;

public class Humano extends Personaje{

    // Constructor ------------------------------------------------------------
    public Humano(String nombre, String apodo, String fechaNacimiento) {
        super(Raza.Humano, nombre, apodo, fechaNacimiento,
                (byte) 100, // salud
                "https://i.ibb.co/HP77GtJ/humano.png",    // Imagen
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
        byte fuerza = 7; // Constante con valor de fuerza igual a 7
        return (byte) (getDestreza() * fuerza * getNivel());
    }
    // ------------------------------------------------------------------------
}
