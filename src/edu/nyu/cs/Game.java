package edu.nyu.cs;

import org.apache.commons.lang3.SystemUtils;

import processing.core.PApplet;
import processing.sound.SoundFile;

public class Game extends PApplet {

  SoundFile startupSound; // will refer to a sound file to play when the program first starts

	/**
	 * This method will be automatically called when the program runs.
   * Use it to set up the initial state of any instance properties you may use in the draw method.
	 */
	public void setup() {
    // load up a sound file and play it once when program starts up
    startupSound = new SoundFile(this, "sounds/vibraphon.mp3");
    startupSound.play();
	}

	/**
	 * This method is called automatically every 1/60th of a second by default.
   * Use it to modify what is drawn to the screen.
	 */
	public void draw() {
    this.background(0, 0, 0); // set r, g, b color to fill the window and cover up anything there already
    this.fill(255, 255, 255); // set r, g, b color for filling in any shapes

    this.ellipseMode(PApplet.CENTER); // setting so the ellipse radiates away from the x and y coordinates we specify.
    this.ellipse(mouseX, mouseY, 20, 20); // draw an ellipse wherever the mouse is
	}

	/**
	 * This method is automatically called every time the user presses a key while viewing the map.
	 * The `key` variable (type char) is automatically is assigned the value of the key that was pressed.
	 * The `keyCode` variable (type int) is automatically is assigned the numeric ASCII/Unicode code of the key that was pressed.
	 */
	public void keyPressed() {
    // the `key` variable holds the char of the key that was pressed, the `keyCode` variable holds the ASCII/Unicode numeric code for that key.
		System.out.println(String.format("Key pressed: %s, key code: %d.", key, keyCode));
	}  

	/**
	 * This method is automatically called every time presses down and releases the mouse.
	 * The `mouseX` and `mouseY` variables are automatically is assigned the coordinates on the screen when the mouse was clicked.
   */
	public void mouseClicked() {
		System.out.println(String.format("Mouse clicked at: %d:%d.", mouseX, mouseY));
	}

	/**
	 * This method is automatically called every time the user presses down and drags the mouse.
	 * The `mouseX` and `mouseY` variables are automatically is assigned the coordinates on the screen when the mouse was clicked.
   */
	public void mouseDragged() {
		System.out.println(String.format("Mouse dragging at: %d:%d.", mouseX, mouseY));
	}

  /**
   * A method that can be used to modify settings of the window, such as set its size.
   * This method shouldn't really be used for anything else.  
   * Use the setup() method for most other tasks to perform when the program first runs.
   */
  public void settings() {
		size(1200, 800); // set the map window size, using the OpenGL 2D rendering engine
		System.out.println(String.format("Set up the window size: %d, %d.", width, height));    
  }

  /**
   * The main function is automatically called first in a Java program.
   * When using the Processing library, this method must call PApplet's main method and pass it the full class name, including package.
   * You shouldn't need to modify this method.
   * 
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
