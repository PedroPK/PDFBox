import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class TestLeitorPDF {
	
	private static final String PATH_PDF_PROPOSTA_HABILITACAO_PITANG = "/Users/pedropk/Downloads/Temp/eFisco/pitangpropostahabilitacao02.pdf";
	private static final String PATH_PDF_POLICENTRO_CONTRA_CHEQUE_2019_02_FEVEREIRO = "/Users/pedropk/Downloads/Temp/eFisco/PEDRO CARLOS SANTOS - 2019 02 Fevereiro - Policentro.pdf";
	
	@Test
	public void testTentarCriarArquivoPropostaHabilitacaoPitang() {
		File arquivo = new File(PATH_PDF_PROPOSTA_HABILITACAO_PITANG);
		
		
		Assert.assertNotNull(arquivo);
		
	}
	
	@Test
	public void testTentarCriarPDDocumentPropostaHabilitacaoPitang() throws InvalidPasswordException, IOException {
		PDDocument documento = LeitorPDF.getPdDocument(PATH_PDF_PROPOSTA_HABILITACAO_PITANG);
		Assert.assertNotNull(documento);
	}
	
	@Test
	public void testTentarLerPropostaHabilitacaoPitang() throws InvalidPasswordException, IOException {
		String texto = LeitorPDF.getTexto(PATH_PDF_PROPOSTA_HABILITACAO_PITANG);
		
		Assert.assertNotNull(texto);
		Assert.assertFalse(texto.isEmpty());
		
		System.out.println(texto.length());
		//System.out.println(texto);
	}
	
	@Test
	public void testTentarCriarArquivoPolicentroContraCheque_2019_02Fevereiro() {
		File arquivo = new File(PATH_PDF_POLICENTRO_CONTRA_CHEQUE_2019_02_FEVEREIRO);
		
		
		Assert.assertNotNull(arquivo);
		
	}
	
	@Test
	public void testTentarCriarPDDocumentPolicentroContraCheque_2019_02Fevereiro() throws InvalidPasswordException, IOException {
		PDDocument documento = LeitorPDF.getPdDocument(PATH_PDF_POLICENTRO_CONTRA_CHEQUE_2019_02_FEVEREIRO);
		Assert.assertNotNull(documento);
	}
	
	@Test
	public void testTentarLerPolicentroContraCheque_2019_02Fevereiro() throws InvalidPasswordException, IOException {
		String texto = LeitorPDF.getTexto(PATH_PDF_POLICENTRO_CONTRA_CHEQUE_2019_02_FEVEREIRO);
		
		Assert.assertNotNull(texto);
		Assert.assertFalse(texto.isEmpty());
		
		System.out.println(texto.length());
		System.out.println(texto);
	}
	
}