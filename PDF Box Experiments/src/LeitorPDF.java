import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class LeitorPDF {
	
	public static PDDocument getPdDocument(String pFilePath) throws InvalidPasswordException, IOException {
		File arquivo = new File(pFilePath);
		
		PDDocument documento = PDDocument.load(arquivo);
		return documento;
	}
	
	public static String getTexto(String pFilePath) throws InvalidPasswordException, IOException {
		PDDocument documento = getPdDocument(pFilePath);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		
		String texto = pdfStripper.getText(documento);
		return texto;
	}
}