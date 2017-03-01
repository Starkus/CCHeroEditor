package net.starkus.cceditor.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import net.starkus.cceditor.MainApp;
import net.starkus.cceditor.model.Hero;
import net.starkus.cceditor.util.SavefileWrapper.HeroListWrapper;

public class SaveNLoad {

	private static MainApp mainApp;
	
	
	
	public static void init(MainApp mainApp) {
		
		SaveNLoad.mainApp = mainApp;
	}
	
	private static File chooseFile(boolean open) {
		
		String title = "Save...";
		if (open)
			title = "Open...";
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.setInitialDirectory(mainApp.getSavefile().getParentFile());
		fileChooser.setInitialFileName(mainApp.getSavefile().getName());

		FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("XML Document (*.xml)", "*.xml");
		FileChooser.ExtensionFilter allFilter = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
		fileChooser.getExtensionFilters().add(xmlFilter);
		fileChooser.getExtensionFilters().add(allFilter);
		
		fileChooser.setSelectedExtensionFilter(xmlFilter);
		
		File chosen;
		
		if (open)
			chosen = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		else
			chosen = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
		
		mainApp.setSavefile(chosen);
		
		return chosen;
	}
	
	public static void load() {
		
		File file = chooseFile(true);
		
		if (file == null)
			return;
		
		load(file);
	}
	
	public static void load(File file) {
		
		if (file == null) {
			load();
			return;
		}
		
		try {
			JAXBContext context = JAXBContext.newInstance(SavefileWrapper.class);
			Unmarshaller um = context.createUnmarshaller();
			
			SavefileWrapper wrapper = (SavefileWrapper) um.unmarshal(file);
			HeroListWrapper heroWrapper = wrapper.getHeroListWrapper();
			
			if (heroWrapper != null) {
				ObservableList<Hero> heroList = mainApp.getHeroList();
				heroList.clear();
				heroList.addAll(heroWrapper.getHeroes());
			}
			else
				System.err.println("No heroes in savefile!");
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Loading error!");
			alert.setHeaderText("Couldn't read input file! Data hasn't been loaded.");
			alert.showAndWait();
		}
	}
	
	public static void save() {
		
		//File file = mainApp.getSavefile();
		File file = chooseFile(false);
		
		if (file == null)
			return;
		
		save(file);
	}
	
	public static void save(File file) {
		
		if (file == null) {
			save();
			return;
		}
		
		try {
			JAXBContext context = JAXBContext.newInstance(SavefileWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			SavefileWrapper.HeroListWrapper heroWrapper = new SavefileWrapper.HeroListWrapper();
			heroWrapper.setHeroes(mainApp.getHeroList());
			
			SavefileWrapper wrapper = new SavefileWrapper();
			wrapper.setHeroListWrapper(heroWrapper);
			
			m.marshal(wrapper, file);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Saving error!");
			alert.setHeaderText("Couldn't write output file! Data hasn't been saved.");
			alert.showAndWait();
		}
	}
}
