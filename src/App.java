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
                    case 5:
                        menuInserirVenda(scanner);
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

    public static void menuInserirEndereco(Scanner scanner) {
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

    public static void menuInserirCinema(Scanner scanner) {
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
    
    public static void menuInserirVenda(Scanner scanner) {
    List<Secao> secaos = SecaoController.listarTodosSecaos();
    if (secaos.isEmpty()) {
        System.out.println("Nenhuma seção disponível. Por favor, insira uma seção antes.");
        return;
    }

    System.out.println("Escolha uma seção pelo ID: ");
    for (Secao secao : secaos) {
        System.out.println(secao.getIdSecao() + ": " + secao.getFilme().getNomeFilme() + ": " + secao.getHorario() + ": " + secao.getCinema().getEndereco().getCidade());
    }

    int idSecao = scanner.nextInt();
    scanner.nextLine(); // Consumir a nova linha

    Secao secaoSelecionado = null;
    for (Secao secao : secaos) {
        if (secao.getIdSecao() == idSecao) {
            secaoSelecionado = secao;
            break; // Adicionado para sair do loop quando a seção for encontrada
        }
    }

    if (secaoSelecionado == null) {
        System.out.println("ID de seção inválido");
        return; // Adicionado para interromper a execução
    }

    System.out.println("Digite o nome da pessoa: ");
    String nome = scanner.nextLine();
    System.out.println("Digite o assento: ");
    int assento = scanner.nextInt();
    System.out.println("Digite a forma de pagamento: ");
    String formaPagamento = scanner.nextLine();

    VendaController.inserirVenda(new Venda(nome, assento, formaPagamento, secaoSelecionado));
}


    // metodos para remover
    public static void menuRemoverEndereco(Scanner scanner) {
        scanner.nextLine();
        List<Endereco> enderecos = EnderecoController.listarTodosEnderecos();
        if (enderecos.isEmpty()) {
            System.out.println("Nenhum endereço disponível. Por favor, insira um endereço antes.");
            return;
        }

        System.out.println("Escolha um Endereço pelo ID: ");
        for (Endereco endereco : enderecos){
            System.out.println(endereco.getIdEndereco() + ": " + endereco.getCidade() + ": " + endereco.getBairro() + ": " + endereco.getCidade());
        }

        int idEndereco = scanner.nextInt();
        Endereco enderecoSelecionado = null;
        for (Endereco endereco : enderecos) {
            if (endereco.getIdEndereco() == idEndereco) {
                enderecoSelecionado = endereco;
            }
        }

        if (enderecoSelecionado == null) {
            System.out.println("ID de endeço inválido.");
            return;
        }

        EnderecoController.excluirEndereco(idEndereco);
    }

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
    import reports.Relatorio;

// Dentro do método main
if (opcao == 1) {
    exibirRelatorio();
}

public static void exibirRelatorio() {
    LinkedList<String> cinemaEndereco = Relatorio.listarCinemaEndereco();
    LinkedList<String> informacoes = Relatorio.listarInformacoes();
    LinkedList<String> soma = Relatorio.listarSoma();

    
    System.out.println("=== Cinema e Endereço ===");
    if (cinemaEndereco != null && !cinemaEndereco.isEmpty()) {
        for (String registro : cinemaEndereco) {
            System.out.println(registro);
        }
    } else {
        System.out.println("Nenhum cinema encontrado.");
    }

    
    System.out.println("\n=== Informações ===");
    if (informacoes != null && !informacoes.isEmpty()) {
        for (String registro : informacoes) {
            System.out.println(registro);
        }
    } else {
        System.out.println("Nenhuma informação encontrada.");
    }

   
    System.out.println("\n=== Soma dos Ingressos ===");
    if (soma != null && !soma.isEmpty()) {
        for (String registro : soma) {
            System.out.println(registro);
        }
    } else {
        System.out.println("Nenhuma soma encontrada.");
    }
}


}
