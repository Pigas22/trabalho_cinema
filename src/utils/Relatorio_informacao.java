package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;  
import conexion.Database;
import utils.Arquivo;

public class Relatorio_informacao {
    
    public static LinkedList<String> listarInformacoes() {
       
        LinkedList<String> listarInformacoes = new LinkedList<String>();
        
       
        String pasta = Arquivo.procuraPasta("SQL");
        String arquivo = Arquivo.lerSQL(pasta + "/" + "re_informacao.sql");
       
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
}
