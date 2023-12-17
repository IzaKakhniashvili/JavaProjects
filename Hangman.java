/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private HangmanLexicon word;
	private String RandomWord;
	private int ATTEMPTS;
	private String GUESSEDWORD;
	private String USERINPUT;
	private int CORRECTGUESSES;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	public void run() {
		println("Welcome to Hangman!");
    	word = new HangmanLexicon();
    	selectRandomWord();
    	GuessedWord();
    	println(RandomWord);
    	ATTEMPTS = 8;
    	while (ATTEMPTS > 0 && GUESSEDWORD.contains("_")) {
    		println("The word now looks like this: " + GUESSEDWORD);
    	    println("You have " + ATTEMPTS + " left.");
    	    char letter = UserInput();
    	    guessedLetter(letter);
    	    if (guessedLetter(letter)){
    	        println("The guess is correct.");
    	    } else {
    	        println("There are no " + letter + "'s in the word.");
    	        ATTEMPTS--;
    	    }
    	}

    	if (!GUESSEDWORD.contains("_") && CorrectWord()) {
    	    println("You guessed the word: " + GUESSEDWORD);
    	    println("You win.");
    	} else {
    	    println("You're completely hung.");
    	    println("The word was: " + RandomWord);
    	    println("You lose");
    	}
    	
    	
	}
	public void selectRandomWord(){
		int index = rgen.nextInt(0,9);  //aqq reinji aris shesacvleli da ar damaviwkdes!!
		RandomWord = word.getWord(index);
		RandomWord = RandomWord.toLowerCase();
	}
	
	private void GuessedWord(){
		GUESSEDWORD = " ";
		for(int i = 0; i < RandomWord.length(); i++){
			GUESSEDWORD += "_ " ; 
		}
	}
	
	private char UserInput(){
		USERINPUT = readLine("Your guess: ").toLowerCase();
		return USERINPUT.charAt(0);
		
	}
	private boolean guessedLetter(char letter){
		boolean correctLetter = false;
		for(int i = 0; i < RandomWord.length(); i++){
			if(RandomWord.charAt(i)== letter){
				correctLetter = true;
				GUESSEDWORD = GUESSEDWORD.substring(0, i) + letter + GUESSEDWORD.substring(i + 1);
			}
		}
		return correctLetter;
	}
	
	private boolean CorrectWord(){
		boolean correctWord = false;
		for(int i = 0; i < GUESSEDWORD.length(); i++){
			if(GUESSEDWORD.charAt(i) == RandomWord.charAt(i)){
				correctWord = true;
			}
		}
		return correctWord;
	}
}