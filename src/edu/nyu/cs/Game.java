package edu.nyu.cs;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.lang3.SystemUtils;

import processing.core.*; // import the base Processing library
import processing.sound.*; // import the processing sound library

/**
 * This is a single-player pong game. The player plays against the wall, from the left side.
 * When the ball hits the side, it bounces normally.
 * However, when the ball hits the opposite side or the paddle, it bounces randomly. 
 * The ball also gets faster as time passes, making it harder to catch. 
 * The game counts the number of seconds the player survived, and records the maximum. 
 * If the maximum record hits 10 and 20, there will be special changes. 
 * 
 * @author Troy Kim
 * @version 0.1
 */
public class Game extends PApplet {

  private SoundFile soundStartup; // will refer to a sound file to play when the program first starts
  private PImage imgMe; // will hold a photo of the background
  private ArrayList<Ball> balls; // will hold an ArrayList of ball objects
  private int score = 0; // the user's highest score
  private int previousScore = 0; // the user's previous score
  private int dy = 5; // the ball's initial y axis velocity
  private int dx = 5; // the ball's initial x axis velocity
  private int absDx = 5; // to make the ball go faster and faster
  private int absCount = 0; // also to make the ball go faster and faster
  private boolean start = true; // to count seconds
  private long startTime = 0; // to count seconds
  private long endTime = 0; // to count seconds


	/**
	 * called when the program runs
	 */
	public void setup() {
    this.cursor(PApplet.CROSS);

    // load up a sound file and play it once when program starts up
		String cwd = Paths.get("").toAbsolutePath().toString(); 
		String path = Paths.get(cwd, "sounds", "vibraphon.mp3").toString(); 
    this.soundStartup = new SoundFile(this, path);
    this.soundStartup.play();

    // load up an image of the background
		path = Paths.get(cwd, "images", "pongbackground.png").toString(); 
    this.imgMe = loadImage(path);

    // some basic settings for drawing shapes
    this.ellipseMode(PApplet.CENTER); 
    this.rectMode(PApplet.CENTER);
    this.imageMode(PApplet.CENTER); 
    
    // create the ball, starting life at the center of the window
    balls = new ArrayList<Ball>();
  	path = Paths.get(cwd, "images", "ball.png").toString();
    Ball ball = new Ball(this, path, this.width/2, this.height/2);
    this.balls.add(ball);
	}

	/**
	 * This method is called automatically by Processing every 1/60th of a second by default.
	 */
	public void draw() {
    // fill the window with solid color
    this.background(0, 0, 0); // fill the background with black.

    // show an image of me that wanders around the window
    image(this.imgMe, this.width / 2, this.height/2); // draw image to center of window

    // draw an ellipse at the current position of the mouse
    this.fill(255, 255, 255); // set the r, g, b color to use for filling in any shapes we draw later.
    this.rect(0, this.mouseY, 8, 160); // draw an rectangle wherever the mouse is

    // draw all balls to their current position
    if (dx>0) {
      dx = absDx;
    }
    else {
      dx = -absDx;
    }
    Ball ball = this.balls.get(0);
    if (start) {
      start = false;
      startTime = System.nanoTime();
    }
    int[] temp = ball.moveRandomly(dy, dx);
    dx = temp[0];
    dy = temp[1];
    ball.draw();
    absCount += 1;
    absDx = (int) (absCount/100) +5;
    if (dx==0) {
      endTime = System.nanoTime();
      previousScore = (int) ((endTime-startTime)/1000000000);
      score = max(previousScore, score);
      start = true;
      this.balls.remove(0);
      String cwd = Paths.get("").toAbsolutePath().toString();
      String path = "";
      // change ball to cat
      if (score>=10&&score<15) {
        path = Paths.get(cwd, "images", "cat.png").toString();
      }
      // change ball to tiger
      else if (score>=15) {
        path = Paths.get(cwd, "images", "tiger.png").toString();
      }
      // default setting: set ball to ball
      else {
        path = Paths.get(cwd, "images", "ball.png").toString();
      }
      ball = new Ball(this, path, this.width/2, this.height/2);
      this.balls.add(ball);
      absCount = 0;
      int randNum = (int) (Math.random()*2);
      if (randNum==0) {
        dx = 5;
      }
      else {
        dx = -5;
      }
      try {
        Thread.sleep(500);
      } 
      catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    // show the score at the bottom of the window
    String scoreString = String.format("HIGHEST RECORD (IN SECONDS): %d PREVIOUS RECORD (IN SECONDS): %d", this.score, this.previousScore);
    text(scoreString, this.width/2+50, this.height-50);

	}

	/**
	 * called when user presses key
   * prints out the pressed key
	 */
	public void keyPressed() {
    // the `key` variable holds the char of the key that was pressed, the `keyCode` variable holds the ASCII/Unicode numeric code (int) for that key
		System.out.println(String.format("Key pressed: %s, key code: %d.", this.key, this.keyCode));
	}  

	/**
	 * called when mouse is clicked, and prints out the mouse location
   */
	public void mouseClicked() {
		System.out.println(String.format("Mouse clicked at: %d:%d.", this.mouseX, this.mouseY));
	}

	/**
	 * prints out the mouse location
   * called when the mouse is moved
   */
	public void mouseDragged() {
		System.out.println(String.format("Mouse dragging at: %d:%d.", mouseX, mouseY));
	}

  /**
   * modify size of the window
   */
  public void settings() {
		size(1200, 800); // set the map window size, using the OpenGL 2D rendering engine
		System.out.println(String.format("Set up the window size: %d, %d.", width, height));    
  }

  /**
   * The main function
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) {
    // make sure we're using Java 1.8
		System.out.printf("\n###  JDK IN USE ###\n- Version: %s\n- Location: %s\n### ^JDK IN USE ###\n\n", SystemUtils.JAVA_VERSION, SystemUtils.getJavaHome());
		boolean isGoodJDK = SystemUtils.IS_JAVA_1_8;
		if (!isGoodJDK) {
			System.out.printf("Fatal Error: YOU MUST USE JAVA 1.8, not %s!!!\n", SystemUtils.JAVA_VERSION);
		}
		else {
			PApplet.main("edu.nyu.cs.Game"); // do not modify this!
		}
  }

}
