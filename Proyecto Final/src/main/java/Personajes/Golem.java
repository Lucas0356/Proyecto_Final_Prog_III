package Personajes;

import Utilidades.ManejoLogs;
import static Partida.Partida.continuar;

public class Golem extends Personaje{

    // Atributos --------------------------------------------------------------
    private byte cargaAtaque = 0; // 0 a 3 - Tarda 3 turnos en cargar su ataque
    // ------------------------------------------------------------------------

    // Constructor ------------------------------------------------------------
    public Golem(String nombre, String apodo, String fechaNacimiento) {
        super(Raza.Golem, nombre, apodo, fechaNacimiento,
                (byte) 100, // salud
                ".img",    // Imagen
                (byte) 2, // Velocidad
                (byte) 4, // Destreza
                (byte) 1, // Nivel
                (byte) 10, // Armadura
                (byte) 6); // Resistencia mágica
    }
    // ------------------------------------------------------------------------

    // Getters ----------------------------------------------------------------
    public byte getCargaAtaque() {
        return cargaAtaque;
    }
    // ------------------------------------------------------------------------

    // Método de cálculo de poder de disparo ----------------------------------
    @Override
    public byte calcularPoderDeDisparo() {
        byte fuerza = 20; // Constante con valor de fuerza igual a 20
        return (byte) (getDestreza() * fuerza * getNivel());
    }
    // ------------------------------------------------------------------------

    // Métodos para manejar el estado de la carga de ataque -------------------
    @Override
    public byte realizarAtaque(Personaje defensor) {
        if (estaVivo()){
            // Verificamos que el golem no esté cargando su ataque
            if (golemEstaCargandoAtaque()) {
                ManejoLogs.recibirLogPartida("----------------------------------------------------------------");
                ManejoLogs.recibirLogPartida("El Golem '" + getApodo() + "' esta cargando su ataque...");
                ManejoLogs.recibirLogPartida("----------------------------------------------------------------");
                if (ManejoLogs.getGuardar()){
                    continuar("\nPulse enter para continuar: ");
                }
            } else{
                byte danio = calcularDanioAtaque(defensor);
                defensor.recibirDanio(danio);
                return danio;
            }
        }
        return 0; // Al estar muerto, no ataca
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
    // ------------------------------------------------------------------------
}
