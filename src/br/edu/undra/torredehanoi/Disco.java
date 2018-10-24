package br.edu.undra.torredehanoi;

/**
 *
 * @author alexandre
 */
public class Disco {
       private final int numero;

    public Disco(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "DISCO-"+this.numero;
    }
    
    public static void main(String[] args) {
        System.out.println(new Disco(100));
    }
    
    
}
