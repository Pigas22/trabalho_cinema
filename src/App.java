import java.io.IOException;
import java.util.Scanner;
import utils.*;

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
            
           if (opcao == 1) {
                Menu.exibirRelatorio();

           } else if (opcao == 2) {
                Menu.imprimirMenuInserirRegistro();
                int opcaoInserir = scanner.nextInt();

                MenuFormatter.limparTerminal();

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
                              
               MenuFormatter.limparTerminal();

           } else if (opcao == 4) {
                Menu.imprimirMenuRemoverRegistro();
                int opcaoRemover = scanner.nextInt();

                MenuFormatter.limparTerminal();

                switch (opcaoRemover) {
                    case 1:
                        Menu.menuRemoverCinema();
                        break;
                    
                    case 2:
                        Menu.menuRemoverEndereco();
                        break;
                
                    default:
                        break;
                }
           }

           MenuFormatter.limparTerminal();

        } while (opcao != 0);

        MenuFormatter.msgTerminalINFO("Encerrando o sistema.");

        scanner.close();
    }
}