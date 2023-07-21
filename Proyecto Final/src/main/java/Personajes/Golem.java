package Personajes;

import java.time.LocalDate;

public class Golem extends Personaje{
    private byte cargaAtaque = 0; // 0 a 3 - Tarda 3 turnos en cargar su ataque
    private final byte fuerza = 20; // Constante con valor de fuerza igual a 20
    public Golem(Raza raza, String nombre, String apodo, LocalDate fechaNacimiento, short edad) {
        super(raza, nombre, apodo, fechaNacimiento, edad,
                (byte) 100, // salud
                ".img",    // Imagen
                (byte) 2, // Velocidad
                (byte) 4, // Destreza
                (byte) 1, // Nivel
                (byte) 10, // Armadura
                (byte) 6); // Resistencia mágica
    }
    @Override
    public byte calcularPoderDeDisparo() {
        byte PD = (byte) (getDestreza() * fuerza * getNivel());
        return PD;
    }
    public byte realizarAtaque(Personaje defensor) {
        // Calcular el valor de ataque del atacante
        byte poderDeDisparo = calcularPoderDeDisparo();
        byte efectividadDeDisparo = generarEfectividadDeDisparo();
        byte valorDeAtaque = calcularValorDeAtaque(poderDeDisparo, efectividadDeDisparo);

        // Calcular el poder de defensa del defensor
        byte PDEF; // poder de defensa o de resistencia mágica.
        if (this.getRaza() == Raza.Elfo){
            PDEF = defensor.calcularPoderDeResistenciaMagica();
        } else{
            PDEF = defensor.calcularPoderDeDefensa();
        }

        // Calcular el valor final del ataque
        byte valorFinalAtaque = calcularValorFinalAtaque(valorDeAtaque, PDEF);

        return valorFinalAtaque;
    }
    public boolean golemEstaCargandoAtaque(){
        if (cargaAtaque >= 3) {
            cargaAtaque = 0; // Reiniciar la cuenta de turnos
            return false;
        } else {
            cargaAtaque++;
            return true;
        }
    }
}
