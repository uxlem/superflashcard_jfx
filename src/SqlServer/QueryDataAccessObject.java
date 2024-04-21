package SqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cards.Card;
import cards.Flashcard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QueryDataAccessObject {
	
	public static void createTable(Connection connection) {
		try {
			ResultSet res_set = connection.getMetaData().getTables(null, null, "FLASHCARDS", null);
			if (res_set.next()) {
				System.out.println("Da co bang FlashCards! hehe");

			} else {
				System.out.println("Chua co bang FlashCards! huhu");
				String sql = "CREATE TABLE flashcards("
						+ "ID BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
						+ "mattruoc varchar(255), "
						+ "matsau varchar(255), "
						+ "date_created datetime)";
		        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		            preparedStatement.executeUpdate();
		            System.out.println("Table created successfully!");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    public static void insertCard(Card flashcard, Connection connection) {
        String sql = "INSERT INTO Flashcards (mattruoc, matsau, date_created) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, flashcard.getMatTruoc());
            preparedStatement.setString(2, flashcard.getMatSau());
            preparedStatement.setString(3, flashcard.getDate_Created());

            preparedStatement.executeUpdate();
            System.out.println("(Flash)Card inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void editCard(Card cardToEdit, String frontText, String backText, Connection connection) {
    	String sql = "UPDATE Flashcards "
    			+ "SET mattruoc='" + frontText + "', matsau='" + backText + "' "
    			+ "WHERE mattruoc = '" + cardToEdit.getMatTruoc() + "'" ;
    	try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
    		preparedStatement.executeUpdate();
    		System.out.println("(Flash)Card edited successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    public static ObservableList<Card> getData(Connection connection) {
        String sql = "SELECT * FROM Flashcards";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
        	ObservableList<Card> data = FXCollections.observableArrayList();
        	while(resultSet.next())
        	{
        		int id = resultSet.getInt("id");
        		String matTruoc = resultSet.getString("mattruoc");
        		String matSau = resultSet.getString("matsau");
        		String date_created = resultSet.getDate("date_created").toString();
        		data.add(new Card(id, matTruoc, matSau, date_created));
        	}
        	return data;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Card findCard(Connection connection, String frontText) {
        String sql = "SELECT * FROM Flashcards WHERE mattruoc = '" + frontText + "'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()) {
        	if (resultSet.next()) {
        		int id = resultSet.getInt("id");
        		String matTruoc = resultSet.getString("mattruoc");
        		String matSau = resultSet.getString("matsau");
        		String date_created = resultSet.getDate("date_created").toString();
        		return new Card(id, matTruoc, matSau, date_created);
        	}
        	else {
        		return null;
        	}
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Card getRandomCard(Connection connection) {
        String sql = "SELECT * FROM Flashcards ORDER BY RAND() LIMIT 1";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                // Retrieve data from the result set
            	int id = resultSet.getInt("id");
        		String matTruoc = resultSet.getString("mattruoc");
        		String matSau = resultSet.getString("matsau");
        		String date_created = resultSet.getDate("date_created").toString();
        		return new Card(id, matTruoc, matSau, date_created);
            } else {
                // No random data found
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteAllCards(Connection connection) {
        String sql = "DELETE FROM Flashcards";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " flashcards deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
