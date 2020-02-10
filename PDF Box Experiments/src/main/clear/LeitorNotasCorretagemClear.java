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
	
	public static final String CONTENT_HEADER = "Q D/CValor Opera��o / AjustePre�o / AjusteQuantidadeObs. (*)Especifica��o do t�tuloPrazoTipo mercadoC/VNegocia��o";
	public static final String CONTENT_FOOTER = "Resumo dos Neg�cios Resumo Financeiro D/C\r\n";

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
	public String readPage(PDDocument pPdfDocument, int pPageNumer) {
		return LeitorPDF.getText(pPdfDocument, pPageNumer);
	}

	@Override
	public String readDate(PDDocument pPdfDocument, int pPageNumer) {
		String response = "";
		
		String pageContent = "";
		
		List<String> eachPageContent = readContentFromEachPage(pPdfDocument);
		
		if ( eachPageContent != null && eachPageContent.size() > pPageNumer ) {
			pageContent = eachPageContent.get(pPageNumer);
		}
		
		if ( pageContent != null && !pageContent.isEmpty() ) {
			List<String>	lines =  Arrays.asList(pageContent.split(LINE_BREAK));
			
			if ( lines != null && !lines.isEmpty() ) {
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
	

}