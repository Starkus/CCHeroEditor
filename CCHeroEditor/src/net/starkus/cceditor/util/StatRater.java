package net.starkus.cceditor.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.TextField;
import net.starkus.cceditor.model.Hero;
import net.starkus.cceditor.model.Stat;

public class StatRater {
	
	private static final Map<StatType, StatRecord> records = new HashMap<>();
	
	
	public static void createRecords(List<Hero> heroes) {
		
		for (StatType type : StatType.values()) {
			records.put(type, new StatRecord());
		}
		
		for (Hero h : heroes) {
			// updateRecords compares a single hero.
			updateRecords(h);
		}
	}
	
	public static void updateRecords(Hero h) {

		records.get(StatType.DAMAGE).compare(h.getDamage());
		records.get(StatType.HEALTH).compare(h.getHealth());
		records.get(StatType.ATKSPD).compare(h.getAttackSpeed());
		records.get(StatType.MOVSPD).compare(h.getMovementSpeed());
		records.get(StatType.EVO1DAMAGE).compare(h.getEvo1Damage());
		records.get(StatType.EVO1HEALTH).compare(h.getEvo1Health());
		records.get(StatType.EVO2DAMAGE).compare(h.getEvo2Damage());
		records.get(StatType.EVO2HEALTH).compare(h.getEvo2Health());
	}
	
	
	static String colorGradient(float factor) {
		
		float r = MathUtils.clamp01((factor - 0.5f) *  4f);
		float b = MathUtils.clamp01((factor - 0.5f) * -4f);
		float g = MathUtils.clamp01(2f - 4f * Math.abs(factor - 0.5f));
		
		Color color = new Color(r, g, b);
		color = color.darker();
		
		String hex = String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
		
		return hex;
	}
	
	
	public static void rateInTextField(TextField field, StatType type, StatSubType subType) {
		
		int value = Integer.parseInt(field.getText());
		
		StatRecord sr = records.get(type);
		
		int min, max;
		
		switch (subType) {
		case PERSTAR:
			min = sr.lowest.getPerStar();
			max = sr.highest.getPerStar();
			break;
		case PERLEVEL:
			min = sr.lowest.getPerLevel();
			max = sr.highest.getPerLevel();
			break;
		default:
			min = sr.lowest.getConstant();
			max = sr.highest.getConstant();
			break;
		}
		
		// Lower is better
		if (type == StatType.ATKSPD) {
			int z = min;
			min = max;
			max = z;
		}
		
		float factor = MathUtils.map(value, min, max, 0f, 1f);
		
		String color = colorGradient(factor);
		
		field.setStyle("-fx-font-weight: bold; -fx-text-inner-color: " + color + ";");
	}
	

	public enum StatType {
		DAMAGE,
		HEALTH,
		ATKSPD,
		MOVSPD,
		EVO1DAMAGE,
		EVO1HEALTH,
		EVO2DAMAGE,
		EVO2HEALTH
	}
	
	public enum StatSubType {
		CONSTANT,
		PERSTAR,
		PERLEVEL
	}
	
	public static class StatRecord {
		
		public final Stat lowest  = new Stat(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
		public final Stat highest = new Stat(0, 0, 0);
		
		public void compare(Stat other) {
			
			int k = other.getConstant();
			int s = other.getPerStar();
			int l = other.getPerLevel();
			
			if (k < lowest.getConstant())
				lowest.setConstant(k);
			if (s < lowest.getPerStar())
				lowest.setPerStar(s);
			if (l < lowest.getPerLevel())
				lowest.setPerLevel(l);
			
			if (k > highest.getConstant())
				highest.setConstant(k);
			if (s > highest.getPerStar())
				highest.setPerStar(s);
			if (l > highest.getPerLevel())
				highest.setPerLevel(l);
		}
		
		public void compare(int singleValue) {
			
			compare(new Stat(singleValue, 0, 0));
		}
	}
}
