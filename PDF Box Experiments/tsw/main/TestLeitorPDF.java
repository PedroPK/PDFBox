package main;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class TestLeitorPDF {
	
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
	public void testTentarCriarArquivoPropostaHabilitacaoPitang() {
		File arquivo = new File(PATH_PDF_PROPOSTA_HABILITACAO_PITANG);
		
		
		assertNotNull(arquivo);
		
	}
	
	@Test
	public void testTentarCriarPDDocumentPropostaHabilitacaoPitang() throws InvalidPasswordException, IOException {
		PDDocument documento = LeitorPDF.getPdDocument(PATH_PDF_PROPOSTA_HABILITACAO_PITANG);
		assertNotNull(documento);
	}
	
	@Test
	public void testTentarLerPropostaHabilitacaoPitang() throws InvalidPasswordException, IOException {
		String texto = LeitorPDF.getTexto(PATH_PDF_PROPOSTA_HABILITACAO_PITANG);
		
		assertNotNull(texto);
		assertFalse(texto.isEmpty());
		
		//System.out.println(texto.length());
		//System.out.println(texto);
	}
	
	@Test
	public void testTentarCriarArquivoPolicentroContraCheque_2019_02Fevereiro() {
		File arquivo = new File(PATH_PDF_POLICENTRO_CONTRA_CHEQUE_2019_02_FEVEREIRO);
		
		
		assertNotNull(arquivo);
		
	}
	
	@Test
	public void testTentarCriarPDDocumentPolicentroContraCheque_2019_02Fevereiro() throws InvalidPasswordException, IOException {
		PDDocument documento = LeitorPDF.getPdDocument(PATH_PDF_POLICENTRO_CONTRA_CHEQUE_2019_02_FEVEREIRO);
		assertNotNull(documento);
	}
	
	@Test
	public void testTentarLerPolicentroContraCheque_2019_02Fevereiro() throws InvalidPasswordException, IOException {
		String texto = LeitorPDF.getTexto(PATH_PDF_POLICENTRO_CONTRA_CHEQUE_2019_02_FEVEREIRO);
		
		assertNotNull(texto);
		assertFalse(texto.isEmpty());
		
		/*System.out.println(texto.length());
		System.out.println(texto);*/
	}
	
	@Test
	public void testPdDocumentoEncryptedNotNullAndSecurityEnabled() throws InvalidPasswordException, IOException {
		PDDocument documento = 
			LeitorPDF.getPdDocumentEncrypted(
				PATH_PDF_EXTRATO_B3_2019_03_MARCO,
				PASSWORD 
		);
		
		assertNotNull(documento);
		assertFalse(documento.isAllSecurityToBeRemoved());
	}
	
	@Test
	public void testPdDocumentoEncryptedSucessfullySecurityRemoved() throws InvalidPasswordException, IOException {
		String path = PATH_PDF_EXTRATO_B3_2019_03_MARCO;
		String password = PASSWORD;
		
		PDDocument documento = LeitorPDF.removeEncryption(path, password);
		
		assertTrue(documento.isAllSecurityToBeRemoved());
	}
	
	@Test
	public void testDecryptedFileNotNull() throws InvalidPasswordException, IOException {
		String path = PATH_PDF_EXTRATO_B3_2019_03_MARCO;
		String password = PASSWORD;
		
		File decryptedFile = LeitorPDF.getDecryptedFile(path, password);
		
		assertNotNull(decryptedFile);
	}
	
	@Test
	public void testDecryptedFileSavedSucessfully() throws InvalidPasswordException, IOException {
		PDDocument documento = 
			LeitorPDF.getPdDocumentEncrypted(
				PATH_PDF_EXTRATO_B3_2019_03_MARCO,
				PASSWORD 
		);
		
		documento.setAllSecurityToBeRemoved(true);
		
		File decryptedFile = new File(PATH_PDF_EXTRATO_B3_2019_03_MARCO + "_decrypted");
		documento.save(decryptedFile);
	}
	
	@Test
	public void getPrefixArrayFromFileName() {
		String originalFileName = PATH_PDF_EXTRATO_B3_2019_03_MARCO;
		String[] fileName = LeitorPDF.getPrefixArray(originalFileName);
		
		
		
		assertNotNull(fileName);
		assertEquals(1, fileName.length);
	}
	
	@Ignore
	@Test
	public void testDecryptedFileNameContainsOriginalFileName() {
		String originalFileName = PATH_PDF_EXTRATO_B3_2019_03_MARCO;
		String decryptedFileName = 
			LeitorPDF.getDecryptedFileName(
				LeitorPDF.getPrefixArray(originalFileName));
		
		//assertTrue( decryptedFileName.contains(originalFileName.) );
	}
	
}