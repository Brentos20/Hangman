package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import model.Game;

/**
 * Created by Brent on 05/01/2017.
 */
public class SettingsController {

    @FXML private Label message;
    @FXML private ToggleGroup difficultyGroup;
    @FXML private RadioButton easy;
    @FXML private RadioButton medium;
    @FXML private RadioButton hard;
    @FXML private RadioButton hardest;
    @FXML private Button submitButton;

    public int difficulty = 0;

    public void difficultyHandler(ActionEvent event) {

        if(easy.isSelected()){
            difficulty = 0;
            message.setText("You have selected easy");
        }

        if(medium.isSelected()){
            difficulty = 1;
            message.setText("You have selected medium");
        }

        if(hard.isSelected()){
            difficulty = 2;
            message.setText("You have selected hard");
        }

        if(hardest.isSelected()){
            difficulty = 3;
            message.setText("You have selected hardest");
        }
        Game.getGame().setDifficulty(difficulty);
    }

    public void submitButtonHandler(ActionEvent event) {

        if(difficulty==0) easy.isSelected();
        if(difficulty==1) medium.isSelected();
        if(difficulty==2) hard.isSelected();
        if(difficulty==3) hardest.isSelected();
        submitButton.getScene().getWindow().hide();
    }
}
