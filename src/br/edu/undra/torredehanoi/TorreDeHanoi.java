package br.edu.undra.torredehanoi;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe modela um jogo de hanoi.
 *
 * @author alexandre
 */
public class TorreDeHanoi {

    private final Pino origem;
    private final Pino destino;
    private final Pino extra;
    private final List<Pino> pinos;
    private final List<Jogada> jogadas;

    private final int numeroDeDiscos;

    public TorreDeHanoi(int numeroDeDiscos) {

        if (numeroDeDiscos <= 0) {
            throw new Error("IMPOSSÍVEL JOGAR COM " + numeroDeDiscos + " DISCO(S). TENTE NOVAMENTE. NÚMEROS POSITIVOS APENAS.");
        }

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

        jogadas = new ArrayList<>();

    }

    public String getEstado() {

        StringBuilder estado = new StringBuilder();

        for (int i = 0; i < pinos.size(); i++) {

            estado.append(pinos.get(i).getEstado());
            if (i < pinos.size() - 1) {
                estado.append("\n");
            }

        }

        return estado.toString();

    }

    public void move_N(Pino origem, Pino destino, Pino extra, int n_1) {

        if (n_1 < 0) {
            return;
        }

        switch (n_1) {

            case 0:
                joga(origem, extra);
                break;
            case 1:
                joga(origem, extra);
                break;
            case 2:
                joga(origem, destino);
                joga(origem, extra);
                joga(destino, extra);
                break;
            default:
                //RECURSAO
                move_N(origem, extra, destino, n_1 - 1);
                joga(origem, extra);
                int discos = n_1 - 1;
                move_N(destino, origem, extra, discos);
                break;
        }

    }

    public void jogar() {

        System.err.println("========== ESTADO DO JOGO AO INICIAR ===============");
        System.err.println(this.getEstado());
        System.err.println("======================================================\n");

        move_N(origem, destino, extra, origem.getNumDiscos() - 1);
        joga(origem, destino);
        move_N(extra, origem, destino, extra.getNumDiscos());
        System.err.println("========== " + this.jogadas.size() + " JOGADAS ===============");

        jogadas.forEach(j -> System.err.println(j));

        System.err.println("========== FIM JOGADAS ===============");
        System.err.println("\n========== ESTADO DO JOGO AO FINALIZAR ===============");
        System.err.println(this.getEstado());
        System.err.println("======================================================\n");

    }

    private void joga(Pino origem, Pino destino) {

        Disco d = origem.pop();

        if (d != null) {

            if (destino.podeEmpilharEsteDisco(d)) {

                destino.push(d);

                Jogada j = new Jogada(origem, destino, d);

                jogadas.add(j);

            } else {

                System.err.println("NAO FOI POSSIVEL JOGAR " + d.toString() + " EM " + destino.toString());
                //devolve
                origem.push(d);

            }

        } else {

            System.err.println("NAO FOI POSSIVEL JOGAR PORQUE NAO HA DISCOS EM " + origem.toString());

        }

    }

    public Pino getOrigem() {
        return origem;
    }

    public Pino getDestino() {
        return destino;
    }

    public Pino getExtra() {
        return extra;
    }

    public void mover() {

        //MOVER N-1 DISCOS DO PINO ORIGEM PARA PINO EXTRA
        //enquanto numDiscos em PINO ORIGEM > 1 E numDisco em PINO EXTRA < N-1
//           1. PEGAR PROXIMO DISCO EM PINO ORIGEM.
//           2. TEM POSICAO EM PINO DESTINO OU EXTRA ?
//                   2.1 SE NAO TEM POSICAO, DEVOLVE DISCO `A ORIGEM E CRIA UMA POSICAO, MODIFICANDO DESTINO E EXTRA
//                   2.2 SE TEM, JOGA NELA ESSE DISCO.
//           3.
        //fim enquanto           
    }

    public static void main(String[] args) {

        int numeroDeDiscos = 4;

        if (args != null && args.length > 0) {
            try {
                numeroDeDiscos = Integer.parseInt(args[0]);
            } catch (Exception e) {
                throw new Error("IMPOSSÍVEL JOGAR COM " + args[0] + " DISCO(S). TENTE NOVAMENTE. NÚMEROS POSITIVOS APENAS.");
            }

        }

        TorreDeHanoi torreDeHanoi = new TorreDeHanoi(numeroDeDiscos);

        torreDeHanoi.jogar();

    }

}
