package br.com.biblioteca.service;

import br.com.biblioteca.exception.BibliotecaException;
import br.com.biblioteca.model.ItemBiblioteca;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.repository.BibliotecaRepository;

public class EmprestimoService {
    private BibliotecaRepository repository;
    
    public EmprestimoService(BibliotecaRepository repository) {
        this.repository = repository;
    }
    
    public void realizarEmprestimo(String codigoItem, String idUsuario) throws BibliotecaException {
        ItemBiblioteca item = repository.buscarItemPorCodigo(codigoItem)
            .orElseThrow(() -> new BibliotecaException("Item não encontrado!"));
        
        Usuario usuario = repository.buscarUsuarioPorId(idUsuario)
            .orElseThrow(() -> new BibliotecaException("Usuário não encontrado!"));
        
        item.emprestar();
        usuario.adicionarAoHistorico(codigoItem);
        System.out.println("Empréstimo realizado com sucesso!");
        System.out.println("Item: " + item.getTitulo());
        System.out.println("Usuário: " + usuario.getNome());
    }
    
    public void realizarDevolucao(String codigoItem) throws BibliotecaException {
        ItemBiblioteca item = repository.buscarItemPorCodigo(codigoItem)
            .orElseThrow(() -> new BibliotecaException("Item não encontrado!"));
        
        item.devolver();
        System.out.println("Devolução realizada com sucesso!");
    }
}