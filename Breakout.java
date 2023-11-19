/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
/**Objects for setup*/
	private GRect brick;
	private GOval ball;
	private GRect paddle;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setUp();
		startGame();
		/* You fill this in, along with any subsidiary methods */
	}
	private void setUp(){ /*We separate process of setting up the game*/
		makeBrickRows();
		
	}
	private void startGame(){
		
	}
	private void makeBrickRows(){
		for(int i = 0; i < NBRICKS_PER_ROW; i++){
			for(int j = 0; j < NBRICK_ROWS; j++){
				double x = BRICK_SEP + i * (BRICK_WIDTH + BRICK_SEP);
				double y = BRICK_Y_OFFSET + j * (BRICK_HEIGHT + BRICK_SEP);
				brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				if(i <= 2) brick.setColor(Color.red);
				if(i <= 4 & i >= 2) brick.setColor(Color.orange);
				if(i <= 6 & i >= 4) brick.setColor(Color.yellow);
				if(i <= 8 & i >= 6) brick.setColor(Color.green);
				if(i <= 10 & i >= 8) brick.setColor(Color.cyan);
				add(brick, x, y);
			}
		}
		
	}

}
