package model;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Brent on 03/01/2017.
 */
public class ReadWriteWordList implements java.io.Serializable {

    private static ArrayList<ArrayList<String>> newList = new ArrayList<>();


    //Constructor that creates the list with the threading condition and stores it in Wordlist.bin
    public ReadWriteWordList(boolean useMultiThreading) {
        CreateWordList createWordList = new CreateWordList();
        try {
            newList = createWordList.wordList(useMultiThreading);
        } catch (Exception e) {
            e.printStackTrace();
        }
        storeList();
    }

    //stores the full wordlist in wordlist.bin
    private static void storeList(){
        try {
            File outFile = new File("WordList.bin");
            FileOutputStream outStream = new FileOutputStream(outFile);
            ObjectOutputStream outObjStream = new ObjectOutputStream(outStream);
            outObjStream.writeObject(newList);
        } catch (Exception e) {
            System.out.print("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //reads the wordlist from Wrodlist.bin
    public ArrayList<ArrayList<String>> ReadList() {
        ArrayList<ArrayList<String>> wordList = null;
        try {
            File inFile = new File("WordList.bin");
            FileInputStream inStream = new FileInputStream(inFile);
            ObjectInputStream inObjStream = new ObjectInputStream(inStream);
            wordList = (ArrayList<ArrayList<String>>) inObjStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.print("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return (wordList);
    }
}
