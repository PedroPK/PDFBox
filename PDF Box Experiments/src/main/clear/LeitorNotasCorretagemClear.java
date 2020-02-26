package main.clear;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import difflib.StringUtills;
import main.LeitorPDF;
import main.utils.FilesFoldersUtil;
import main.utils.StringUtils;
import seleniumWebDriver.entities.StockOrder;


public class LeitorNotasCorretagemClear implements ILeitorNotasCorretagemClear {
	
	private static final String SINGLE_SPACE = " ";

	private static final String LINE_BREAK = "\n";

	public static final String PAGE_HEADER	=	"NOTA DE CORRETAGEM";
	
	public static final String CONTENT_HEADER_FULL_LINE = "Q D/CValor Operação / AjustePreço / AjusteQuantidadeObs. (*)Especificação do títuloPrazoTipo mercadoC/VNegociação";
	public static final String CONTENT_HEADER_LAST_TOKEN = "VNegociação\r\n";
	
	public static final String CONTENT_FOOTER = "\r\nResumo dos Negócios Resumo Financeiro D/C\r\n";
	
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
		@SuppressWarnings("resource")
		PDDocument response = new PDDocument();
		
		try {
			response = LeitorPDF.getPdDocument(getPathToFirstPdfFile());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * This code below is preventing to read the Content of PDF Document
		 * 
		 * finally { try { response.close(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 */
		
		return response;
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
	
	public static String	getPathToFirstPdfFile() {
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
		
		try {
			response = LeitorPDF.getText( pPdfDocument );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	@Override
	public String readAllContentFrom(
		PDDocument	pPdfDocument,
		boolean		pClosePdDocument
	) {
		String response = "";
		
		try {
			response = 
				LeitorPDF.getText( 
					pPdfDocument, 
					pClosePdDocument );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	@Override
	public PDPage getPage(int pPageNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String readPage(
		PDDocument	pPdfDocument, 
		int			pPageNumber
	) {
		return 
			readPage(
				pPdfDocument,
				pPageNumber,
				true);
	}
	
	@Override
	public String readPage(
		PDDocument	pPdfDocument, 
		int			pPageNumber,
		boolean		pClosePdDocument
	) {
		String pageContent = "";
		
		if ( pPageNumber >= 0 ) {
			List<String> eachPageContent = 
				readContentFromEachPage(
					pPdfDocument,
					pClosePdDocument);
			
			if ( eachPageContent != null && eachPageContent.size() > pPageNumber ) {
				pageContent = eachPageContent.get(pPageNumber);
			}
		}
		return pageContent;
	}
	
	@Override
	public String readDate(PDDocument pPdfDocument, int pPageNumber) {
		String response = "";
		
		String pageContent = readPage(pPdfDocument, pPageNumber);
		
		if ( isValid(pageContent) ) {
			String splitToken = LINE_BREAK;
			
			List<String> lines = StringUtils.split(pageContent, splitToken);
			
			if ( isListValid(lines) ) {
				response = lines.get(2);
				
				if ( response != null && response.length() > 10 ) {
					response = response.substring(0, 10);
				}
			}
		}
		
		return response;
	}
	
	@Override
	public List<String> readContentFromEachPage(PDDocument pPdfDocument) {
		return readContentFromEachPage(pPdfDocument, true);
	}
	
	@Override
	public List<String> readContentFromEachPage(
		PDDocument	pPdfDocument,
		boolean		pClosePdDocument
	) {
		List<String>	response = new ArrayList<String>();
		
		String allData				=	readAllContentFrom(pPdfDocument);
		if ( isValid(allData) ) {
			response = StringUtils.split(allData, PAGE_HEADER);
		}
		
		// Removing Empty Strings generated by the Split
		response = removeEmptyStrings(response);
		
		return response;
	}
	
	private static List<String> removeEmptyStrings(List<String> pList) {
		List<String>	notEmptyStringResponse = new ArrayList<String>();
		Iterator<String> iterator = pList.iterator();
		while ( iterator.hasNext() ) {
			String token = iterator.next();
			
			if ( !token.isEmpty() ) {
				notEmptyStringResponse.add(token);
			}
		}
		return notEmptyStringResponse;
	}

	@Override
	public String readOrdersContent(PDDocument pPdfDocument, int pPageNumer) {
		return readOrdersContent(pPdfDocument, pPageNumer, true);
	}

	@Override
	public String readOrdersContent(PDDocument pPdfDocument, int pPageNumer, boolean pClosePdDocument) {
		String response = "";
		
		String pageContent = readPage(pPdfDocument, pPageNumer, pClosePdDocument);
		if ( isValid(pageContent) ) {
			List<String>	pageSections = 
				StringUtils.split( pageContent, CONTENT_HEADER_LAST_TOKEN );
			
			if ( 
					isListValid(pageSections)		&&
					pageSections.size() > 1
			) {
				// Removing the Content Header
				String ordersWithoutHeader = pageSections.get(1);
				
				pageSections =
					StringUtils.split(ordersWithoutHeader, CONTENT_FOOTER);
				
				if (	isListValid(pageSections)	) {
					// Removing the Content Footer
					String ordersWihtoutHeaderAndFooter = pageSections.get(0);
					
					response	=	ordersWihtoutHeaderAndFooter;
				}
			}
		}
		
		return response;
	}
	
	@Override
	public List<String> readLines(PDDocument pPdfDocument, int pPageNumer) {
		return readLines(pPdfDocument, pPageNumer, true);
	}

	@Override
	public List<String> readLines(PDDocument pPdfDocument, int pPageNumer, boolean pClosePdDocument) {
		List<String>	response	= new ArrayList<String>();
		
		String ordersContent = readOrdersContent(pPdfDocument, pPageNumer, pClosePdDocument);
		
		if ( isValid(ordersContent) ) {
			response = 
				StringUtils.split(ordersContent, LINE_BREAK);
		}
		
		// Removing "\r" from each Order Line
		response = StringUtils.removeCarriageReturnFromEachString(response);
		
		return response;
	}
	
	@Override
	public String readLine(PDDocument pPdfDocument, int pPageNumer, int pLineNumber) {
		return readLine(pPdfDocument, pPageNumer, pLineNumber, true);
	}

	@Override
	public String readLine(PDDocument pPdfDocument, int pPageNumer, int pLineNumber, boolean pClosePdDocument) {
		String response = "";
		
		List<String>	lines	=	readLines(pPdfDocument, pPageNumer, pClosePdDocument);
		
		if ( isListValid(lines) && lines.size() > pLineNumber ) {
			response = lines.get(pLineNumber);
		}
		
		return response;
	}

	@Override
	public StockOrder readStockOrder(PDDocument pPdfDocument, int pPageNumer, int pLineNumber) {
		StockOrder		response	=	null;
		
		String stockLine	=	readLine(pPdfDocument, pPageNumer, pLineNumber);
		
		if ( isValid(stockLine) ) {
			
		}
		
		//response	=	new StockOrder(pYear, pMonth, pDay, pHour, pMinute, pSecond, pTicker, pQuantity, pPrice, pOrderType)
		
		return response;
	}

	@Override
	public StockOrder readStockOrder(PDDocument pPdfDocument, int pPageNumer, int pLineNumber,
			boolean pClosePdDocument) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isValid(String pOrdersContent) {
		return pOrdersContent != null && !pOrdersContent.isEmpty();
	}
	
	private static boolean isListValid(List<String> pPageSections) {
		return pPageSections != null		&&
		!pPageSections.isEmpty();
	}
	
	@Override
	public String readOrderType(PDDocument pPdfDocument, int pPageNumer, int pLineNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readStockName(PDDocument pPdfDocument, int pPageNumer, int pLineNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readFirstValueFromLine(String pLine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readFirstValueFromLine(PDDocument pPdfDocument, int pPageNumer, int pLineNumber) {
		return
			readFirstValueFromLine(
				pPdfDocument, 
				pPageNumer, 
				pLineNumber, 
				true);
	}

	@Override
	public String readFirstValueFromLine(
		PDDocument	pPdfDocument, 
		int			pPageNumer, 
		int			pLineNumber,
		boolean		pClosePdDocument
	) {
		String response = "";
		
		List<String> tokensList = StringUtils.split(readLine(pPdfDocument, pPageNumer, pLineNumber), SINGLE_SPACE);
		
		if ( tokensList != null && !tokensList.isEmpty() ) {
			response = tokensList.get(0);
		}
		
		return response;
	}

	@Override
	public String readSecondOrderTypeFromLine(String pLine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readSecondOrderTypeFromLine(PDDocument pPdfDocument, int pPageNumer, int pLineNumber) {
		return readSecondOrderTypeFromLine(pPdfDocument, pPageNumer, pLineNumber, true);
	}

	@Override
	public String readSecondOrderTypeFromLine(
		PDDocument		pPdfDocument, 
		int				pPageNumer, 
		int				pLineNumber,
		boolean			pClosePdDocument
	) {
		String response = "";
		
		List<String> tokensList = StringUtils.split(readLine(pPdfDocument, pPageNumer, pLineNumber), SINGLE_SPACE);
		
		if ( tokensList != null && !tokensList.isEmpty() && tokensList.size() >= 2 ) {
			response = tokensList.get(1).substring(0, 1);
		}
		
		return response;
	}

	@Override
	public String readThirdTotalOrderValueFromLine(String pLine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readThirdTotalOrderValueFromLine(PDDocument pPdfDocument, int pPageNumer, int pLineNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readThirdTotalOrderValueFromLine(PDDocument pPdfDocument, int pPageNumer, int pLineNumber,
			boolean pClosePdDocument) {
		// TODO Auto-generated method stub
		return null;
	}
	
}