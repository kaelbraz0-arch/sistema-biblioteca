package br.com.biblioteca;

import br.com.biblioteca.exception.BibliotecaException;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.model.Revista;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.service.BibliotecaService;
import java.util.Scanner;

public class Main {
    private static BibliotecaService bibliotecaService = new BibliotecaService();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        inicializarDados();
        
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("   Digite o número da sua opção: ");
            
            try {
                switch (opcao) {
                    case 1 -> cadastrarItem();
                    case 2 -> cadastrarUsuario();
                    case 3 -> bibliotecaService.listarTodosItens();
                    case 4 -> buscarItem();
                    case 5 -> emprestarItem();
                    case 6 -> devolverItem();
                    case 7 -> bibliotecaService.mostrarItensDisponiveis();
                    case 8 -> bibliotecaService.mostrarItensEmprestados();
                    case 9 -> System.out.println("Saindo do sistema...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (BibliotecaException e) {
                System.out.println("Erro: " + e.getMessage());
            }
            
        } while (opcao != 9);
        
        scanner.close();
    }
    
      private static void exibirMenu() { 
    System.out.println("\n╔═════════════════════════════════════╗");
 System.out.println("║       SISTEMA DE BIBLIOTECA         ║");
 System.out.println("╠═════════════════════════════════════╣");
 System.out.println("║  1. Cadastrar Item (Livro/Revista)  ║");
 System.out.println("║  2. Cadastrar Usuário               ║");
 System.out.println("║  3. Listar Todos os Itens           ║");
 System.out.println("║  4. Buscar Item por Código          ║");
 System.out.println("║  5. Emprestar Item                  ║");
 System.out.println("║  6. Devolver Item                   ║");
 System.out.println("║  7. Mostrar Itens Disponíveis       ║");
 System.out.println("║  8. Mostrar Itens Emprestados       ║");
 System.out.println("║  9. Sair                            ║");
 System.out.println("╚═════════════════════════════════════╝");
 
      }  
    private static void cadastrarItem() {
        System.out.println("\n--- Cadastro de Item ---");
        System.out.println("1. Livro");
        System.out.println("2. Revista");
        int tipo = lerInteiro("Tipo de item: ");
        
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        
        if (tipo == 1) {
            System.out.print("Autor: ");
            String autor = scanner.nextLine();
            int ano = lerInteiro("Ano de publicação: ");
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            
            bibliotecaService.cadastrarItem(new Livro(codigo, titulo, autor, ano, isbn));
        } else if (tipo == 2) {
            System.out.print("Edição: ");
            String edicao = scanner.nextLine();
            System.out.print("Mês de referência: ");
            String mes = scanner.nextLine();
            
            bibliotecaService.cadastrarItem(new Revista(codigo, titulo, edicao, mes));
        } else {
            System.out.println("Tipo inválido!");
        }
    }
    
    private static void cadastrarUsuario() {
        System.out.println("\n--- Cadastro de Usuário ---");
        System.out.print("ID do usuário: ");
        String id = scanner.nextLine();
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        bibliotecaService.cadastrarUsuario(new Usuario(id, nome, email));
    }
    
    private static void buscarItem() {
        System.out.print("\nDigite o código do item: ");
        String codigo = scanner.nextLine();
        bibliotecaService.buscarItem(codigo);
    }
    
    private static void emprestarItem() throws BibliotecaException {
        System.out.println("\n--- Empréstimo ---");
        System.out.print("Código do item: ");
        String codigoItem = scanner.nextLine();
        System.out.print("ID do usuário: ");
        String idUsuario = scanner.nextLine();
        
        bibliotecaService.emprestarItem(codigoItem, idUsuario);
    }
    
    private static void devolverItem() throws BibliotecaException {
        System.out.println("\n--- Devolução ---");
        System.out.print("Código do item: ");
        String codigoItem = scanner.nextLine();
        
        bibliotecaService.devolverItem(codigoItem);
    }
    
    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
    
    private static void inicializarDados() {
        bibliotecaService.cadastrarItem(new Livro("L001", "Clean Code", "Robert Martin", 2008, "9780132350884"));
        bibliotecaService.cadastrarItem(new Livro("L002", "Design Patterns", "Erich Gamma", 1994, "9780201633610"));
        bibliotecaService.cadastrarItem(new Revista("R001", "Veja", "Ed. 2756", "Janeiro/2024"));
        bibliotecaService.cadastrarUsuario(new Usuario("U001", "João Silva", "joao@email.com"));
        bibliotecaService.cadastrarUsuario(new Usuario("U002", "Maria Santos", "maria@email.com"));
    }
}
