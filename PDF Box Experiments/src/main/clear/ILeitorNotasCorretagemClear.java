package main.clear;

import java.io.File;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

public interface ILeitorNotasCorretagemClear {
	
	public String		getRelativePath_SrcMainResources();
	
	public File			getDirectory();
	
	public File			getFile();
	
	public List<File>	getAllFilesInDirectory();
	
	public List<File>	getAllPDF_FilesInDirectory();
	
	public PDDocument	getPdfDocument();
	
	public String		readAllContentFrom(PDDocument pPdfDocument);
	
	public String		readLine();
	
}