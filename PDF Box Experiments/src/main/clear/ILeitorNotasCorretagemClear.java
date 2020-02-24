package main.clear;

import java.io.File;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import seleniumWebDriver.entities.StockOrder;

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
	
	public String		readPage(
		PDDocument	pPdfDocument,
		int			pPageNumer,
		boolean		pClosePdDocument
	);
	
	public String		readDate(
		PDDocument	pPdfDocument,
		int			pPageNumer
	);
	
	public String		readOrdersContent(
		PDDocument	pPdfDocument,
		int			pPageNumer);
	
	public String		readOrdersContent(
		PDDocument	pPdfDocument,
		int			pPageNumer,
		boolean		pClosePdDocument);
	
	public List<String>		readLines(
		PDDocument	pPdfDocument,
		int			pPageNumer
	);
	
	public List<String>		readLines(
		PDDocument	pPdfDocument,
		int			pPageNumer,
		boolean		pClosePdDocument
	);
	
	public String			readLine(
		PDDocument	pPdfDocument,
		int			pPageNumer,
		int			pLineNumber
	);
	
	public String			readLine(
		PDDocument	pPdfDocument,
		int			pPageNumer,
		int			pLineNumber,
		boolean		pClosePdDocument
	);
	
	public StockOrder		readStockOrder(
		PDDocument	pPdfDocument,
		int			pPageNumer,
		int			pLineNumber
	);
	
	public StockOrder		readStockOrder(
		PDDocument	pPdfDocument,
		int			pPageNumer,
		int			pLineNumber,
		boolean		pClosePdDocument
	);
	
	public String			readOrderType(
		PDDocument	pPdfDocument,
		int			pPageNumer,
		int			pLineNumber
	);
	
	public String			readStockName(
		PDDocument	pPdfDocument,
		int			pPageNumer,
		int			pLineNumber
	);
	
	public String			readFirstValueFromLine(
		String		pLine
	);
	
	public String			readFirstValueFromLine(
		PDDocument	pPdfDocument,
		int			pPageNumer,
		int			pLineNumber
	);
	
	public String			readFirstValueFromLine(
		PDDocument	pPdfDocument,
		int			pPageNumer,
		int			pLineNumber,
		boolean		pClosePdDocument
	);
	
	public String			readSecondValueFromLine(
		String		pLine
	);
	
	public String			readSecondValueFromLine(
		PDDocument	pPdfDocument,
		int			pPageNumer,
		int			pLineNumber
	);
	
	public String			readSecondValueFromLine(
		PDDocument	pPdfDocument,
		int			pPageNumer,
		int			pLineNumber,
		boolean		pClosePdDocument
	);
	
}