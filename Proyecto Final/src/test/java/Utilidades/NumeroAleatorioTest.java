package Utilidades;

import org.junit.jupiter.api.Test;

class NumeroAleatorioTest {
    @Test
    void test() {
        byte rango = 3;
        byte num = NumeroAleatorio.generarNumeroAleatorio(rango);
        System.out.println("El número aleatorio (de 1 a " + rango + ") es: " + num);
    }
}