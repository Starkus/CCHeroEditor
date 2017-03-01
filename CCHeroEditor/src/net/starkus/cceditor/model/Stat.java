package net.starkus.cceditor.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Stat {
	
	private IntegerProperty constant;
	private IntegerProperty perStar;
	private IntegerProperty perLevel;
	
	/*
	 * Used by marshaller
	 */
	public Stat() {
		this(0, 0, 0);
	}
	/*
	 * Fixed stats like ATK SPD and MOV SPD
	 */
	public Stat(int perStar) {
		this(perStar, 0);
	}
	/*
	 * Use for base ATK and HP stats
	 */
	public Stat(int perStar, int perLevel) {
		this(0, perStar, perLevel);
	}
	/*
	 * Use for evo stats
	 */
	public Stat(int constant, int perStar, int perLevel) {
		
		this.constant = new SimpleIntegerProperty(constant);
		this.perStar  = new SimpleIntegerProperty(perStar);
		this.perLevel = new SimpleIntegerProperty(perLevel);
	}
	
	
	public void copyFrom(Stat other) {
		this.constant = other.constant;
		this.perStar  = other.perStar;
		this.perLevel = other.perLevel;
	}
	
	
	public int getConstant() {
		return constant.get();
	}
	public void setConstant(int constant) {
		this.constant.set(constant);
	}
	public IntegerProperty constantProperty() {
		return constant;
	}
	
	
	public int getPerStar() {
		return perStar.get();
	}
	public void setPerStar(int perStar) {
		this.perStar.set(perStar);
	}
	public IntegerProperty perStarProperty() {
		return perStar;
	}
	
	
	public int getPerLevel() {
		return perLevel.get();
	}
	public void setPerLevel(int perLevel) {
		this.perLevel.set(perLevel);
	}
	public IntegerProperty perLevelProperty() {
		return perLevel;
	}

}
