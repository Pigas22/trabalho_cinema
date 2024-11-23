package conexionMongoDb;

import com.mongodb.client.*;
import org.bson.Document;
import utils.*;

public class DatabaseMongoDb {
    private static final String NOME_DATABASE = "conexion_mongo_db";
    private static final String URL_MONGODB = "mongodb://localhost:27017";
    private static final String ARQ_CREATE = "create_collections.json";
    private static final String ARQ_INSERT = "insert_documents.json";
    private static final String ARQ_DROP = "drop_collections.json";
    private static final String NOME_PASTA_JSON = "JSON";
    private static final String CAMINHO_PASTA_JSON = Arquivo.procuraPasta(NOME_PASTA_JSON);

    public static MongoDatabase conectar() {
        try {
            MongoClient mongoClient = MongoClients.create(URL_MONGODB);
            return mongoClient.getDatabase(NOME_DATABASE);
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao conectar ao MongoDB: " + e.getMessage());
            return null;
        }
    }

    public static boolean criarDatabase() {
        try (MongoClient mongoClient = MongoClients.create(URL_MONGODB)) {
            MongoDatabase database = mongoClient.getDatabase(NOME_DATABASE);
            if (database != null) {
                MenuFormatter.msgTerminalINFO("Banco de dados '" + NOME_DATABASE + "' já existe no MongoDB.");
                return false;
            }
            MenuFormatter.msgTerminalINFO("Banco de dados '" + NOME_DATABASE + "' criado com sucesso.");
            return true;
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao criar o banco de dados: " + e.getMessage());
            return false;
        }
    }

    public static void droparDatabase() {
        try (MongoClient mongoClient = MongoClients.create(URL_MONGODB)) {
            MongoDatabase database = mongoClient.getDatabase(NOME_DATABASE);
            if (database != null) {
                database.drop();
                MenuFormatter.msgTerminalINFO("Banco de dados '" + NOME_DATABASE + "' dropado com sucesso.");
            } else {
                MenuFormatter.msgTerminalINFO("Banco de dados não encontrado.");
            }
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao dropar o banco de dados: " + e.getMessage());
        }
    }

    public static void inicializarDatabase() {
        try (MongoDatabase database = conectar()) {
            if (database == null) return;

            String createJSON = Arquivo.lerJSON(CAMINHO_PASTA_JSON + "/" + ARQ_CREATE);
            executarCreate(createJSON);

            String insertJSON = Arquivo.lerJSON(CAMINHO_PASTA_JSON + "/" + ARQ_INSERT);
            executarInsert(insertJSON);

            MenuFormatter.msgTerminalINFO("Coleções criadas e dados inseridos no banco de dados '" + NOME_DATABASE + "'.");
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao inicializar o banco de dados: " + e.getMessage());
        }
    }

    public static void excluirColecoes() {
        try (MongoDatabase database = conectar()) {
            if (database == null) return;

            String dropJSON = Arquivo.lerJSON(CAMINHO_PASTA_JSON + "/" + ARQ_DROP);
            executarDrop(dropJSON);

            MenuFormatter.msgTerminalINFO("Coleções excluídas do banco de dados '" + NOME_DATABASE + "'.");
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao excluir coleções: " + e.getMessage());
        }
    }

    public static int contarColecoes() {
        try (MongoDatabase database = conectar()) {
            if (database == null) return -999;
            return database.listCollections().into(new java.util.ArrayList<>()).size();
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao contar as coleções: " + e.getMessage());
            return -999;
        }
    }

    private static void executarCreate(String json) {
        try (MongoDatabase database = conectar()) {
            if (database == null) return;

            Document doc = Document.parse(json);
            String collectionName = doc.getString("collection");
            database.createCollection(collectionName);
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao executar comando de criação: " + e.getMessage());
        }
    }

    private static void executarInsert(String json) {
        try (MongoDatabase database = conectar()) {
            if (database == null) return;

            Document doc = Document.parse(json);
            String collectionName = doc.getString("collection");
            database.getCollection(collectionName).insertOne(doc);
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao executar comando de inserção: " + e.getMessage());
        }
    }

    private static void executarDrop(String json) {
        try (MongoDatabase database = conectar()) {
            if (database == null) return;

            Document doc = Document.parse(json);
            String collectionName = doc.getString("collection");
            database.getCollection(collectionName).drop();
        } catch (Exception e) {
            MenuFormatter.msgTerminalERROR("Erro ao executar comando de drop: " + e.getMessage());
        }
    }
}

