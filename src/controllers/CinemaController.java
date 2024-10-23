package controllers;

import conexion.Database;
import models.Cinema;
import utils.MenuFormatter;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;

public class CinemaController {
    public static boolean inserirRegistro(Cinema cinema) {
        String sql = "INSERT INTO cinema (id_cinema, nome_cinema, id_endereco) VALUES (?, ?, ?)";

        try (Connection conn = Database.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int idCinema = CinemaController.getMaiorId();
            if (idCinema == -500) {
                idCinema = 0;
            } else if (idCinema == -999) {
                return false;
            }

            pstmt.setInt(1, idCinema + 1);
            pstmt.setString(2, cinema.getNomeCinema());
            pstmt.setInt(3, cinema.getEndereco().getIdEndereco());

            pstmt.executeUpdate();

            return true;

        } catch (Exception e) {
            System.err.println("Erro ao inserir cinema: " + e.getMessage());
            return false;
        }
    }

    public static boolean excluirRegistro(int idCinema) {
        String sql = "DELETE FROM cinema WHERE id_cinema = ?";

        if (CinemaController.existeRegistro(idCinema)) {
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

    public static boolean atualizarRegistro(int idCinema, String nome, int idEndereco) {
        String sql = "UPDATE cinema SET nome_cinema = ?, id_endereco = ? WHERE id_cinema = ?";

        if (CinemaController.existeRegistro(idCinema)) {
            try (Connection conn = Database.conectar();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, nome);
                pstmt.setInt(2, idEndereco);
                pstmt.setInt(3, idCinema);

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

    public static boolean atualizarRegistro(Cinema cinema) {
        String sql = "UPDATE cinema SET nome_cinema = ?, id_endereco = ? WHERE id_cinema = ?";

        if (CinemaController.existeRegistro(cinema.getIdCinema())) {
            try (Connection conn = Database.conectar();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, cinema.getNomeCinema());
                pstmt.setInt(2, cinema.getEndereco().getIdEndereco());
                pstmt.setInt(3, cinema.getIdCinema());

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

    public static Cinema buscarRegistroPorId(int idCinemaPesquisa) {
        if (CinemaController.existeRegistro(idCinemaPesquisa)) {
            String sql = "SELECT * FROM cinema WHERE id_cinema = ?";

            try (Connection conn = Database.conectar();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, idCinemaPesquisa);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int idCinema = rs.getInt("id_cinema");
                    String nome = rs.getString("nome_cinema");
                    int idEndereco = rs.getInt("id_endereco");
                    return new Cinema(idCinema, nome, EnderecoController.buscarRegistroPorId(idEndereco));
                }
            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
            }
        } else {
            MenuFormatter.msgTerminalERROR("Não encontrado nenhum registro com o ID informado.");
        }
        return null;
    }

    public static LinkedList<Cinema> listarTodosRegistros () {
        LinkedList<Cinema> listaCinemas = new LinkedList<>();
        String sql = "SELECT * FROM cinema";
        
        try (Connection conn = Database.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int idCinema = rs.getInt("id_cinema");
                String nome = rs.getString("nome_cinema");
                int idEndereco = rs.getInt("id_endereco");
                listaCinemas.add(new Cinema(idCinema, nome, EnderecoController.buscarRegistroPorId(idEndereco)));
            }
        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
        }
        return listaCinemas;
    }

    public static boolean existeRegistro(int idCinema) {
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

    public static int contarRegistros () {
        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {
            
            int qtdCinema = 0;
            ResultSet rs = stmt.executeQuery("SELECT COUNT(id_cinema) AS resultado FROM cinema;");
            
            while (rs.next()) {
                qtdCinema = rs.getInt("resultado");
            }

            return qtdCinema;

        } catch (SQLException e) {
            return -999;
        }
    }
}