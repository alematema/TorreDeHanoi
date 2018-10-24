package br.edu.undra.torredehanoi;

/**
 * Classe modela um disco da torre de hanoi.
 * @author alexandre
 */
public class Disco {
       private final int numero;

    public Disco(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "DISCO_"+this.numero;
    }

    public int getNumero() {
        return numero;
    }
    
    public static void main(String[] args) {
        System.out.println(new Disco(100));
    }
    
}
