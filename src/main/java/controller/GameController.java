package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Game;
import model.SaveGame;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by Brent on 05/01/2017.
 */
public class GameController implements Initializable {
    @FXML private Label welcomeLabel;
    @FXML private Label wordLabel;
    @FXML private Button aButton;
    @FXML private Button bButton;
    @FXML private Button cButton;
    @FXML private Button dButton;
    @FXML private Button eButton;
    @FXML private Button fButton;
    @FXML private Button gButton;
    @FXML private Button hButton;
    @FXML private Button iButton;
    @FXML private Button jButton;
    @FXML private Button kButton;
    @FXML private Button lButton;
    @FXML private Button mButton;
    @FXML private Button nButton;
    @FXML private Button oButton;
    @FXML private Button pButton;
    @FXML private Button qButton;
    @FXML private Button rButton;
    @FXML private Button sButton;
    @FXML private Button tButton;
    @FXML private Button uButton;
    @FXML private Button vButton;
    @FXML private Button wButton;
    @FXML private Button xButton;
    @FXML private Button yButton;
    @FXML private Button zButton;
    @FXML private ImageView hangmanImage;
    @FXML private Button gameSaveButton;
    @FXML private Button gameQuitButton;
    @FXML private Button gameNextButton;
    @FXML private Label timeTaken;

    private Game thisGame = Game.getGame();
    private String actualWord = "";
    private String guessedWord = "";
    public int numbOftries = thisGame.getNumbOfTries();
    public ArrayList<Button> guessedLetters = new ArrayList<>(10);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        timeTaken.setText("Time taken \nto make wordlist \nis " + thisGame.time + " milliseconds");
        if(!thisGame.isSavedGame()) {
            numbOftries = 0;
            hangmanImage.setImage(new Image( "/images/" +"0.png"));
            thisGame.setNumbOfTries(0);

            actualWord = thisGame.makeWord();
            thisGame.setWord(actualWord);
            guessedWord = thisGame.makeGuessedWord();
            thisGame.setGuessedWord(guessedWord);
            wordLabel.setText(thisGame.getGuessedWord());
            thisGame.getGuessedLetters().clear();
        }else{

            File inFile = new File("SaveGame.bin");
            FileInputStream inStream = null;
            try {
                inStream = new FileInputStream(inFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ObjectInputStream inObjStream = null;
            try {
                inObjStream = new ObjectInputStream(inStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                assert inObjStream != null;
                thisGame = (Game) inObjStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            actualWord = thisGame.getWord();
            guessedWord = thisGame.getGuessedWord();
            wordLabel.setText(guessedWord);
            if(!thisGame.getGuessedLetters().isEmpty()){
                if(thisGame.getGuessedLetters().contains("a")) aButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("b")) bButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("c")) cButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("d")) dButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("e")) eButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("f")) fButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("g")) gButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("h")) hButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("i")) iButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("j")) jButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("k")) kButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("l")) lButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("m")) mButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("n")) nButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("o")) oButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("p")) pButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("q")) qButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("r")) rButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("s")) sButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("t")) yButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("u")) uButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("v")) vButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("w")) wButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("x")) xButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("y")) yButton.setDisable(true);
                if(thisGame.getGuessedLetters().contains("z")) zButton.setDisable(true);
            }

            if(!thisGame.getGuessedLetters().isEmpty()){
                for ( String letter: thisGame.getGuessedLetters()) {
                    if(!actualWord.contains(letter)) {
                        numbOftries++;
                        thisGame.getNumbOfTries();
                    }
                }
            }

            hangmanImage.setImage(new Image("/images/"+ numbOftries + ".png"));
        }
    }

    public void nextHandler(ActionEvent event) throws Exception {

        thisGame.getGuessedLetters().clear();
        numbOftries = 0;
        hangmanImage.setImage(new Image("/images/0.png"));
        thisGame.setNumbOfTries(0);
        actualWord = thisGame.makeWord();
        thisGame.setWord(actualWord);
        guessedWord = thisGame.makeGuessedWord();
        thisGame.setGuessedWord(guessedWord);
        wordLabel.setText(thisGame.getGuessedWord());
        welcomeLabel.setText("Please choose a letter");

        disableLetters();
        enableLetters();
    }

    public void saveHandler(ActionEvent event) {

        File outFile = new File("SaveGame.bin");
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectOutputStream outObjStream = null;
        try {
            outObjStream = new ObjectOutputStream(outStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert outObjStream != null;
            outObjStream.writeObject(thisGame);
        } catch (IOException e) {
            e.printStackTrace();
        }
        welcomeLabel.setText("model.Game has successfully saved your progress");
    }

    public void quitHandler(ActionEvent event) throws Exception {

        Parent root;
        Stage mainStage;
        mainStage = (Stage) gameQuitButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/view/TitleScreen.fxml"));
        mainStage.setTitle("Hangman");
        Scene scene = new Scene(root, 800, 600);
        mainStage.setScene(scene);
        mainStage.show();
        numbOftries = 0;
        hangmanImage.setImage(new Image("/images/1.png"));
        thisGame.setNumbOfTries(0);
        thisGame.getGuessedLetters().clear();
    }

    public void letterButtonHandler(ActionEvent event) {

        Button letterButton = (Button)event.getSource();
        String letter = letterButton.toString().substring(10,11);
        letterButton.setDisable(true);
        thisGame.addGuessedLetter(letter);
        guessedLetters.add(letterButton);


        if(actualWord.contains(letter)){
            thisGame.updateGuessedWord(letter);
            guessedWord = thisGame.getGuessedWord();
            wordLabel.setText(thisGame.getGuessedWord());
        }else{
            numbOftries++;
            thisGame.setNumbOfTries(numbOftries);
            String image = "/images/" +  numbOftries + ".png";
            hangmanImage.setImage(new Image(image));
        }


        if(!Objects.equals(guessedWord, actualWord) && numbOftries==10){
            welcomeLabel.setText("Oh no you lost! :(");
            disableLetters();
        }

        if(Objects.equals(guessedWord, actualWord)){
            welcomeLabel.setText("Well done you've won!");
            disableLetters();

        }
    }

    private void disableLetters() {

        aButton.setDisable(true);
        bButton.setDisable(true);
        cButton.setDisable(true);
        dButton.setDisable(true);
        eButton.setDisable(true);
        fButton.setDisable(true);
        gButton.setDisable(true);
        hButton.setDisable(true);
        iButton.setDisable(true);
        jButton.setDisable(true);
        kButton.setDisable(true);
        lButton.setDisable(true);
        mButton.setDisable(true);
        nButton.setDisable(true);
        oButton.setDisable(true);
        pButton.setDisable(true);
        qButton.setDisable(true);
        rButton.setDisable(true);
        sButton.setDisable(true);
        tButton.setDisable(true);
        uButton.setDisable(true);
        vButton.setDisable(true);
        wButton.setDisable(true);
        xButton.setDisable(true);
        yButton.setDisable(true);
        zButton.setDisable(true);

    }

    private void enableLetters() {


        aButton.setDisable(false);
        bButton.setDisable(false);
        cButton.setDisable(false);
        dButton.setDisable(false);
        eButton.setDisable(false);
        fButton.setDisable(false);
        gButton.setDisable(false);
        hButton.setDisable(false);
        iButton.setDisable(false);
        jButton.setDisable(false);
        kButton.setDisable(false);
        lButton.setDisable(false);
        mButton.setDisable(false);
        nButton.setDisable(false);
        oButton.setDisable(false);
        pButton.setDisable(false);
        qButton.setDisable(false);
        rButton.setDisable(false);
        sButton.setDisable(false);
        tButton.setDisable(false);
        uButton.setDisable(false);
        vButton.setDisable(false);
        wButton.setDisable(false);
        xButton.setDisable(false);
        yButton.setDisable(false);
        zButton.setDisable(false);

    }

}
