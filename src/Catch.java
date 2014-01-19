import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Webcam-based catch game
 * Template for PS-1, Dartmouth CS 10, Winter 2014
 */
public class Catch extends Webcam {
	private Color trackColor;				// color of regions of interest (set by mouse press)
	private Flier flier;					// it's flying once mouse is pressed
	private RegionFinder finder;			// handles finding regions
	private int scorekeeper;
	private ArrayList<ArrayList<Point>> regions;		// one test region
	
	private int r = 10; //flier radius
	private int startx = 0, starty = 0;
	
	
	public Catch() {
		finder = new RegionFinder();
	}
	
	/**
	 * Overrides Webcam method to do the region finding the flier stuff.
	 */
	public void processImage() {
		if (trackColor != null) {
			// Detect regions and recolor
			//super("Region finder test", filename);
			
			// Do the region finding and recolor the image.
			RegionFinder finder = new RegionFinder();
			finder.findRegions(image, trackColor);
			finder.recolorRegions(image);
			
			// Move the flier and detect catches and misses
			flier.move();
			
			//notify if flier hits a region
			if (finder.checkCatch((int) flier.getX(), (int) flier.getY())) {
				flier.setX(0);
				flier.setY(0);
				scorekeeper++;
				System.out.println("Hit. New Score is" + scorekeeper);
			}
			
			
			//notify if flier leaves screen
			if (flier.getX() > width - r) {
				flier.setX(startx);
				flier.setY(starty);
			}
			else if (flier.getX() < r) {
				flier.setX(startx);
				flier.setY(starty);
			}
			if (flier.getY() > height - r) {
				flier.setX(startx);
				flier.setY(starty);
			}
			else if (flier.getY() < r) {
				flier.setX(startx);
				flier.setY(starty);
			}
		}		
	}
	
	/**
	 * Overrides the Webcam method to also draw the flier.
	 */
	public void draw(Graphics g) {
		super.draw(g);
		
		// Draw flier
		if (flier != null) {
			flier.draw(g);
		}
	}

	/**
	 * Overrides the Webcam method to set the track color and start the flier.
	 */
	public void handleMousePress(MouseEvent event) {
		// Set tracking color
		int x = event.getPoint().x; 
		int y = event.getPoint().y;
		trackColor = new Color(image.getRGB(x, y));
		
		
		// Start object flying
		flier = new Flier(startx, starty, r);
	}
	
	/**
	 * Main method for the application
	 * @param args		command-line arguments (ignored)
	 */	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Catch();
			}
		});
	}
}
