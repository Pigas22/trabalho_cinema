import java.util.List;
import java.util.Scanner;

import controllers.CinemaController;
import controllers.EnderecoController;
import controllers.FilmeController;
import models.Cinema;
import models.Endereco;
import models.Filme;

/**
 * App
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
           imprimirMenu();
           opcao = scanner.nextInt();

           if (opcao == 1) {
                //metodo Relatório;
           } else if (opcao == 2) {
                imprimirMenuInserirRegistro();
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
                imprimirMenuRemoverRegistro();
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
    public static void imprimirMenu() {
        System.out.println("==================================");
        System.out.println("|          MENU PRINCIPAL        |");
        System.out.println("==================================");
        System.out.println("| 1. Relatório                   |");
        System.out.println("| 2. Inserir Registro            |");
        System.out.println("| 3. Alterar Registro            |");
        System.out.println("| 4. Remover Registro            |");
        System.out.println("| 0. Sair                        |");
        System.out.println("==================================");
        System.out.print("Escolha uma opção: ");
    }

    public static void imprimirMenuInserirRegistro() {
        System.out.println("==================================");
        System.out.println("|         INSERIR REGISTRO       |");
        System.out.println("==================================");
        System.out.println("| 1. Cinema                      |");
        System.out.println("| 2. Endereço                    |");
        System.out.println("| 3. Filme                       |");
        System.out.println("| 4. Sessão                      |");
        System.out.println("| 5. Venda                       |");
        System.out.println("| 6. Voltar                      |");
        System.out.println("==================================");
        System.out.print("Escolha uma opção: ");
    }

    public static void imprimirMenuRemoverRegistro() {
        System.out.println("==================================");
        System.out.println("|         REMOVER REGISTRO       |");
        System.out.println("==================================");
        System.out.println("| 1. Cinema                      |");
        System.out.println("| 2. Endereço                    |");
        System.out.println("| 3. Filme                       |");
        System.out.println("| 4. Sessão                      |");
        System.out.println("| 5. Venda                       |");
        System.out.println("| 6. Voltar                      |");
        System.out.println("==================================");
        System.out.print("Escolha uma opção: ");
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

        FilmeController.inserirFilme(new Filme(nome, 0));

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