package br.edu.ifes.si.trabtpa;

/**
 * Cada aresta é composta por dois Vertices
 */
public class Aresta {

    private final Vertice v1;
    private final Vertice v2;

    /**
     * Inicializa uma aresta (sem peso) entre vértices.
     * @param v1 vértice 1 (origem)
     * @param v2 vértice 2 (destino)
     * @throws IndexOutOfBoundsException se o vértice 1 ou vértice 2 forem negativos
     * @throws IllegalArgumentException se o peso for um valor não numérico
     */
    public Aresta(Vertice v1, Vertice v2) {
        if (v1.getArtigo() < 0) {
            throw new IndexOutOfBoundsException("Vértice deve ser um inteiro não negativo");
        }
        if (v1.getArtigo() < 0) {
            throw new IndexOutOfBoundsException("Vértice deve ser um inteiro não negativo");
        }
        this.v1 = v1;
        this.v2 = v2;
    }
    
    /**
     * Inicializa uma aresta entre vértices e atribui seu peso.
     * @param v1 vértice 1 (origem)
     * @param v2 vértice 2 (destino)
     * @return Vertice
     * @throws IndexOutOfBoundsException se o vértice 1 ou vértice 2 forem negativos
     * @throws IllegalArgumentException se o peso for um valor não numérico
     */
    
    public Vertice getV1() {
        return v1;
    }

    public Vertice getV2() {
        return v2;
    }

    /**
     * Retorna um vértice qualquer desta aresta (origem desta aresta).
     * @return vértice 1 desta aresta
     */
    public Vertice umVertice() {
        return getV1();
    }

    /**
     * Retorna o outro vértice da aresta. Ou seja, o vértice diferente do recebido como parâmetro.
     * @param vertice um vértice qualquer desta aresta
     * @return o vértice diferente do recebido como parâmetro.
     * @throws IllegalArgumentException se o vértico do parâmetro não for um dos vértices da aresta
     */
    public Vertice outroVertice(Vertice vertice) {
        if (vertice.equals(getV1())) {
            return getV2();
        } else if (vertice.equals(getV2())) {
            return getV1();
        } else {
            throw new IllegalArgumentException("Vértice inválido");
        }
    }


    /**
     * Retorna a representação String da aresta
     * @return uma representação String da aresta
     */
    public String toString() {
        return String.format("%d-%d", getV1().getArtigo(), getV2().getArtigo());
    }
}
