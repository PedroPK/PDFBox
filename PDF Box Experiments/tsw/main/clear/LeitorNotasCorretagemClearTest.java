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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import main.utils.FilesFoldersUtil;

class LeitorNotasCorretagemClearTest {

	private static final String PATH__SRC_MAIN_RESOURCES = "src\\main\\resources\\";

	@Test
	void testGetRelativePath_SrcMainResources_NotNull() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		String path = leitorNotasClear.getRelativePath_SrcMainResources();
		
		// Assert
		assertNotNull(path);
	}
	
	@Test
	void testGetRelativePath_SrcMainResources_NotEmpty() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		String path = leitorNotasClear.getRelativePath_SrcMainResources();
		
		// Assert
		assertFalse(path.isEmpty());
	}
	
	@Test
	void testGetRelativePath_SrcMainResources_ContainsSrcMainResources() {
		// Arrange
		LeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		String path = leitorNotasClear.getRelativePath_SrcMainResources();
		
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
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		PDDocument document= leitorNotasClear.getPdfDocument();
		
		// Assert
		assertNotNull(document);
		
		// Avoiding a Warning message in Console.
		document.close();
	}
	
	@Test
	void testGetPdfDocument_FirstFile_PageQuantityGreaterThanZero() throws IOException {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear	= new LeitorNotasCorretagemClear();
		PDDocument					pdfDocument			=	leitorNotasClear.getPdfDocument();
		
		// Act
		int result= pdfDocument.getNumberOfPages();
		
		// Assert
		assertThat(result).isGreaterThan(0);
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Ignore
	@Disabled
	@Test
	void test_readAllContentFrom_PdfDocument_NotNull() throws IOException {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument					pdfDocument			=	leitorNotasClear.getPdfDocument();
		
		// Act
		String response = 
			leitorNotasClear.readAllContentFrom(
			pdfDocument
		);
		
		// Assert
		assertThat(response).isNotNull();
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Ignore
	@Disabled
	@Test
	void test_readAllContentFrom_PdfDocument_NotEmpty() throws IOException {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument					pdfDocument			=	leitorNotasClear.getPdfDocument();
		
		// Act
		String response =
			leitorNotasClear.readAllContentFrom(
			pdfDocument
		);
		
		// Assert
		assertThat(response).isNotEmpty();
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
		
		System.out.println(response);
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
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		List<File> result = leitorNotasClear.getAllFilesInDirectory();
		
		// Assert
		assertNotNull(result);
	}
	
	@Test
	void testGetAllFilesInDirectory_NotEmpty() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		List<File> result = leitorNotasClear.getAllFilesInDirectory();
		
		// Assert
		assertFalse(result.isEmpty());
	}
	
	@Test
	void testGetAllFilesInDirectory_OnlyFilesOnList() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		List<File> result = leitorNotasClear.getAllFilesInDirectory();
		
		// Assert
		for (File file : result) {
			if ( !file.isFile() ) {
				fail("There is a Non File in the List");
			}
		}
	}
	
	@Test
	void test_getAllPDF_FilesInDirectory_AllFilesArePDFs() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		List<File> result = leitorNotasClear.getAllPDF_FilesInDirectory();
		
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
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = leitorNotasClear.getPdfDocument();
		int firstPageNumber = 0;
		
		// Act
		String response = leitorNotasClear.readPage(pdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isNotNull();
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Test
	void test_readPage_FirstPageContent_NotEmpty() throws IOException {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = leitorNotasClear.getPdfDocument();
		int firstPageNumber = 0;
		
		// Act
		String response = leitorNotasClear.readPage(pdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isNotEmpty();
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Test
	void test_readDate_FirstPage_NotNull() throws IOException {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = leitorNotasClear.getPdfDocument();
		int firstPageNumber = 0;
		
		// Act
		String response = leitorNotasClear.readDate(pdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isNotNull();
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Test
	void test_readDate_FirstPage_NotEmpty() throws IOException {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = leitorNotasClear.getPdfDocument();
		int firstPageNumber = 0;
		
		// Act
		String response = leitorNotasClear.readDate(pdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isNotEmpty();
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Test
	void test_readDate_FirstPage_13July2018() throws IOException {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = leitorNotasClear.getPdfDocument();
		int firstPageNumber = 0;
		
		// Act
		String response = leitorNotasClear.readDate(pdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isEqualTo("13/07/2018");
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Test
	void test_readDate_SecondtPage_20July2018() throws IOException {
		// Arrange
		LeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = leitorNotasClear.getPdfDocument();
		int firstPageNumber = 1;
		
		// Act
		String response = leitorNotasClear.readDate(pdfDocument, firstPageNumber);
		
		// Assert
		assertThat(response).isEqualTo("20/07/2018");
		
		// Avoiding a Warning message in Console.
		pdfDocument.close();
	}
	
	@Test
	void test_readContentFromEachPage_NullDocument() {
		// Arrange
		LeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = null;
		
		// Act
		List<String> response =
			leitorNotasClear.readContentFromEachPage(
			pdfDocument
		);
		
		// Assert
		assertThat(response).isNotNull();
		assertThat(response).isEmpty();
	}
	
	@Test
	void test_readContentFromEachPage_NewEmptyDocument() {
		// Arrange
		LeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = new PDDocument();
		
		// Act
		List<String> response =
			leitorNotasClear.readContentFromEachPage(
			pdfDocument
		);
		
		// Assert
		assertThat(response).isNotNull();
		assertThat(response).isEmpty();
	}
	
	@Test
	void test_readContentFromEachPage_OnePageDocument() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		fail("Not yet implemented");
	}
	
	@Test
	void test_readContentFromEachPage_174PagesDocument() {
		// Arrange
		LeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		PDDocument pdfDocument = leitorNotasClear.getPdfDocument();
		
		// Act
		List<String> response =
			leitorNotasClear.readContentFromEachPage(
			pdfDocument
		);
		
		// Assert
		assertThat(response).isNotNull();
		assertThat(response).isNotEmpty();
		assertThat(response.size()).isEqualTo(174);
	}
	
}