package Personajes;

import Partida.Partida;
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
                "https://i.ibb.co/8YMm8xS/orco.png",// Imagen
                (byte) 4, // Velocidad
                (byte) 2, // Destreza
                (byte) 1, // Nivel
                (byte) 7, // Armadura
                (byte) 2); // Resistencia mágica
    }
    // ------------------------------------------------------------------------

    // Getters ------------------------------------------------
    public boolean getEstadoFerocidad() {
        return ferocidad;
    }
    public int getAtaquesRecibidos() {
        return ataquesRecibidos;
    }
    // ------------------------------------------------------------------------

    // Métodos de cálculos y ataques ------------------------------------------
    @Override
    public void realizarAtaque(Personaje defensor){
        if (estaVivo()){
            byte danio = calcularDanioAtaque(defensor);

            if (ferocidad) {
                danio *= 1.5; // Aumentar el daño en un 50% si está enfurecido
                desactivarFerocidad();
            }

            // Verificamos si el defensor es un orco y si se enfureció luego de recibir el ataque
            boolean esUnOrcoEnfurecido = defensor.recibirDanio(danio);

            // Imprimimos el ataque
            ManejoLogs.recibirLogPartida("-----------------------------------------------------------------------------");
            ManejoLogs.recibirLogPartida(getRaza() + " '" + getApodo() + "' ataca a " +
                    defensor.getRaza() + " '" + defensor.getApodo() + "'");
            ManejoLogs.recibirLogPartida("Le ha provocado " + danio + " de daño. " + defensor.getApodo() +
                    " queda con " + defensor.getSalud() + " de salud.");
            ManejoLogs.recibirLogPartida("-----------------------------------------------------------------------------");

            // Si el defensor es un orco y se enfureció, se imprime por pantalla
            if (esUnOrcoEnfurecido){
                imprimirEnfurecimientoOrco(defensor);
            }

            Partida.continuar("\nPulse enter para continuar: ");
        }
    }
    @Override
    public byte calcularPoderDeDisparo() {
        byte fuerza = 10; // Constante con valor de fuerza igual a 10
        return (byte) (getDestreza() * fuerza * getNivel());
    }
    // ------------------------------------------------------------------------

    // Métodos para manejar el estado de la ferocidad -------------------------
    @Override
    public boolean recibirDanio(byte cantidad) {
        byte salud = getSalud();
        salud -= cantidad;
        if (salud <= 0) {
            setSalud((byte) 0);
        } else{
            setSalud(salud);
            manejarFerocidadOrco();
        }
        return getEstadoFerocidad();
    }
    private void manejarFerocidadOrco() {
        incrementarAtaquesRecibidos();
        if (getAtaquesRecibidos() >= 2) {
            activarFerocidad();
            resetearAtaquesRecibidos();
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
    // ------------------------------------------------------------------------
}
