package net.starkus.cceditor.model;

public enum Grade {

	ORDINARY("Ordinary"),
	ELITE("Elite"),
	LEGENDARY("Legendary");
	
	private String str;
	
	private Grade(String name) {
		str = name;
	}
	
	@Override
	public String toString() {
		return str;
	}
}
