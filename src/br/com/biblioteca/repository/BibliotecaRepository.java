package br.com.biblioteca.repository;

import br.com.biblioteca.model.ItemBiblioteca;
import br.com.biblioteca.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BibliotecaRepository {
    private List<ItemBiblioteca> itens;
    private List<Usuario> usuarios;
    
    public BibliotecaRepository() {
        this.itens = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }
    
    public void adicionarItem(ItemBiblioteca item) {
        itens.add(item);
    }
    
    public List<ItemBiblioteca> listarTodosItens() {
        return new ArrayList<>(itens);
    }
    
    public Optional<ItemBiblioteca> buscarItemPorCodigo(String codigo) {
        return itens.stream()
            .filter(item -> item.getCodigo().equalsIgnoreCase(codigo))
            .findFirst();
    }
    
    public List<ItemBiblioteca> buscarItensDisponiveis() {
        return itens.stream()
            .filter(ItemBiblioteca::isDisponivel)
            .collect(Collectors.toList());
    }
    
    public List<ItemBiblioteca> buscarItensEmprestados() {
        return itens.stream()
            .filter(item -> !item.isDisponivel())
            .collect(Collectors.toList());
    }
    
    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }
    
    public Optional<Usuario> buscarUsuarioPorId(String id) {
        return usuarios.stream()
            .filter(usuario -> usuario.getId().equalsIgnoreCase(id))
            .findFirst();
    }
    
    public List<Usuario> listarTodosUsuarios() {
        return new ArrayList<>(usuarios);
    }
}