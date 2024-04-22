package SqlServer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cards.Card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QueryDataAccessObject {
	
	public static void createTable() {
		try {
			ResultSet res_set = DatabaseConnector.getConnection().getMetaData().getTables(null, null, "FLASHCARDS", null);
			if (res_set.next()) {
				System.out.println("Đã có bảng flashCards!");

			} else {
				System.out.println("Chưa có bảng flashcards, tiến hành tạo bảng.");
				String sql = "CREATE TABLE flashcards("
						+ "ID BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
						+ "mattruoc varchar(255), "
						+ "matsau varchar(255), "
						+ "date_created datetime)";
		        try (PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(sql)) {
		            preparedStatement.executeUpdate();
		            System.out.println("Tạo bảng thành công!");
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    public static void insertCard(Card flashcard) {
        String sql = "INSERT INTO Flashcards (mattruoc, matsau, date_created) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(sql)) {

            preparedStatement.setString(1, flashcard.getMatTruoc());
            preparedStatement.setString(2, flashcard.getMatSau());
            preparedStatement.setString(3, flashcard.getDate_Created());

            preparedStatement.executeUpdate();
            System.out.println("Thêm Card thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void editCard(Card cardToEdit, String frontText, String backText) {
    	String sql = "UPDATE Flashcards "
    			+ "SET mattruoc=?, matsau=? "
    			+ "WHERE mattruoc = ?" ;
    	try (PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(sql);) {
    		preparedStatement.setString(1, frontText);
            preparedStatement.setString(2, backText);
            preparedStatement.setString(3, cardToEdit.getMatTruoc());
    		preparedStatement.executeUpdate();
    		System.out.println("(Flash)Card edited successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    public static ObservableList<Card> getData() {
        String sql = "SELECT * FROM Flashcards";
        try (PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(sql);
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
    
    public static Card findCard(String frontText) {
        String sql = "SELECT * FROM Flashcards WHERE mattruoc = ?";
        try (PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(sql);) {
        	preparedStatement.setString(1, frontText);
            ResultSet resultSet = preparedStatement.executeQuery();
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
    
    public static Card getRandomCard() {
        String sql = "SELECT * FROM Flashcards ORDER BY RAND() LIMIT 1";

        try (PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(sql);
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

    public static void deleteAllCards() {
        String sql = "DELETE FROM Flashcards";

        try (PreparedStatement preparedStatement = DatabaseConnector.getConnection().prepareStatement(sql)) {
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " flashcards deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
