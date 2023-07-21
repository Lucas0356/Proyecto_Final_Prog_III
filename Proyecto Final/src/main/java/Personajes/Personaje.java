package Personajes;

import Utilidades.NumeroAleatorio;

import java.time.LocalDate;
public abstract class Personaje {
    // DATOS:
    private Raza raza;
    private String nombre;
    private String apodo;
    private LocalDate fechaNacimiento; // fechaNacimiento = LocalDate.of(1990, 1, 1);
    private short edad; // entre 0 a 300
    private byte salud;
    private String imagenLink;

    // CARACTERÍSTICAS:
    private byte velocidad; // 1 a 10
    private byte destreza; // 1 a 5
    private byte nivel; // 1 a 10
    private byte armadura; // 1 a 10 - Reduce el daño recibido de los ataques físicos.
    private byte resistenciaMagica; // 1 a 10 - Reduce el daño recibido de los ataques mágicos.

    public Personaje(Raza raza, String nombre, String apodo, LocalDate fechaNacimiento, short edad, byte salud,
                     String imagenLink, byte velocidad, byte destreza, byte nivel, byte armadura,
                     byte resistenciaMagica) {
        this.raza = raza;
        this.nombre = nombre;
        this.apodo = apodo;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
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

    public String getNombre() {
        return nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public byte getVelocidad() {
        return velocidad;
    }

    public byte getDestreza() {
        return destreza;
    }

    public byte getNivel() {
        return nivel;
    }

    public byte getArmadura() {
        return armadura;
    }

    public byte getResistenciaMagica() {
        return resistenciaMagica;
    }

    // ------------------------------------------------------------------------

    // Sección de lógica de los ataques ---------------------------------------
    public abstract byte calcularPoderDeDisparo();
    public byte generarEfectividadDeDisparo() {
        // Genera un valor aleatorio de 1 a 100.
        byte ED = NumeroAleatorio.generarNumeroAleatorio(100);
        // Retorna la efectividad de disparo
        return ED;
    }
    public byte calcularValorDeAtaque(byte PD, byte ED) {
        double VA = PD * (ED / 100.0); // Convertir ED a double para evitar divisiones enteras

        // Limitar el valor de VA (0 a 100).
        VA = Math.max(VA, 1); // Asegurar que VA no sea menor que 0
        VA = Math.min(VA, 100); // Asegurar que VA no sea mayor que 100

        return (byte) Math.round(VA); // Redondear el valor y convertirlo a byte
    }
    public byte calcularPoderDeDefensa(){
        byte PDEF = (byte) (armadura * velocidad); // Poder de Defensa
        // Retorna el poder de defensa
        return PDEF;
    }
    public byte calcularPoderDeResistenciaMagica(){
        byte PDEF = (byte) (resistenciaMagica * velocidad); // Poder de Resistencia mágica
        // Retorna el poder de resistencia mágica
        return PDEF;
    }
    public byte calcularValorFinalAtaque(double VA, double PDEF){
        double valorAtaqueFinal = (VA - (VA * (PDEF / 100))); // Convertir en double para evitar divisiones enteras
        return (byte) Math.round(valorAtaqueFinal); // Redondear el valor y convertirlo a byte
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
    public void recibirDaño(byte cantidad) {
        salud -= cantidad;
        if (salud < 0) {
            salud = 0;
        }
    }

    // ------------------------------------------------------------------------

    // Otros métodos auxiliares, utilidades, etc. -----------------------------
    public boolean estaVivo() {
        if (salud > 0){
            return true;
        }
        return false;
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
}

