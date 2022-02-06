package com.company;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static String nameOfFile = "Words.txt";
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {

        //Game Loop
        while (Menu()) {
            Game();
        }


    }
    private static void Game() throws FileNotFoundException{
        String[] words = ReadFile();
        if(words == null) return;
        final int wordSize = words[0].length();
        final String ans = words[new Random().nextInt(words.length)];
        System.out.println("""
                You have 15 tries to find the right word.
                For every try, a letter will become green if it's in the right spot
                Yellow if it's in the wrong spot
                Or Red if it's not in the word at all""");
        String[] history = new String[15];
        for(int i = 0; i < 15; i++){
            boolean inv = true;
            while(inv){
                System.out.print("Attempt #" + (i+1) + " : ");
                String usrAtt = scanner.nextLine();
                for (String word : words) {
                    if(word.equals(usrAtt)) {
                        String val = ValidateAnswer(word.toUpperCase(), ans.toUpperCase());
                        history[i] = val;

                        for(int j = 0; j <= i; j++){
                            System.out.println(history[j]);
                        }
                        System.out.println(Colors.RESET);


                        if(word.equalsIgnoreCase(ans)){
                            //Game won
                            System.out.println("Good Job! You won the game!");
                            return;
                        }
                        inv = false;
                    }
                }
                if(inv)
                    System.out.println("This word is not part of the wordlist!");
            }
        }
        System.out.println("Oops, look like you've lost! The word was " + ans +
                "\n ¯\\_(ツ)_/¯");


    }
    private static String ValidateAnswer(String word, String answer){
        String retVal = "";
        String[] retValTab = new String[word.length()];
        char[] tempTabO = answer.toCharArray();
        char[] tempTabU = word.toCharArray();

        for(int i = 0; i < retValTab.length; i++){
            if(tempTabO[i] == tempTabU[i]){
                retValTab[i] = Colors.GREEN.toString() + word.charAt(i);
                tempTabO[i] = 0;
                tempTabU[i] = 0;
            }
        }
        for(int i = 0; i < retValTab.length; i++){
            if(tempTabU[i] != 0)
                for(int j = 0; j < tempTabO.length; j++)
                    if(tempTabO[j] != 0)
                        if(tempTabO[j] == tempTabU[i]){
                            retValTab[i] = Colors.YELLOW.toString() + tempTabO[j];
                            tempTabO[j] = 0;
                            tempTabU[i] = 0;
                        }
        }

        for(int i = 0; i < retValTab.length; i++){
            if(retValTab[i] == null)
                retVal += Colors.RED.toString() + tempTabU[i];
            else retVal += retValTab[i];
        }
        return retVal;
    }




    //Start Menu
    private static boolean Menu(){
        //Menu loop
        while(true){
            System.out.println("""
                Welcome to Scuffed Wordle!
                It's like the normal wordle, but scuffed!
                Select an option :
                1. Start new game
                2. Change file containing words
                3. Quit""");
            String usr = scanner.nextLine();
            switch (usr){
                case "1":
                    return true;
                case "2":
                    FileNameChange();
                    break;
                case "3":
                    return false;
            }
        }

    }

    private static void FileNameChange(){
        System.out.println("What is the name of the file containing the words?" +
                "\n(Don't forget to add the file extension at the end!");
        nameOfFile = scanner.nextLine();
    }


    //Read le file
    private static String[] ReadFile() throws FileNotFoundException {
        String retVal = "";

        try {
             File file = new File(nameOfFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine())
                retVal += scanner.nextLine() + "\n";

            int length = retVal.split("\n")[0].length();
            for(int i = 1; i < retVal.split("\n").length; i++){
                if(length != retVal.split("\n")[1].length()){
                    System.out.println("All words are not of the same length in that file!");
                    return null;
                }
            }
            return retVal.split("\n"); //Split for every line
        }
        catch (Exception ex){
            System.out.println("The file " + nameOfFile + " was not found in the parent directory of this project." +
                    "\nPlease try again.");
        }






        return null;
    }


}
