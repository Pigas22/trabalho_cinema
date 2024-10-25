package controllers;

import conexion.Database;
import models.Endereco;
import utils.MenuFormatter;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;

public class EnderecoController {
    public static boolean inserirRegistro (Endereco endereco) {
        String sql = "INSERT INTO endereco (id_endereco, numero, rua, bairro, cidade, uf) VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int idEndereco = EnderecoController.getMaiorId();
            if (idEndereco == -500) {
                idEndereco = 0;

            } else if (idEndereco == -999) {
                return false;
            }
            
            pstmt.setInt(1, idEndereco+1);
            pstmt.setInt(2, endereco.getNumero());
            pstmt.setString(3, endereco.getRua());
            pstmt.setString(4, endereco.getBairro());
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getUf());

            pstmt.executeUpdate();

            return true;
            
        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }
    
    public static boolean excluirRegistro (int idEndereco) {
        String sql = "DELETE FROM endereco WHERE id_endereco = ?;";

        if (EnderecoController.existeRegistro(idEndereco)) {
            try (Connection conn = Database.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, idEndereco);
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

                String sql = "DELETE FROM endereco;";
                stmt.executeUpdate(sql);

                return true;
                

            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
    }

    public static boolean atualizarRegistro (int idEndereco, int numero, String rua, String bairro, String cidade, String uf) {
        String sql = "UPDATE endereco SET numero = ?, rua = ?, bairro = ?, cidade = ?, uf = ? WHERE id_endereco = ?;";

        if (EnderecoController.existeRegistro(idEndereco)) {
            try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
                pstmt.setInt(1, numero);
                pstmt.setString(2, rua);
                pstmt.setString(3, bairro);
                pstmt.setString(4, cidade);
                pstmt.setString(5, uf);
                pstmt.setInt(6, idEndereco);

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

    public static boolean atualizarRegistro (Endereco endereco) {
        String sql = "UPDATE endereco SET numero = ?, rua = ?, bairro = ?, cidade = ?, uf = ? WHERE id_endereco = ?;";

        if (EnderecoController.existeRegistro(endereco.getIdEndereco())) {
            try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
                pstmt.setInt(1, endereco.getNumero());
                pstmt.setString(2, endereco.getRua());
                pstmt.setString(3, endereco.getBairro());
                pstmt.setString(4, endereco.getCidade());
                pstmt.setString(5, endereco.getUf());
                pstmt.setInt(6, endereco.getIdEndereco());

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
    
    public static Endereco buscarRegistroPorId (int idEnderecoPesquisa) {
        if (EnderecoController.existeRegistro(idEnderecoPesquisa)) {
            String sql = "SELECT * FROM endereco WHERE id_endereco = ?";
    
            // Inicialização com valores irreais
            int idEndereco = -500, numero = -500;
            String rua = "N/A", bairro = "N/A", cidade = "N/A", uf = "N/A";
    
            try (Connection conn = Database.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
                pstmt.setInt(1, idEnderecoPesquisa);
    
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    idEndereco = rs.getInt("id_endereco");
                    numero = rs.getInt("numero");
                    rua = rs.getString("rua");
                    bairro = rs.getString("bairro");
                    cidade = rs.getString("cidade");
                    uf = rs.getString("uf");
    
                }
                
                return new Endereco(idEndereco, numero, rua, bairro, cidade, uf);
    
            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return null;
            }
        } else {
            MenuFormatter.msgTerminalERROR("Não encontrado nenhum resgistro com o ID informado.");
            return null;
        }

    }

    public static LinkedList<Endereco> listarTodosRegistros () {
        LinkedList<Endereco> listaResgistros = new LinkedList<Endereco>();
        String sql = "SELECT * FROM endereco ORDER BY id_endereco ASC";

        int idEndereco, numero;
        String rua, bairro, cidade, uf;

        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                idEndereco = rs.getInt("id_endereco");
                numero = rs.getInt("numero");
                rua = rs.getString("rua");
                bairro = rs.getString("bairro");
                cidade = rs.getString("cidade");
                uf = rs.getString("uf");

                listaResgistros.add(new Endereco(idEndereco, numero, rua, bairro, cidade, uf));
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
            
            int qtdEndereco = 0;
            ResultSet rs = stmt.executeQuery("SELECT COUNT(id_endereco) AS resultado FROM endereco;");
            
            while (rs.next()) {
                qtdEndereco = rs.getInt("resultado");
            }

            return qtdEndereco;

        } catch (SQLException e) {
            return -999;
        }
    }

    public static boolean existeRegistro (int idEndereco) {
        String sql = "SELECT COUNT(id_endereco) AS resultado FROM endereco WHERE id_endereco = ?;";
        int qtdEndereco = 0;

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEndereco);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                qtdEndereco = rs.getInt("resultado");
            }

            if (qtdEndereco == 0) {
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
        String sql = "SELECT id_endereco AS resultado FROM endereco ORDER BY id_endereco DESC LIMIT 1;";
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
