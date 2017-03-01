package net.starkus.cceditor.util;

import java.util.ArrayList;
import java.util.List;

public class GrowthParser {
	
	public static String parseText(String s) {

		List<String> textSegments = new ArrayList<String>();
		List<String> expressionSegments = new ArrayList<String>();
		
		findExpressions(s, textSegments, expressionSegments);
		
		List<String> finalGrowths = new ArrayList<String>();
		
		for (String exp : expressionSegments) {
			
			if (exp.toUpperCase().contains("X") && exp.contains("+")) {
				String parsed = parseLinearExpression(exp);
				finalGrowths.add(parsed);
			}
			else {
				finalGrowths.add("[" + exp + "]");
			}
		}
		
		String result = textSegments.get(0);
		for (int i=0; i < expressionSegments.size(); ++i) {
			//result += "[";
			result += finalGrowths.get(i);
			//result += "]";
			result += textSegments.get(i+1);
		}
		
		return result;
	}
	
	static void findExpressions(String s, List<String> textSegments, List<String> expressions) {
		
		for (String substr : s.split("\\[")) {
			
			String pair[] = substr.split("\\]");
			
			// If theres no ']' it's probably first sub-string. Carry on.
			if (pair.length == 1) {
				
				textSegments.add(pair[0]);
				
				continue;
			}
			
			// Save text segment
			textSegments.add(pair[1]);
			
			// Trim it down to stuff between parenthesis.
			substr = pair[0];
			
			// Add to expression list
			expressions.add(substr);
		}
	}

	static String parseLinearExpression(String s) {
		
		int a = 0;
		int b = 0;
		
		for (String half : s.split("\\+")) {
			
			half = half.toUpperCase();
			
			if (half.contains("X")) {
				a = Integer.parseInt(half.replace("X", ""));
			}
			else {
				b = Integer.parseInt(half);
			}
		}
		
		String result = "[";
		for (int i=0; i<10; ++i) {
			result += Integer.toString(b + a*i);
			result += "/";
		}
		result = result.substring(0, result.length()-1) + "]";
		
		return result;
	}
}
