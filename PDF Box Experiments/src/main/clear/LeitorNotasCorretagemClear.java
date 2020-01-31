package main.clear;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

import main.LeitorPDF;
import main.utils.FilesFoldersUtil;


public class LeitorNotasCorretagemClear implements ILeitorNotasCorretagemClear {

	@Override
	public String getRelativePath_SrcMainResources() {
		// TODO Auto-generated method stub
		
		return FilesFoldersUtil.getFullPathToSrcMainResourceFolder();
	}

	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PDDocument getPdfDocument() {
		PDDocument response = new PDDocument();
		
		try {
			response = LeitorPDF.getPdDocument(getPathToFirstPdfFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}

	@Override
	public String readLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getDirectory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> getAllFilesInDirectory() {
		List<File> response = 
			FilesFoldersUtil.getAllFiles(
			FilesFoldersUtil.getFullPathToSrcMainResourceFolder()
		);
		
		return response;
	}

	@Override
	public List<File> getAllPDF_FilesInDirectory() {
		List<File> allFiles = getAllFilesInDirectory();
		
		List<File> response = new ArrayList<File>();
		
		for (File file : allFiles) {
			String fileExtension = FilesFoldersUtil.getFileExtension(file);
			
			if ( FilesFoldersUtil.isPDF(fileExtension) ) {
				response.add(file);
			}
		}
		
		return response;
	}
	
	private static String	getPathToFirstPdfFile() {
		String response = "";
		
		LeitorNotasCorretagemClear leitorNotasCorretagemClear =
			new LeitorNotasCorretagemClear();
		
		List<File> allPdfFiles = leitorNotasCorretagemClear.getAllPDF_FilesInDirectory();
		
		if ( allPdfFiles != null && !allPdfFiles.isEmpty() ) {
			response = allPdfFiles.get(0).getAbsolutePath();
		}
		
		return response;
	}

	@Override
	public String readAllContentFrom(PDDocument pPdfDocument) {
		String response = "";
		
		LeitorNotasCorretagemClear leitorNotasCorretagemClear =
			new LeitorNotasCorretagemClear();
		
		try {
			response = LeitorPDF.getTexto( leitorNotasCorretagemClear.getPdfDocument() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}

}