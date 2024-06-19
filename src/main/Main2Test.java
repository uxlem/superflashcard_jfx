package main;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

public class Main2Test {

    public static void main(String[] args) throws FileNotFoundException {
      try (Document document = new Document(new PdfDocument(new PdfWriter("hello-pdf.pdf")))) {
            document.add(new Paragraph("Hello PDF!"));
        }
    }
}