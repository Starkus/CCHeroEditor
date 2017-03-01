package net.starkus.cceditor.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hero {
	
	private final StringProperty name;
	
	private final IntegerProperty baseDamage;
	private final IntegerProperty baseHealth;
	private final IntegerProperty atkSpeed;
	private final IntegerProperty movSpeed;
	
	private final ObjectProperty<Grade> grade;
	
	private final StringProperty skill;
	
	
	
	public Hero() {
		// Default is Angel with "Unknown" name
		this("Unknown");
	}
	
	public Hero(String name) {
		// Just Angel's base stats
		this(name, 125, 900, 1000, 130, Grade.ORDINARY);
	}
	
	public Hero(String name, int dmg, int hp, int atkspd, int movspd, Grade grade) {
		// No skill. Probably not useful...
		this(name, 125, 900, 1000, 130, Grade.ORDINARY, "");
	}
	
	public Hero(String name, int dmg, int hp, int atkspd, int movspd, Grade grade, String skill) {
		// Full
		this.name = new SimpleStringProperty(name);

		baseDamage = new SimpleIntegerProperty(dmg);
		baseHealth = new SimpleIntegerProperty(hp);
		atkSpeed = new SimpleIntegerProperty(atkspd);
		movSpeed = new SimpleIntegerProperty(movspd);
		
		this.grade = new SimpleObjectProperty<>(grade);
		
		this.skill = new SimpleStringProperty(skill);
	}

	
	
	public String getName() {
		return name.get();
	}
	
	public void setName(String s) {
		name.set(s);
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	
	
	public int getBaseDamage() {
		return baseDamage.get();
	}
	
	public void setBaseDamage(int n) {
		baseDamage.set(n);
	}
	
	public IntegerProperty baseDamageProperty() {
		return baseDamage;
	}
	
	
	
	public int getBaseHealth() {
		return baseHealth.get();
	}
	
	public void setBaseHealth(int n) {
		baseHealth.set(n);
	}
	
	public IntegerProperty baseHealthProperty() {
		return baseHealth;
	}
	
	
	
	public int getAttackSpeed() {
		return atkSpeed.get();
	}
	
	public void setAttackSpeed(int n) {
		atkSpeed.set(n);
	}
	
	public IntegerProperty attackSpeedProperty() {
		return atkSpeed;
	}
	
	
	
	public int getMovementSpeed() {
		return movSpeed.get();
	}
	
	public void setMovementSpeed(int n) {
		movSpeed.set(n);
	}
	
	public IntegerProperty movementSpeedProperty() {
		return movSpeed;
	}
	
	
	
	public Grade getGrade() {
		return grade.get();
	}
	
	public void setGrade(Grade grade) {
		this.grade.set(grade);
	}
	
	public ObjectProperty<Grade> gradeProperty() {
		return grade;
	}

	
	
	public String getSkill() {
		return skill.get();
	}
	
	public void setSkill(String s) {
		skill.set(s);
	}
	
	public StringProperty skillProperty() {
		return skill;
	}

}
