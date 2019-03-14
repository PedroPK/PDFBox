import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class TestLeitorPDF {
	
	@Test
	public void testTentarCriarArquivoPropostaHabilitacaoPitang() {
		File arquivo = new File("/Users/pedropk/Downloads/Temp/eFisco/pitangpropostahabilitacao02.pdf");
		
		
		Assert.assertNotNull(arquivo);
		
	}
	
	@Test
	public void testTentarCriarPDDocumentPropostaHabilitacaoPitang() throws InvalidPasswordException, IOException {
		PDDocument documento = LeitorPDF.getPdDocument();
		Assert.assertNotNull(documento);
	}
	
	@Test
	public void testTentarLerPropostaHabilitacaoPitang() throws InvalidPasswordException, IOException {
		String texto = LeitorPDF.getTexto();
		
		Assert.assertNotNull(texto);
		Assert.assertFalse(texto.isEmpty());
		
		System.out.println(texto.length());
		System.out.println(texto);
	}
	
}