package net.starkus.cceditor.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import net.starkus.cceditor.model.Hero;
import net.starkus.cceditor.model.Stat;
import net.starkus.cceditor.test.AltSaveFileWrapper.AltHeroListWrapper;
import net.starkus.cceditor.util.SavefileWrapper;

public class SavefileFixing {
	
	static List<OldHero> oldHeroList = new ArrayList<>();
	static List<Hero> heroList = new ArrayList<>();
	

	static File getInputFile() {
		
		return new File(System.getenv("APPDATA") + "/CCStuff/herolist.xml");
	}
	static File getOutputFile() {
		
		return new File(System.getenv("APPDATA") + "/CCStuff/letssee.xml");
	}
	
	static void load(File file) {
		
		try {
			JAXBContext context = JAXBContext.newInstance(AltSaveFileWrapper.class);
			Unmarshaller um = context.createUnmarshaller();
			
			AltSaveFileWrapper wrapper = (AltSaveFileWrapper) um.unmarshal(file);
			AltHeroListWrapper heroWrapper = wrapper.getHeroListWrapper();
			
			if (heroWrapper != null) {
				oldHeroList.addAll(heroWrapper.getHeroes());
			}
			else
				System.err.println("No heroes in savefile!");
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	static void save(File file) {
		
		try {
			JAXBContext context = JAXBContext.newInstance(SavefileWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			SavefileWrapper.HeroListWrapper heroWrapper = new SavefileWrapper.HeroListWrapper();
			heroWrapper.setHeroes(heroList);
			
			SavefileWrapper wrapper = new SavefileWrapper();
			wrapper.setHeroListWrapper(heroWrapper);
			
			m.marshal(wrapper, file);
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	static void makeNewHeroes() {
		
		for (OldHero oldh : oldHeroList) {
			Hero newh = new Hero();
			newh.setName(oldh.getName());
			newh.setGrade(oldh.getGrade());
			newh.setSkill(oldh.getSkill());
			
			newh.setDamage(new Stat(0, oldh.getDamagePerStar(), oldh.getDamagePerLevel()));
			newh.setHealth(new Stat(0, oldh.getHealthPerStar(), oldh.getHealthPerLevel()));
			
			newh.setAttackSpeed(oldh.getAttackSpeed());
			newh.setMovementSpeed(oldh.getMovementSpeed());
			
			newh.setEvo1Damage(new Stat(
					oldh.getEvo1DamageConstant(),
					oldh.getEvo1DamagePerStar(),
					oldh.getEvo1DamagePerLevel()));
			
			newh.setEvo1Health(new Stat(
					oldh.getEvo1HealthConstant(),
					oldh.getEvo1HealthPerStar(),
					oldh.getEvo1HealthPerLevel()));
			
			newh.setEvo2Damage(new Stat(
					oldh.getEvo2DamageConstant(),
					oldh.getEvo2DamagePerStar(),
					oldh.getEvo2DamagePerLevel()));
			
			newh.setEvo2Health(new Stat(
					oldh.getEvo2HealthConstant(),
					oldh.getEvo2HealthPerStar(),
					oldh.getEvo2HealthPerLevel()));
			
			heroList.add(newh);
		}
	}
	
	public static void main(String[] args) {

		File input = getInputFile();
		File output = getOutputFile();
		
		load(input);
		
		makeNewHeroes();
		
		save(output);
	}

}
