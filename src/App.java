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
}