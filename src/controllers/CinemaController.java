package controllers;

import conexion.Database;
import models.Cinema;
import models.Endereco;
import utils.MenuFormatter;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;

public class CinemaController {
    public static boolean inserirCinema(Cinema cinema){
        String sql = "INSERT INTO cinema (id_cinema, nome_cinema, id_endereco) VALUES (?, ?, ?)";

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, cinema.getIdCinema());
            pstmt.setString(2, cinema.getNome());
            pstmt.setInt(3, cinema.getEndereco());
            
            
        } catch (Exception e) {
            System.err.println("Erro ao inserir cinema: " + e.getMessage());
        }
    }

    public static boolean excluirCinema (int idCinema) {
        String sql = "DELETE FROM Cinema WHERE id_cinema = ?;";

        


    }


    public static boolean existeCinema (int idCinema) {
        String sql = "SELECT COUNT(id_cinema) AS resultado FROM cinema WHERE id_cinema = ?;";
        int qtdCinema = 0;

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCinema);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                qtdCinema = rs.getInt("resultado");
            }

            if (qtdCinema == 0) {
                return false;
                
            } else {
                return true;
            }

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }
}