package edu.nyu.cs;

import processing.core.PApplet;
import processing.core.PImage;

public class Ball {
    // instance properties
    private Game app; // will hold a reference to the main Game object
    private PImage img; // will hold a reference to an image of a star
    private int x; // will hold the x coordinate of this object on the screen
    private int y; // will hold the y coordinate of this object on the screen

    /**
     * Constructor to create a Ball object at a specific position on the screen
     * @param app a reference to the Game object that created this object
     * @param x the x coordinate of this object on the screen
     * @param y the y coordinate of this object on the screen
     */
    public Ball(Game app, String imgFilePath, int x, int y) {
        this.app = app; // store a reference to the main game object

        // load the specified image
        this.img = app.loadImage(imgFilePath);

        // store the x and y coordinates of this object on the screen
        this.x = x;
        this.y = y;
    }

    /**
     * Draw this ball's image to the screen at the appropriate coordinates
     */
    public void draw() {
        // draw this ball's image at its x and y coordinates
        this.app.imageMode(PApplet.CENTER); // setting so the image is drawn centered on the specified x and y coordinates
        this.app.image(this.img, this.x, this.y);
    }

    /**
     * move the ball and bounce on ceilings and the pads
     * the speed of x should be same, but changes direction once hitting the paddle
     * the speed will increase on the main method, not here
     * the speed of y should also be same, but the ball changes direction once hitting the ceiling (upwards and downwards)
     * when hitting the paddle and the opposite part, the ball should bounce weirdly, using random angles
     * @param dy The ball's y axis velocity
     * @param dx The ball's x axis velocity
     */
    public int[] moveRandomly(int dy, int dx) {

        this.x += dx;
        this.y += dy;

        // when ball reaches ceiling
        if (this.y<=0 || this.y>=800) {
            dy = -dy;
        }
        // when ball reaches the opposite side
        if (this.x>=1200) {
            dx = -dx;
            int randNum = (int) (Math.random()*11)-5;
            dy = randNum;
        }
        // when ball reaches left side (paddle)
        if (this.x<=0) {
            if (this.y>=this.app.mouseY-80 && this.y<=this.app.mouseY+80) {
                dx = -dx;
                int randNum = (int) (Math.random()*11)-5;
                dy = randNum;
                
            }
            else {
                // if dx = 0, user loses
                dx = 0;
            }
        }

        // update the ball's coordinates
        this.x += dx;
        this.y += dy;

        int[] temp = {dx,dy};
        return temp;
    }

}
