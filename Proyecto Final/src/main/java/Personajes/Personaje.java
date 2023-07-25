package Personajes;

import Partida.Partida;
import Utilidades.ManejoLogs;
import Utilidades.NumeroAleatorio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public abstract class Personaje {

    // Datos ------------------------------------------------------------------
    private final Raza raza;
    private final String nombre;
    private final String apodo;
    private final String fechaNacimiento;
    private final short edad; // entre 0 a 300
    private byte salud;
    private final String imagenLink;
    // ------------------------------------------------------------------------

    // Características --------------------------------------------------------
    private final byte velocidad; // 1 a 10
    private final byte destreza; // 1 a 10
    private byte nivel; // 1 a 4
    private final byte armadura; // 1 a 10 - Reduce el daño recibido de los ataques físicos.
    private final byte resistenciaMagica; // 1 a 10 - Reduce el daño recibido de los ataques mágicos.
    // ------------------------------------------------------------------------

    // Constructor ------------------------------------------------------------
    public Personaje(Raza raza, String nombre, String apodo, String fechaNacimiento, byte salud,
                     String imagenLink, byte velocidad, byte destreza, byte nivel, byte armadura,
                     byte resistenciaMagica) {
        this.raza = raza;
        this.nombre = nombre;
        this.apodo = apodo;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = calcularEdad(fechaNacimiento);
        this.salud = salud;
        this.imagenLink = imagenLink;
        this.velocidad = velocidad;
        this.destreza = destreza;
        this.nivel = nivel;
        this.armadura = armadura;
        this.resistenciaMagica = resistenciaMagica;
    }
    // ------------------------------------------------------------------------

    // Sección de Getters y Setters -------------------------------------------
    public Raza getRaza() {
        return raza;
    }
    public byte getSalud() {
        return salud;
    }
    public void setSalud(byte salud) {
        this.salud = salud;
    }
    public String getRazaYapodo() {
        return raza + " '" + apodo + "'";
    }
    public String getApodo() {
        return apodo;
    }
    public byte getDestreza() {
        return destreza;
    }
    public byte getNivel() {
        return nivel;
    }

    // ------------------------------------------------------------------------

    // Sección de lógica de los ataques ---------------------------------------
    public void realizarAtaque(Personaje defensor){
        if (estaVivo()){
            byte danio = calcularDanioAtaque(defensor);

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
    protected byte calcularDanioAtaque(Personaje defensor) {
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
        // Convertir en double para evitar divisiones enteras
        double danio = (valorDeAtaque - (valorDeAtaque * ((double) PDEF / 100)));
        // Redondear el valor y convertirlo a byte para retornarlo
        return (byte) Math.round(danio);
    }
    protected abstract byte calcularPoderDeDisparo();
    protected byte generarEfectividadDeDisparo() {
        // Genera un valor aleatorio de 1 a 100.
        // Retorna la efectividad de disparo
        return NumeroAleatorio.generarNumeroAleatorio(100);
    }
    protected byte calcularValorDeAtaque(byte PD, byte ED) {
        double VA = PD * (ED / 100.0); // Convertir ED a double para evitar divisiones enteras

        // Limitar el valor de VA (0 a 100).
        VA = Math.max(VA, 1); // Asegurar que VA no sea menor que 0
        VA = Math.min(VA, 100); // Asegurar que VA no sea mayor que 100

        return (byte) Math.round(VA); // Redondear el valor y convertirlo a byte
    }
    protected byte calcularPoderDeDefensa(){
        // Retorna el poder de defensa
        return (byte) (armadura * velocidad);
    }
    protected byte calcularPoderDeResistenciaMagica(){
        // Retorna el poder de resistencia mágica
        return (byte) (resistenciaMagica * velocidad);
    }
    public boolean recibirDanio(byte cantidad) {
        salud -= cantidad;
        if (salud < 0) {
            salud = 0;
        }
        return false;
    }

    // ------------------------------------------------------------------------

    // Otros métodos auxiliares, utilidades, impresiones, etc. ----------------
    protected void imprimirEnfurecimientoOrco(Personaje defensor){
        ManejoLogs.recibirLogPartida("-----------------------------------------------------------------------------");
        ManejoLogs.recibirLogPartida("¡El Orco '" + defensor.getApodo() + "' se ha enfurecido y hará más daño \nen el próximo ataque!");
        ManejoLogs.recibirLogPartida("-----------------------------------------------------------------------------");
    }
    public void aumentarNivel() {
        nivel++;
    }
    public boolean estaVivo() {
        return salud > 0;
    }
    public static short calcularEdad(String fechaDeNacimiento) {
        LocalDate fechaNacimiento = LocalDate.parse(fechaDeNacimiento, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate fechaActual = LocalDate.now();
        Period edad = Period.between(fechaNacimiento, fechaActual);
        return (short) edad.getYears();
    }
    @Override
    public String toString() {
        return "Raza: " + raza + "\n" +
                "Nombre: " + nombre + "\n" +
                "Apodo: " + apodo + "\n" +
                "Fecha de nacimiento: " + fechaNacimiento + "\n" +
                "Edad: " + edad + "\n" +
                "Salud: " + salud + "\n" +
                "Link de imagen: " + imagenLink + "\n" +
                "Velocidad: " + velocidad + "\n" +
                "Destreza: " + destreza + "\n" +
                "Nivel: " + nivel + "\n" +
                "Armadura: " + armadura + "\n" +
                "Resistencia mágica: " + resistenciaMagica;
    }
    public String imprimirEnVictoria (int num) {
        String mensajeDedicado;
        if (estaVivo()) {
            mensajeDedicado = "\nHa sobrevivido con " + getSalud() + " de salud restante";
        } else {
            mensajeDedicado = """

                    Ha muerto en el campo de batalla, pero siempre será
                    recordado por todos aquellos que vieron su valentía.""";
        }
        return "================================================================" +
                "\n[" + (num + 1) + "] " + getRazaYapodo() + mensajeDedicado +
                "\nNivel máximo alcanzado = " + getNivel() +
                "\n================================================================";
    }
    // ------------------------------------------------------------------------
}

