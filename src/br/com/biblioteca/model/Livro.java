package br.com.biblioteca.model;

public class Livro extends ItemBiblioteca {
    private String autor;
    private int anoPublicacao;
    private String isbn;
    
    public Livro(String codigo, String titulo, String autor, int anoPublicacao, String isbn) {
        super(codigo, titulo);
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.isbn = isbn;
    }
    
    public String getAutor() { return autor; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public String getIsbn() { return isbn; }
    
    @Override
    public String getTipoItem() {
        return "Livro";
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Autor: %s | Ano: %d | ISBN: %s",
            autor, anoPublicacao, isbn);
    }
}