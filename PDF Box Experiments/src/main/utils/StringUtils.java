package main.utils;

import java.util.Arrays;
import java.util.List;

public class StringUtils {
	
	public static List<String> split(String pContent, String pSplitToken) {
		List<String>	lines =  Arrays.asList(pContent.split(pSplitToken));
		return lines;
	}
	
}