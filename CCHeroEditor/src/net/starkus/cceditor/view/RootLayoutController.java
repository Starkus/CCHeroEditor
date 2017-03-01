package net.starkus.cceditor.view;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import net.starkus.cceditor.util.SaveNLoad;

public class RootLayoutController extends DialogController {

	@FXML
	MenuItem open;
	@FXML
	MenuItem save;
	@FXML
	MenuItem saveAs;
	@FXML
	MenuItem close;
	
	@FXML
	void initialize() {
		
		open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
		save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
		saveAs.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
		close.setAccelerator(new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN));
	}
	
	@FXML
	void handleLoad() {
		
		SaveNLoad.load();
	}
	
	@FXML
	void handleSave() {
		
		SaveNLoad.save(mainApp.getSavefile());
	}
	
	@FXML
	void handleSaveAs() {
		
		SaveNLoad.save();
	}
}
