package main.clear;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import main.LeitorPDF;
import main.utils.FilesFoldersUtil;

class LeitorNotasCorretagemClearTest {
	
	private static final String PATH__SRC_MAIN_RESOURCES = "src\\main\\resources\\";
	
	private static LeitorNotasCorretagemClear	aLeitorNotasClear;
	private static PDDocument					aPdfDocument;
	
	@BeforeAll
	static void loadPdfDocument() {
		aLeitorNotasClear	= new LeitorNotasCorretagemClear();
		aPdfDocument		= aLeitorNotasClear.getPdfDocument();
	}
	
	@AfterAll
	static void cloesPdfDocument() throws IOException {
		aPdfDocument.close();;
	}
	
	@BeforeEach
	void loadPdfDocumentIfClosed() {
		if (	
				aPdfDocument == null
				||
				LeitorPDF.isClosed(aPdfDocument)
		) {
			aPdfDocument = aLeitorNotasClear.getPdfDocument();
		}
	}
	
	@Test
	void testGetRelativePath_SrcMainResources_NotNull() {
		// Arrange done by @BeforeAll method
		
		// Act
		String path = aLeitorNotasClear.getRelativePath_SrcMainResources();
		
		// Assert
		assertNotNull(path);
	}
	
	@Test
	void testGetRelativePath_SrcMainResources_NotEmpty() {
		// Arrange done by @BeforeAll method
		
		// Act
		String path = aLeitorNotasClear.getRelativePath_SrcMainResources();
		
		// Assert
		assertFalse(path.isEmpty());
	}
	
	@Test
	void testGetRelativePath_SrcMainResources_ContainsSrcMainResources() {
		// Arrange done by @BeforeAll method
		
		// Act
		String path = aLeitorNotasClear.getRelativePath_SrcMainResources();
		
		// Assert
		assertTrue(path.endsWith(PATH__SRC_MAIN_RESOURCES));
	}
	
	@Disabled
	@Test
	void testGetFile() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		
		fail("Not yet implemented");
	}
	
	@Test
	void testGetPdfDocument_FirstFile_NotNull() throws IOException {
		// Arrange done by @BeforeAll method
		
		// Act
		PDDocument document= aLeitorNotasClear.getPdfDocument();
		
		// Assert
		assertNotNull(document);
		
		// Avoiding a Warning message in Console.
		document.close();
	}
	
	@Test
	void testGetPdfDocument_FirstFile_PageQuantityGreaterThanZero() throws IOException {
		// Arrange done by @BeforeAll method
		
		// Act
		int result= aPdfDocument.getNumberOfPages();
		
		// Assert
		assertThat(result).isGreaterThan(0);
	}
	
	@Ignore
	@Disabled
	@Test
	void test_readAllContentFrom_PdfDocument_NotNull() throws IOException {
		// Arrange done by @BeforeAll method
		
		// Act
		String response = 
			aLeitorNotasClear.readAllContentFrom(
				aPdfDocument
		);
		
		// Assert
		assertThat(response).isNotNull();
	}
	
	@Ignore
	@Disabled
	@Test
	void test_readAllContentFrom_PdfDocument_NotEmpty() throws IOException {
		// Arrange done by @BeforeAll method
		
		// Act
		String response =
			aLeitorNotasClear.readAllContentFrom(
				aPdfDocument
		);
		
		// Assert
		assertThat(response).isNotEmpty();
	}
	
	@Disabled
	@Test
	void testReadLine() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		
		fail("Not yet implemented");
	}
	
	@Test
	void testGetAllFilesInDirectory_NotNull() {
		// Arrange done by @BeforeAll method
		
		// Act
		List<File> result = aLeitorNotasClear.getAllFilesInDirectory();
		
		// Assert
		assertNotNull(result);
	}
	
	@Test
	void testGetAllFilesInDirectory_NotEmpty() {
		// Arrange done by @BeforeAll method
		
		// Act
		List<File> result = aLeitorNotasClear.getAllFilesInDirectory();
		
		// Assert
		assertFalse(result.isEmpty());
	}
	
	@Test
	void testGetAllFilesInDirectory_OnlyFilesOnList() {
		// Arrange done by @BeforeAll method
		
		// Act
		List<File> result = aLeitorNotasClear.getAllFilesInDirectory();
		
		// Assert
		for (File file : result) {
			if ( !file.isFile() ) {
				fail("There is a Non File in the List");
			}
		}
	}
	
	@Test
	void test_getAllPDF_FilesInDirectory_AllFilesArePDFs() {
		// Arrange done by @BeforeAll method
		
		// Act
		List<File> result = aLeitorNotasClear.getAllPDF_FilesInDirectory();
		
		// Assert
		for (File file : result) {
			boolean isPDF = FilesFoldersUtil.isPDF(file);
			
			if ( !isPDF ) {
				fail("There is a Non PDF File in the List");
			}
		}
	}
	
	@Test
	void test_readPage_FirstPageContent_NotNull() throws IOException {
		// Arrange
		int firstPageNumber = 0;
		
		// Act
		String response = aLeitorNotasClear.readPage(aPdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isNotNull();
	}
	
	@Test
	void test_readPage_FirstPageContent_NotEmpty() throws IOException {
		// Arrange
		int firstPageNumber = 0;
		
		// Act
		String response = aLeitorNotasClear.readPage(aPdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isNotEmpty();
		
	}
	
	@Test
	void test_readDate_FirstPage_NotNull() throws IOException {
		// Arrange
		int firstPageNumber = 0;
		
		// Act
		String response = aLeitorNotasClear.readDate(aPdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isNotNull();
	}
	
	@Test
	void test_readDate_FirstPage_NotEmpty() throws IOException {
		// Arrange
		int firstPageNumber = 0;
		
		// Act
		String response = aLeitorNotasClear.readDate(aPdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isNotEmpty();
	}
	
	
	
	@Test
	void test_readContentFromEachPage_NullDocument() {
		// Arrange
		PDDocument pdfDocument = null;
		
		// Act
		List<String> response =
			aLeitorNotasClear.readContentFromEachPage(
				pdfDocument
		);
		
		// Assert
		assertThat(response).isNotNull();
		assertThat(response).isEmpty();
	}
	
	@Test
	void test_readContentFromEachPage_NewEmptyDocument() {
		// Arrange
		PDDocument pdfDocument = new PDDocument();
		
		// Act
		List<String> response =
			aLeitorNotasClear.readContentFromEachPage(
				pdfDocument
		);
		
		// Assert
		assertThat(response).isNotNull();
		assertThat(response).isEmpty();
	}
	
	@Ignore
	@Disabled
	@Test
	void test_readContentFromEachPage_OnePageDocument() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		fail("Not yet implemented");
	}
	
	@Test
	void test_readContentFromEachPage_174PagesDocument() {
		// Arrange done by @BeforeAll method
		
		// Act
		List<String> response =
			aLeitorNotasClear.readContentFromEachPage(
				aPdfDocument
		);
		
		// Assert
		assertThat(response).isNotNull();
		assertThat(response).isNotEmpty();
		assertThat(response.size()).isEqualTo(174);
	}
	
	@Test
	void test_readDate_firstPage_13July2018() throws IOException {
		// Arrange
		int firstPageNumber = 0;
		
		// Act
		String response = aLeitorNotasClear.readDate(aPdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isEqualTo("13/07/2018");
	}
	
	@Test
	void test_readDate_secondPage_20July2018() throws IOException {
		// Arrange
		int firstPageNumber = 1;
		
		// Act
		String response = aLeitorNotasClear.readDate(aPdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isEqualTo("20/07/2018");
	}
	
	@Test
	void test_readDate_thirdPage_23July2018() throws IOException {
		// Arrange
		int firstPageNumber = 2;
		
		// Act
		String response = aLeitorNotasClear.readDate(aPdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isEqualTo("23/07/2018");
	}
	
	@Test
	void test_readDate_fourthPage_31July2018() throws IOException {
		// Arrange
		int firstPageNumber = 3;
		
		// Act
		String response = aLeitorNotasClear.readDate(aPdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isEqualTo("31/07/2018");
	}
	
	@Test
	void test_readDate_fifthPage_09August2018() throws IOException {
		// Arrange
		int firstPageNumber = 4;
		
		// Act
		String response = aLeitorNotasClear.readDate(aPdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isEqualTo("09/08/2018");
	}
	
	@Test
	void test_readDate_sixthPage_24August2018() throws IOException {
		// Arrange
		int firstPageNumber = 5;
		
		// Act
		String response = aLeitorNotasClear.readDate(aPdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isEqualTo("24/08/2018");
	}
	
	@Test
	void test_readDate_fourtyThirdhPage_07January2019() throws IOException {
		// Arrange
		int firstPageNumber = 42;
		
		// Act
		String response = aLeitorNotasClear.readDate(aPdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isEqualTo("07/01/2019");
	}
	
	@Test
	void test_readDate_fourtyFourthPage_07January2019() throws IOException {
		// Arrange
		int firstPageNumber = 43;
		
		// Act
		String response = aLeitorNotasClear.readDate(aPdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isEqualTo("07/01/2019");
	}
	
	@Test
	void test_readDate_fourtyFifthPage_08January2019() throws IOException {
		// Arrange
		int firstPageNumber = 44;
		
		// Act
		String response = aLeitorNotasClear.readDate(aPdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isEqualTo("08/01/2019");
	}
	
	@Test
	void test_readOrdersContent_nullPdDocument() {
		// Arrange
		PDDocument document = null;
		int firstPageNumber = 0;
		
		// Act
		String response = aLeitorNotasClear.readOrdersContent(document, firstPageNumber);
		
		// Assert
		assertThat(response).isNotNull();
		assertThat(response).isEmpty();
	}
	
	@Test
	void test_readOrdersContent_newPdDocument() {
		// Arrange
		PDDocument document = new PDDocument();
		int firstPageNumber = 0;
		
		// Act
		String response = aLeitorNotasClear.readOrdersContent(document, firstPageNumber);
		
		// Assert
		assertThat(response).isNotNull();
		assertThat(response).isEmpty();
	}
	
	@Test
	void test_readOrdersContent_firstPage_startsWith() {
		// Arrange
		int firstPageNumber = 0;
		
		// Act
		String response = aLeitorNotasClear.readOrdersContent(aPdfDocument, firstPageNumber);
		
		
		// Assert
		String expectedResponse = 
			"20,50 D20,501ON      NMEMBRAER01/00FRACIONARIOC1-BOVESPA\r\n" + 
			"21,00 D21,001ON      NMEMBRAER01/00FRACIONARIOC1-BOVESPA\r\n" + 
			"43,00 D43,001ON      NMULTRAPAR01/00FRACIONARIOC1-BOVESPA";
		
		assertThat(response).startsWith(expectedResponse);
	}
	
	@Test
	void test_readOrdersContent_firstPage_endsWith() {
		// Arrange
		int firstPageNumber = 0;
		
		// Act
		String response = aLeitorNotasClear.readOrdersContent(aPdfDocument, firstPageNumber);
		
		
		// Assert
		String expectedResponse = 
			"20,50 D20,501ON      NMEMBRAER01/00FRACIONARIOC1-BOVESPA\r\n" + 
			"21,00 D21,001ON      NMEMBRAER01/00FRACIONARIOC1-BOVESPA\r\n" + 
			"43,00 D43,001ON      NMULTRAPAR01/00FRACIONARIOC1-BOVESPA";
		
		assertThat(response).endsWith(expectedResponse);
	}
	
	@Test
	void test_readOrdersContent_firstPage_isEquals() {
		// Arrange
		int firstPageNumber = 0;
		
		// Act
		String response = aLeitorNotasClear.readOrdersContent(aPdfDocument, firstPageNumber);
		
		
		// Assert
		String expectedResponse = 
			"20,50 D20,501ON      NMEMBRAER01/00FRACIONARIOC1-BOVESPA\r\n" + 
			"21,00 D21,001ON      NMEMBRAER01/00FRACIONARIOC1-BOVESPA\r\n" + 
			"43,00 D43,001ON      NMULTRAPAR01/00FRACIONARIOC1-BOVESPA";
		
		assertThat(response).isEqualTo(expectedResponse);
	}
	
	@Test
	void test_splitPageContentByOrderHeader_notNull() {
		// Arrange
		String pageContent = "\\r\\nData preg�oFolhaNr.Nota\\r\\n13/07/20181683423\\r\\nCLEAR CORRETORA - GRUPO XP\\r\\n04543-907 SAO PAULO - SPAv. Presidente Juscelino Kubitschek - Torre Sul, 1909 - 29� ANDARVILA \\r\\nTel. (55 11) 4003-6245  \\r\\natendimento@clear.com.bre-mail :www.clear.com.br SAC: 0800-774-0404Internet :\\r\\nC.N.P.J.: 02.332.886/0011-78 Carta Patente:\\r\\ne-mail ouvidoria: ouvidoria@clear.com.brTel. 0800.774.-0404Ouvidoria :\\r\\n \\r\\nC.P.F./C.N.P.J./C.V.M./C.O.B.Cliente\\r\\n039.821.084-54PEDRO CARLOS FERREIRA SANTOS10201466-\\r\\n AssessorC�digo clienteRUA MARIA JUDITH LINS, 640   BAIRRO NOVO\\r\\n 01-2014665-30853130-080  OLINDA - PE \\r\\nParticipante destino do repasse C.I.ValorCliente Custodiante\\r\\nN- C0,00\\r\\nP. VincComplemento nomeAdministradorAcionistaConta correnteAg�nciaBanco\\r\\nN2168501509001\\r\\nNeg�cios realizados\\r\\nQ D/CValor Opera��o / AjustePre�o / AjusteQuantidadeObs. (*)Especifica��o do t�tuloPrazoTipo mercadoC/VNegocia��o\\r\\n20,50 D20,501ON      NMEMBRAER01/00FRACIONARIOC1-BOVESPA\\r\\n21,00 D21,001ON      NMEMBRAER01/00FRACIONARIOC1-BOVESPA\\r\\n43,00 D43,001ON      NMULTRAPAR01/00FRACIONARIOC1-BOVESPA\\r\\nResumo dos Neg�cios Resumo Financeiro D/C\\r\\n0,00Deb�ntures Clearing\\r\\n0,00Vendas � vista D84,50Valor l�quido das opera��es\\r\\n84,50Compras � vista D0,02Taxa de liquida��o\\r\\n0,00Op��es - compras D0,00Taxa de Registro\\r\\n0,00Op��es - vendas D84,52Total  CBLC\\r\\n0,00Opera��es � termo Bolsa\\r\\n0,00Valor das oper. c/ t�tulos p�bl. (v. nom.) D0,00Taxa de termo/op��es\\r\\n84,50Valor das opera��es D0,00Taxa A.N.A.\\r\\nD0,00Emolumentos\\r\\n0,00 DTotal Bovespa / Soma\\r\\nEspecifica��es diversas Corretagem / Despesas\\r\\nClearing D2,40\\r\\nExecu��o 0,00 DA coluna Q indica liquida��o no Agente do Qualificado.\\r\\n0,00Execu��o casa D\\r\\n0,23ISS  (  S�O PAULO  )\\r\\nD0,00Outros\\r\\n2,63Total corretagem / Despesas\\r\\nT - Liquida��o pelo BrutoA - Posi��o Futuro(*) - Observa��es: L�quido para  18/07/2018 87,15 D\\r\\nI - POP Observa��o:  (1) As opera��es a termo n�o s�o computadas no l�quido da faturaC - Clubes e Fundos de A��es 2 - Corretora ou pessoa vinculada atuou na contra parte.\\r\\nP - Carteira Pr�pria # - Neg�cio direto\\r\\nH - Home Broker 8 - Liquida��o Institucional.\\r\\nX - Box D - Day-Trade\\r\\nY - Desmanche de Box F - Cobertura ________________________________________________\\r\\nL - Precat�rio B - Deb�ntures CLEAR CORRETORA - GRUPO XP\\r\\n* O campo ISS contempla al�m do pr�prio ISS  os valores PIS e COFINS ( ISS 5%, PIS 0,65% e COFINS 4%)\\r\\norrlnota.qrp\\r\\n";
		
		
		// Act
		String[] pageContentSplited = pageContent.split("VNegocia��o");
		
		
		// Assert
		assertThat(pageContentSplited).isNotNull(); 
	}
	
	@Test
	void test_splitPageContentByOrderHeader_notEmpty() {
		// Arrange
		String pageContent = "\\r\\nData preg�oFolhaNr.Nota\\r\\n13/07/20181683423\\r\\nCLEAR CORRETORA - GRUPO XP\\r\\n04543-907 SAO PAULO - SPAv. Presidente Juscelino Kubitschek - Torre Sul, 1909 - 29� ANDARVILA \\r\\nTel. (55 11) 4003-6245  \\r\\natendimento@clear.com.bre-mail :www.clear.com.br SAC: 0800-774-0404Internet :\\r\\nC.N.P.J.: 02.332.886/0011-78 Carta Patente:\\r\\ne-mail ouvidoria: ouvidoria@clear.com.brTel. 0800.774.-0404Ouvidoria :\\r\\n \\r\\nC.P.F./C.N.P.J./C.V.M./C.O.B.Cliente\\r\\n039.821.084-54PEDRO CARLOS FERREIRA SANTOS10201466-\\r\\n AssessorC�digo clienteRUA MARIA JUDITH LINS, 640   BAIRRO NOVO\\r\\n 01-2014665-30853130-080  OLINDA - PE \\r\\nParticipante destino do repasse C.I.ValorCliente Custodiante\\r\\nN- C0,00\\r\\nP. VincComplemento nomeAdministradorAcionistaConta correnteAg�nciaBanco\\r\\nN2168501509001\\r\\nNeg�cios realizados\\r\\nQ D/CValor Opera��o / AjustePre�o / AjusteQuantidadeObs. (*)Especifica��o do t�tuloPrazoTipo mercadoC/VNegocia��o\\r\\n20,50 D20,501ON      NMEMBRAER01/00FRACIONARIOC1-BOVESPA\\r\\n21,00 D21,001ON      NMEMBRAER01/00FRACIONARIOC1-BOVESPA\\r\\n43,00 D43,001ON      NMULTRAPAR01/00FRACIONARIOC1-BOVESPA\\r\\nResumo dos Neg�cios Resumo Financeiro D/C\\r\\n0,00Deb�ntures Clearing\\r\\n0,00Vendas � vista D84,50Valor l�quido das opera��es\\r\\n84,50Compras � vista D0,02Taxa de liquida��o\\r\\n0,00Op��es - compras D0,00Taxa de Registro\\r\\n0,00Op��es - vendas D84,52Total  CBLC\\r\\n0,00Opera��es � termo Bolsa\\r\\n0,00Valor das oper. c/ t�tulos p�bl. (v. nom.) D0,00Taxa de termo/op��es\\r\\n84,50Valor das opera��es D0,00Taxa A.N.A.\\r\\nD0,00Emolumentos\\r\\n0,00 DTotal Bovespa / Soma\\r\\nEspecifica��es diversas Corretagem / Despesas\\r\\nClearing D2,40\\r\\nExecu��o 0,00 DA coluna Q indica liquida��o no Agente do Qualificado.\\r\\n0,00Execu��o casa D\\r\\n0,23ISS  (  S�O PAULO  )\\r\\nD0,00Outros\\r\\n2,63Total corretagem / Despesas\\r\\nT - Liquida��o pelo BrutoA - Posi��o Futuro(*) - Observa��es: L�quido para  18/07/2018 87,15 D\\r\\nI - POP Observa��o:  (1) As opera��es a termo n�o s�o computadas no l�quido da faturaC - Clubes e Fundos de A��es 2 - Corretora ou pessoa vinculada atuou na contra parte.\\r\\nP - Carteira Pr�pria # - Neg�cio direto\\r\\nH - Home Broker 8 - Liquida��o Institucional.\\r\\nX - Box D - Day-Trade\\r\\nY - Desmanche de Box F - Cobertura ________________________________________________\\r\\nL - Precat�rio B - Deb�ntures CLEAR CORRETORA - GRUPO XP\\r\\n* O campo ISS contempla al�m do pr�prio ISS  os valores PIS e COFINS ( ISS 5%, PIS 0,65% e COFINS 4%)\\r\\norrlnota.qrp\\r\\n";
		
		
		// Act
		String[] pageContentSplited = pageContent.split("VNegocia��o");
		
		
		// Assert
		assertThat(pageContentSplited.length).isGreaterThan(0); 
	}
	
	@Test
	void test_readLines_NullDocument_NotNullList() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		fail("Not implemented yet");
	}
	
	@Test
	void test_readLines_NullDocument_EmptyList() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		fail("Not implemented yet");
	}
	
	@Test
	void test_readLines_newDocument_NotNullList() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		fail("Not implemented yet");
	}
	
	@Test
	void test_readLines_newDocument_EmptyList() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		fail("Not implemented yet");
	}
	
	@Test
	void test_readLines_firstPage_NotNullList() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		fail("Not implemented yet");
	}
	
	@Test
	void test_readLines_firstPage_NotEmptyList() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		fail("Not implemented yet");
	}
	
	@Test
	void test_readLines_firstPage_sizeEquals3() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		fail("Not implemented yet");
	}
	
}