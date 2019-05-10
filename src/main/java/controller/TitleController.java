package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Game;
import model.ReadWriteWordList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TitleController {
    @FXML public ToggleGroup Savewordlist;
    @FXML private Label promptLabel;
    @FXML private RadioButton seqButton;
    @FXML private RadioButton parButton;
    @FXML private Button submitButton;
    @FXML private Button newGameButton;
    @FXML private Button loadGameButton;
    @FXML private Button settingsButton;
    @FXML private Button backButton;
    @FXML private Button quitButton;


    private Boolean threadingChoice = true;
    private ArrayList<ArrayList<String>> newWordList;


    Stage mainStage;

    //controls whether the wordlist is generated with multithreading or not
    public void radioSelect(ActionEvent event) {
        if(seqButton.isSelected()) {
            threadingChoice = false;
            promptLabel.setText("Words won't be generated with multithreading");
        }
        if(parButton.isSelected()) {
            threadingChoice = true;
            promptLabel.setText("Words will be generated with multithreading");
        }
    }

    //controls what happens where submitButton, backButton, quitButton, or backToTitlebutton is pressed
    public void buttonSelectHandler(ActionEvent event) throws Exception {

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent root = null;
        if(event.getSource()== submitButton){
            //recording time taken for wordlist to be generated and prepared
            Game.getGame().start  = System.currentTimeMillis();
            ReadWriteWordList wordList = new ReadWriteWordList(threadingChoice);
            newWordList = wordList.ReadList();
            Game.getGame().setFullWordList(newWordList);
            Game.getGame().finish = System.currentTimeMillis();
            Game.getGame().time = (int)  (Game.getGame().finish - Game.getGame().start);
            try{
                //load up titlescreen
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/TitleScreen.fxml")));
            }catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(Objects.requireNonNull(root), 800, 600);
            //get reference to the button's stage
            mainStage=(Stage) submitButton.getScene().getWindow();
            mainStage.setTitle("Hangman");

            mainStage.setScene(scene);
            mainStage.setResizable(false);
            mainStage.show();
        }


        //handles going back to the prompt screen from titlescreen
        if(event.getSource()== backButton){
            //gets reference to the button's stage
            stage=(Stage) backButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/view/prompt.fxml"));
            stage.setTitle("Hangman Word Chooser");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }

        //Ends application
        if(event.getSource()== quitButton){
            stage = (Stage) quitButton.getScene().getWindow();
            stage.close();
        }
    }
    //Controls settingsButton
    public void settingsHandler(ActionEvent event) throws Exception {
        Parent root;
        Stage stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("/view/Settings.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Settings");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(settingsButton.getScene().getWindow());
        stage.setResizable(false);
        stage.showAndWait();
    }

    //Controls newGameButton
    public void newGameHandler(ActionEvent event) {

        Game.getGame().setSavedGame(false);
        //newGameButton.getScene().getWindow().hide();
        //newGameButton.getScene().setRoot(FXMLLoader.load(getClass().getResource("model.Game.fxml")));
        Parent root = null;
        //Stage stage = new Stage();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        try {
            root = FXMLLoader.load(getClass().getResource("/view/Game.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle("Hangman");
        stage.setResizable(false);
        stage.show();


    }

    //Controls loadGameButton
    public void loadGameHandler(ActionEvent event) {

        Game.getGame().setSavedGame(true);
        //newGameButton.getScene().getWindow().hide();
        Parent root = null;
        //Stage stage = new Stage();

        try {
            root = FXMLLoader.load(getClass().getResource("/view/Game.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Hangman");
        stage.setResizable(false);
        stage.show();
    }
}