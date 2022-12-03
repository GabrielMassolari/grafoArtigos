package br.edu.ifes.si.trabtpa;

import java.util.Arrays;
import java.util.List;

public class AlgoritmoTopArtigos {
    private int[] qtdCitacoesArtigos;
    private Digrafo digrafo;
    
    public AlgoritmoTopArtigos(Digrafo G) {
        qtdCitacoesArtigos = new int[G.V()];
        Arrays.fill(qtdCitacoesArtigos, 0);
        digrafo = G;
    }
    
    public int[] numeroCitacoesArtigos(){
        int artigo;
        List<Aresta> arestas = digrafo.arestas();
        for(Aresta a : arestas){
            artigo =  a.getV2().getArtigo();
            qtdCitacoesArtigos[artigo]++;
        }
        return qtdCitacoesArtigos;
    }
}
