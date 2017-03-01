package net.starkus.cceditor.test;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "save")
public class AltSaveFileWrapper {
	
	private AltHeroListWrapper heroes;
	
	@XmlElement(name = "heroes")
	public AltHeroListWrapper getHeroListWrapper() {
		return heroes;
	}
	
	public void setHeroListWrapper(AltHeroListWrapper heroes) {
		this.heroes = heroes;
	}

	public static class AltHeroListWrapper {
		
		private List<OldHero> heroes;
		
		@XmlElement(name = "hero")
		public List<OldHero> getHeroes() {
			return this.heroes;
		}
		
		public void setHeroes(List<OldHero> heroes) {
			this.heroes = heroes;
		}
	}
}
