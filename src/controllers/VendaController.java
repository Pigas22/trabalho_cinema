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

public class VendaController {
    public static boolean inserirVenda (Venda venda) {
        String sql = "INSERT INTO venda (id_venda, nome_cliente, assento, forma_pagamento, id_secao) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int idVenda = VendaController.getMaiorId();
            if (idVenda == -500) {
                idVenda = 0;

            } else if (idVenda == -999) {
                return false;
            }
            
            pstmt.setInt(1, idVenda+1);
            pstmt.setString(2, venda.getNomeCliente());
            pstmt.setInt(3, venda.getAssento());
            pstmt.setString(4, venda.getFormaPagamento());
            pstmt.setInt(5, venda.getSecao().getIdSecao());

            pstmt.executeUpdate();

            return true;
            
        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }
    
    public static boolean excluirVenda (int idVenda) {
        String sql = "DELETE FROM venda WHERE id_venda = ?;";

        if (VendaController.existeVenda(idVenda)) {
            try (Connection conn = Database.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, idVenda);
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

                String sql = "DELETE FROM venda;";
                stmt.executeUpdate(sql);

                return true;
                

            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
    }

    public static boolean atualizarVenda (int idVenda, String nomeCliente, int assento, String formaPagamento, Secao secao) {
        String sql = "UPDATE venda SET nome_cliente = ?, assento = ?, forma_pagamento = ?, id_secao = ? WHERE id_venda = ?;";

        if (VendaController.existeVenda(idVenda)) {
            try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
                pstmt.setString(1, nomeCliente);
                pstmt.setInt(2, assento);
                pstmt.setString(3, formaPagamento);
                pstmt.setInt(4, secao.getIdSecao());
                pstmt.setInt(5, idVenda);

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

    public static boolean atualizarVenda (Venda venda) {
        String sql = "UPDATE venda SET nome_cliente = ?, assento = ?, forma_pagamento = ?, id_secao = ? WHERE id_venda = ?;";

        if (VendaController.existeVenda(venda.getIdVenda())) {
            try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, venda.getNomeCliente());
            pstmt.setInt(2, venda.getAssento());
            pstmt.setString(3, venda.getFormaPagamento());
            pstmt.setInt(4, venda.getSecao().getIdSecao());
            pstmt.setInt(5, venda.getIdVenda());

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
    
    public static Venda buscarVendaPorId (int idVendaPesquisa) {
        if (VendaController.existeVenda(idVendaPesquisa)) {
            String sql = "SELECT * FROM venda WHERE id_venda = ?";
    
            // Inicialização com valores irreais
            int idVenda = -500, assento = -500, idSecao = -500;
            String nomeCliente = "N/A", formaPagamento = "N/A";
            Secao secao = null;

            try (Connection conn = Database.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
                pstmt.setInt(1, idVendaPesquisa);
    
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    idVenda = rs.getInt("id_venda");
                    nomeCliente = rs.getString("nome_cliente");
                    assento = rs.getInt("assento");
                    formaPagamento = rs.getString("forma_pagamento");
                    idSecao = rs.getInt("id_secao");
    
                }

                secao = SecaoController.buscarRegistroPorId(idSecao);
                
                return new Venda(idVenda, nomeCliente, assento, formaPagamento, secao);

    
            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return null;
            }
        } else {
            MenuFormatter.msgTerminalERROR("Não encontrado nenhum resgistro com o ID informado.");
            return null;
        }

    }

    public static LinkedList<Venda> listarTodosRegistros () {
        LinkedList<Venda> listaResgistros = new LinkedList<Venda>();
        String sql = "SELECT * FROM venda ORDER BY id_venda ASC";

        int idVenda, assento, idSecao;
        String nomeCliente, formaPagamento;
        Secao secao;

        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                idVenda = rs.getInt("id_venda");
                nomeCliente = rs.getString("nome_cliente");
                assento = rs.getInt("assento");
                formaPagamento = rs.getString("forma_pagamento");
                idSecao = rs.getInt("id_secao");

                secao = SecaoController.buscarRegistroPorId(idSecao);

                listaResgistros.add(new Venda(idVenda, nomeCliente, assento, formaPagamento, secao));
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
            
            int qtdVenda = 0;
            ResultSet rs = stmt.executeQuery("SELECT COUNT(id_venda) AS resultado FROM venda;");
            
            while (rs.next()) {
                qtdVenda = rs.getInt("resultado");
            }

            return qtdVenda;

        } catch (SQLException e) {
            return -999;
        }
    }

    public static boolean existeVenda (int idVenda) {
        String sql = "SELECT COUNT(id_venda) AS resultado FROM venda WHERE id_venda = ?;";
        int qtdVenda = 0;

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idVenda);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                qtdVenda = rs.getInt("resultado");
            }

            if (qtdVenda == 0) {
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
        String sql = "SELECT id_venda AS resultado FROM venda ORDER BY id_venda DESC LIMIT 1;";
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
