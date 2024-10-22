package reports;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import conexion.Database;
import utils.Arquivo;
import utils.MenuFormatter;

public class Relatorio {
    private static final String PASTA = Arquivo.procuraPasta("SQL");

    public static LinkedList<String> listarCinemaEndereco () {
        LinkedList<String> listaCinema = new LinkedList<String>();
        String arquivo = Arquivo.lerSQL(PASTA + "/" + "re_endereco_cinema.sql");

        String nome_cinema, endereco_rua;

        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(arquivo);
            while (rs.next()) {
                nome_cinema = rs.getString("nome_cinema");
                endereco_rua = rs.getString("rua");

                listaCinema.add("Cinema: " + nome_cinema + ", Endereço: " + endereco_rua);
            }

            return listaCinema;

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static LinkedList<String> listarInformacoes() {   
        LinkedList<String> listarInformacoes = new LinkedList<String>();
        String arquivo = Arquivo.lerSQL(PASTA + "/" + "re_informacao.sql");
       
        String horario;
        int qtd_assentos;
        String nome_filme;
        double preco;

        try (Connection conn = Database.conectar();  
             Statement stmt = conn.createStatement()) { 

            ResultSet rs = stmt.executeQuery(arquivo);       
            while (rs.next()) {
                horario = rs.getString("horario");            
                qtd_assentos = rs.getInt("qtd_assentos");       
                nome_filme = rs.getString("nome_filme");        
                preco = rs.getDouble("preco");                   
                
                listarInformacoes.add("Horário: " + horario + ", Filme: " + nome_filme + 
                              ", Quantidade de Assentos: " + qtd_assentos + ", Preço: R$" + preco);
            }
            return listarInformacoes;

        } catch (SQLException e) {
            // Caso ocorra um erro durante a consulta ou processamento dos dados
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }
    
    public static LinkedList<String> listarSoma() {
        LinkedList<String> listaSoma = new LinkedList<String>();
        String arquivo = Arquivo.lerSQL(PASTA + "/" + "re_soma_ingressos.sql");
        
        int secaoID;
        String nomeFilme;
        String valorFinal;

        try (Connection conn = Database.conectar();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(arquivo);
            while (rs.next()) {
                secaoID = rs.getInt("id_secao");        
                nomeFilme = rs.getString("nome_filme");    
                valorFinal = rs.getString("valor_final");      

               
                listaSoma.add("Seção ID: " + secaoID + ", Filme: " + nomeFilme + ", Valor Arrecadado: " + valorFinal);
            }

            return listaSoma;

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        LinkedList<String> cinemaEndereco = listarCinemaEndereco();
        LinkedList<String> informacoes = listarInformacoes();
        LinkedList<String> soma = listarSoma();
        
        System.out.println("Cinema X Endereco");
        for (int i = 0; i < cinemaEndereco.size(); i++) {
            System.out.println(cinemaEndereco.get(i));
        }
        
        System.out.println("\nInformações");
        for (int i = 0; i < informacoes.size(); i++) {
            System.out.println(informacoes.get(i));
        }
        
        System.out.println("\nSoma");
        for (int i = 0; i < soma.size(); i++) {
            System.out.println(soma.get(i));
        }
    }
}
