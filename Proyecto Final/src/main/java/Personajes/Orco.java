package Personajes;

import Utilidades.ManejoLogs;

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

    // Get estado de ferocidad ------------------------------------------------
    public boolean getEstadoFerocidad() {
        return ferocidad;
    }
    // ------------------------------------------------------------------------

    // Métodos de cálculos y ataques ------------------------------------------
    @Override
    public byte realizarAtaque(Personaje defensor) {
        if (estaVivo()){
            byte danio = calcularDanioAtaque(defensor);
            defensor.recibirDanio(danio);

            if (ferocidad) {
                danio *= 1.5; // Aumentar el daño en un 50%
                desactivarFerocidad();
            }
            return danio;
        }
        return 0; // Al estar muerto, no ataca
    }
    @Override
    public byte calcularPoderDeDisparo() {
        byte fuerza = 10; // Constante con valor de fuerza igual a 10
        return (byte) (getDestreza() * fuerza * getNivel());
    }
    // ------------------------------------------------------------------------

    // Métodos para manejar el estado de la ferocidad -------------------------
    @Override
    public void recibirDanio(byte cantidad) {
        byte salud = getSalud();
        salud -= cantidad;
        if (salud <= 0) {
            setSalud((byte) 0);
        } else{
            manejarFerocidadOrco();
        }
    }
    private void manejarFerocidadOrco() {
        incrementarAtaquesRecibidos();
        if (getAtaquesRecibidos() >= 2) {
            activarFerocidad();
            resetearAtaquesRecibidos();
            ManejoLogs.recibirLogPartida("----------------------------------------------------------------");
            ManejoLogs.recibirLogPartida("¡El Orco '" + getApodo() + "' se ha enfurecido y hará más daño\nen el próximo ataque!");
            ManejoLogs.recibirLogPartida("----------------------------------------------------------------");
        }
    }
    private void desactivarFerocidad(){
        ferocidad = false;
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
