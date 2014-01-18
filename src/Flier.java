import java.util.*;
import java.awt.*;

/**
 * An animated object flying across the scene in a fixed direction
 * Template for PS-1, Dartmouth CS 10, Winter 2014
 */
public class Flier extends Agent {
	private int xmax, ymax; //max range, past which the agent will disappear
	private int dx, dy; //direction 
	private double startx, starty;
	

	// YOUR CODE HERE
	// instance variables, constructor, helper methods, etc.
	
	public Flier(int x, int y, int r) {
		super(x,y); // since Agent requires a position; you may modify this if desired
		this.xmax = xmax; 
		this.ymax = ymax;
		this.r = r;
		
		 startx = x;
		 starty = y;
		
	
		dx = 10/*(int) ((int) 100 * (Math.random() - 0.5))*/;
		dy = 10/*(int) ((int) 100 * (Math.random() - 0.5))*/;
	}
	
	public void move() {
		x += dx;
		y += dy;

	}
	
	
}
