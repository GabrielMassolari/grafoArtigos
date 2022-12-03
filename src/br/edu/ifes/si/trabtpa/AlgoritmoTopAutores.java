package br.edu.ifes.si.trabtpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgoritmoTopAutores {
    private Digrafo digrafo;
    private Map<Integer, Integer> qtdCitacoesAutor = new HashMap<Integer, Integer>();

    public AlgoritmoTopAutores(Digrafo digrafo) {
        this.digrafo = digrafo;
        inserirAutoresHashMap();
    }
    
    private void inserirAutoresHashMap(){
        for(int autor : digrafo.autores()){
            qtdCitacoesAutor.put(autor, 0);
        }
    }
    
    public Map<Integer, Integer> numeroCitacoesPorAutor(){
        int autor;
        Integer valor;
        List<Aresta> arestas = digrafo.arestas();
        for(Aresta a : arestas){
            autor =  a.getV2().getAutor();
            valor = qtdCitacoesAutor.get(autor);
            qtdCitacoesAutor.put(autor, valor + 1);
        }

        return qtdCitacoesAutor;
    }
    
    
}
