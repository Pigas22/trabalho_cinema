package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;  
import utils.*;
import conexion.Database;
import models.Endereco;

public class Relatorio_soma_ingresos {
    public static LinkedList<String> listarSoma() {
       
        LinkedList<String> listaSoma = new LinkedList<String>();
        String pasta = Arquivo.procuraPasta("SQL");
        String arquivo = Arquivo.lerSQL(pasta + "/" + "re_soma_ingressos.sql");
        
        int secao_id;
        String nome_filme;
        String endereco_rua;

        try (Connection conn = Database.conectar();
             Statement stmt = conn.createStatement()) {

            
            ResultSet rs = stmt.executeQuery(arquivo);
            
           
            while (rs.next()) {
                secao_id = rs.getInt("secao_id");        
                nome_filme = rs.getString("nome_filme");    
                endereco_rua = rs.getString("rua");      

               
                listaSoma.add("Seção ID: " + secao_id + ", Filme: " + nome_filme + ", Endereço: " + endereco_rua);
            }

          
            return listaSoma;

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }
}
