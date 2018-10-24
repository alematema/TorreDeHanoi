package br.edu.undra.torredehanoi;

/**
 *
 * @author alexandre
 */
public class Jogada {
    
    private final Pino origem;
    private final Pino destino;
    private final Disco disco;

    public Jogada(Pino origem, Pino destino, Disco disco) {
        this.origem = origem;
        this.destino = destino;
        this.disco = disco;
    }

    public Pino getOrigem() {
        return origem;
    }

    public Pino getDestino() {
        return destino;
    }

    public Disco getDisco() {
        return disco;
    }

    @Override
    public String toString() {
        return this.disco.toString()+" DE " + this.origem.toString() + " PARA "+ this.destino.toString();
    }
    
    
    
}
