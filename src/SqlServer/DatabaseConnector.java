package SqlServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	private static Connection connection = null;

	public static void DatabaseConnect(String url, String username, String password) {
		try {
			// Register MySQL JDBC driver
			// Class.forName("com.mysql.cj.jdbc.Driver");

			// Open a connection to the database
			connection = DriverManager.getConnection(url, username, password);
		} catch (/* ClassNotFoundException| */ SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Da dong connection.");
		}
	}

	public static void openDB() {
		String url = "jdbc:h2:./testbase";
		String username = "nice";
		String password = "password";
		DatabaseConnect(url, username, password);
		QueryDataAccessObject.createTables();
	}
}