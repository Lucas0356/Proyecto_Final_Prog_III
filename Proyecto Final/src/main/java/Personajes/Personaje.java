package Personajes;

import Utilidades.NumeroAleatorio;

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
    public Personaje(Raza raza, String nombre, String apodo, String fechaNacimiento, short edad, byte salud,
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

    // Sección de Getters -----------------------------------------------------
    public Raza getRaza() {
        return raza;
    }
    public byte getSalud() {
        return salud;
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
        byte daño = calcularDaño(valorDeAtaque, PDEF);

        return daño;
    }
    protected abstract byte calcularPoderDeDisparo();
    protected byte generarEfectividadDeDisparo() {
        // Genera un valor aleatorio de 1 a 100.
        byte ED = NumeroAleatorio.generarNumeroAleatorio(100);
        // Retorna la efectividad de disparo
        return ED;
    }
    protected byte calcularValorDeAtaque(byte PD, byte ED) {
        double VA = PD * (ED / 100.0); // Convertir ED a double para evitar divisiones enteras

        // Limitar el valor de VA (0 a 100).
        VA = Math.max(VA, 1); // Asegurar que VA no sea menor que 0
        VA = Math.min(VA, 100); // Asegurar que VA no sea mayor que 100

        return (byte) Math.round(VA); // Redondear el valor y convertirlo a byte
    }
    protected byte calcularPoderDeDefensa(){
        byte PDEF = (byte) (armadura * velocidad); // Poder de Defensa
        // Retorna el poder de defensa
        return PDEF;
    }
    protected byte calcularPoderDeResistenciaMagica(){
        byte PDEF = (byte) (resistenciaMagica * velocidad); // Poder de Resistencia mágica
        // Retorna el poder de resistencia mágica
        return PDEF;
    }
    protected byte calcularDaño(double VA, double PDEF){
        double daño = (VA - (VA * (PDEF / 100))); // Convertir en double para evitar divisiones enteras
        return (byte) Math.round(daño); // Redondear el valor y convertirlo a byte
    }
    public void recibirDaño(byte cantidad) {
        salud -= cantidad;
        if (salud < 0) {
            salud = 0;
        }
    }

    // ------------------------------------------------------------------------

    // Otros métodos auxiliares, utilidades, impresiones, etc. ----------------
    public void aumentarNivel() {
        nivel++;
    }
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
    public String imprimirEnVictoria (int num) {
        String mensajeDedicado;
        if (estaVivo()) {
            mensajeDedicado = "\nHa sobrevivido con " + getSalud() + " de salud restante";
        } else {
            mensajeDedicado = "\nHa muerto en el campo de batalla, pero siempre será" +
            "\nrecordado por todos aquellos que vieron su valentía.";
        }
        return "================================================================" +
                "\n[" + (num + 1) + "] " + getRazaYapodo() + mensajeDedicado +
                "\nNivel máximo alcanzado = " + getNivel() +
                "\n================================================================";
    }
    // ------------------------------------------------------------------------
}

