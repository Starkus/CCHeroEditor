package net.starkus.cceditor.test;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import net.starkus.cceditor.model.Grade;


public class OldHero {

	private final StringProperty name;

	private final IntegerProperty damagePerStar;
	private final IntegerProperty damagePerLevel;
	private final IntegerProperty healthPerStar;
	private final IntegerProperty healthPerLevel;
	private final IntegerProperty atkSpeed;
	private final IntegerProperty movSpeed;

	private final IntegerProperty evo1DamagePerStar;
	private final IntegerProperty evo2DamagePerStar;
	private final IntegerProperty evo1HealthPerStar;
	private final IntegerProperty evo2HealthPerStar;

	private final IntegerProperty evo1DamageConstant;
	private final IntegerProperty evo2DamageConstant;
	private final IntegerProperty evo1HealthConstant;
	private final IntegerProperty evo2HealthConstant;
	
	private final IntegerProperty evo1DamagePerLevel;
	private final IntegerProperty evo2DamagePerLevel;
	private final IntegerProperty evo1HealthPerLevel;
	private final IntegerProperty evo2HealthPerLevel;
	
	private final ObjectProperty<Grade> grade;
	
	private final StringProperty skill;
	
	
	
	public OldHero() {
		// Default is Angel with "Unknown" name
		this("Unknown");
	}
	
	public OldHero(String name) {
		// Just Angel's base stats
		this(name, 125, 900, 1000, 130, Grade.ORDINARY);
	}
	
	public OldHero(String name, int dmg, int hp, int atkspd, int movspd, Grade grade) {
		// No skill. Probably not useful...
		this(name, dmg, hp, atkspd, movspd, grade, "");
	}
	
	public OldHero(String name, int dmg, int hp, int atkspd, int movspd,
			Grade grade, String skill) {
		// Full
		this.name = new SimpleStringProperty(name);

		damagePerStar = new SimpleIntegerProperty(dmg);
		healthPerStar = new SimpleIntegerProperty(hp);
		atkSpeed = new SimpleIntegerProperty(atkspd);
		movSpeed = new SimpleIntegerProperty(movspd);

		evo1DamagePerStar = new SimpleIntegerProperty();
		evo2DamagePerStar = new SimpleIntegerProperty();
		evo1HealthPerStar = new SimpleIntegerProperty();
		evo2HealthPerStar = new SimpleIntegerProperty();

		evo1DamageConstant = new SimpleIntegerProperty();
		evo2DamageConstant = new SimpleIntegerProperty();
		evo1HealthConstant = new SimpleIntegerProperty();
		evo2HealthConstant = new SimpleIntegerProperty();

		damagePerLevel = new SimpleIntegerProperty();
		healthPerLevel = new SimpleIntegerProperty();
		evo1DamagePerLevel = new SimpleIntegerProperty();
		evo2DamagePerLevel = new SimpleIntegerProperty();
		evo1HealthPerLevel = new SimpleIntegerProperty();
		evo2HealthPerLevel = new SimpleIntegerProperty();
		
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
	
	
	
	public int getDamagePerStar() {
		return damagePerStar.get();
	}
	
	public void setDamagePerStar(int n) {
		damagePerStar.set(n);
	}
	
	public IntegerProperty baseDamageProperty() {
		return damagePerStar;
	}
	
	
	
	public int getHealthPerStar() {
		return healthPerStar.get();
	}
	
	public void setHealthPerStar(int n) {
		healthPerStar.set(n);
	}
	
	public IntegerProperty baseHealthProperty() {
		return healthPerStar;
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
	
	
	
	public int getEvo1DamagePerStar() {
		return evo1DamagePerStar.get();
	}
	
	public void setEvo1DamagePerStar(int n) {
		evo1DamagePerStar.set(n);
	}
	
	public IntegerProperty evo1DamageProperty() {
		return evo1DamagePerStar;
	}
	
	
	
	public int getEvo2DamagePerStar() {
		return evo2DamagePerStar.get();
	}
	
	public void setEvo2DamagePerStar(int n) {
		evo2DamagePerStar.set(n);
	}
	
	public IntegerProperty evo2DamageProperty() {
		return evo2DamagePerStar;
	}
	
	
	
	public int getEvo1HealthPerStar() {
		return evo1HealthPerStar.get();
	}
	
	public void setEvo1HealthPerStar(int n) {
		evo1HealthPerStar.set(n);
	}
	
	public IntegerProperty evo1HealthProperty() {
		return evo1HealthPerStar;
	}
	
	
	
	public int getEvo2HealthPerStar() {
		return evo2HealthPerStar.get();
	}
	
	public void setEvo2HealthPerStar(int n) {
		evo2HealthPerStar.set(n);
	}
	
	public IntegerProperty evo2HealthProperty() {
		return evo2HealthPerStar;
	}
	
	
	
	public int getEvo1DamageConstant() {
		return evo1DamageConstant.get();
	}
	
	public void setEvo1DamageConstant(int n) {
		evo1DamageConstant.set(n);
	}
	
	public IntegerProperty evo1DamageConstantProperty() {
		return evo1DamageConstant;
	}
	
	
	
	public int getEvo2DamageConstant() {
		return evo2DamageConstant.get();
	}
	
	public void setEvo2DamageConstant(int n) {
		evo2DamageConstant.set(n);
	}
	
	public IntegerProperty evo2DamageConstantProperty() {
		return evo2DamageConstant;
	}
	
	
	
	public int getEvo1HealthConstant() {
		return evo1HealthConstant.get();
	}
	
	public void setEvo1HealthConstant(int n) {
		evo1HealthConstant.set(n);
	}
	
	public IntegerProperty evo1HealthConstantProperty() {
		return evo1HealthConstant;
	}
	
	
	
	public int getEvo2HealthConstant() {
		return evo2HealthConstant.get();
	}
	
	public void setEvo2HealthConstant(int n) {
		evo2HealthConstant.set(n);
	}
	
	public IntegerProperty evo2HealthConstantProperty() {
		return evo2HealthConstant;
	}
	
	
	
	public int getDamagePerLevel() {
		return damagePerLevel.get();
	}
	
	public void setDamagePerLevel(int n) {
		damagePerLevel.set(n);
	}
	
	public IntegerProperty damagePerLevelProperty() {
		return damagePerLevel;
	}
	
	
	
	public int getHealthPerLevel() {
		return healthPerLevel.get();
	}
	
	public void setHealthPerLevel(int n) {
		healthPerLevel.set(n);
	}
	
	public IntegerProperty healthPerLevelProperty() {
		return healthPerLevel;
	}
	
	
	
	public int getEvo1DamagePerLevel() {
		return evo1DamagePerLevel.get();
	}
	
	public void setEvo1DamagePerLevel(int n) {
		evo1DamagePerLevel.set(n);
	}
	
	public IntegerProperty evo1DamagePerLevelProperty() {
		return evo1DamagePerLevel;
	}
	
	
	
	public int getEvo2DamagePerLevel() {
		return evo2DamagePerLevel.get();
	}
	
	public void setEvo2DamagePerLevel(int n) {
		evo2DamagePerLevel.set(n);
	}
	
	public IntegerProperty evo2DamagePerLevelProperty() {
		return evo2DamagePerLevel;
	}
	
	
	
	public int getEvo1HealthPerLevel() {
		return evo1HealthPerLevel.get();
	}
	
	public void setEvo1HealthPerLevel(int n) {
		evo1HealthPerLevel.set(n);
	}
	
	public IntegerProperty evo1HealthPerLevelProperty() {
		return evo1HealthPerLevel;
	}
	
	
	
	public int getEvo2HealthPerLevel() {
		return evo2HealthPerLevel.get();
	}
	
	public void setEvo2HealthPerLevel(int n) {
		evo2HealthPerLevel.set(n);
	}
	
	public IntegerProperty evo2HealthPerLevelProperty() {
		return evo2HealthPerLevel;
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
