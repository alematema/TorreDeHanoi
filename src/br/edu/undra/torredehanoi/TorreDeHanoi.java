package br.edu.undra.torredehanoi;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe modela um jogo de hanoi.<br>
 * Resolve o jogo usando recursao sobre o número n de discos. 
 *
 * @author alexandre
 */
public class TorreDeHanoi {

    private final Pino pino1;
    private final Pino pino2;
    private final Pino pino3;
    private final List<Pino> pinos;
    private final List<Jogada> jogadas;

    /**
     * Controi um jogo, e, inicialmente. empilha todos discos no pino1. 
     * 
     * @param numeroDeDiscos o numero de discos da torre.
     */
    public TorreDeHanoi(int numeroDeDiscos) {

        pino1 = new Pino("PINO_1");
        pino2 = new Pino("PINO_2");
        pino3 = new Pino("PINO_3");

        for (int i = numeroDeDiscos; i >= 1; i--) {
            pino1.push(new Disco(i));
        }

        pinos = new ArrayList();
        pinos.add(pino1);
        pinos.add(pino2);
        pinos.add(pino3);

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

    /**
     * Move, <strong>RECURSIVAMENTE, {@code} n</strong> discos do pino origem para pino
 <strong>extra</strong>.
 *  
     * @param origem Pino origem
     * @param destino Pino destino
     * @param extra Pino extra
     * @param n numero de discos que serao movidos da origem para destino.
     */
    public void move_N(Pino origem, Pino destino, Pino extra, int n) {

        if (n < 0) {
            return;
        }

        switch (n) {

            case 0:
                move(origem, extra);
                break;
            case 1:
                move(origem, extra);
                break;
            case 2:
                move(origem, destino);
                move(origem, extra);
                move(destino, extra);
                break;
            default:
                //RECURSAO
                move_N(origem, extra, destino, n - 1);
                move(origem, extra);
                move_N(destino, origem, extra, n - 1);
                break;
        }

    }

    /**
     *
     */
    public void jogar() {

        System.err.println("========== ESTADO DO JOGO AO INICIAR ===============");
        System.err.println(this.getEstado());
        System.err.println("======================================================\n");

        move_N(pino1, pino2, pino3, pino1.getNumDiscos() - 1);
        
        move(pino1, pino2);
        
        move_N(pino3, pino1, pino2, pino3.getNumDiscos() );
        
        System.err.println("========== " + this.jogadas.size() + " JOGADAS ===============");

        jogadas.forEach(j -> System.err.println(j));

        System.err.println("========== FIM JOGADAS ===============");
        System.err.println("\n========== ESTADO DO JOGO AO FINALIZAR ===============");
        System.err.println(this.getEstado());
        System.err.println("======================================================\n");

    }

    /**
     * Move o disco mais alto do pino origem para o pino destino. 
     * @param origem o pino de onde se pegara o mais alto disco.
     * @param destino o pino que receberá o disco.
     */
    private void move(Pino origem, Pino destino) {

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

    public Pino getPino1() {
        return pino1;
    }

    public Pino getPino2() {
        return pino2;
    }

    public Pino getPino3() {
        return pino3;
    }

    public static void main(String[] args) {

        int numeroDeDiscos = 4;

        if (args != null && args.length > 0) {
            try {
                numeroDeDiscos = Integer.parseInt(args[0]);
            } catch (Exception e) {
                throw new Error("NO NOMENTO, IMPOSSÍVEL JOGAR COM " + args[0] + " DISCO(S). TENTE NOVAMENTE. NÚMEROS INTEIROS POSITIVOS APENAS.");
            }

        }
        
        if (numeroDeDiscos <= 0) {
            throw new Error("NO MOMENTO, IMPOSSÍVEL JOGAR COM " + numeroDeDiscos + " DISCO(S). TENTE NOVAMENTE. NÚMEROS INTEIROS POSITIVOS APENAS.");
        }

        TorreDeHanoi torreDeHanoi = new TorreDeHanoi(numeroDeDiscos);

        torreDeHanoi.jogar();

    }

}
