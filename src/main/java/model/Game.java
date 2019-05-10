package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Brent on 05/01/2017.
 */
public class Game implements java.io.Serializable {

    private boolean savedGame = false;
    private final static Game game = new Game();
    private int difficulty = 0;
    private ArrayList<ArrayList<String>> fullWordList = new ArrayList<>();
    private String word = "";
    private String guessedWord = "";
    private ArrayList<String> guessedLetters = new ArrayList<>(10);
    private int numbOfTries = 0;

    public long start = 0;
    public long finish = 0;
    public int time = 0;

    public boolean isSavedGame() {
        return savedGame;
    }

    public void setSavedGame(boolean savedGame) {
        this.savedGame = savedGame;
    }

    public static Game getGame() {
        return game;
    }

    public int getNumbOfTries() {
        return numbOfTries;
    }

    public void setNumbOfTries(int numbOfTries) {
        this.numbOfTries = numbOfTries;
    }

    public ArrayList<String> getGuessedLetters() {
        return guessedLetters;
    }

    public void setGuessedLetters(ArrayList<String> guessedLetters) {
        this.guessedLetters = guessedLetters;
    }

    public String getGuessedWord() {
        return guessedWord;
    }

    public void setGuessedWord(String guessedWord) {
        this.guessedWord = guessedWord;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<ArrayList<String>> getFullWordList() {
        return fullWordList;
    }

    public void setFullWordList(ArrayList<ArrayList<String>> fullWordList) {
        this.fullWordList = fullWordList;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String makeWord(){
        ArrayList<String> temp; //temp in case I have to directly affect the list of words
        temp = fullWordList.get(difficulty); //get the right list dependent on difficulty
        Random random = new Random();
        int randNumb = random.nextInt(temp.size()+1);
        return temp.get(randNumb); //returns a random word from list
    }

    public String makeGuessedWord() {

        StringBuffer temp = new StringBuffer("");

        for(int i=0; i<word.length(); i++){
            temp.append("_");
        }
        String to_return = temp.toString();

        return to_return;
    }

    public void updateGuessedWord(String letter) {
        StringBuffer temp = new StringBuffer(guessedWord);
        for(int i=0; i<word.length(); i++){

            if(letter.equals(word.substring(i,i+1))){

                temp.setCharAt(i,letter.charAt(0));

            }
        }
        guessedWord = temp.toString();
    }

    public void addGuessedLetter(String letter){
        guessedLetters.add(letter);
    }

}
