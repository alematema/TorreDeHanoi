package br.edu.undra.torredehanoi;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexandre
 */
public class TorreDeHanoi {

    private final Pino origem;
    private final Pino destino;
    private final Pino extra;
    private final List<Pino> pinos;

    private final int numeroDeDiscos;

    public TorreDeHanoi(int numeroDeDiscos) {

        this.numeroDeDiscos = numeroDeDiscos;

        origem = new Pino("ORIGEM");
        destino = new Pino("DESTINO");
        extra = new Pino("EXTRA");

        for (int i = numeroDeDiscos; i >= 1; i--) {
            origem.push(new Disco(i));
        }

        pinos = new ArrayList();
        pinos.add(origem);
        pinos.add(destino);
        pinos.add(extra);

    }

    public String getEstado() {

        StringBuilder estado = new StringBuilder();

        for (int i = 0; i < pinos.size(); i++) {

            estado.append(pinos.get(i).getEstado());
            estado.append("\n");

        }

        return estado.toString();

    }

    public static void main(String[] args) {

        TorreDeHanoi torreDeHanoi = new TorreDeHanoi(5);
        System.err.println(torreDeHanoi.getEstado());

    }

}
