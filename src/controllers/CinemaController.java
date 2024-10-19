package controllers;

import conexion.Database;
import utils.MenuFormatter;
import models.*;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;

public class CinemaController {
    public static boolean inserirCinema(Cinema cinema) {
        String sql = "INSERT INTO cinema (id_cinema, nome_cinema, id_endereco) VALUES (?, ?, ?)";

        try (Connection conn = Database.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int idCinema = CinemaController.getMaiorId();
            if (idCinema == -500) {
                idCinema = 0;
            } else if (idCinema == -999) {
                return false;
            }

<<<<<<< HEAD
            pstmt.setInt(1, idCinema + 1);
            pstmt.setString(2, cinema.getNome());
=======
            pstmt.setInt(1, idCinema+1);
            pstmt.setString(2, cinema.getNomeCinema());
>>>>>>> 6c80aa67bfcfe4da061edd1d109421e2d3d47790
            pstmt.setInt(3, cinema.getEndereco().getIdEndereco());

            pstmt.executeUpdate();

            return true;

        } catch (Exception e) {
            System.err.println("Erro ao inserir cinema: " + e.getMessage());
            return false;
        }
    }

    public static boolean excluirCinema(int idCinema) {
        String sql = "DELETE FROM cinema WHERE id_cinema = ?";

        if (CinemaController.existeCinema(idCinema)) {
            try (Connection conn = Database.conectar();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, idCinema);
                pstmt.executeUpdate();
                return true;

            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
        } else {
            MenuFormatter.msgTerminalERROR("Cinema não encontrado no Banco de Dados.");
            return false;
        }
    }

    public static boolean existeCinema(int idCinema) {
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

    private static int getMaiorId() {
        String sql = "SELECT id_cinema AS resultado FROM cinema ORDER BY id_endereco DESC LIMIT 1;";
        int ultimoId = -500;

        try (Connection conn = Database.conectar();
                Statement pstmt = conn.createStatement()) {

            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                ultimoId = rs.getInt("resultado");
            }

            return ultimoId;

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return -999;
        }
    }


    public static Cinema buscarCinemaPorId(int idCinema) {
        return null;
    }
}