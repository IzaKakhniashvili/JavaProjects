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

public class BreakoutExtensions extends GraphicsProgram {

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
	private GLabel lives;
	private GLabel score;

/**Velocity of the ball*/
	private double vx;
	private double vy;
	private double newvx;

/**Number of bricks*/
	private int NBRICKS = NBRICKS_PER_ROW * NBRICK_ROWS;
	
/**Number of lives left*/
	private int livesLeft = NTURNS; 
	
/**Score: number of bricks user takes*/
	private int SCORE = 0;
	
	private boolean startGame = false;
	

/* Method: run() */
/** Runs the Breakout program. */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	public void run() {
		setUp();
		addMouseListeners();
		while(livesLeft > 0){
			   initialiseGame();
		   }
		pause(100);
		gameOver();
	}
	
	private void setUp(){ /*We separate process of setting up the game*/
		makeBrickRows();
		makeBall();
		makePaddle();
		addLivesLabel();
		addScoreLabel();
	}
	private void initialiseGame(){
		if(startGame = true){
			makeBallMove();
			}
		}
	private void clickToStart(){
		GLabel start = new GLabel("CLICK TO START");
		start.setFont(new Font("Serif", Font.BOLD, 20));
		add(start, (getWidth() - start.getWidth()) / 2, getHeight() / 2 - start.getHeight());
		waitForClick();
		remove(start);
		startGame = true;
	}
	private void addLivesLabel(){
		lives = new GLabel("Lives left: " + livesLeft);
		lives.setFont(new Font("Arial", Font.BOLD, 15));
		lives.setColor(Color.red);
		add(lives, 0, lives.getHeight());
		
	}
	private void addScoreLabel(){
		score = new GLabel("Score: " + SCORE);
		score.setFont(new Font("Arial", Font.BOLD, 15));
		score.setColor(Color.green);
		add(score, getWidth() - score.getWidth(), score.getHeight());
		
	}
	
		
	private void makeBrickRows(){
		for(int i = 0; i < NBRICKS_PER_ROW; i++){
			for(int j = 0; j < NBRICK_ROWS; j++){
				double x = BRICK_SEP + i * (BRICK_WIDTH + BRICK_SEP);
				double y = BRICK_Y_OFFSET + j * (BRICK_HEIGHT + BRICK_SEP);
				brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				if(j <= 2) brick.setColor(Color.red);
				if(j <= 4 & j >= 2) brick.setColor(Color.orange);
				if(j <= 6 & j >= 4) brick.setColor(Color.yellow);
				if(j <= 8 & j >= 6) brick.setColor(Color.green);
				if(j <= 10 & j >= 8) brick.setColor(Color.cyan);
				add(brick, x, y);
			}
		}	
	}
	private void makeBall(){
		double x = WIDTH / 2 - BALL_RADIUS;
		double y = HEIGHT / 2 - BALL_RADIUS;
		ball = new GOval(2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		add(ball, x, y);
	}
	private void makePaddle(){
		double x = (WIDTH - PADDLE_WIDTH) / 2;
		double y = (HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET);
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle, x, y);
	}
	private void makeBallMove(){
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		vy = 3;
		while(livesLeft>0){
			checkCollisions();
			ball.move(vx , vy);
			pause(10);  
		}
		}
	public void mouseMoved(MouseEvent e){
		newvx = e.getX();
		if(e.getX() + PADDLE_WIDTH > WIDTH){
			newvx = WIDTH - PADDLE_WIDTH;
		}
		if(e.getX() < 0){
			newvx = 0;
		}
		paddle.setLocation(newvx, HEIGHT - 40);	
	}
	private GObject getCollidingObject(){
		if(getElementAt(ball.getX(), ball.getY()) != null){
			return getElementAt(ball.getX(), ball.getY());
		}else if(getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) != null){
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY());
		}else if(getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) != null){
			return getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS);
		}else if(getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null){
			return getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
		}else{
			return null;
		}
	}
	private void checkCollisions(){
		/*If ball collided with paddle its vy should change to -vy, if ball collided with bricks it should remove the brick and change its vy to -vy*/
		GObject collider = getCollidingObject();
		if(collider == paddle){
			bounceClip.play();
			if(vy > 0 & ball.getY() + 2*BALL_RADIUS> HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET) vy =-vy;
		}else if(collider != null){
			if(SCORE % 7 == 0) vy = vy * 2;
			bounceClip.play();
			remove(collider);
			vy = -vy;
			NBRICKS --;
			SCORE ++;
			remove(score);
			score = new GLabel("Score: " + SCORE );
			score.setFont(new Font("Arial", Font.BOLD, 15));
			score.setColor(Color.green);
			add(score, getWidth() - score.getWidth(), score.getHeight());
			
		}
		/*If ball collides with walls it should start moving in opposite direction*/
		if(ball.getX() + 2 * BALL_RADIUS >= WIDTH ||
				ball.getX() < 0) vx = -vx;
		if(ball.getY() < 0) vy = -vy;
		/*If ball falls down, lives left should reduce and ball should reappear in the middle of the board*/
		if(ball.getY() >= HEIGHT - 2*BALL_RADIUS){
			livesLeft--;
			remove(lives);
			pause(250);
			lives = new GLabel("Lives left: " + livesLeft);
			lives.setFont(new Font("Arial", Font.BOLD, 15));
			lives.setColor(Color.red);
			add(lives, 0, lives.getHeight());
			if(livesLeft > 0){
				pause(100);
				ball.setLocation(WIDTH / 2 - 2 * BALL_RADIUS, HEIGHT / 2 - 2 * BALL_RADIUS );
				pause(100);
			}
		}
	}
	private void gameOver(){
		if(NBRICKS == 0 ){
			removeAll();
			GLabel gameWon = new GLabel("You have won the game. Your score is: " + SCORE);
			gameWon.setFont(new Font("Serif", Font.BOLD, 20));
			gameWon.setColor(Color.red);
			add(gameWon, (WIDTH - gameWon.getWidth()) / 2, HEIGHT / 2 - gameWon.getHeight());
		}
		if(livesLeft == 0){
			removeAll();
			GLabel gameLost = new GLabel("You have lost the game. Your score is: " + SCORE);
			gameLost.setFont(new Font("Serif", Font.BOLD, 20));
			gameLost.setColor(Color.red);
			add(gameLost,(WIDTH - gameLost.getWidth()) / 2, HEIGHT / 2 - gameLost.getHeight() );
		}
		
	}
}

