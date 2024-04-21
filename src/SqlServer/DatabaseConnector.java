package SqlServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private Connection connection;

    public DatabaseConnector(String url, String username, String password) {
        try {
            // Register MySQL JDBC driver
            // Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection to the database
            connection = DriverManager.getConnection(url, username, password);
        } catch (/*ClassNotFoundException|*/ SQLException e) {
            e.printStackTrace();
        }
        
        
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}