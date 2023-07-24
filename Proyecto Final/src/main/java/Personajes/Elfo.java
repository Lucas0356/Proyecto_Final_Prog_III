package Personajes;

public class Elfo extends Personaje{

    // Constructor ------------------------------------------------------------
    public Elfo(String nombre, String apodo, String fechaNacimiento) {
        super(Raza.Elfo, nombre, apodo, fechaNacimiento,
                (byte) 100, // salud
                "https://i.ibb.co/kcKBrdx/elfo.png",    // Imagen
                (byte) 7, // Velocidad
                (byte) 5, // Destreza
                (byte) 1, // Nivel
                (byte) 4, // Armadura
                (byte) 7); // Resistencia mágica
    }
    // ------------------------------------------------------------------------

    // Método de cálculo de poder de disparo ----------------------------------
    @Override
    public byte calcularPoderDeDisparo() {
        byte poderMagico = 9; // Constante con valor de poder mágico igual a 9
        return (byte) (getDestreza() * poderMagico * getNivel());
    }
    // ------------------------------------------------------------------------
}