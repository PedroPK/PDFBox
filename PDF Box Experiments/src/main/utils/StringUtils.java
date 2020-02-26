package main.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StringUtils {
	
	public static List<String> split(String pContent, String pSplitToken) {
		List<String>	lines =  Arrays.asList(pContent.split(pSplitToken));
		return lines;
	}
	
	public static List<String> removeCarriageReturnFromEachString(List<String> pListWithReturnCarriage) {
		List<String>	responseWithoutBackCarriage = new ArrayList<String>();
		for (String orderLine : pListWithReturnCarriage) {
			orderLine = orderLine.replace("\r", "");
			responseWithoutBackCarriage.add(orderLine);
		}
		pListWithReturnCarriage = responseWithoutBackCarriage;
		return pListWithReturnCarriage;
	}
	
	public static List<String> removeEmptyStrings(List<String> pList) {
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