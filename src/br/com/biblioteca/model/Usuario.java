package br.com.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String id;
    private String nome;
    private String email;
    private List<String> historicoEmprestimos;
    
    public Usuario(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.historicoEmprestimos = new ArrayList<>();
    }
    
    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public List<String> getHistoricoEmprestimos() { return historicoEmprestimos; }
    
    public void adicionarAoHistorico(String codigoItem) {
        historicoEmprestimos.add(codigoItem);
    }
    
    @Override
    public String toString() {
        return String.format("ID: %s | Nome: %s | Email: %s", id, nome, email);
    }
}