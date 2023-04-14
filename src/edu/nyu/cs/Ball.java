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
        // draw this object's image at its x and y coordinates
        this.app.imageMode(PApplet.CENTER); // setting so the image is drawn centered on the specified x and y coordinates
        this.app.image(this.img, this.x, this.y);
    }

    /**
     * move the ball and bounce on ceilings and the pads
     * the speed of x should be same, but changes direction once hitting the paddle
     * the speed will increase on the main class, not here
     * the speed of y should also be same, change direction once hitting the ceiling (upwards and downwards)
     * when hitting the paddle and the opposite part, the ball should bounce weirdly
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

    /**
     * Determines whehter a given x, y coordinate overlaps with this Star.
     * @param x The x coordinate of interest.
     * @param y The y coordinate of interest.
     * @param fudgeFactor An amount by which to expand the area we consider overlap
     * @return Boolean true if the x,y coordinate overlaps with this star, false otherwise.
     */
    public boolean overlaps(int x, int y, int fudgeFactor) {
        // get the coordinates of all edges of this ball's image
        int l = this.x - this.img.width/2 - fudgeFactor; // the left edge's x coord
        int r = this.x + this.img.width/2 + fudgeFactor; // the right edge's x coord
        int t = this.y - this.img.height/2 - fudgeFactor; // the top edge's y coord
        int b = this.y + this.img.height/2 + fudgeFactor; // the bottom edge's y coord
        // return whether the x,y coords are within the bounds of this ball's image
        return (x > l && x < r && y > t && y < b);
    }

}
