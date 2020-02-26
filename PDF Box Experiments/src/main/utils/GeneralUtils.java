package main.utils;

import java.util.List;

public class GeneralUtils {
	
	public static boolean isValid(List<String> pPageSections) {
		return pPageSections != null		&&
		!pPageSections.isEmpty();
	}
	
	public static boolean isValid(String pOrdersContent) {
		return pOrdersContent != null && !pOrdersContent.isEmpty();
	}
	
}