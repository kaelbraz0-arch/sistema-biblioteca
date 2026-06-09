package br.com.biblioteca.model;

public class Revista extends ItemBiblioteca {
    private String edicao;
    private String mesReferencia;
    
    public Revista(String codigo, String titulo, String edicao, String mesReferencia) {
        super(codigo, titulo);
        this.edicao = edicao;
        this.mesReferencia = mesReferencia;
    }
    
    public String getEdicao() { return edicao; }
    public String getMesReferencia() { return mesReferencia; }
    
    @Override
    public String getTipoItem() {
        return "Revista";
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | Edição: %s | Mês: %s",
            edicao, mesReferencia);
    }
}