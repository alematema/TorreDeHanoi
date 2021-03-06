package br.edu.undra.torredehanoi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private int intervaloEntreJogadas = 3500;

    /**
     * Controi um jogo, e, inicialmente. empilha todos discos no pino1. 
     * 
     * @param numeroDeDiscos o numero de discos da torre.
     */
    public TorreDeHanoi(int numeroDeDiscos) {

        pino1 = new Pino("ORIGEM");
        pino2 = new Pino("DESTINO");
        pino3 = new Pino("APOIO");

        for (int i = numeroDeDiscos; i >= 1; i--) {
            pino1.push(new Disco(i));
        }

        pinos = new ArrayList();
        pinos.add(pino1);
        pinos.add(pino2);
        pinos.add(pino3);

        jogadas = new ArrayList<>();
        
        intervaloEntreJogadas = 3500;

    }
    
        /**
     * Controi um jogo, e, inicialmente. empilha todos discos no pino1. 
     * 
     * @param numeroDeDiscos o numero de discos da torre.
     * @param intervaloEntreJogadas o intervalo entre as jogadas. 
     */
    public TorreDeHanoi(int numeroDeDiscos , int intervaloEntreJogadas) {

        this(numeroDeDiscos);
        this.intervaloEntreJogadas = intervaloEntreJogadas;
        
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

        StringBuilder sb = new StringBuilder();
        
        jogadas.forEach( j -> sb.append(j.toString()).append("\n") );
        
        //System.err.println(sb);

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
                
                 System.err.println(">>>"+j+"");
                 System.err.println(this.getEstado()+"\n");
                 
                try {
                    Thread.sleep(intervaloEntreJogadas);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TorreDeHanoi.class.getName()).log(Level.SEVERE, null, ex);
                }

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

        int numeroDeDiscos = 5;
        int intervaloEntreJogadas = 3000;

        if (args != null && args.length == 1) {
            try {
                numeroDeDiscos = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                throw new Error("NO NOMENTO, IMPOSSÍVEL JOGAR COM " + args[0] + " DISCO(S). TENTE NOVAMENTE. NÚMEROS INTEIROS POSITIVOS APENAS.");
            }

        }
        
        if (numeroDeDiscos <= 0) {
            throw new Error("NO MOMENTO, IMPOSSÍVEL JOGAR COM " + numeroDeDiscos + " DISCO(S). TENTE NOVAMENTE. NÚMEROS INTEIROS POSITIVOS APENAS.");
        }
        
        if (args != null && args.length == 2) {
            try {
                numeroDeDiscos = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                throw new Error("NO NOMENTO, IMPOSSÍVEL JOGAR COM " + args[0] + " DISCO(S). TENTE NOVAMENTE. NÚMEROS INTEIROS POSITIVOS APENAS.");
            }
            try {
                intervaloEntreJogadas = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                throw new Error("NO NOMENTO, IMPOSSÍVEL JOGAR COM " + args[1] + " ,POIS É INTERVALO NEGATIVO ENTRE JOGADAS. TENTE NOVAMENTE. NÚMEROS INTEIROS POSITIVOS APENAS.");
            }
            
        }
        
        if (numeroDeDiscos <= 0) {
            throw new Error("NO MOMENTO, IMPOSSÍVEL JOGAR COM " + numeroDeDiscos + " DISCO(S). TENTE NOVAMENTE. NÚMEROS INTEIROS POSITIVOS APENAS.");
        }
        
        if( intervaloEntreJogadas < 0 ){
            throw new Error("NO MOMENTO, IMPOSSÍVEL JOGAR COM " + intervaloEntreJogadas + " ,POIS É INTERVALO NEGATIVO ENTRE JOGADAS. TENTE NOVAMENTE. NÚMEROS INTEIROS POSITIVOS APENAS.");
        }
        
        TorreDeHanoi torreDeHanoi = new TorreDeHanoi(numeroDeDiscos,intervaloEntreJogadas);

        torreDeHanoi.jogar();

    }

}
