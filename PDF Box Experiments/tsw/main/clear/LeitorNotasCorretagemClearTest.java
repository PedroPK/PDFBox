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
	
}