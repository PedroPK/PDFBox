import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

public class LeitorPDF {
	
	public static final String PDF_EXTENSION = ".pdf";
	
	public static PDDocument getPdDocument(String pFilePath) throws InvalidPasswordException, IOException {
		File arquivo = new File(pFilePath);
		
		PDDocument documento = PDDocument.load(arquivo);
		return documento;
	}
	
	public static PDDocument getPdDocumentEncrypted(
		String		pFilePath,
		String		pPassword)
	throws InvalidPasswordException, IOException {
		File arquivo = new File(pFilePath);
		
		PDDocument documento = PDDocument.load(arquivo, pPassword);
		return documento;
	}
	
	public static String getTexto(String pFilePath) throws InvalidPasswordException, IOException {
		PDDocument documento = getPdDocument(pFilePath);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		
		String texto = pdfStripper.getText(documento);
		return texto;
	}
	
	public static String[] getPrefixArray(String pOriginalFileName) {
		String[] fileName = pOriginalFileName.split(PDF_EXTENSION);
		return fileName;
	}
	
	public static String getDecryptedFileName(String[] pFileName) {
		return pFileName[0] + "_decrypted" + LeitorPDF.PDF_EXTENSION;
	}
	
	public static PDDocument removeEncryption(String pPath, String pPassword) throws InvalidPasswordException, IOException {
		PDDocument documento = 
			LeitorPDF.getPdDocumentEncrypted(
				pPath,
				pPassword 
		);
		
		documento.setAllSecurityToBeRemoved(true);
		return documento;
	}
	
	public static File getDecryptedFile(String pPath, String pPassword) throws InvalidPasswordException, IOException {
		PDDocument documento = 
			LeitorPDF.removeEncryption(
				pPath,
				pPassword 
		);
		
		File decryptedFile = new File(pPath + "_decrypted");
		
		return decryptedFile;
	}
	
	public void saveDecryptedFile(
		String	pFilePath,
		String	pPassword)
	throws InvalidPasswordException, IOException {
		PDDocument documento = 
			LeitorPDF.getPdDocumentEncrypted(
				pFilePath,
				pPassword 
		);
		
		documento.setAllSecurityToBeRemoved(true);
		
		File decryptedFile = new File(pFilePath + "_decrypted");
		
		documento.save(decryptedFile);
	}
	
}