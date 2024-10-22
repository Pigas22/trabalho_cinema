package utils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import utils.*;
import conexion.Database;
import models.Endereco;

public class Relatorio_endereco_cinema{
    public static LinkedList<String> listarCinemaEndereco () {
        LinkedList<String> listaCinema = new LinkedList<String>();
        String pasta=Arquivo.procuraPasta("SQL");
        String arquivo=Arquivo.lerSQL(pasta+"/"+"re_endereco_cinema.sql");
        String nome_cinema, endereco_rua;
        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(arquivo);
            while (rs.next()) {
                nome_cinema = rs.getString("nome_cinema");
                endereco_rua = rs.getString("endereco_rua");
                

                listaCinema.add(nome_cinema);
                listaCinema.add(endereco_rua);
            }

            return listaCinema;

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }
}
