import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Webcam-based catch game
 * Template for PS-1, Dartmouth CS 10, Winter 2014
 */
public class CatchExtraCredit extends Webcam {
	private Color trackColor;				// color of regions of interest (set by mouse press)
	private Flier flier;					// it's flying once mouse is pressed
	private RegionFinderExtraCredit finder;			// handles finding regions
	private int scorekeeper;
	private int losskeeper;
	private ArrayList<ArrayList<Point>> regions;		// one test region
	
	private int r = 10; //flier radius
	private int startx = 0, starty = 0;
	
	
	public CatchExtraCredit() {
		finder = new RegionFinderExtraCredit();
	}
	
	/**
	 * Overrides Webcam method to do the region finding the flier stuff.
	 */
	public void processImage() {
		if (trackColor != null) {
			// Detect regions and recolor
			//super("Region finder test", filename);
			
			// Do the region finding and recolor the image.
			RegionFinderExtraCredit finder = new RegionFinderExtraCredit();
			finder.findRegions(image, trackColor);
			finder.recolorRegions(image);
			
			// Move the flier and detect catches and misses
			flier.move();
			
			//notify if flier leaves screen
			if (flier.getX() > width - r) {
				losskeeper++;
				System.out.println("Miss. New Miss Total is " + losskeeper);
				flier.setX(startx);
				flier.setY(starty);
			}
			else if (flier.getX() < r) {
				losskeeper++;
				System.out.println("Miss. New Miss Total is " + losskeeper);
				flier.setX(startx);
				flier.setY(starty);
			}
			else if (flier.getY() > height - r) {
				losskeeper++;
				System.out.println("Miss. New Miss Total is " + losskeeper);
				flier.setX(startx);
				flier.setY(starty);
			}
			else if (flier.getY() < r) {
				losskeeper++;
				System.out.println("Miss. New Miss Total is " + losskeeper);
				flier.setX(startx);
				flier.setY(starty);
			}
			
			
			//notify if flier hits a region
			if (finder.checkCatch((int) flier.getX(), (int) flier.getY())) {
				flier.setX(startx);
				flier.setY(starty);
				scorekeeper++;
				System.out.println("Hit. New Score is " + scorekeeper);
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
				new CatchExtraCredit();
			}
		});
	}
}