package main.clear;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class LeitorNotasCorretagemClearTest {

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
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		String path = leitorNotasClear.getRelativePath_SrcMainResources();
		
		// Assert
		assertTrue(path.endsWith("src\\main\\resources\\"));
	}
	
	@Disabled
	@Test
	void testGetFile() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		
		fail("Not yet implemented");
	}
	
	@Disabled
	@Test
	void testGetPdfDocument() {
		// Arrange
		
		
		// Act
		
		
		// Assert
		fail("Not yet implemented");
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
	void testGetAllFilesInDirectory_AllFilesArePDFs() {
		// Arrange
		ILeitorNotasCorretagemClear leitorNotasClear = new LeitorNotasCorretagemClear();
		
		// Act
		List<File> result = leitorNotasClear.getAllFilesInDirectory();
		
		// Assert
		for (File file : result) {
			String fileExtension = 
				file.getName().substring(
					file.getName().length() - 3, 
					file.getName().length()
			);
			
			if ( !fileExtension.equalsIgnoreCase("PDF") ) {
				fail("There is a Non PDF File in the List");
			}
		}
	}

}
