/*******************************************************************************
 *  Compilação:        javac Digrafo.java
 *  Execução:          java Digrafo dados.txt
 *  Dependências:      Aresta.java
 *  Arquivos de dados: Digrafo1.txt
 *  Link dos dados:    https://drive.google.com/open?id=0B3q56TwNCeXoc2tlbllCRmo1MTQ
 *
 *  Um dígrafo implementado utilizando listas de adjacências.
 *
 *  % java Digrafo Digrafo1.txt
 *  13 22
 *  0: 1  5  
 *  1: 
 *  2: 3  0  
 *  3: 2  5  
 *  4: 2  3  
 *  5: 4  
 *  6: 0  8  4  9  
 *  7: 9  6  
 *  8: 6  
 *  9: 10  11  
 *  10: 12  
 *  11: 12  4  
 *  12: 9  
 *
 ******************************************************************************/

package br.edu.ifes.si.trabtpa;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe implementa a representação do dígrafo com lista de adjacências.
 * Para documentação adicional, acesse:
 * <a href="http://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class Digrafo {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;         // número de vértices no dígrafo
    private int A;               // número de arestas no dígrafo
    private List<Aresta>[] adj;  // adj[v1] = lista de adjacência do vértice v1
    private List<Vertice> vert = new ArrayList<Vertice>();
    private List<Integer> autores = new ArrayList<Integer>();
    
    /**
     * Inicializa um dígrafo com V vertices e 0 arestas.
     * @param  V o número de vértices
     * @throws IllegalArgumentException se V < 0
     */
    public Digrafo(int V) {
        if (V < 0) throw new IllegalArgumentException("Número de vértices no dígrafo deve ser não negativo");
        this.V = V;
        this.A = 0;
        adj = new ArrayList[V];
        for (int v = 0; v < V; v++)
            adj[v] = new ArrayList<>();
    }


    /**  
     * Inicializa um dígrafo à partir de um arquivo de dados.
     * O formato é o número de vértices V e o número de arestas A
     * seguido por pares de pares de vértices.
     * @param  in o arquivo de entrada de dados
     * @throws IndexOutOfBoundsException se os pontos finais de qualquer borda estão fora da área prescrita
     * @throws IllegalArgumentException se o número de vértices ou arestas for negativo
     */
    public Digrafo(In in) {
        this(in.readInt());
        int A = in.readInt();
        if (A < 0) throw new IllegalArgumentException("Número de arestas deve ser não negativo");
        // Lendo os vertices
        for (int i = 0; i < V; i++) {
            int artigo = in.readInt();
            int autor = in.readInt();
            if (artigo < 0 || artigo >= V) throw new IndexOutOfBoundsException("vértice " + artigo + " não está entre 0 e " + (V-1));
            if(vert.size() == artigo){
                vert.add(new Vertice(artigo, autor));
                inserirAutorLista(autor);
            }else{
                throw new IndexOutOfBoundsException("Artigo " + artigo + " não está na ordem");
            }
        }
        //Lendo as Arestas
        for (int i = 0; i < A; i++) {
            int i_v1 = in.readInt();
            int i_v2 = in.readInt();
            if (i_v1 < 0 || i_v1 >= V) throw new IndexOutOfBoundsException("vértice " + i_v1 + " não está entre 0 e " + (V-1));
            if (i_v2 < 0 || i_v2 >= V) throw new IndexOutOfBoundsException("vértice " + i_v2 + " não está entre 0 e " + (V-1));
            Vertice v1 = vert.get(i_v1);
            Vertice v2 = vert.get(i_v2);
            addAresta(new Aresta(v1, v2));
        }
        
        
    }
    
    private void inserirAutorLista(Integer autor){
        if(!autores.contains(autor)){
            autores.add(autor);
        }
    }

    /**
     * Retorna o número de vértices do dígrafo.
     * @return o número de vértices do dígrafo
     */
    public int V() {
        return V;
    }

    /**
     * Retorna o número de arestas do dígrafo.
     * @return o número de arestas do dígrafo
     */
    public int A() {
        return A;
    }

    /**
     * Valida vértice do dígrafo.
     * @throws IndexOutOfBoundsException caso v não seja 0 <= v < V
     */
    private void validaVertice(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vértice " + v + " não está entre 0 e " + (V-1));
    }

    /**
     * Adiciona aresta direcionada a no dígrafo.
     * @param  a a aresta
     * @throws IndexOutOfBoundsException caso extremidades não estejam entre 0 e V-1
     */
    public void addAresta(Aresta a) {
        int v1 = a.getV1().getArtigo();
        int v2 = a.getV2().getArtigo();
        validaVertice(v1);
        validaVertice(v2);
        adj[v1].add(a);
        A++;
    }


    /**
     * Retorna as arestas incidentes no vértice v1.
     * @param  v o vértice
     * @return as arestas incidentes no vértice v1 como um Iterable
     * @throws IndexOutOfBoundsException caso v não seja 0 <= v < V
     */
    public List<Aresta> adj(int v) {
        validaVertice(v);
        return adj[v];
    }
    
    public List<Integer> autores(){
        return autores;
    }


    /**
     * Retorna todas as arestas neste dígrafo.
     * @return todas as arestas neste dígrafo, como um Iterable
     */
    public List<Aresta> arestas() {
        List<Aresta> lista = new ArrayList();
        for (int v = 0; v < V; v++) {
            for (Aresta a : adj(v)) {
                lista.add(a);
            }
        }
        return lista;
    } 

    /**
     * Retorna uma representação String deste dígrafo.
     * @return uma representação String deste dígrafo
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + A + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Aresta a : adj[v]) {
                s.append(a + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * Testa a classe Digrafo.
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digrafo G = new Digrafo(in);
        System.out.println(G);
    }

}