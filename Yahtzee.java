/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.Arrays;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		
		dice = new int[N_DICE];
		for(int i = 0; i < N_DICE; i++) {
			dice[i] = rgen.nextInt(1, 6);
		}
		
		
		playGame();
	}

	private void playGame() {
		display.waitForPlayerToClickRoll(1);
		display.displayDice(dice);
		for(int i = 0; i < 2; i++) {
			display.waitForPlayerToSelectDice();
			for(int j = 0; j < N_DICE; j++) {
				if(display.isDieSelected(j)) {
					dice[j] = rgen.nextInt(1, 6);
				}
			}
			display.displayDice(dice);
		}
		display.displayDice(dice);
		int category = display.waitForPlayerToSelectCategory();
		if(YahtzeeMagicStub.checkCategory(dice, category)) {
			
		}
		/* You fill this in */
	}
	private int checkScore(int category, int[] dice) {
		score = 0;
		Arrays.sort(dice);
		switch(category) {
			case ONES:
			case TWOS:
			case THREES:
			case FOURS:
			case FIVES:
			case SIXES:
				for(int i = 0; i < dice.length; i++) {
					if(dice[i] == category) {
						score += dice[i];
					}
				}
				break;
			case THREE_OF_A_KIND:
				if(isOfAKind(3)) {
					for(int i = 0; i < dice.length; i++) {
						score += dice[i];
					}
				}
				break;
			case FOUR_OF_A_KIND:
				if(isOfAKind(4)) {
					for(int i =0; i < dice.length; i++) {
						score += dice[i];
					}
				}
				break;
			case YAHTZEE:
				if(isOfAKind(5)) {
					for(int i = 0; i < dice.length; i++) {
						score += dice[i];
					}
				}
				break;
			case CHANCE:
				for(int i = 0; i < dice.length; i++) {
					score += dice[i];
				}
				break;
				
		}
		return score;
	}
	private boolean isOfAKind(int n) {
		for(int i = 1; i<=6; i++) {
			count = 0;
			for(int j = 0; j<dice.length; j++) {
				if(dice[j]==i) {
					count++;
				}
			}
			if(count >= n) {
				return true;
			}
		}
		return false;
	}
	
		
/* Private instance variables */
	private int score;
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] dice;
	private int count;

}
