package main.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;

public class FilesFoldersUtil {
	
	private	static final	String TEST					= "test";
	public	static final	String RESOURCES			= "resources";
	public	static final	String MAIN					= "main";
	public	static final	String SRC					= "src";
	private	static final	String FILE_SEPARATOR		= "file.separator";
	private	static final	String OS_NAME				= "os.name";
	private	static final	String USER_DIR				= "user.dir";
	public	static final	String TEST_XLSX_FILENAME	= "test.xlsx";
	public	static final	String CONFIG_PROPERTIES	= "config.properties";
	
	private					Scanner		aScanner;
	private	static			Properties	aProperties;
	
	public static Properties			loadProperties() {
		if ( aProperties == null ) {
			aProperties = new Properties();
		}
		
		try {
			aProperties.load(getConfigPropertiesFileInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aProperties;
	}
	
	public String						readLineConfigProperties() {
		if ( aScanner == null ) {
			aScanner	= new Scanner(getConfigPropertiesFileInputStream());
		}
		
		String line = null;
		
		if ( aScanner.hasNextLine() ) {
			line =	aScanner.nextLine();
		} else {
					aScanner.close();
		}
		
		return line;
	}
	
	public static BufferedReader		getConfigPropertiesBufferedReader() {
		return null;
	}
	
	public static FileInputStream		getConfigPropertiesFileInputStream() {
		FileInputStream response = null;
		try {
			response = new FileInputStream(getConfigPropertiesFilePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	public static File					getConfigPropertiesFile() {
		return new File(getConfigPropertiesFilePath());
	}
	
	public static String				getSrcMainResourceFolderContent() {
		File fileOrFolder = drillDownPath(getFullPathToSrcMainResourceFolder());
		 
			
		return fileOrFolder.getAbsolutePath();
	}

	public static File drillDownPath(String pPathName) {
		File fileOrFolder = new File(pPathName);
		
		String response = "";
		
		if ( fileOrFolder != null ) {
			System.out.println(fileOrFolder.getName());
			
			response =
				response + fileOrFolder.getName();
			
			System.out.println(response);
			
			if ( fileOrFolder.isDirectory() ) {
				String[] subsoldersAndOrFiles = fileOrFolder.list();
				
				for (String fileOrFolderPathName : subsoldersAndOrFiles) {
					drillDownPath( getFullPath(pPathName, fileOrFolderPathName) );
				}
				
				// TODO Resume from here
			}
		}
		return fileOrFolder;
	}

	public static String getFullPath(String pPathName, String pFileOrFolderPathName) {
		return pPathName + getFolderSeparator() + pFileOrFolderPathName;
	}
	
	public static String				getOptionalEmpty() {
		Optional<String> response = Optional.empty();
		
		return response.get();
	}
	
	public static String				getConfigPropertiesFilePath() {
		return getFullPathToSrcMainResourceFolder() + CONFIG_PROPERTIES;
	}
	
	public static String				getRelativePathToSrcTestResourceFolder() {
		String relativePath = 
			getFolderSeparator()	+ SRC + 
			getFolderSeparator()	+ TEST + 
			getFolderSeparator()	+ RESOURCES + 
			getFolderSeparator();
		return relativePath;
	}
	
	public static String				getRelativePathToSrcMainResourceFolder() {
		String relativePath = 
			getFolderSeparator()	+ SRC + 
			getFolderSeparator()	+ MAIN + 
			getFolderSeparator()	+ RESOURCES + 
			getFolderSeparator();
		return relativePath;
	}
	
	public static String				getFolderSeparator() {
		String folderSeparator = System.getProperty(FILE_SEPARATOR);
		return folderSeparator;
	}
	
	public static String				getPathDirectory() {
		String path = System.getProperty(USER_DIR);
		return path;
	}
	
	public static String				getOperationalSystemName() {
		return System.getProperty(OS_NAME);
	}
	
	public static String				getFullPathToSrcTestResourceFolder() {
		String fullPath = getPathDirectory() + getRelativePathToSrcTestResourceFolder();
		return fullPath;
	}
	
	public static String				getFullPathToSrcMainResourceFolder() {
		String fullPath = getPathDirectory() + getRelativePathToSrcMainResourceFolder();
		return fullPath;
	}
	
	public static File					createFile( String pFileName ) throws IOException {
		File file = 
			new File(
					getFullPathToSrcTestResourceFolder() + getFolderSeparator() + pFileName
			);
		
		file.createNewFile();
		
		return file;
	}
	
	public static InputStream			createInputStream( String pFileName ) throws IOException {
		InputStream inputStream = 
			new FileInputStream(
				createFile(pFileName)
			);
		
		return inputStream;
	}
	
}