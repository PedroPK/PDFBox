package main.clear;

import java.io.File;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public interface ILeitorNotasCorretagemClear {
	
	public String		getRelativePath_SrcMainResources();
	
	public File			getDirectory();
	
	public File			getFile();
	
	public List<File>	getAllFilesInDirectory();
	
	public List<File>	getAllPDF_FilesInDirectory();
	
	public PDDocument	getPdfDocument();
	
	public PDPage		getPage(int pPageNumber);
	
	public String		readAllContentFrom(
		PDDocument pPdfDocument);
	
	public String		readAllContentFrom(
		PDDocument pPdfDocument,
		boolean pClosePdDocument);
	
	public List<String>	readContentFromEachPage(
		PDDocument pPdfDocument);
	
	public List<String>	readContentFromEachPage(
		PDDocument pPdfDocument,
		boolean pClosePdDocument);
	
	public String		readPage(
		PDDocument	pPdfDocument,
		int			pPageNumer
	);
	
	public String		readDate(
		PDDocument	pPdfDocument,
		int			pPageNumer
	);
	
	public String		readLine();
	
}