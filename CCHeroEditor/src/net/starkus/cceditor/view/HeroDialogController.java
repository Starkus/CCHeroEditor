package net.starkus.cceditor.view;

import java.io.File;

import org.fxmisc.richtext.InlineCssTextArea;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.starkus.cceditor.MainApp;
import net.starkus.cceditor.model.Grade;
import net.starkus.cceditor.model.Hero;
import net.starkus.cceditor.util.GrowthParser;
import net.starkus.cceditor.util.SkillStyler;

public class HeroDialogController extends DialogController {
	
	private Hero hero;
	
	@FXML
	TextField nameField;
	@FXML
	ChoiceBox<Grade> gradeBox;
	@FXML
	ImageView portraitView;

	@FXML
	TextField dmgField;
	@FXML
	TextField hpField;
	@FXML
	TextField atkspdField;
	@FXML
	TextField movspdField;
	
	@FXML
	InlineCssTextArea skillField;

	@FXML
	ImageView dmgIcon;
	@FXML
	ImageView hpIcon;
	@FXML
	ImageView asIcon;
	@FXML
	ImageView msIcon;
	
	
	private String rawSkill = "";
	private String parsedSkill = "";
	
	
	@FXML
	void initialize() {
		
		gradeBox.setItems(FXCollections.observableArrayList(Grade.ORDINARY, Grade.ELITE, Grade.LEGENDARY));
		
		nameField.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				updatePortrait();
			}
		});
		
		skillField.setStyle("-fx-font-size: 14;");
		
		skillField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue == false) {
					// Lost focus
					
					// Save raw skill
					rawSkill = skillField.getText();
					// Parse expressions
					parsedSkill = GrowthParser.parseText(skillField.getText());
					
					// Replace with parsed skill and stylize
					skillField.replaceText(parsedSkill);
					SkillStyler.stylizeSkillText(skillField);
				}
				else {
					// Go back to raw skill
					skillField.replaceText(rawSkill);
					skillField.clearStyle(0);
				}
			}
		});
	}
	
	@Override
	public void setMainApp(MainApp mainApp) {
		super.setMainApp(mainApp);
		
		findIcons();
	}
	
	
	
	void updatePortrait() {
		
		try {
			String name = nameField.getText();
			name = name.replace(' ', '_');
			
			File file = new File(mainApp.getResourcesFolder() + "Heroes/" + name + ".png");
			Image image = new Image(file.toURI().toString());
			
			portraitView.setImage(image);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	void findIcons() {
		
		ImageView controls[] = {dmgIcon, hpIcon, asIcon, msIcon};
		String files[] = {"Ico_attack", "Ico_hp", "Ico_attackspeed", "Ico_movespeed"};
		
		for (int i=0; i<4; i++) {
			try {
				File icon = new File(mainApp.getResourcesFolder() + "Icons/" + files[i] + ".png");
				Image image = new Image(icon.toURI().toString());
				
				controls[i].setImage(image);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public void setHero(Hero hero) {
		this.hero = hero;
		
		nameField.setText(hero.getName());
		updatePortrait();
		
		gradeBox.getSelectionModel().select(hero.getGrade());
		
		dmgField.setText(Integer.toString(hero.getBaseDamage()));
		hpField.setText(Integer.toString(hero.getBaseHealth()));
		atkspdField.setText(Integer.toString(hero.getAttackSpeed()));
		movspdField.setText(Integer.toString(hero.getMovementSpeed()));
		
		rawSkill = hero.getSkill();
		parsedSkill = GrowthParser.parseText(rawSkill);
		
		skillField.replaceText(parsedSkill);
		SkillStyler.stylizeSkillText(skillField);
	}
	
	@FXML
	void handleSave() {
		
		hero.setName(nameField.getText());
		hero.setGrade(gradeBox.getSelectionModel().getSelectedItem());
		hero.setSkill(rawSkill);
		
		try {
			hero.setBaseDamage		(Integer.parseInt(dmgField.getText()));
			hero.setBaseHealth		(Integer.parseInt(hpField.getText()));
			hero.setAttackSpeed		(Integer.parseInt(atkspdField.getText()));
			hero.setMovementSpeed	(Integer.parseInt(movspdField.getText()));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Syntax error");
			alert.setHeaderText("Some number is written incorrectly");
			alert.showAndWait();
			
			return;
		}
		
		stage.close();
	}
	
	@FXML
	void handleCancel() {
		
		stage.close();
	}

}
