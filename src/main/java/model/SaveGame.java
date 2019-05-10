package model;

import javafx.scene.control.Button;

import java.util.ArrayList;

/**
 * Created by Brent on 06/01/2017.
 */
public class SaveGame {

    private final static SaveGame savegame = new SaveGame();
    private int difficulty;
    private String word;
    private String guessedWord;
    private ArrayList<Button> guessedLetters = new ArrayList<>(10);
    private int numbOfTries;

    public static SaveGame getSavegame() {
        return savegame;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getGuessedWord() {
        return guessedWord;
    }

    public void setGuessedWord(String guessedWord) {
        this.guessedWord = guessedWord;
    }

    public ArrayList<Button> getGuessedLetters() {
        return guessedLetters;
    }

    public void setGuessedLetters(ArrayList<Button> guessedLetters) {
        this.guessedLetters = guessedLetters;
    }

    public int getNumbOfTries() {
        return numbOfTries;
    }

    public void setNumbOfTries(int numbOfTries) {
        this.numbOfTries = numbOfTries;
    }
}
