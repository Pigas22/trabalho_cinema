package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.*;

public class Database {
    // Váriaveis referentes à criação do banco da Aplicação
    private static final String NOME_DATABASE = "ger_cinema_db";
    private static final String PORTA_SERVICO = "5432";
    private static final String URL_POSTGRES = "jdbc:postgresql://localhost:" + PORTA_SERVICO + "/postgres";

    // Usuário padrão PostgreSQL
    private static final String USER_POSTGRES = "postgres";
    private static final String PASSWORD_POSTGRES = "postgres";

    // Usuário VM
    // private static final String USER_POSTGRES = "labdatabase";
    // private static final String PASSWORD_POSTGRES = "labDatabase2022";
    
    // Váriaveis do Arquivo SQL Inicial
    private static final String ARQ_CREATE = "create_tabelas.sql";
    private static final String ARQ_DROP = "drop_tabelas.sql";
    private static final String ARQ_INSERT = "insert_tables.sql";
    private static final String NOME_PASTA_SQL = "SQL";
    private static final String CAMINHO_PASTA_SQL = Arquivo.procuraPasta(NOME_PASTA_SQL);

    // Váriaveis referentes ao banco da Aplicação
    private static final String URL_DB = "jdbc:postgresql://localhost:" + PORTA_SERVICO + "/" + NOME_DATABASE;

    // Váriavel para checar dados prévios
    private static final String CHECK_DATA_SQL = "SELECT COUNT(*) AS qtd_tabelas FROM information_schema.tables WHERE table_schema = 'public';";

    // Método para criar o banco informado na variável NOME_DATABASE
    public static boolean criarDatabase() {
        String createDatabaseSQL = "CREATE DATABASE " + NOME_DATABASE;

        try (Connection conn = DriverManager.getConnection(URL_POSTGRES, USER_POSTGRES, PASSWORD_POSTGRES);
            Statement stmt = conn.createStatement()) {

            String bancoExiste = "";
            ResultSet rs = stmt.executeQuery("SELECT CASE COUNT(*) WHEN 0 THEN 'N' WHEN 1 THEN 'S' END AS resultado FROM pg_database WHERE datname = '" + NOME_DATABASE + "'");
            while (rs.next()) {
                bancoExiste = rs.getString("resultado");

            }   

            if (bancoExiste.strip().equals("N")) {
                stmt.executeUpdate(createDatabaseSQL);
                MenuFormatter.msgTerminalINFO("Banco de dados '" + NOME_DATABASE + "' criado com sucesso.");
                return true;

            } else {
                MenuFormatter.msgTerminalINFO("Banco de dados já existente.");
                return false;

            }


        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR("Erro ao criar o banco de dados: " + e.getMessage());
            return false;
        }
    }

    // Método para dropar o banco informado na variável NOME_DATABASE
    public static void droparDatabase() {
        String createDatabaseSQL = "DROP DATABASE IF EXISTS " + NOME_DATABASE;

        try (Connection conn = DriverManager.getConnection(URL_POSTGRES, USER_POSTGRES, PASSWORD_POSTGRES);
            Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createDatabaseSQL);
            MenuFormatter.msgTerminalINFO("Banco de dados '" + NOME_DATABASE + "' dropado com sucesso.");

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR("Erro ao dropar o banco de dados: " + e.getMessage());
        }
    }

    // Método para conectar no BANCO
    public static Connection conectar() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL_DB, USER_POSTGRES, PASSWORD_POSTGRES);

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
        }
        return conexao;
    }

    // Método para inserir os dados inciais do BANCO
    public static void inicializarDatabase() {
        try (Connection conexao = conectar();
             Statement stmt = conexao.createStatement()) {

            // Verifica a quantidade de Tabelas
            ResultSet rs = stmt.executeQuery(CHECK_DATA_SQL);
            int num = 0;
            while (rs.next()) {
                num = rs.getInt("qtd_tabelas");

            }

            // Caso tenha alguma tabela no schema, todas elas serão excluídas
            if (num > 0) {
                excluirTabelas();
            } 

            // Adiciona as tabelas informadas no arquivo create_tabelas.sql
            String createSQL = Arquivo.lerSQL(CAMINHO_PASTA_SQL + "/" + ARQ_CREATE);
            executarSQL(createSQL);

            String insertSQL = Arquivo.lerSQL(CAMINHO_PASTA_SQL + "/" + ARQ_INSERT);
            executarSQL(insertSQL);

            MenuFormatter.msgTerminalINFO("Tabelas criadas e Dados inseridos no Banco: '" + NOME_DATABASE + "'.");

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
        }
    }
    

    // Excluí as Tabelas informadas no arquivo drop_tabelas.sql
    public static void excluirTabelas () {
        try {

            String comandoSQL = Arquivo.lerSQL(CAMINHO_PASTA_SQL + "/" + ARQ_DROP);
            executarSQL(comandoSQL);
            MenuFormatter.msgTerminalINFO("Tabelas anteriores exluídas do Banco: '" + NOME_DATABASE + "'.");

        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao Excluir as tabelas presentes no Bancos: '" + NOME_DATABASE + "'.");
        }
    }


    // Método para executar um comando SQL
    private static void executarSQL(String sql) {
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR("Ao realizar a execução do comando SQL: " + e.getMessage());

        }
    }

    // Main
    public static void main(String[] args) {
        // Teste da classe

        // droparDatabase();
        // excluirTabelas();

        criarDatabase();
        inicializarDatabase();

        /*
        Connection conexao = conectar();
        if (conexao == null) {
            if(criarDatabase()) {
                conectar();
                inicializarDatabase();

            }

        } else {
            inicializarDatabase();
        }
        */
    }
}
