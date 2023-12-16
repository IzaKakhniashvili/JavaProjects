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
	private RandomGenerator rgen = RandomGenerator.getInstance();
    public void run() {
    	println("Welcome to Hangman!");
    	word = new HangmanLexicon();
    	selectRandomWord();
    	println(RandomWord);
		/* You fill this in */
	}
	public void selectRandomWord(){
		int index = rgen.nextInt(0,10);  //aqq reinji aris shesacvleli da ar damaviwkdes!!
		RandomWord = word.getWord(index);
	}

}
