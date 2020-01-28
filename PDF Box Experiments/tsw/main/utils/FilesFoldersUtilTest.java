package main.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class FilesFoldersUtilTest {
	
	private static FilesFoldersUtil aFilesFolderUtil;
	
	@Disabled
	@Test
	void testLoadProperties() {
		fail("Not yet implemented");
	}
	
	@Disabled
	@Test
	void testReadLineConfigProperties() {
		fail("Not yet implemented");
	}
	
	@Disabled
	@Test
	void testGetConfigPropertiesBufferedReader() {
		fail("Not yet implemented");
	}
	
	@Disabled
	@Test
	void testGetConfigPropertiesFileInputStream() {
		fail("Not yet implemented");
	}
	
	@Disabled
	@Test
	void testGetConfigPropertiesFile() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetSrcMainResiurceFolderContent_NotNull() {
		// Arrange + Act
		String content = aFilesFolderUtil.getSrcMainResourceFolderContent();
		
		// Assert
		assertNotNull(content);
		
		System.out.println(content);
	}
	
	/**
	 * TODO		Resume here
	 */
	@Test
	void testGetSrcMainResiurceFolderContent_NotEmpty() {
		// Arrange + Act
		String content = aFilesFolderUtil.getSrcMainResourceFolderContent();
		
		// Assert
		assertFalse(content.isEmpty());
		
		System.out.println(content);
	}
	
	@Disabled
	@Test
	void testGetConfigPropertiesFilePath() {
		fail("Not yet implemented");
	}
	
	@Disabled
	@Test
	void testGetRelativePathToSrcTestResourceFolder() {
		fail("Not yet implemented");
	}
	
	@Disabled
	@Test
	void testGetRelativePathToSrcMainResourceFolder() {
		fail("Not yet implemented");
	}
	
	@Disabled
	@Test
	void testGetFolderSeparator() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testGetPathDirectory() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testGetOperationalSystemName() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testGetFullPathToSrcTestResourceFolder() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testGetFullPathToSrcMainResourceFolder() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testCreateFile() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testCreateInputStream() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetAllFiles_NotNullList() {
		// Arrange
		String path = aFilesFolderUtil.getFullPathToSrcMainResourceFolder();
		
		// Act
		List<File> result = aFilesFolderUtil.getAllFiles(path);
		
		
		// Assert
		assertNotNull(result);
	}
	
	@Disabled
	@Test
	void testGetAllFiles_NotNullElementsOnList() {
		// Arrange
		
		
		// Act
		
		
		// Assert
	}
	
	@Disabled
	@Test
	void testGetAllFiles_AllFilesNoDirectoryOnList() {
		// Arrange
		
		
		// Act
		
		
		// Assert
	}
	
	@Disabled
	@Test
	void testGetAllFiles_OnlyPDF_FilesOnList() {
		// Arrange
		
		
		// Act
		
		
		// Assert
	}

}
