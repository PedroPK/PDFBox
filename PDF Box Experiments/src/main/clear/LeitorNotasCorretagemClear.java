package main.clear;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import main.LeitorPDF;
import main.utils.FilesFoldersUtil;


public class LeitorNotasCorretagemClear implements ILeitorNotasCorretagemClear {
	
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
		
		if ( pageContent != null && !pageContent.isEmpty() ) {
			List<String>	lines =  Arrays.asList(pageContent.split(LINE_BREAK));
			
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
		if ( allData != null && !allData.isEmpty() ) {
			response = Arrays.asList( allData.split(PAGE_HEADER) );
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
		if ( pageContent != null && !pageContent.isEmpty() ) {
			List<String>	pageSections = Arrays.asList( pageContent.split(CONTENT_HEADER_LAST_TOKEN) );
			
			if ( 
					isListValid(pageSections)		&&
					pageSections.size() > 1
			) {
				// Removing the Content Header
				String ordersWithoutHeader = pageSections.get(1);
				
				pageSections = Arrays.asList( ordersWithoutHeader.split(CONTENT_FOOTER) );
				
				if (	isListValid(pageSections)	) {
					// Removing the Content Footer
					String ordersWihtoutHeaderAndFooter = pageSections.get(0);
					
					response	=	ordersWihtoutHeaderAndFooter;
				}
			}
		}
		
		return response;
	}
	
	public boolean isListValid(List<String> pPageSections) {
		return pPageSections != null		&&
		!pPageSections.isEmpty();
	}

	@Override
	public List<String> readLines(PDDocument pPdfDocument, int pPageNumer) {
		return readLines(pPdfDocument, pPageNumer, true);
	}

	@Override
	public List<String> readLines(PDDocument pPdfDocument, int pPageNumer, boolean pClosePdDocument) {
		List<String>	response	= new ArrayList<String>();
		
		String ordersContent = readOrdersContent(pPdfDocument, pPageNumer, pClosePdDocument);
		
		if ( ordersContent != null && !ordersContent.isEmpty() ) {
			response = 
				Arrays.asList(
					ordersContent.split("\n")
				);
		}
		
		// Removing "\r" from each Order Line
		List<String>	responseWithoutBackCarriage = new ArrayList<String>();
		for (String orderLine : response) {
			orderLine = orderLine.replace("\r", "");
			responseWithoutBackCarriage.add(orderLine);
		}
		response = responseWithoutBackCarriage;
		
		return response;
	}
	

}