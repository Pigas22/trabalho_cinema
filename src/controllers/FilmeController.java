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

public class FilmeController {
    public static boolean inserirFilme (Filme filme) {
        String sql = "INSERT INTO filme (id_filme, nome_filme, preco) VALUES (?, ?, ?);";

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int idFilme = FilmeController.getMaiorId();
            if (idFilme == -500) {
                idFilme = 0;

            } else if (idFilme == -999) {
                return false;
            }
            
            pstmt.setInt(1, idFilme+1);
            pstmt.setString(2, filme.getNomeFilme());
            pstmt.setDouble(3, filme.getPreco());

            pstmt.executeUpdate();

            return true;
            
        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }
    
    public static boolean excluirFilme (int idFilme) {
        String sql = "DELETE FROM filme WHERE id_filme = ?;";

        if (FilmeController.existeFilme(idFilme)) {
            try (Connection conn = Database.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, idFilme);
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

                String sql = "DELETE FROM filme;";
                stmt.executeUpdate(sql);

                return true;
                

            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
    }

    public static boolean atualizarFilme (int idFilme, String nomeFilme, double preco) {
        String sql = "UPDATE filme SET nome_filme = ?, preco = ? WHERE id_filme = ?;";

        if (FilmeController.existeFilme(idFilme)) {
            try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, idFilme);
            pstmt.setString(2, nomeFilme);
            pstmt.setDouble(3, preco);

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

    public static boolean atualizarFilme (Filme filme) {
        String sql = "UPDATE filme SET nome_filme = ?, preco = ? WHERE id_filme = ?;";

        if (FilmeController.existeFilme(filme.getIdFilme())) {
            try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, filme.getIdFilme());
            pstmt.setString(2, filme.getNomeFilme());
            pstmt.setDouble(3, filme.getPreco());

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
    
    public static Filme buscarFilmePorId (int idFilmePesquisa) {
        if (FilmeController.existeFilme(idFilmePesquisa)) {
            String sql = "SELECT * FROM filme WHERE id_filme = ?";
    
            // Inicialização com valores irreais
            int idFilme = -500;
            String nomeFilme = "N/A";
            double preco = -500;
    
            try (Connection conn = Database.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
                pstmt.setInt(1, idFilmePesquisa);
    
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    idFilme = rs.getInt("id_filme");
                    nomeFilme = rs.getString("nome_filme");
                    preco = rs.getDouble("preco");
                }
                
                return new Filme(idFilme, nomeFilme, preco);
    
            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return null;
            }

        } else {
            MenuFormatter.msgTerminalERROR("Não encontrado nenhum resgistro com o ID informado.");
            return null;
        }

    }

    public static LinkedList<Filme> listarTodosFilmes () {
        LinkedList<Filme> listaResgistros = new LinkedList<Filme>();
        String sql = "SELECT * FROM filme";

        int idFilme;
        String nomeFilme;
        double preco;

        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                idFilme = rs.getInt("id_filme");
                nomeFilme = rs.getString("nome_filme");
                preco = rs.getDouble("preco");

                listaResgistros.add(new Filme(idFilme, nomeFilme, preco));
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
            
            int qtdFilme = 0;
            ResultSet rs = stmt.executeQuery("SELECT COUNT(id_filme) AS resultado FROM filme;");
            
            while (rs.next()) {
                qtdFilme = rs.getInt("resultado");
            }

            return qtdFilme;

        } catch (SQLException e) {
            return -999;
        }
    }

    public static boolean existeFilme (int idFilme) {
        String sql = "SELECT COUNT(id_filme) AS resultado FROM filme WHERE id_filme = ?;";
        int qtdFilme = 0;

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idFilme);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                qtdFilme = rs.getInt("resultado");
            }

            if (qtdFilme == 0) {
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
        String sql = "SELECT id_filme AS resultado FROM filme ORDER BY id_filme DESC LIMIT 1;";
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
