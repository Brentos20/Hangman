package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Brent on 24/11/2016.
 */
public class CreateWordList implements Callable {

    private int count;

    //Creates the Entire list of words to be used in the game
    public ArrayList<ArrayList<String>> wordList(boolean useMultiThreading) throws ExecutionException, InterruptedException {
        int threads;
        if(useMultiThreading) {
            threads = 4;
        } else {
            threads = 1;
        }
        //Executor service will either use 1 thread or 4 threads depending on what the user inputs in the prompt screen
        ExecutorService es = Executors.newFixedThreadPool(threads);
        List<Future<ArrayList<String>>> list = new ArrayList<Future<ArrayList<String>>>();

            for (int i = 1; i <= 4; i++) {
                CreateWordList results = new CreateWordList();
                results.count = i;
                Future future = es.submit(results);
                list.add(future);
            }

        ArrayList<ArrayList<String>> wordList = new ArrayList<>(4);

            for(Future<ArrayList<String>> result : list){

                ArrayList<String> toAdd = null;
                try {
                    toAdd = result.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                wordList.add(toAdd);
            }
        es.shutdown();
        return wordList;
    }

    @Override
    public ArrayList call() throws Exception {

        return chooseFiftyFrom("/file" + count + ".txt");
    }
    //Method to choose 50 words from each file
    private ArrayList<String> chooseFiftyFrom(String inFile) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(inFile)));
        List<String> lines = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        ArrayList<String> to_return = new ArrayList<>(50);
        ArrayList<Integer> randNumbList = randFiftyNumb(lines.size());
        //adds 50 random words into the to_return list
        for(int i=0; i<=49; i++){
            int randNumb = randNumbList.get(i);
            to_return.add(lines.get(randNumb));
        }
        return to_return;
    }
    //Method solely to have 50 random numbers to be used by chooseFiftyFrom
    private static ArrayList<Integer> randFiftyNumb(int max){
        ArrayList<Integer> randFifty = new ArrayList<>(50);
        while(randFifty.size() != 50){
            //find a random number between 0 and the max number of lines in the file
            Random random = new Random();
            int randNumb = random.nextInt(max);
            //insures no duplicates
            if(!randFifty.contains(randNumb)) randFifty.add(randNumb);
        }
        return randFifty;
    }


}
