/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;


public class HangmanLexicon {
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private ArrayList<String> lexicon = new ArrayList<>();
	
	public HangmanLexicon() { 
		try{
			BufferedReader br = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			String word = br.readLine();
			while(word != null){
				lexicon.add(word);
				word = br.readLine();
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexicon.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		 return lexicon.get(index);
	}
}
