import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Webcam-based catch game
 * Template for PS-1, Dartmouth CS 10, Winter 2014
 */
public class Catch extends Webcam {
	private Color trackColor;				// color of regions of interest (set by mouse press)
	private Flier flier;					// it's flying once mouse is pressed
	private RegionFinder finder;			// handles finding regions
	
	public Catch() {
		finder = new RegionFinder();
	}
	
	/**
	 * Overrides Webcam method to do the region finding the flier stuff.
	 */
	public void processImage() {
		if (trackColor != null) {
			// Detect regions and recolor
			// YOUR CODE HERE

			// Move the flier and detect catches and misses
			// YOUR CODE HERE
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
		// YOUR CODE HERE

		// Start object flying
		// YOUR CODE HERE
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
