/*******************************************************************************
 *  Compilação:        javac AlgoritmoDFSDigrafo.java
 *  Execução:          java AlgoritmoDFSDigrafo dados.txt vo
 *  Dependências:      Digrafo.java Pilha.java
 *  Arquivos de dados: Digrafo1.txt
 *  Link dos dados:    https://drive.google.com/open?id=0B3q56TwNCeXoc2tlbllCRmo1MTQ
 * 
 *  Determina acessibilidade em um dígrafo de um dado vértice utilizando busca em profundidade
 *
 *  % java AlgoritmoDFSDigrafo Digrafo1.txt 3
 *  3 to 0:  3-5-4-2-0
 *  3 to 1:  3-5-4-2-0-1
 *  3 to 2:  3-5-4-2
 *  3 to 3:  3
 *  3 to 4:  3-5-4
 *  3 to 5:  3-5
 *  3 to 6:  not connected
 *  3 to 7:  not connected
 *  3 to 8:  not connected
 *  3 to 9:  not connected
 *  3 to 10:  not connected
 *  3 to 11:  not connected
 *  3 to 12:  not connected
 * 
 * 3 para 0:  3-2-0
 * 3 para 1:  3-2-0-1
 * 3 para 2:  3-2
 * 3 para 3:  3
 * 3 para 4:  3-2-0-5-4
 * 3 para 5:  3-2-0-5
 * 3 para 6:  não conectado
 * 3 para 7:  não conectado
 * 3 para 8:  não conectado
 * 3 para 9:  não conectado
 * 3 para 10:  não conectado
 * 3 para 11:  não conectado
 * 3 para 12:  não conectado
 *
 ******************************************************************************/

package br.edu.ifes.si.trabtpa;

/**
 * Esta classe implementa o algoritmo de busca em profundidade em um dígrafo.
 * Para documentação adicional, acesse:
 * <a href="http://algs4.cs.princeton.edu/42digraph">Section 4.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class AlgoritmoTodosCaminhos {
     private boolean[] noCaminho;        // vértices no caminho atual
    private Pilha<Integer> caminho;     // o caminho atual
    private int numeroDeCaminhos;       // número de caminhos simples

    // mostra todos os caminhos simples de vo (vértice origem) para vd (vértice destino) - usando DFS
    public AlgoritmoTodosCaminhos(Digrafo G, int vo, int vd) {
        noCaminho = new boolean[G.V()];
        caminho   = new Pilha<Integer>();
        dfs(G, vo, vd);
    }

    // usando a ideia de exploração do método DFS
    private void dfs(Digrafo G, int v, int vd) {

        // adiciona v ao caminho atual
        caminho.empilha(v);
        noCaminho[v] = true;

        // encontrado caminho de v para vd (vértice destino)
        if (v == vd) {
            imprimeCaminhoAtual();
            numeroDeCaminhos++;
        }

        // considerar todos os vizinhos que continuariam o caminho
        else {
            for (Aresta a : G.adj(v)) {
                int x = a.getV2().getArtigo();
                if (!noCaminho[x])
                    dfs(G, x, vd);
            }
        }

        // feita a exploração de v, então o remove do caminho
        caminho.desempilha();
        noCaminho[v] = false;
    }

    // esta implementação simplesmente imprime o caminho
    private void imprimeCaminhoAtual() {
        Pilha<Integer> pilhaInvertida = new Pilha<Integer>();
        for (int v : caminho)
            pilhaInvertida.empilha(v);
        if (pilhaInvertida.tamanho() >= 1)
            System.out.print(pilhaInvertida.desempilha());
        while (!pilhaInvertida.isEmpty())
            System.out.print("-" + pilhaInvertida.desempilha());
        System.out.println();
    }

    // retorna o número de caminhos simples entre vo (vértice origem) e vd (vértice destino)
    public int numeroDeCaminhos() {
        return numeroDeCaminhos;
    }

}
