package br.edu.undra.torredehanoi;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexandre
 */
public class Pino {

    private List<Disco> discos = new ArrayList<>();
    private String nome;

    public Pino(String nome) {
        this.nome = nome;
    }
    
    public Pino(List<Disco> discos) {
        this.discos = discos;
    }

    public void push(Disco d) {
        discos.add(d);
    }

    public Disco pop() {

        Disco d = discos.get(discos.size() - 1);
        discos.remove(discos.size() - 1);

        return d;
    }

    public String getEstado() {

        StringBuilder estado = new StringBuilder();

        estado.append(this.toString());
        estado.append(":");
        for (int j = 0; j < discos.size(); j++) {

            estado.append(discos.get(j));
            estado.append(",");

        }

        return estado.toString();
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
    public static void main(String[] args) {
        
        Pino pino = new Pino("PINO-T");
        pino.push(new Disco(5));
        System.err.println(pino.getEstado());
        Disco d = pino.pop();
        System.err.println(d);
        System.err.println(pino.getEstado());
        
        pino.push(d);
        pino.push(new Disco(4));
        System.err.println(pino.getEstado());
        pino.pop();
        pino.pop();
        System.err.println(pino.getEstado());
        
    }

}
