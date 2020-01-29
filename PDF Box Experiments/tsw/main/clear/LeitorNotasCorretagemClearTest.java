package main.clear;

import static org.junit.jupiter.api.Assertions.*;

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
		fail("Not yet implemented");
	}
	
	@Disabled
	@Test
	void testGetPdfDocument() {
		fail("Not yet implemented");
	}
	
	@Disabled
	@Test
	void testReadLine() {
		fail("Not yet implemented");
	}

}
