package Personajes;

public class Orco extends Personaje{

    // Atributos --------------------------------------------------------------
    private boolean ferocidad = false; // falso o verdadero - Aumenta el daño infligido por el orco en estado de ira.
    private byte ataquesRecibidos = 0; // Se activa el enfurecimiento cuando recibe 2 ataques
    // ------------------------------------------------------------------------

    // Constructor ------------------------------------------------------------
    public Orco(String nombre, String apodo, String fechaNacimiento) {
        super(Raza.Orco, nombre, apodo, fechaNacimiento,
                (byte) 100, // salud
                ".img",    // Imagen
                (byte) 4, // Velocidad
                (byte) 2, // Destreza
                (byte) 1, // Nivel
                (byte) 7, // Armadura
                (byte) 2); // Resistencia mágica
    }
    // ------------------------------------------------------------------------

    // Métodos de cálculos y ataques ------------------------------------------
    @Override
    public byte calcularPoderDeDisparo() {
        byte fuerza = 10; // Constante con valor de fuerza igual a 10
        return (byte) (getDestreza() * fuerza * getNivel());
    }
    @Override
    public byte realizarAtaque(Personaje defensor) {
        // Método para realizar un ataque al personaje defensor

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
        byte danio = calcularDanio(valorDeAtaque, PDEF);

        if (ferocidad) {
            danio *= 1.5; // Aumentar el daño en un 50%
            desactivarFerocidad();
        }
        return danio;
    }
    // ------------------------------------------------------------------------

    // Métodos para manejar el estado de la ferocidad -------------------------
    private void desactivarFerocidad(){
        ferocidad = true;
    }
    public void activarFerocidad(){
        ferocidad = true;
    }
    // ------------------------------------------------------------------------

    // Métodos para el contador de ataques recibidos---------------------------
    public void incrementarAtaquesRecibidos() {
        ataquesRecibidos++;
    }
    public void resetearAtaquesRecibidos() {
        ataquesRecibidos = 0;
    }
    public int getAtaquesRecibidos() {
        return ataquesRecibidos;
    }
    // ------------------------------------------------------------------------
}
