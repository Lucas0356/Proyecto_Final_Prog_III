package Personajes;

import java.time.LocalDate;

public class Golem extends Personaje{

    // Atributos --------------------------------------------------------------
    private byte cargaAtaque = 0; // 0 a 3 - Tarda 3 turnos en cargar su ataque
    private final byte fuerza = 20; // Constante con valor de fuerza igual a 20
    // ------------------------------------------------------------------------

    // Constructor ------------------------------------------------------------
    public Golem(Raza raza, String nombre, String apodo, String fechaNacimiento, short edad) {
        super(raza, nombre, apodo, fechaNacimiento, edad,
                (byte) 100, // salud
                ".img",    // Imagen
                (byte) 2, // Velocidad
                (byte) 4, // Destreza
                (byte) 1, // Nivel
                (byte) 10, // Armadura
                (byte) 6); // Resistencia mágica
    }
    // ------------------------------------------------------------------------

    // Método de cálculo de poder de disparo ----------------------------------
    @Override
    public byte calcularPoderDeDisparo() {
        byte PD = (byte) (getDestreza() * fuerza * getNivel());
        return PD;
    }
    // ------------------------------------------------------------------------

    // Métodos para manejar el estado de la carga de ataque -------------------
    public boolean golemEstaCargandoAtaque(){
        if (cargaAtaque >= 3) {
            cargaAtaque = 0; // Reiniciar la cuenta de turnos
            return false;
        } else {
            cargaAtaque++;
            return true;
        }
    }
    // ------------------------------------------------------------------------
}
