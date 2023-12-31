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
		playGame();
	}
	
	
	
	private void rollDice(){
		dice = new int[N_DICE];
		for(int i = 0; i < N_DICE; i++) {
			dice[i] = rgen.nextInt(1, 6);
		}
	}
	
	
	private void playGame() {
		TotalScore = new int[nPlayers];
		for(int n = 0; n < 13; n++) {
			for(int pl = 1; pl <= nPlayers; pl++){
				display.waitForPlayerToClickRoll(pl);
				rollDice();
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
				checkScore(category, dice);
				display.updateScorecard(category, pl, score);
				TotalScore[pl - 1] += score;
				display.updateScorecard(TOTAL, pl, TotalScore[pl - 1]);
			}
		}
		
		
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
			case FULL_HOUSE:
				if(isFullHouse()) {
					score = 25;
				}
				break;
			case SMALL_STRAIGHT:
				if(isSmallStraight()){
					score = 30;
				}
				break;
			case LARGE_STRAIGHT:
				if(isLargeStraight()) {
					score = 40;
				}
				break;
			default:
				break;
		}
		return score;
	}
	//Check if users choice meets requirements
	
	private boolean isSmallStraight(){
		for (int i = 0; i < dice.length - 1; i++) {
	        if (dice[i + 1] - dice[i] != 1) {
	            return false;
	        }
		 }
	     return true;
	}
	
	private boolean isLargeStraight(){
		if (Arrays.equals(dice, new int[]{1, 2, 3, 4, 5}) ||
                Arrays.equals(dice, new int[]{2, 3, 4, 5, 6})) {
			return true;
		}
		return false;
		
	}
	
	private boolean isFullHouse() {
		if((dice[0] == dice[1] && dice[2] == dice[4])||
		(dice[0] == dice[2] && dice[3] == dice[4])){
			return true;
		}
		return false;
	}
	
	private boolean isOfAKind(int n) {
		for(int i = 1; i<=6; i++) {
			int count = 0;
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
	private int TotalScore[];

}
