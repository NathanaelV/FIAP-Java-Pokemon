package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection;

    public static void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }

            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Descrever o que é cada parte da URL
            // - jdbc: Protocolo de comunicação
            // - oracle: Indica ao DriverManager, qual driver será usado
            // - thin: é o Subprotocolo
            // - @oracle.fiap.com.br: endereço IP onde o banco de dados Oracle está instalado
            // - 1521: Porta
            // - ORCL: é o SID (System ID) Service Name, nome da instância dentro do servidor
            String url = System.getenv("DB_URL"); // "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
            final String USER = System.getenv("DB_USER");
            final String PASSWORD = System.getenv("DB_PASSWORD");

            // Valida se as informações foram preenchidas antes de tentar uma conexão com o Banco de Dados.
            if (url == null || USER == null || PASSWORD == null) {
                throw new RuntimeException("Variáveis de ambiente do banco não configuradas!");
            }

            connection = DriverManager.getConnection(url, USER, PASSWORD);

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erro nome da classe: " + e.getMessage());
        }

        return connection;
    }
}
