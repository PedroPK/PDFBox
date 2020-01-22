package main.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

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
		String content = aFilesFolderUtil.getSrcMainResiurceFolderContent();
		
		// Assert
		assertNotNull(content);
		
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

}
