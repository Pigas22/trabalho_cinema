import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

import utils.*;
import reports.*;

/**
 * App
 */
public class App {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Arquivo.setLog(false);
        
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
           Menu.imprimirMenu();
           opcao = scanner.nextInt();

           MenuFormatter.limparTerminal();
           Menu.splashScreen();
            
           if (opcao == 1) {
                Menu.exibirRelatorio();

           } else if (opcao == 2) {
                Menu.imprimirMenuInserirRegistro();
                int opcaoInserir = scanner.nextInt();

                MenuFormatter.limparTerminal();
                Menu.splashScreen();

                switch (opcaoInserir) {
                    case 1:
                        Menu.menuInserirCinema();
                        break;
                    case 2:
                        Menu.menuInserirEndereco();
                        break;
                    case 3:
                        Menu.menuInserirFilme();
                        break;
                    case 4:
                        Menu.menuInserirSecao();
                        break;
                    case 5:
                        Menu.menuInserirVenda();
                        break;
                
                    default:
                        break;
                }

           } else if (opcao == 3) {
               Menu.imprimirMenuAlterarRegistro();
               int opcaoAlterar = scanner.nextInt();
                              
               MenuFormatter.limparTerminal();
               Menu.splashScreen();

               switch (opcaoAlterar) {
                case 1:
                    Menu.menuAlterarCinema();
                    break;
                
                case 2:
                    Menu.menuAlterarEndereco();
                    break;
                
                case 3:
                    Menu.menuAlterarFilme();
                    break;
                
                case 4:
                    Menu.menuAlterarSecao();
                    break;
                
                case 5:
                    Menu.menuAlterarVenda();
                    break;
            
                default:
                    break;
            }


               switch (opcaoAlterar) {
                    case 1:
                        
                        break;
                    case 2:
                        
                        break;
                    case 3:
                        
                        break;
                    case 4:
                        
                        break;
                    case 5:
                        
                        break;
                
                    default:
                        break;
               }

           } else if (opcao == 4) {
                Menu.imprimirMenuRemoverRegistro();
                int opcaoRemover = scanner.nextInt();

                MenuFormatter.limparTerminal();
                Menu.splashScreen();

                switch (opcaoRemover) {
                    case 1:
                        Menu.menuRemoverCinema();
                        break;
                    case 2:
                        Menu.menuRemoverEndereco();
                        break;
                    case 3:
                        Menu.menuRemoverFilme();
                        break;
                    case 4:
                        Menu.menuRemoverSecao();
                        break;
                    case 5:
                        Menu.menuRemoverVenda();
                        break;
                
                    default:
                        break;
                }
           }

           MenuFormatter.limparTerminal();
           Menu.splashScreen();

        } while (opcao != 0);

        MenuFormatter.msgTerminalINFO("Encerrando o sistema.");

        scanner.close();
    }
    public static void exibirRelatorio(Scanner scanner) {
    System.out.println("Escolha um relatório para exibir:");
    System.out.println("1. Cinema e Endereço");
    System.out.println("2. Informações");
    System.out.println("3. Soma dos Ingressos");
    System.out.print("Digite sua opção (1-3): ");
    int opcaoRelatorio = scanner.nextInt();

    switch (opcaoRelatorio) {
        case 1:
            mostrarCinemaEndereco();
            break;
        case 2:
            mostrarInformacoes();
            break;
        case 3:
            mostrarSomaIngressos();
            break;
        default:
            System.out.println("Opção inválida. Tente novamente.");
            break;
    }
}

private static void mostrarCinemaEndereco() {
    LinkedList<String> cinemaEndereco = Relatorio.listarCinemaEndereco();
    
    System.out.println("=== Cinema e Endereço ===");
    if (cinemaEndereco != null && !cinemaEndereco.isEmpty()) {
        for (String registro : cinemaEndereco) {
            System.out.println(registro);
        }
    } else {
        System.out.println("Nenhum cinema encontrado.");
    }
}

private static void mostrarInformacoes() {
    LinkedList<String> informacoes = Relatorio.listarInformacoes();

    System.out.println("\n=== Informações ===");
    if (informacoes != null && !informacoes.isEmpty()) {
        for (String registro : informacoes) {
            System.out.println(registro);
        }
    } else {
        System.out.println("Nenhuma informação encontrada.");
    }
}

private static void mostrarSomaIngressos() {
    LinkedList<String> soma = Relatorio.listarSoma();

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
