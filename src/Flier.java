import java.util.*;
import java.awt.*;

/**
 * An animated object flying across the scene in a fixed direction
 * Template for PS-1, Dartmouth CS 10, Winter 2014
 */
public class Flier extends Agent {
	private int xmax, ymax; //max range, past which the agent will disappear
	private double dx, dy; //direction 
	private double startx, starty;
	
	// YOUR CODE HERE
	// instance variables, constructor, helper methods, etc.
	
	public Flier(double x, double y, int xmax, int ymax) {
		super(x,y); // since Agent requires a position; you may modify this if desired
		this.xmax = xmax; 
		this.ymax = ymax;
		
		 startx = x;
		 starty = y;
		
	
		dx = 100 * (Math.random() - 0.5);
		dy = 100 * (Math.random() - 0.5);
	}
	
	public void move() {
		x += dx;
		y += dy;
		
		if (x > xmax - r) {
			x = startx;
			y = starty;
		}
		else if (x < r) {
			x = startx;
			y = starty;
		}
		if (y > ymax - r) {
			x = startx;
			y = starty;
		}
		else if (y < r) {
			x = startx;
			y = starty;
		}
	}
}
