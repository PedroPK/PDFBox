package main.clear;

import static org.junit.jupiter.api.Assertions.*;

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
	void testGetFile() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPdfDocument() {
		fail("Not yet implemented");
	}

	@Test
	void testReadLine() {
		fail("Not yet implemented");
	}

}
