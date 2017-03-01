package net.starkus.cceditor.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hero {
	
	private final StringProperty name;

	private final Stat damage;
	private final Stat health;
	private final IntegerProperty atkSpeed;
	private final IntegerProperty movSpeed;

	private final Stat evo1Damage;
	private final Stat evo1Health;
	
	private final Stat evo2Damage;
	private final Stat evo2Health;
	
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
		this(name, dmg, hp, atkspd, movspd, grade, "");
	}
	
	public Hero(String name, int dmg, int hp, int atkspd, int movspd,
			Grade grade, String skill) {
		// Full
		this.name = new SimpleStringProperty(name);

		damage = new Stat(dmg);
		health = new Stat(hp);
		
		atkSpeed = new SimpleIntegerProperty(atkspd);
		movSpeed = new SimpleIntegerProperty(movspd);
		
		evo1Damage = new Stat();
		evo1Health = new Stat();
		
		evo2Damage = new Stat();
		evo2Health = new Stat();
		
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
	
	
	
	public Stat getDamage() {
		return damage;
	}
	public void setDamage(Stat skill) {
		damage.copyFrom(skill);
	}
	
	
	
	public Stat getHealth() {
		return health;
	}
	public void setHealth(Stat skill) {
		health.copyFrom(skill);
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
	
	
	
	public Stat getEvo1Damage() {
		return evo1Damage;
	}
	public void setEvo1Damage(Stat stat) {
		evo1Damage.copyFrom(stat);
	}
	
	
	
	public Stat getEvo1Health() {
		return evo1Health;
	}
	public void setEvo1Health(Stat stat) {
		evo1Health.copyFrom(stat);
	}
	
	
	
	public Stat getEvo2Damage() {
		return evo2Damage;
	}
	public void setEvo2Damage(Stat stat) {
		evo2Damage.copyFrom(stat);
	}
	
	
	
	public Stat getEvo2Health() {
		return evo2Health;
	}
	public void setEvo2Health(Stat stat) {
		evo2Health.copyFrom(stat);
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
