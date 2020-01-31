package main;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

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
	
	@Test
	public void test_tentarCriarPDDocumentPropostaHabilitacaoPitang() throws InvalidPasswordException, IOException {
		// Arrange + Act
		PDDocument documento = LeitorPDF.getPdDocument(PATH_PDF_PROPOSTA_HABILITACAO_PITANG);
		
		// Assert
		assertNotNull(documento);
	}
	
	@Test
	public void test_tentarLerPropostaHabilitacaoPitang() throws InvalidPasswordException, IOException {
		// Arrange + Act
		String texto = LeitorPDF.getTexto(PATH_PDF_PROPOSTA_HABILITACAO_PITANG);
		
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
	
	@Test
	public void test_tentarCriarPDDocumentPolicentroContraCheque_2019_02Fevereiro() throws InvalidPasswordException, IOException {
		// Arrange + Act
		PDDocument documento = LeitorPDF.getPdDocument(PATH_PDF_POLICENTRO_CONTRA_CHEQUE_2019_02_FEVEREIRO);
		
		// assert
		assertNotNull(documento);
	}
	
	@Test
	public void test_tentarLerPolicentroContraCheque_2019_02Fevereiro() throws InvalidPasswordException, IOException {
		// Arrange + act
		String texto = LeitorPDF.getTexto(PATH_PDF_POLICENTRO_CONTRA_CHEQUE_2019_02_FEVEREIRO);
		
		
		// Assert
		assertNotNull(texto);
		assertFalse(texto.isEmpty());
	}
	
	@Test
	public void test_getPdDocumentEncrypted_NullAndSecurityEnabled() throws InvalidPasswordException, IOException {
		// Arrange + Act
		PDDocument documento = 
			LeitorPDF.getPdDocumentEncrypted(
				PATH_PDF_EXTRATO_B3_2019_03_MARCO,
				PASSWORD 
		);
		
		// Assert
		assertNotNull(documento);
		assertFalse(documento.isAllSecurityToBeRemoved());
	}
	
	@Test
	public void test_removeEncryption_PdDocumentoEncryptedSucessfullySecurityRemoved() throws InvalidPasswordException, IOException {
		// Arrange
		String path = PATH_PDF_EXTRATO_B3_2019_03_MARCO;
		String password = PASSWORD;
		
		// Act
		PDDocument documento = LeitorPDF.removeEncryption(path, password);
		
		// Assert
		assertTrue(documento.isAllSecurityToBeRemoved());
	}
	
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
	
	@Test
	public void test_getPdDocumentEncrypted_DecryptedFileSavedSucessfully() throws InvalidPasswordException, IOException {
		// Arrange
		PDDocument documento = 
			LeitorPDF.getPdDocumentEncrypted(
				PATH_PDF_EXTRATO_B3_2019_03_MARCO,
				PASSWORD 
		);
		
		// Act
		documento.setAllSecurityToBeRemoved(true);
		
		File decryptedFile = new File(PATH_PDF_EXTRATO_B3_2019_03_MARCO + "_decrypted");
		documento.save(decryptedFile);
		
		// Assert
		fail("Assert not yet implemented");
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
	void test_getPage_NotNull() throws InvalidPasswordException, IOException {
		// Arrange
		String		path			=	LeitorNotasCorretagemClear.getPathToFirstPdfFile();
		PDDocument	pdfDocument		=	LeitorPDF.getPdDocument(path);
		
		// Act
		PDPage		page			=	LeitorPDF.getPage(pdfDocument, 0);
		
		// Assert
		assertThat(page).isNotNull();
	}
	
}