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
	private HangmanCanvas canvas;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	public void run() {
		println("Welcome to Hangman!");
		canvas.reset();
    	word = new HangmanLexicon();
    	selectRandomWord();
    	GuessedWord();
    	ATTEMPTS = 8;
    	HangmanText();
    	PlayAgain();
    	
	}
	public void selectRandomWord(){
		int lastIndex = word.getWordCount();
		int index = rgen.nextInt(0, lastIndex);  //aqq reinji aris shesacvleli da ar damaviwkdes!!
		RandomWord = word.getWord(index);
		RandomWord = RandomWord.toLowerCase();
	}
	
	private void GuessedWord(){
		GUESSEDWORD = "";
		for(int i = 0; i < RandomWord.length(); i++){
			GUESSEDWORD += "-" ; 
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
	public void init() { 
		canvas = new HangmanCanvas(); 
		add(canvas); 
	} 
	public void HangmanText(){
		while (ATTEMPTS > 0 && GUESSEDWORD.contains("-")) {
    		canvas.displayWord(GUESSEDWORD);
    		println("The word now looks like this: " + GUESSEDWORD);
    	    println("You have " + ATTEMPTS + " left.");
    	    char letter = UserInput();
    	    guessedLetter(letter);
    	    if (guessedLetter(letter)){
    	        println("The guess is correct.");
    	    } else {
    	        println("There are no " + letter + "'s in the word.");
    	        ATTEMPTS--;
    	        canvas.noteIncorrectGuess(letter);
    	        if(ATTEMPTS == 7)canvas.HangmanHead();
    	        if(ATTEMPTS == 6)canvas.HangmanBody();
    	        if(ATTEMPTS == 5)canvas.LeftArm();
    	        if(ATTEMPTS == 4)canvas.RightArm();
    	        if(ATTEMPTS == 3)canvas.LeftLeg();
    	        if(ATTEMPTS == 2)canvas.RightLeg();
    	        if(ATTEMPTS == 1)canvas.LeftFoot();
    	    }
    	   
    	}

    	if (!GUESSEDWORD.contains("-") && CorrectWord()) {
    	    println("You guessed the word: " + GUESSEDWORD);
    	    println("You win.");
    	    canvas.displayWord(GUESSEDWORD);
    	} else {
    		canvas.RightFoot();
    	    println("You're completely hung.");
    	    println("The word was: " + RandomWord);
    	    println("You lose");
    	    canvas.displayWord(GUESSEDWORD);
    	}
  
	}
		private void PlayAgain(){
			while(true){
				if(askUser()){
					println("Welcome to Hangman!");
					canvas.reset();
			    	word = new HangmanLexicon();
			    	selectRandomWord();
			    	GuessedWord();
			    	println(RandomWord);
			    	ATTEMPTS = 8;
			    	HangmanText();
				}else{
					break;
				}
			}
		}
		private boolean askUser(){
			String answer = readLine("Play again?(yes/no): ");
			boolean wantsToPlay = false;
			if(answer.equals("yes")){
				wantsToPlay = true;
			}
			return wantsToPlay;
		}
	}

