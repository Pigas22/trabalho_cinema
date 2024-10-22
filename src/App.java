import java.util.List;
import java.util.Scanner;

import controllers.CinemaController;
import controllers.EnderecoController;
import controllers.FilmeController;
import models.*;
import utils.*;

/**
 * App
 */
public class App {
    public final static boolean DEBUG = false;
    public static void main(String[] args) {
        if (DEBUG) {
            Arquivo.setLog(DEBUG);
        }

        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
           Menu.imprimirMenu();
           opcao = scanner.nextInt();

           if (opcao == 1) {
                //metodo Relatório;
           } else if (opcao == 2) {
                Menu.imprimirMenuInserirRegistro();
                int opcaoInserir = scanner.nextInt();
                switch (opcaoInserir) {
                    case 1:
                        menuInserirCinema(scanner);
                        break;
                    case 2:
                        menuInserirEndereco(scanner);
                        break;
                    case 3:
                        menuInserirFilme(scanner);
                        break;
                    case 4:
                        
                        break;
                
                    default:
                        break;
                }
           } else if (opcao == 3) {
            // Alterar Registro
           } else if (opcao == 4) {
                Menu.imprimirMenuRemoverRegistro();
                int opcaoRemover = scanner.nextInt();
                switch (opcaoRemover) {
                    case 1:
                        
                        break;
                
                    default:
                        break;
                }
           }

        } while (opcao != 0);
    }

    public static void menuInserirEndereco(Scanner scanner){
        scanner.nextLine();
        System.out.print("Digite o número: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite a rua: ");
        String rua = scanner.nextLine();
        System.out.print("Digite o bairro: ");
        String bairro = scanner.nextLine();
        System.out.print("Digite a cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Digite o UF: ");
        String uf = scanner.nextLine();

        EnderecoController.inserirEndereco(new Endereco(numero, rua, bairro, cidade, uf));
        
        System.out.println("Endereço inserido com sucesso!");
    }

    public static void menuInserirCinema(Scanner scanner){
        scanner.nextLine();
        System.out.println("Digite o nome: ");
        String nome = scanner.nextLine();

        List<Endereco> enderecos =  EnderecoController.listarTodosEnderecos();
        if (enderecos.isEmpty()) {
            System.out.println("Nenhum endereço disponível. Por favor, insira um endereço antes.");
            return;
        }

        System.out.println("Escolha um endereço pelo ID:");
        for (Endereco endereco : enderecos) {
            System.out.println(endereco.getIdEndereco() + ": " + endereco.getRua() + ", " + endereco.getBairro() + ", " + endereco.getCidade() + " - " + endereco.getUf());
        }

        int idEndereco = scanner.nextInt();
        Endereco enderecoSelecionado = null;
        for(Endereco endereco : enderecos) {
            if (endereco.getIdEndereco() == idEndereco) {
                enderecoSelecionado = endereco;
            }
        }

        if (enderecoSelecionado == null) {
            System.out.println("ID de endereço inválido.");
            return;
        }

        CinemaController.inserirCinema(new Cinema(nome, enderecoSelecionado));
    }

    public static void menuInserirFilme(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Digite o nome do Filme: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o preço do Filme: ");
        Double preco = scanner.nextDouble();

        FilmeController.inserirFilme(new Filme(nome, preco));

        System.out.println("Endereço inserido com sucesso!");

    }

    // metodos para remover
    public static void menuRemoverCinema(Scanner scanner){
        scanner.nextLine();
        List<Cinema> cinemas = CinemaController.listarTodosCinemas();
        if (cinemas.isEmpty()) {
            System.out.println("Nenhum cinema disponível. Por favor, insira um cinema antes.");
            return;
        }

        System.out.println("Escolha um Cinema pelo ID: ");
        for (Cinema cinema : cinemas){
            System.out.println(cinema.getIdCinema() + ": " + cinema.getNomeCinema());
        }

        int idCinema = scanner.nextInt();
        Cinema cinemaSelecionado = null;
        for (Cinema cinema : cinemas) {
            if (cinema.getIdCinema() == idCinema) {
                cinemaSelecionado = cinema;
            }
        }

        if (cinemaSelecionado == null) {
            System.out.println("ID de cinema inválido.");
            return;
        }

        CinemaController.excluirCinema(idCinema);

    }

}