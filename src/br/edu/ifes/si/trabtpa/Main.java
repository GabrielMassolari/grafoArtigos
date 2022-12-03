package br.edu.ifes.si.trabtpa;

import java.util.Map;
import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digrafo G = new Digrafo(in);
        Scanner scanner = new Scanner(System.in);
        menuAplicacao(scanner, G);
    }
    
    private static void menuAplicacao(Scanner scanner, Digrafo G){
        int opcao = 0;
        do{
            System.out.println("-- Grafo Artigos Cientificos ---\n");
            System.out.println("1 - Menor caminho entre dois artigos");
            System.out.println("2 - Todos os caminhos entre dois artigos");
            System.out.println("3 - Número de citações de cada artigo");
            System.out.println("4 - Número de citações de cada auto");
            System.out.println("5 - SAIR");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch(opcao){
                case 1:
                    menorCaminhoEntreArtigos(scanner, G);
                    break;
                case 2:
                    todosOsCaminhosEntreArtigos(scanner, G);
                    break;
                case 3:
                    citacoesPorArtigo(G);
                    break;
                case 4:
                    citacoesPorAutor(G);
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        }while(opcao != 5);
    }
    
    private static void menorCaminhoEntreArtigos(Scanner scanner, Digrafo G){
        int origem;
        int destino;
        System.out.println("-- Menor Caminho entre Artigos ---\n");
        System.out.println("Vertice de Origem: ");
        origem = scanner.nextInt();
        System.out.println("Vertice de Destino: ");
        destino = scanner.nextInt();
        
        AlgoritmoMenorQtdArtigosLidos menorCaminho = new AlgoritmoMenorQtdArtigosLidos(G, origem);
        if (menorCaminho.temCaminhoPara(origem)) {
            System.out.printf("%d para %d (%d):  ", origem, destino, menorCaminho.distanciaPara(destino));
            for (int x : menorCaminho.caminhoPara(destino)) {
                if (x == origem) {
                    System.out.print(x);
                } else {
                    System.out.print("->" + x);
                }
            }
            System.out.println();
        } else {
            System.out.printf("%d para %d (-):  não conectado\n", origem, destino);
        }
    }
    
    private static void todosOsCaminhosEntreArtigos(Scanner scanner, Digrafo G){
        int origem;
        int destino;
        System.out.println("-- Todos os Caminhos entre Artigos ---\n");
        System.out.println("Vertice de Origem: ");
        origem = scanner.nextInt();
        System.out.println("Vertice de Destino: ");
        destino = scanner.nextInt();
        
        AlgoritmoTodosCaminhos todosCaminhos1 = new AlgoritmoTodosCaminhos(G, origem, destino);
        System.out.println("# caminhos = " + todosCaminhos1.numeroDeCaminhos());
    }
    
    private static void citacoesPorArtigo(Digrafo G){
        AlgoritmoTopArtigos topArtigos = new AlgoritmoTopArtigos(G);
        int[] citacoesPorArtigo = topArtigos.numeroCitacoesArtigos();
        
        System.out.println("-- Top Artigos ---\n");
        for(int i = 0; i < G.V(); i++){
            System.out.printf("Artigo[%d]: %d \n", i, citacoesPorArtigo[i]);
        }
    }
    
    private static void citacoesPorAutor(Digrafo G){
        AlgoritmoTopAutores topAutores = new AlgoritmoTopAutores(G);
        Map<Integer, Integer> citacoesPorAutor = topAutores.numeroCitacoesPorAutor();
        
        System.out.println("-- Top Autores ---\n");
        
        citacoesPorAutor.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });
    }
    
    
    
    
}
