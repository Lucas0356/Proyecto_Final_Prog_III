package Personajes;

import java.time.LocalDate;
public class Orco extends Personaje{
    private boolean ferocidad; // falso o verdadero - Aumenta el daño infligido por el orco en estado de ira.
    private final byte fuerza = 10; // Constante con valor de fuerza igual a 10
    public Orco(Raza raza, String nombre, String apodo, LocalDate fechaNacimiento, short edad) {
        super(raza, nombre, apodo, fechaNacimiento, edad,
              (byte) 100, // salud
              ".img",    // Imagen
              (byte) 4, // Velocidad
              (byte) 2, // Destreza
              (byte) 1, // Nivel
              (byte) 7, // Armadura
              (byte) 2); // Resistencia mágica
        this.ferocidad = false;
    }
    @Override
    public byte calcularPoderDeDisparo() {
        byte PD = (byte) (getDestreza() * fuerza * getNivel());
        return PD;
    }
}
