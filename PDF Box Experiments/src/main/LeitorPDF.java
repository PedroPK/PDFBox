package main;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

public class LeitorPDF {
	
	private static final String IOEXCEPTION_IS_CLOSED_MESSAGE = "COSStream has been closed and cannot be read. Perhaps its enclosing PDDocument has been closed?";
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
		documento.close();
		
		return documento;
	}
	
	public static PDPage		getPage( 
		PDDocument	pPdf,
		int			pPageNumber
	) {
		PDPage	response = null;
		
		if ( pPdf != null && pPdf.getNumberOfPages() > pPageNumber ) {
			response = pPdf.getPage(pPageNumber);
		}
		
		return	response;
	}
	
	public static String getText(String pFilePath) throws InvalidPasswordException, IOException {
		return getText(pFilePath, true);
	}
	
	public static String getText(
		String		pFilePath,
		boolean		pClosePdDocument)
	throws InvalidPasswordException, IOException {
		String responseText = "";
		if ( pFilePath != null && !pFilePath.isEmpty() ) { 
			PDDocument document = getPdDocument(pFilePath);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			
			responseText = pdfStripper.getText(document);
			
			if ( pClosePdDocument ) {
				document.close();
			}
		}
		return responseText;
	}
	
	public static String getText(PDDocument pDocument) throws InvalidPasswordException, IOException {
		return getText(pDocument, true);
	}
	
	public static String getText(
		PDDocument	pDocument,
		boolean		pClosePdDocument)
	throws InvalidPasswordException, IOException {
		String responseText = "";
		
		if ( pDocument != null ) {
			PDFTextStripper pdfStripper = new PDFTextStripper();
			
			responseText = pdfStripper.getText(pDocument);
			
			if ( pClosePdDocument ) {
				pDocument.close();
			}
		}
		
		return responseText;
	}
	
	public static String getText(
		PDDocument	pPdfDocument,
		int			pPageNumber
	) {
		return 
			getText(
				pPdfDocument, 
				pPageNumber, 
				true);
	}
	
	public static String getText(
		PDDocument	pPdfDocument,
		int			pPageNumber,
		boolean		pClosePdDocument
	) {
		String response = "";
		
		if ( pPdfDocument != null && pPageNumber >= 0 ) {
			PDFTextStripper pdfStripper;
			try {
				pdfStripper = new PDFTextStripper();
				
				// Inclusive
				pdfStripper.setStartPage(	pPageNumber);
				
				// Exclusive
				pdfStripper.setEndPage(		pPageNumber + 1);
				
				response = pdfStripper.getText(pPdfDocument);
				
				if ( pClosePdDocument ) {
					pPdfDocument.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return response;
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
		PDDocument document = 
			LeitorPDF.getPdDocumentEncrypted(
				pFilePath,
				pPassword 
		);
		
		document.setAllSecurityToBeRemoved(true);
		
		File decryptedFile = new File(pFilePath + "_decrypted");
		
		document.save(decryptedFile);
		
		document.close();
	}
	
	public static boolean isClosed (PDDocument		pPdfDocument) {
		boolean response = false;
		
		if ( pPdfDocument == null ) {
			response = true;
		} else {
			try {
				getText(pPdfDocument);
			} catch ( IOException ioe ) {
				if ( ioe.getMessage().equals(IOEXCEPTION_IS_CLOSED_MESSAGE) ) {
					response = true;
				}
			}
		}
		
		return response;
	}
	
}