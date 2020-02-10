package main;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import static com.google.common.truth.Truth.*;

import main.clear.LeitorNotasCorretagemClear;

public class LeitorPDFTest {
	
	private static final String PATH_PDF_PROPOSTA_HABILITACAO_PITANG = "/Users/pedropk/Downloads/Temp/eFisco/pitangpropostahabilitacao02.pdf";
	private static final String PATH_PDF_POLICENTRO_CONTRA_CHEQUE_2019_02_FEVEREIRO = "/Users/pedropk/Downloads/Temp/eFisco/PEDRO CARLOS SANTOS - 2019 02 Fevereiro - Policentro.pdf";
	private static final String PATH_PDF_EXTRATO_B3_2019_03_MARCO = "/Users/pedropk/Downloads/Temp/B3/0000278765_00308_0201466.pdf";
	
	/**
	 * REPLACE_WITH_REAL_PASSWORD
	 * 
	 * @throws InvalidPasswordException
	 * @throws IOException
	 */
	private static final String PASSWORD = "";
	
	@Test
	public void test_tentarCriarArquivoPropostaHabilitacaoPitang() {
		// Arrange + Act
		File arquivo = new File(PATH_PDF_PROPOSTA_HABILITACAO_PITANG);
		
		// Assert
		assertNotNull(arquivo);
	}
	
	@Ignore
	@Test
	public void test_tentarCriarPDDocumentPropostaHabilitacaoPitang() throws InvalidPasswordException, IOException {
		// Arrange + Act
		PDDocument document = LeitorPDF.getPdDocument(PATH_PDF_PROPOSTA_HABILITACAO_PITANG);
		
		// Assert
		assertNotNull(document);
		
		// Avoiding a Warning message in Console.
		document.close();
	}
	
	@Ignore
	@Test
	public void test_getText_tentarLerPropostaHabilitacaoPitang() throws InvalidPasswordException, IOException {
		// Arrange + Act
		String texto = LeitorPDF.getText(PATH_PDF_PROPOSTA_HABILITACAO_PITANG);
		
		// Assert
		assertNotNull(texto);
		assertFalse(texto.isEmpty());
	}
	
	@Test
	public void test_tentarCriarArquivoPolicentroContraCheque_2019_02Fevereiro() {
		// Arrange + Act
		File arquivo = new File(PATH_PDF_POLICENTRO_CONTRA_CHEQUE_2019_02_FEVEREIRO);
		
		// Assert
		assertNotNull(arquivo);
	}
	
	@Ignore
	@Test
	public void test_tentarCriarPDDocumentPolicentroContraCheque_2019_02Fevereiro() throws InvalidPasswordException, IOException {
		// Arrange + Act
		PDDocument document = LeitorPDF.getPdDocument(PATH_PDF_POLICENTRO_CONTRA_CHEQUE_2019_02_FEVEREIRO);
		
		// Assert
		
		assertNotNull(document);
		
		// Avoiding a Warning message in Console.
		document.close();
	}
	
	@Ignore
	@Test
	public void test_getText_tentarLerPolicentroContraCheque_2019_02Fevereiro() throws InvalidPasswordException, IOException {
		// Arrange + act
		String texto = LeitorPDF.getText(PATH_PDF_POLICENTRO_CONTRA_CHEQUE_2019_02_FEVEREIRO);
		
		
		// Assert
		assertNotNull(texto);
		assertFalse(texto.isEmpty());
	}
	
	@Disabled
	@Ignore
	@Test
	public void test_getPdDocumentEncrypted_NullAndSecurityEnabled() throws InvalidPasswordException, IOException {
		// Arrange + Act
		PDDocument document = 
			LeitorPDF.getPdDocumentEncrypted(
				PATH_PDF_EXTRATO_B3_2019_03_MARCO,
				PASSWORD 
		);
		
		// Assert
		assertNotNull(document);
		assertFalse(document.isAllSecurityToBeRemoved());
		
		// Avoiding a Warning message in Console.
		document.close();
	}
	
	@Disabled
	@Ignore
	@Test
	public void test_removeEncryption_PdDocumentoEncryptedSucessfullySecurityRemoved() throws InvalidPasswordException, IOException {
		// Arrange
		String path = PATH_PDF_EXTRATO_B3_2019_03_MARCO;
		String password = PASSWORD;
		
		// Act
		PDDocument document = LeitorPDF.removeEncryption(path, password);
		
		// Assert
		assertTrue(document.isAllSecurityToBeRemoved());
		
		// Avoiding a Warning message in Console.
		document.close();
	}
	
	@Disabled
	@Ignore
	@Test
	public void test_getDecryptedFile_NotNull() throws InvalidPasswordException, IOException {
		// Arrange
		String path = PATH_PDF_EXTRATO_B3_2019_03_MARCO;
		String password = PASSWORD;
		
		// Act
		File decryptedFile = LeitorPDF.getDecryptedFile(path, password);
		
		// Assert
		assertNotNull(decryptedFile);
	}
	
	@Ignore
	@Test
	public void test_getPdDocumentEncrypted_DecryptedFileSavedSucessfully() throws InvalidPasswordException, IOException {
		// Arrange
		PDDocument document = 
			LeitorPDF.getPdDocumentEncrypted(
				PATH_PDF_EXTRATO_B3_2019_03_MARCO,
				PASSWORD 
		);
		
		// Act
		document.setAllSecurityToBeRemoved(true);
		
		File decryptedFile = new File(PATH_PDF_EXTRATO_B3_2019_03_MARCO + "_decrypted");
		document.save(decryptedFile);
		
		// Assert
		fail("Assert not yet implemented");
		
		// Avoiding a Warning message in Console.
		document.close();
	}
	
	@Test
	public void get_getPrefixArray_FromFileName() {
		// Arrange
		String originalFileName = PATH_PDF_EXTRATO_B3_2019_03_MARCO;
		
		// Act
		String[] fileName = LeitorPDF.getPrefixArray(originalFileName);
		
		// Assert
		assertNotNull(fileName);
		assertEquals(1, fileName.length);
	}
	
	@Ignore
	@Test
	public void test_getDecryptedFileName_ContainsOriginalFileName() {
		// Arrange
		String originalFileName = PATH_PDF_EXTRATO_B3_2019_03_MARCO;
		
		// Act
		String decryptedFileName = 
			LeitorPDF.getDecryptedFileName(
				LeitorPDF.getPrefixArray(originalFileName));
		
		// Assert
		fail("Assert not yet implemented");
		//assertTrue( decryptedFileName.contains(originalFileName.) );
	}
	
	@Test
	public void test_getPage_NotNull() throws InvalidPasswordException, IOException {
		// Arrange
		String		path			=	LeitorNotasCorretagemClear.getPathToFirstPdfFile();
		PDDocument	pdfDocument		=	LeitorPDF.getPdDocument(path);
		
		// Act
		PDPage		page			=	LeitorPDF.getPage(pdfDocument, 0);
		
		// Assert
		assertThat(page).isNotNull();
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Test
	public void test_getText_FromPage01_PdDocumentNull_TextNotNull() throws InvalidPasswordException, IOException {
		// Arrange
		PDDocument	pdfDocument		=	null;
		
		// Act
		String response = LeitorPDF.getText(pdfDocument, 0);
		
		// Assert
		assertThat(response).isNotNull();
	}
	
	@Test
	public void test_getText_FromPage01_PdDocumentNull_TextEmpty() throws InvalidPasswordException, IOException {
		// Arrange
		PDDocument	pdfDocument		=	null;
		
		// Act
		String response = LeitorPDF.getText(pdfDocument, 0);
		
		// Assert
		assertThat(response).isEmpty();
		//fail("Not implemented yet");
	}
	
	@Test
	public void test_getText_FromPage01_PageNegative_TextNotNull() throws InvalidPasswordException, IOException {
		// Arrange
		String		path			=	LeitorNotasCorretagemClear.getPathToFirstPdfFile();
		PDDocument	pdfDocument		=	LeitorPDF.getPdDocument(path);
		
		// Act
		String response = LeitorPDF.getText(pdfDocument, -1);
		
		// Assert
		assertThat(response).isNotNull();
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Test
	public void test_getText_FromPage01_PageNegative_TextEmpty() throws InvalidPasswordException, IOException {
		// Arrange
		String		path			=	LeitorNotasCorretagemClear.getPathToFirstPdfFile();
		PDDocument	pdfDocument		=	LeitorPDF.getPdDocument(path);
		
		// Act
		String response = LeitorPDF.getText(pdfDocument, -1);
		
		// Assert
		assertThat(response).isEmpty();
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Test
	public void test_getText_FromPage01_NotNull() throws InvalidPasswordException, IOException {
		// Arrange
		String		path			=	LeitorNotasCorretagemClear.getPathToFirstPdfFile();
		PDDocument	pdfDocument		=	LeitorPDF.getPdDocument(path);
		
		// Act
		String response = LeitorPDF.getText(pdfDocument, 0);
		
		// Assert
		assertThat(response).isNotNull();
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Test
	public void test_getText_FromPage01_NotEmpty() throws InvalidPasswordException, IOException {
		// Arrange
		String		path			=	LeitorNotasCorretagemClear.getPathToFirstPdfFile();
		PDDocument	pdfDocument		=	LeitorPDF.getPdDocument(path);
		
		// Act
		String response = LeitorPDF.getText(pdfDocument, 0);
		
		// Assert
		assertThat(response).isNotEmpty();
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Test
	public void test_getText_FromPage01_NotBlank() throws InvalidPasswordException, IOException {
		// Arrange
		String		path			=	LeitorNotasCorretagemClear.getPathToFirstPdfFile();
		PDDocument	pdfDocument		=	LeitorPDF.getPdDocument(path);
		
		// Act
		String response = LeitorPDF.getText(pdfDocument, 0);
		
		// Assert
		assertThat(response.trim()).isNotEmpty();
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Test
	public void test_isClosed_nullPdDocument() throws InvalidPasswordException, IOException {
		// Arrange
		PDDocument	pdfDocument		=	null;
		
		// Act
		boolean response = LeitorPDF.isClosed(pdfDocument);
		
		// Assert
		assertThat(response).isTrue();
	}
	
	@Test
	public void test_isClosed_newPdDocument() throws InvalidPasswordException, IOException {
		// Arrange
		PDDocument	pdfDocument		=	new PDDocument();
		
		// Act
		boolean response = LeitorPDF.isClosed(pdfDocument);
		
		// Assert
		assertThat(response).isFalse();
	}
	
	@Test
	public void test_isClosed_notClosedPdDocument() throws InvalidPasswordException, IOException {
		// Arrange
		String		path			=	LeitorNotasCorretagemClear.getPathToFirstPdfFile();
		PDDocument	pdfDocument		=	LeitorPDF.getPdDocument(path);
		
		// Act
		boolean response = LeitorPDF.isClosed(pdfDocument);
		
		// Assert
		assertThat(response).isFalse();
	}
	
	@Test
	public void test_isClosed_ClosedPdDocument() throws InvalidPasswordException, IOException {
		// Arrange
		String		path			=	LeitorNotasCorretagemClear.getPathToFirstPdfFile();
		PDDocument	pdfDocument		=	LeitorPDF.getPdDocument(path);
		pdfDocument.close();
		
		// Act
		boolean response = LeitorPDF.isClosed(pdfDocument);
		
		// Assert
		assertThat(response).isTrue();
	}
	
}