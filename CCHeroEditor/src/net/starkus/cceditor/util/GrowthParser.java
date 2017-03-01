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
			
			if (exp.toUpperCase().contains("X") && (exp.contains("+") || exp.contains("-"))) {
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
		
		float a = 0;
		float b = 0;
		
		int sign = 1;
		
		String c = "\\+";
		if (s.contains("-")) {
			c = "\\-";
			sign = -1;
		}
		
		String pair[] = s.split(c);
		
		for (String half : pair) {
			
			half = half.toUpperCase();
			
			if (half.contains("X")) {
				a = Float.parseFloat(half.replace("X", "")) * sign;
			}
			else {
				b = Float.parseFloat(half);
			}
		}
		
		String result = "[";
		for (int i=0; i<10; ++i) {
			String num = Float.toString(b + a*i);
			if (num.endsWith(".0"))
				num = num.substring(0, num.length()-2);
			
			result += num;
			result += "/";
		}
		result = result.substring(0, result.length()-1) + "]";
		
		return result;
	}
}
