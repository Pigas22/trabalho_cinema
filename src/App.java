import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import controllers.CinemaController;
import controllers.EnderecoController;
import controllers.FilmeController;
import controllers.SecaoController;
import controllers.VendaController;
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
                        menuInserirSecao(scanner);
                        break;
                
                    default:
                        break;
                }
           } else if (opcao == 3) {
                Menu.imprimirMenuAlterarRegistro();
           } else if (opcao == 4) {
                Menu.imprimirMenuRemoverRegistro();
                int opcaoRemover = scanner.nextInt();
                switch (opcaoRemover) {
                    case 1:
                        menuRemoverCinema(scanner);
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

        System.out.println("Filme inserido com sucesso!");

    }

    public static void menuInserirSecao(Scanner scanner) {
        List<Cinema> cinemas = CinemaController.listarTodosCinemas();
        if (cinemas.isEmpty()) {
            System.out.println("Nenhum cinema disponível. Por favor, insira um cinema antes.");
            return;
        }
    
        List<Filme> filmes = FilmeController.listarTodosFilmes();
        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme disponível. Por favor, insira um filme antes.");
            return;
        }
    
        System.out.println("Escolha um cinema pelo ID: ");
        for (Cinema cinema : cinemas) {
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
    
        System.out.println("Escolha um filme pelo ID: ");
        for (Filme filme : filmes) {
            System.out.println(filme.getIdFilme() + ": " + filme.getNomeFilme());
        }
    
        int idFilme = scanner.nextInt();
        Filme filmeSelecionado = null;
        for (Filme filme : filmes) {
            if (filme.getIdFilme() == idFilme) {
                filmeSelecionado = filme;
            }
        }
    
        if (filmeSelecionado == null) {
            System.out.println("ID de filme inválido.");
            return;
        }
    
        System.out.println("Digite o horário da seção (formato: yyyy-MM-dd HH:mm:ss): ");
        scanner.nextLine(); // Consumir a nova linha
        String horarioStr = scanner.nextLine();
        Timestamp horario = Timestamp.valueOf(horarioStr);

        System.out.println("Digite a quantidade de assentos: ");
        scanner.nextLine();
        int qtd_assento = scanner.nextInt();
    
        //insira no banco de dados
        SecaoController.inserirSecao(new Secao(horario, cinemaSelecionado, filmeSelecionado, qtd_assento));
    
        System.out.println("Seção inserido com sucesso!");
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