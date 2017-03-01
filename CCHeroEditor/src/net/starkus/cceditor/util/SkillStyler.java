package net.starkus.cceditor.util;

import org.fxmisc.richtext.InlineCssTextArea;

public class SkillStyler {
	
	public static void stylizeSkillText(InlineCssTextArea textArea) {
		
		textArea.clearStyle(0);
		
		String text = textArea.getText();
		
		int cursor = 0;
		
		while (true) {
			int start = text.indexOf('[', cursor);
			int end = text.indexOf(']', start)+1;
			
			if (start == -1 || end == -1) {
				break;
			}
			
			textArea.setStyle(start, end, "-fx-fill: orange; -fx-font-weight: bold;");
			
			cursor = end+1;
		}
		
	}

}
