package main.clear;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

}