package net.starkus.cceditor.util;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.starkus.cceditor.model.Hero;


@XmlRootElement(name = "save")
public class SavefileWrapper {
	
	private HeroListWrapper heroes;
	
	@XmlElement(name = "heroes")
	public HeroListWrapper getHeroListWrapper() {
		return heroes;
	}
	
	public void setHeroListWrapper(HeroListWrapper heroes) {
		this.heroes = heroes;
	}

	public static class HeroListWrapper {
		
		private List<Hero> heroes;
		
		@XmlElement(name = "hero")
		public List<Hero> getHeroes() {
			return this.heroes;
		}
		
		public void setHeroes(List<Hero> heroes) {
			this.heroes = heroes;
		}
	}
}
