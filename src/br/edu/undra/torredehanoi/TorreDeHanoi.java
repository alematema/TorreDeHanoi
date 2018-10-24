package br.edu.undra.torredehanoi;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe modela um jogo de hanoi.
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
            estado.append("\n");

        }

        return estado.toString();

    }
    
    public void joga(Pino origem, Pino destino){
        
        Disco d = origem.pop();
        
        if ( destino.podeEmpilharEsteDisco(d) ){
            
            destino.push(d);
            
            Jogada j = new Jogada(origem, destino, d);
            
            jogadas.add(j);
            
            System.err.println(j);
            
        }else{
            
            System.err.println("NAO FOI POSSIVEL JOGAR " + d.toString()+ " EM "+destino.toString());
            //devolve
            origem.push(d);
            
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
    
    public void mover()
    {
        
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

        TorreDeHanoi torreDeHanoi = new TorreDeHanoi(5);
        System.err.println(torreDeHanoi.getEstado());
        torreDeHanoi.joga(torreDeHanoi.getOrigem(), torreDeHanoi.getDestino());
        System.err.println(torreDeHanoi.getEstado());
        torreDeHanoi.joga(torreDeHanoi.getOrigem(), torreDeHanoi.getExtra());
        System.err.println(torreDeHanoi.getEstado());
        torreDeHanoi.joga(torreDeHanoi.getOrigem(), torreDeHanoi.getDestino());
        System.err.println(torreDeHanoi.getEstado());

    }

}
