package reports;

import java.sql.*;

import conexion.Database;
import utils.*;

public class Relatorio {
    private static final String PASTA = Arquivo.procuraPasta("SQL");

    public static String listarCinemaEndereco () {
        String arquivo = Arquivo.lerSQL(PASTA + "/" + "re_endereco_cinema.sql");
        String[] cabecalho = {"Cinema", "Endereço (Rua)"};

        int qtdResultado = contarRegistros("SELECT COUNT(*) AS resultado FROM ("+ arquivo +")");

        String[] linhas = new String[qtdResultado];
        int tamanho = MenuFormatter.getNumEspacamentoUni()+2;

        String nomeCinema, enderecoRua;

        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(arquivo);
            int cont = 0;
            while (rs.next()) {
                nomeCinema = rs.getString("nome_cinema");
                enderecoRua = rs.getString("rua");

                String[] linha = {nomeCinema, enderecoRua};
                linhas[cont] = MenuFormatter.criarLinhaTabela(linha, tamanho);
                cont++;
            }

            return MenuFormatter.criaTabelaCompleta(cabecalho, linhas, tamanho);

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static String listarInformacoes() {   
        String arquivo = Arquivo.lerSQL(PASTA + "/" + "re_informacao.sql");
        String[] cabecalho = {"Horário", "Filme", "Qtd Assentos", "Preço (R$)"};

        int qtdResultado = contarRegistros("SELECT COUNT(*) AS resultado FROM (" + arquivo + ")");

        String[] linhas = new String[qtdResultado];
        int tamanho = MenuFormatter.getNumEspacamentoUni()+2;
       
        String horario, nomeFilme;
        int qtdAssentos;
        double preco;

        try (Connection conn = Database.conectar();  
             Statement stmt = conn.createStatement()) { 

            ResultSet rs = stmt.executeQuery(arquivo);     
            int cont = 0;  
            while (rs.next()) {
                horario = rs.getString("horario");            
                qtdAssentos = rs.getInt("qtd_assentos");       
                nomeFilme = rs.getString("nome_filme");        
                preco = rs.getDouble("preco");  
                
                String[] linha = {horario, nomeFilme, qtdAssentos+"", preco+""};
                linhas[cont] = MenuFormatter.criarLinhaTabela(linha, tamanho);
                cont++;
            }

            return MenuFormatter.criaTabelaCompleta(cabecalho, linhas, tamanho);

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }
    
    public static String listarSomaIngressos () {
        String arquivo = Arquivo.lerSQL(PASTA + "/" + "re_soma_ingressos.sql");
        String[] cabecalho = {"ID Seção", "Nome Filme", "Valor Total"};

        int qtdResultado = contarRegistros("SELECT COUNT(*) AS resultado FROM ("+ arquivo +")");

        String[] linhas = new String[qtdResultado];
        int tamanho = MenuFormatter.getNumEspacamentoUni()+2;

        int secaoID;
        String nomeFilme, valorFinal;

        try (Connection conn = Database.conectar();
        Statement stmt = conn.createStatement()) {
            
            ResultSet rs = stmt.executeQuery(arquivo);
            int cont = 0;
            while (rs.next()) {
                secaoID = rs.getInt("id_secao");        
                nomeFilme = rs.getString("nome_filme");    
                valorFinal = rs.getString("valor_final");      

                String[] linha = {secaoID+"", nomeFilme, valorFinal};
                linhas[cont] = MenuFormatter.criarLinhaTabela(linha, tamanho);
                cont++;
            }

            return MenuFormatter.criaTabelaCompleta(cabecalho, linhas, tamanho);

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }

    public static int contarRegistros (String sql) {
        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {
            
            int qtd = 0;
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                qtd = rs.getInt("resultado");
            }

            return qtd;

        } catch (SQLException e) {
            return -999;
        }
    }
}
