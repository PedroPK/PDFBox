package main.clear;

import java.io.File;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

public interface ILeitorNotasCorretagemClear {
	
	public String		getRelativePath_SrcMainResources();
	
	public File			getDirectory();
	
	public File			getFile();
	
	public List<File>	getAllFilesInDirectory();
	
	public PDDocument	getPdfDocument();
	
	public String		readLine();
	
}