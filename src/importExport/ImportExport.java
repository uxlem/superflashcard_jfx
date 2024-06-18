package importExport;

import cards.Card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Repositories.DateRepository;

public class ImportExport {
	public static void Export(ObservableList <Card> list, String FileName) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter (FileName))) {
			for (Card i : list) {
				String front = i.getMatTruoc().replaceAll("(\r\n|\n|\r)", "<br>");
				String back = i.getMatSau().replaceAll("(\r\n|\n|\r)", "<br>");
				String k = front + ";" + i.getMatSau();
				System.out.println(k);
				writer.write(k);
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println("Không xuất file được");
		}
	}
	
	public static ObservableList<Card> Import(String FileName) {
		ObservableList<Card> list = FXCollections.observableArrayList();
		try (BufferedReader reader = new BufferedReader (new FileReader (FileName))) {
			String s;
			while ((s = reader.readLine()) != null) {
				s = s.strip();
				String[] r = s.split(";");
				String front = r[0].replaceAll("<br>", "\n");
				String back = r[1].replaceAll("<br>", "\n");
				list.add(new Card(front, back, DateRepository.getDateNow()));
				System.out.println("Front:\n" + front + "\nBack:\n" + back);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file");
		} catch (IOException e) {
			System.out.println("Không nhập file được");
		}
		return list; 
	}
}
