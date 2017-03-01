package net.starkus.cceditor.view;

import com.sun.javafx.scene.control.skin.TableColumnHeader;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import net.starkus.cceditor.MainApp;
import net.starkus.cceditor.model.Grade;
import net.starkus.cceditor.model.Hero;
import net.starkus.cceditor.util.DialogManager;

@SuppressWarnings("restriction")
public class HomeController extends DialogController {

	@FXML
	TableView<Hero> heroTable;
	@FXML
	TableColumn<Hero, String> nameColumn;
	@FXML
	TableColumn<Hero, Grade> gradeColumn;
	@FXML
	TableColumn<Hero, Number> dmgColumn;
	@FXML
	TableColumn<Hero, Number> hpColumn;
	@FXML
	TableColumn<Hero, Number> asColumn;
	@FXML
	TableColumn<Hero, Number> msColumn;
	
	public HomeController() {
		
	}
	
	@FXML
	void initialize() {

		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		gradeColumn.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());
		dmgColumn.setCellValueFactory(cellData -> cellData.getValue().baseDamageProperty());
		hpColumn.setCellValueFactory(cellData -> cellData.getValue().baseHealthProperty());
		asColumn.setCellValueFactory(cellData -> cellData.getValue().attackSpeedProperty());
		msColumn.setCellValueFactory(cellData -> cellData.getValue().movementSpeedProperty());
		
		heroTable.setOnMouseClicked(event -> {
    		if (!event.getButton().equals(MouseButton.PRIMARY))
    			return;
    		
    		if (event.getClickCount() == 2 && (heroTable.getSelectionModel().getSelectedIndex() != -1)) {
    			
    			Node node = event.getPickResult().getIntersectedNode();
    			if (node.getClass() == TableColumnHeader.class ||
    					node.getParent().getClass() == TableColumnHeader.class ||
    					node.getParent().getParent().getClass() == TableColumnHeader.class)
    				return;
    						
    			handleEditHero();
    		}
    	});
		
		
	}
	
	@Override
	public void setMainApp(MainApp mainApp) {
		super.setMainApp(mainApp);
		
		heroTable.setItems(mainApp.getHeroList());
	}
	
	
	@FXML
	void handleAddHero() {
		Hero neww = new Hero("name");
		heroTable.getItems().add(neww);
		heroTable.getSelectionModel().select(neww);
		
		handleEditHero();
	}
	
	@FXML
	void handleEditHero() {
		
		Hero selectedHero = heroTable.getSelectionModel().getSelectedItem();
		DialogManager.showHeroDialog(selectedHero);
	}
	
	@FXML
	void handleDeleteHero() {
		
		int i = heroTable.getSelectionModel().getSelectedIndex();
		heroTable.getItems().remove(i);
	}
}
