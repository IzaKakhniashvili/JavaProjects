/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		Gallows();
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		int x = XOFFSET;
		int y = SCAFFOLD_HEIGHT + YOFFSET + 30;
		GLabel guessedWord = new GLabel(word);
		guessedWord.setLocation(x, y);
		guessedWord.setFont("Font.ITALIC-20");
		add(guessedWord);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		int x = XOFFSET;
		int y = SCAFFOLD_HEIGHT + YOFFSET + 60;
		GLabel incorrect = new GLabel(""+letter);
		incorrect.setLocation(x, y);
		incorrect.setFont("Font.ITALIC-20");
		add(incorrect);
	}
	public void Gallows(){
		GLine scaffold = new GLine(XOFFSET, YOFFSET, XOFFSET, YOFFSET + SCAFFOLD_HEIGHT);
		add(scaffold);
		GLine beam = new GLine(XOFFSET, YOFFSET, XOFFSET + BEAM_LENGTH, YOFFSET);
		add(beam);
		GLine rope = new GLine(XOFFSET + BEAM_LENGTH, YOFFSET, XOFFSET + BEAM_LENGTH, YOFFSET + ROPE_LENGTH);
		add(rope);
	}
	public void HangmanHead(){
		GOval HangmanHead = new GOval(HEAD_RADIUS*2, HEAD_RADIUS*2);
		HangmanHead.setLocation(XOFFSET + BEAM_LENGTH - HEAD_RADIUS, YOFFSET + ROPE_LENGTH);
		add(HangmanHead);	
	}
	public void HangmanBody(){
		int xbody = XOFFSET + BEAM_LENGTH;
		int ybody = YOFFSET + HEAD_RADIUS * 2 + ROPE_LENGTH;
		GLine body = new GLine(xbody, ybody, xbody, ybody + BODY_LENGTH);
		add(body);
	}
	public void LeftArm(){
		int xArm = XOFFSET + BEAM_LENGTH;
		int yArm = YOFFSET + HEAD_RADIUS * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD ;
		GLine leftArm = new GLine(xArm, yArm, xArm - UPPER_ARM_LENGTH, yArm);
		add(leftArm);
		GLine lowerArm = new GLine(xArm - UPPER_ARM_LENGTH, yArm, xArm - UPPER_ARM_LENGTH, yArm + LOWER_ARM_LENGTH);
		add(lowerArm);
	}
	public void RightArm(){
		int xArm = XOFFSET + BEAM_LENGTH;
		int yArm = YOFFSET + HEAD_RADIUS * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD;
		GLine rightArm = new GLine(xArm, yArm, xArm + UPPER_ARM_LENGTH, yArm);
		add(rightArm);
		GLine lowerArm = new GLine(xArm + UPPER_ARM_LENGTH, yArm, xArm + UPPER_ARM_LENGTH, yArm + LOWER_ARM_LENGTH);
		add(lowerArm);
	}
	public void LeftLeg(){
		int xLeg = XOFFSET + BEAM_LENGTH;
		int yLeg = YOFFSET + HEAD_RADIUS * 2 + ROPE_LENGTH + BODY_LENGTH;
		GLine hip = new GLine(xLeg, yLeg, xLeg - HIP_WIDTH, yLeg);
		add(hip);
		GLine leg = new GLine(xLeg - HIP_WIDTH, yLeg, xLeg - HIP_WIDTH, yLeg + LEG_LENGTH);
		add(leg);
	}
	public void RightLeg(){
		int xLeg = XOFFSET + BEAM_LENGTH;
		int yLeg = YOFFSET + HEAD_RADIUS * 2 + ROPE_LENGTH + BODY_LENGTH;
		GLine hip = new GLine(xLeg, yLeg, xLeg + HIP_WIDTH, yLeg);
		add(hip);
		GLine leg = new GLine(xLeg + HIP_WIDTH, yLeg, xLeg + HIP_WIDTH, yLeg + LEG_LENGTH);
		add(leg);
	}
	public void LeftFoot(){
		int xFoot = XOFFSET + BEAM_LENGTH - HIP_WIDTH;
		int yFoot = YOFFSET + HEAD_RADIUS * 2 + ROPE_LENGTH + BODY_LENGTH + LEG_LENGTH;
		GLine foot =new GLine(xFoot, yFoot, xFoot - FOOT_LENGTH, yFoot);
		add(foot);
	}
	public void RightFoot(){
		int xFoot = XOFFSET + BEAM_LENGTH + HIP_WIDTH;
		int yFoot = YOFFSET + HEAD_RADIUS * 2 + ROPE_LENGTH + BODY_LENGTH + LEG_LENGTH;
		GLine foot =new GLine(xFoot, yFoot, xFoot + FOOT_LENGTH, yFoot);
		add(foot);
	}


/* Constants for the simple version of the picture (in pixels) */
	private static final int XOFFSET = 30;
	private static final int YOFFSET = 20;
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
