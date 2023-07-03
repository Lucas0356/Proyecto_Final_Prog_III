package Personajes;

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
    private byte evasion; // 1 a 10 - Afecta la probabilidad de evitar los ataques enemigos.

    public Personaje(Raza raza, String nombre, String apodo, LocalDate fechaNacimiento, short edad, byte salud,
                     String imagenLink, byte velocidad, byte destreza, byte nivel, byte armadura,
                     byte resistenciaMagica, byte evasion) {
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
        this.evasion = evasion;
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
                "Resistencia mágica: " + resistenciaMagica + "\n" +
                "Evasión: " + evasion;
    }
}

