package main;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;

public interface ILeitorNotasCorretagemClear {
	
	public String getRelativePath_SrcMainResources();
	
	public File getFile();
	
	public PDDocument getPdfDocument();
	
	public String readLine();
	
	
	
}