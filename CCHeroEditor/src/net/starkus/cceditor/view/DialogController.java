package net.starkus.cceditor.view;

import javafx.stage.Stage;
import net.starkus.cceditor.MainApp;

public class DialogController {

	protected MainApp mainApp;
	protected Stage stage;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
