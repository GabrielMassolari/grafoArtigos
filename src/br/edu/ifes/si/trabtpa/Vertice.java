package br.edu.ifes.si.trabtpa;

public class Vertice {
    private Integer artigo;
    private Integer autor;

    public Vertice(Integer artigo, Integer autor) {
        this.artigo = artigo;
        this.autor = autor;
    }

    public Integer getArtigo() {
        return artigo;
    }

    public void setArtigo(Integer artigo) {
        this.artigo = artigo;
    }

    public Integer getAutor() {
        return autor;
    }

    public void setAutor(Integer autor) {
        this.autor = autor;
    }
}
