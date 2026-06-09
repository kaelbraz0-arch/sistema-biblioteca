package br.com.biblioteca.model;

import br.com.biblioteca.exception.BibliotecaException;
import java.time.LocalDate;

public abstract class ItemBiblioteca {
    private String codigo;
    private String titulo;
    private LocalDate dataAquisicao;
    private boolean disponivel;
    
    public ItemBiblioteca(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.dataAquisicao = LocalDate.now();
        this.disponivel = true;
    }
    
    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public LocalDate getDataAquisicao() { return dataAquisicao; }
    public boolean isDisponivel() { return disponivel; }
    
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    
    public void emprestar() throws BibliotecaException {
        if (!disponivel) {
            throw new BibliotecaException("Item já está emprestado!");
        }
        disponivel = false;
    }
    
    public void devolver() throws BibliotecaException {
        if (disponivel) {
            throw new BibliotecaException("Item já está disponível!");
        }
        disponivel = true;
    }
    
    public abstract String getTipoItem();
    
    @Override
    public String toString() {
        return String.format("Código: %s | Título: %s | Tipo: %s | Status: %s",
            codigo, titulo, getTipoItem(), disponivel ? "Disponível" : "Emprestado");
    }
}