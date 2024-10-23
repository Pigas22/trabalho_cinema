package controllers;

import conexion.Database;
import utils.MenuFormatter;
import models.*;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;

public class SecaoController {
    public static boolean inserirRegistro (Secao secao) {
        String sql = "INSERT INTO secao (id_secao, horario, id_cinema, id_filme, qtd_assentos) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int idSecao = SecaoController.getMaiorId();
            if (idSecao == -500) {
                idSecao = 0;

            } else if (idSecao == -999) {
                return false;
            }
            
            pstmt.setInt(1, idSecao+1);
            pstmt.setTimestamp(2, secao.getHorario());
            pstmt.setInt(3, secao.getCinema().getIdCinema());
            pstmt.setInt(4, secao.getFilme().getIdFilme());
            pstmt.setInt(5, secao.getQtdAssentos());

            pstmt.executeUpdate();

            return true;
            
        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }
    
    public static boolean excluirRegistro (int idSecao) {
        String sql = "DELETE FROM secao WHERE id_secao = ?;";

        if (SecaoController.existeRegistro(idSecao)) {
            try (Connection conn = Database.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, idSecao);
                pstmt.executeUpdate();

                return true;

            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
            
        } else {
            MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
            return false;
        }
    }

    public static boolean excluirTodosRegistros () {
        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {

                String sql = "DELETE FROM secao;";
                stmt.executeUpdate(sql);

                return true;
                

            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
    }

    public static boolean atualizarRegistro (int idSecao, Timestamp horario, int idCinema, int idFilme, int qtdAssentos) {
        String sql = "UPDATE secao SET horario = ?, id_cinema = ?, id_filme = ?, qtd_assentos = ? WHERE id_secao = ?;";

        if (FilmeController.existeRegistro(idFilme)) {
            try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
                pstmt.setTimestamp(1, horario);
                pstmt.setInt(2, idCinema);
                pstmt.setInt(3, idFilme);
                pstmt.setInt(4, qtdAssentos);
                pstmt.setInt(5, idSecao);

            pstmt.executeUpdate();

            return true;
            
            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
            
        } else {
            MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
            return false;
        }
    }

    public static boolean atualizarRegistro (Secao secao) {
        String sql = "UPDATE secao SET horario = ?, id_cinema = ?, id_filme = ?, qtd_assentos = ? WHERE id_secao = ?;";

        if (FilmeController.existeRegistro(secao.getIdSecao())) {
            try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
                pstmt.setTimestamp(1, secao.getHorario());
                pstmt.setInt(2, secao.getCinema().getIdCinema());
                pstmt.setInt(3, secao.getFilme().getIdFilme());
                pstmt.setInt(4, secao.getQtdAssentos());
                pstmt.setInt(5, secao.getIdSecao());

            pstmt.executeUpdate();

            return true;
            
            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
            
        } else {
            MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
            return false;
        }
    }
   
    public static Secao buscarRegistroPorId (int idSecaoPesquisa) {
        if (SecaoController.existeRegistro(idSecaoPesquisa)) {
            String sql = "SELECT * FROM secao WHERE id_secao = ?";
    
            // Inicialização com valores irreais
            int idSecao = -500, idCinema  = -500, idFilme = -500, qtdAssentos = -500;
            Timestamp horairo = null;
    
            try (Connection conn = Database.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
                pstmt.setInt(1, idSecaoPesquisa);
    
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    idSecao = rs.getInt("id_secao");
                    horairo = rs.getTimestamp("horario");
                    idCinema = rs.getInt("id_cinema");
                    idFilme = rs.getInt("id_filme");
                    qtdAssentos = rs.getInt("qtd_assentos");
                }

                Cinema cinema = CinemaController.buscarRegistroPorId(idCinema);
                Filme filme = FilmeController.buscarRegistroPorId(idFilme);
                
                return new Secao(idSecao, horairo, cinema, filme, qtdAssentos);
    
            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return null;
            }

        } else {
            MenuFormatter.msgTerminalERROR("Não encontrado nenhum resgistro com o ID informado.");
            return null;
        }

    }

    public static LinkedList<Secao> listarTodosRegistros () {
        LinkedList<Secao> listaResgistros = new LinkedList<Secao>();
        String sql = "SELECT * FROM secao";

        int idSecao, idCinema, idFilme, qtdAssentos;
        Timestamp horairo;
        Cinema cinema;
        Filme filme;

        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                idSecao = rs.getInt("id_secao");
                horairo = rs.getTimestamp("horario");
                idCinema = rs.getInt("id_cinema");
                idFilme = rs.getInt("id_filme");
                qtdAssentos = rs.getInt("qtd_assentos");

                cinema = CinemaController.buscarRegistroPorId(idCinema);
                filme = FilmeController.buscarRegistroPorId(idFilme);

                listaResgistros.add(new Secao(idSecao, horairo, cinema, filme, qtdAssentos));
            }

            return listaResgistros;

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;
        }
    }
    
    public static int contarRegistros () {
        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {
            
            int qtdSecao = 0;
            ResultSet rs = stmt.executeQuery("SELECT COUNT(id_secao) AS resultado FROM secao;");
            
            while (rs.next()) {
                qtdSecao = rs.getInt("resultado");
            }

            return qtdSecao;

        } catch (SQLException e) {
            return -999;
        }
    }

    public static boolean existeRegistro (int idSecao) {
        String sql = "SELECT COUNT(id_secao) AS resultado FROM secao WHERE id_secao = ?;";
        int qtdSecao = 0;

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idSecao);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                qtdSecao = rs.getInt("resultado");
            }

            if (qtdSecao == 0) {
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
        String sql = "SELECT id_secao AS resultado FROM secao ORDER BY id_secao DESC LIMIT 1;";
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
}