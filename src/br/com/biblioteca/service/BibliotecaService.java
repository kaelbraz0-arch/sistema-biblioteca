package br.com.biblioteca.service;

import br.com.biblioteca.exception.BibliotecaException;
import br.com.biblioteca.model.ItemBiblioteca;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.repository.BibliotecaRepository;
import java.util.List;

public class BibliotecaService {
    private BibliotecaRepository repository;
    private EmprestimoService emprestimoService;
    
    public BibliotecaService() {
        this.repository = new BibliotecaRepository();
        this.emprestimoService = new EmprestimoService(repository);
    }
    
    public void cadastrarItem(ItemBiblioteca item) {
        repository.adicionarItem(item);
        System.out.println("Item cadastrado com sucesso: " + item.getTitulo());
    }
    
    public void cadastrarUsuario(Usuario usuario) {
        repository.adicionarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso: " + usuario.getNome());
    }
    
    public void emprestarItem(String codigoItem, String idUsuario) throws BibliotecaException {
        emprestimoService.realizarEmprestimo(codigoItem, idUsuario);
    }
    
    public void devolverItem(String codigoItem) throws BibliotecaException {
        emprestimoService.realizarDevolucao(codigoItem);
    }
    
    public void listarTodosItens() {
        List<ItemBiblioteca> itens = repository.listarTodosItens();
        if (itens.isEmpty()) {
            System.out.println("Nenhum item cadastrado.");
        } else {
            itens.forEach(System.out::println);
        }
    }
    
    public void buscarItem(String codigo) {
        repository.buscarItemPorCodigo(codigo)
            .ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Item não encontrado.")
            );
    }
    
    public void mostrarItensDisponiveis() {
        List<ItemBiblioteca> disponiveis = repository.buscarItensDisponiveis();
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum item disponível.");
        } else {
            disponiveis.forEach(System.out::println);
        }
    }
    
    public void mostrarItensEmprestados() {
        List<ItemBiblioteca> emprestados = repository.buscarItensEmprestados();
        if (emprestados.isEmpty()) {
            System.out.println("Nenhum item emprestado.");
        } else {
            emprestados.forEach(System.out::println);
        }
    }
}