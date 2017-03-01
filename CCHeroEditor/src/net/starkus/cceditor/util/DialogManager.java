package net.starkus.cceditor.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.starkus.cceditor.MainApp;
import net.starkus.cceditor.model.Hero;
import net.starkus.cceditor.view.DialogController;
import net.starkus.cceditor.view.HeroDialogController;

public class DialogManager {
	
	static MainApp mainApp;
	static Stage primaryStage;
	static Stage dialogStage;
	static BorderPane mainLayout;
	
	public static void init(MainApp mainApp, Stage primary, BorderPane mainLayout) {
		DialogManager.mainApp = mainApp;
		DialogManager.primaryStage = primary;
		DialogManager.mainLayout = mainLayout;
	}
	
	
	private static Object load(String location) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(mainApp.getClass().getResource(location));
		
		Object R = loader.load();
		
		DialogController controller = loader.getController();
		controller.setMainApp(mainApp);
		
		return R;
	}
	
	private static Dialog loadDialog(String location) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(mainApp.getClass().getResource(location));
		Pane page = (Pane) loader.load();
		
		Stage dialogStage = new Stage();
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		
		DialogController controller = loader.getController();
		controller.setMainApp(mainApp);
		controller.setStage(dialogStage);
		
		return new Dialog(dialogStage, controller);
	}
	

	public static void showHome() {
		try {
			
			AnchorPane home = (AnchorPane) load("view/Home.fxml");
			mainLayout.setCenter(home);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void showHeroDialog(Hero hero) {
		try {
			Dialog dialog = loadDialog("view/HeroDialog.fxml");
			
			((HeroDialogController) dialog.controller).setHero(hero);
			dialog.stage.showAndWait();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static class Dialog {
		
		public Dialog(Stage stage, DialogController controller) {
			this.stage = stage;
			this.controller = controller;
		}
		
		public Stage stage;
		public DialogController controller;
	}
}
