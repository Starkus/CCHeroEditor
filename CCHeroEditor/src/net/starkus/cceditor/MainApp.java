package net.starkus.cceditor;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import net.starkus.cceditor.model.Hero;
import net.starkus.cceditor.util.DialogManager;
import net.starkus.cceditor.util.SaveNLoad;
import net.starkus.cceditor.view.RootLayoutController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane mainLayout;
	
	private ObservableList<Hero> heroList;
	
	private File savefile;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			primaryStage.setTitle("CC Hero Editor");
			
			initRootLayout();
			defaultSavefile();
			
			heroList = FXCollections.observableArrayList();
			heroList.add(new Hero("Angel"));
			
			DialogManager.init(this, primaryStage, mainLayout);
			DialogManager.showHome();
			
			SaveNLoad.init(this);
			SaveNLoad.load(getSavefile());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void initRootLayout() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			mainLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void defaultSavefile() {
		setSavefile(new File(getResourcesFolder() + "herolist.xml"));
	}
	
	
	public String getResourcesFolder() {
		
		String path;
		
		if (System.getProperty("os.name").toLowerCase().contains("win"))
			path = System.getenv("APPDATA");
		else
			path = System.getProperty("user.home");
		
		path += "/CCStuff/";
		
		return path;
	}
	

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public BorderPane getMainLayout() {
		return mainLayout;
	}

	public ObservableList<Hero> getHeroList() {
		return heroList;
	}
	
	public File getSavefile() {
		return savefile;
	}
	
	public void setSavefile(File file) {
		this.savefile = file;
		
		primaryStage.setTitle("CC Hero Editor - " + file.getAbsolutePath());
	}
	

	
	public static void main(String[] args) {
		launch(args);
	}
}
