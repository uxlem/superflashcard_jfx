import Repositories.DateRepository;
import SqlServer.DatabaseConnector;
import SqlServer.QueryDataAccessObject;
import cards.Card;
import cards.Flashcard;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class SQLMain {
	/*
	public static void main(String[] args) {
		String url = "jdbc:h2:./testbase";
		String username = "nice";
		String password = "password";
		DatabaseConnector dbConnector = new DatabaseConnector(url, username, password);
		Connection connection = dbConnector.getConnection();

		QueryDataAccessObject.createTable(connection);

		// Hỏi xem User muốn làm gì
		Scanner scanner = new Scanner(System.in);
		int userInput;
		while (true) {
			System.out.println("""

					Bạn muốn làm gì?
					Bấm "1" để tạo Flashcard mới
					Bấm "2" để nhận thông tin từ Flashcard ngẫu nhiên trong Database
					Bấm "3" để xóa sạch thông tin trong Database (cứ cẩn thận)
					Bấm "4" để dừng phần mềm
					Bấm "5" để xem có bảng chưa =))))
					""");
			if (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Please type 1, 2, 3 or 5:");
				scanner.next();
			} else {
				userInput = scanner.nextInt();
				switch (userInput) {
				case 1:
					scanner = new Scanner(System.in);

					// Khởi tạo Flashcard (dữ liệu nhét vào biến "matTruoc" và "matSau")
					System.out.println("Nhập mặt trước Flashcard:");
					String matTruoc = scanner.nextLine();
					System.out.println("Nhập mặt sau Flashcard:");
					String matSau = scanner.nextLine();
					String dateCreated = DateRepository.getDateNow();
					Card newFlashcard = new Card(matTruoc, matSau, dateCreated);

					// Add Flashcard vào Database (MySQL)
					QueryDataAccessObject.insertCard(newFlashcard, connection);

					System.out.println("Đã khởi tạo thành công Flashcard với Mặt Trước: " + matTruoc + ", Mặt Sau: "
							+ matSau + " tại " + dateCreated);
					continue;
				case 2:

					// Lấy thông tin từ Random Flashcard từ Database và in ra console thông tin
					Flashcard randomFlashcard = QueryDataAccessObject.getRandomData(connection);
					System.out.println(randomFlashcard.toString());

					continue;
				case 3:

					// Xóa tất cả Flashcard (bỏ comment dòng dưới để hoạt động)
					// QueryDataAccessObject.deleteAllFlashcards(connection);
					System.out.println("Đã xóa Database, không có cách khôi phục đâu");

					continue;
				case 4:
					System.out.println("Máy bạn đã bị nhiễm virus, phần mềm sẽ tự tắt để máy bạn được an toàn.");
					break;
				default:
					System.out.println("Không hợp lệ. Hãy nhập: 1, 2, 3 hoặc 4:");
					continue;
				}
				break;
			}
		}
	}
	*/
}