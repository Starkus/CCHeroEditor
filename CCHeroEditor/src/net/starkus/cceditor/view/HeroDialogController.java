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
import javafx.scene.layout.HBox;
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
	TextField dmgStarField;
	@FXML
	TextField dmgLevelField;
	@FXML
	TextField hpStarField;
	@FXML
	TextField hpLevelField;
	@FXML
	TextField atkspdField;
	@FXML
	TextField movspdField;

	@FXML
	TextField evo1dmgKField;
	@FXML
	TextField evo1dmgStarField;
	@FXML
	TextField evo1dmgLevelField;

	@FXML
	TextField evo1hpKField;
	@FXML
	TextField evo1hpStarField;
	@FXML
	TextField evo1hpLevelField;
	
	@FXML
	TextField evo2dmgKField;
	@FXML
	TextField evo2dmgStarField;
	@FXML
	TextField evo2dmgLevelField;
	
	@FXML
	TextField evo2hpKField;
	@FXML
	TextField evo2hpStarField;
	@FXML
	TextField evo2hpLevelField;

	@FXML
	HBox evo1Stuff;
	@FXML
	HBox evo2Stuff;
	
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
		
		dmgStarField.setStyle("-fx-text-inner-color: red;");
		
		
		gradeBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Grade>() {

			@Override
			public void changed(ObservableValue<? extends Grade> observable, Grade oldValue, Grade newValue) {
				
				boolean disable = true;
				
				if (newValue == Grade.LEGENDARY) {
					disable = false;
				}

				evo1Stuff.setDisable(disable);
				evo2Stuff.setDisable(disable);
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
		
		dmgStarField.setText(Integer.toString(hero.getDamagePerStar()));
		dmgLevelField.setText(Integer.toString(hero.getDamagePerLevel()));
		
		hpStarField.setText(Integer.toString(hero.getHealthPerStar()));
		hpLevelField.setText(Integer.toString(hero.getHealthPerLevel()));
		
		atkspdField.setText(Integer.toString(hero.getAttackSpeed()));
		movspdField.setText(Integer.toString(hero.getMovementSpeed()));
		
		evo1dmgKField.setText		(Integer.toString(hero.getEvo1DamageConstant()));
		evo1dmgStarField.setText	(Integer.toString(hero.getEvo1DamagePerStar()));
		evo1dmgLevelField.setText	(Integer.toString(hero.getEvo1DamagePerLevel()));
		
		evo1hpKField.setText 		(Integer.toString(hero.getEvo1HealthConstant()));
		evo1hpStarField.setText 	(Integer.toString(hero.getEvo1HealthPerStar()));
		evo1hpLevelField.setText 	(Integer.toString(hero.getEvo1HealthPerLevel()));
		
		evo2dmgKField.setText		(Integer.toString(hero.getEvo2DamageConstant()));
		evo2dmgStarField.setText	(Integer.toString(hero.getEvo2DamagePerStar()));
		evo2dmgLevelField.setText	(Integer.toString(hero.getEvo2DamagePerLevel()));
		
		evo2hpKField.setText 		(Integer.toString(hero.getEvo2HealthConstant()));
		evo2hpStarField.setText 	(Integer.toString(hero.getEvo2HealthPerStar()));
		evo2hpLevelField.setText 	(Integer.toString(hero.getEvo2HealthPerLevel()));
		
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
			hero.setDamagePerStar		(Integer.parseInt(dmgStarField.getText()));
			hero.setDamagePerLevel		(Integer.parseInt(dmgLevelField.getText()));

			hero.setHealthPerStar		(Integer.parseInt(hpStarField.getText()));
			hero.setHealthPerLevel		(Integer.parseInt(hpLevelField.getText()));
			
			hero.setAttackSpeed			(Integer.parseInt(atkspdField.getText()));
			hero.setMovementSpeed		(Integer.parseInt(movspdField.getText()));

			hero.setEvo1DamageConstant	(Integer.parseInt(evo1dmgKField.getText()));
			hero.setEvo1DamagePerStar	(Integer.parseInt(evo1dmgStarField.getText()));
			hero.setEvo1DamagePerLevel	(Integer.parseInt(evo1dmgLevelField.getText()));

			hero.setEvo1HealthConstant	(Integer.parseInt(evo1hpKField.getText()));
			hero.setEvo1HealthPerStar	(Integer.parseInt(evo1hpStarField.getText()));
			hero.setEvo1HealthPerLevel	(Integer.parseInt(evo1hpLevelField.getText()));

			hero.setEvo2DamageConstant	(Integer.parseInt(evo2dmgKField.getText()));
			hero.setEvo2DamagePerStar	(Integer.parseInt(evo2dmgStarField.getText()));
			hero.setEvo2DamagePerLevel	(Integer.parseInt(evo2dmgLevelField.getText()));

			hero.setEvo2HealthConstant	(Integer.parseInt(evo2hpKField.getText()));			
			hero.setEvo2HealthPerStar	(Integer.parseInt(evo2hpStarField.getText()));
			hero.setEvo2HealthPerLevel	(Integer.parseInt(evo2hpLevelField.getText()));

			
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
