import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Webcam-based catch game
 * PS-1, Dartmouth CS 10, Winter 2014
 * Joshua Wang & Alexander Tsu
 */
public class Catch extends Webcam {
	private Color trackColor;				// color of regions of interest (set by mouse press)
	private Flier flier;					// it's flying once mouse is pressed
	private RegionFinder finder;			// handles finding regions
	private int scorekeeper;				// keeps track of number of hits
	private int losskeeper;					// keeps track of number of misses
	private int r = 10; 					//flier radius
	private int startx = 10, starty = 10; 	//starting location
	
	
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
			
			//notify if flier leaves screen
			if (flier.getX() > width - r) { //if statement triggers when flier extends past the leftmost bound of the webcam
				losskeeper++; //adds one to the losskeeper
				System.out.println("Miss. New Miss Total is " + losskeeper); //prints the current quantity of missed balls
				flier.setX(startx); //sets the flier location at the starting x point
				flier.setY(starty); //sets the flier location at the starting y point
				flier.setspeedX(5+(int)(Math.random()*15)); //sets a new x movement speed for the flier
				flier.setspeedY(5+(int)(Math.random()*15)); //sets a new y movement speed for the flier
			}
			else if (flier.getX() < r) { //else if triggers if flier touches the leftmost boundary. All following code is same as above if statement
				losskeeper++;
				System.out.println("Miss. New Miss Total is " + losskeeper);
				flier.setX(startx);
				flier.setY(starty);
				flier.setspeedX(5+(int)(Math.random()*15));
				flier.setspeedY(5+(int)(Math.random()*15));
			}
			else if (flier.getY() > height - r) { //else if triggers if flier touches the bottommost boundary. All following code is same as above if statement
				losskeeper++;
				System.out.println("Miss. New Miss Total is " + losskeeper);
				flier.setX(startx);
				flier.setY(starty);
				flier.setspeedX(5+(int)(Math.random()*15));
				flier.setspeedY(5+(int)(Math.random()*15));
			}
			else if (flier.getY() < r) { //else if triggers if flier touches topmost boundary. All following code is same as above if statement
				losskeeper++;
				System.out.println("Miss. New Miss Total is " + losskeeper);
				flier.setX(startx);
				flier.setY(starty);
				flier.setspeedX(5+(int)(Math.random()*15));
				flier.setspeedY(5+(int)(Math.random()*15));
			}
			
			//notify if flier hits a region
			if (finder.checkCatch((int) flier.getX(), (int) flier.getY())) { //checkCatch returns true if the location of the flier matches a region. 
				flier.setX(startx); //setX, setY, setspeedX, setspeedY are the same as above
				flier.setY(starty);
				flier.setspeedX(5+(int)(Math.random()*15));
				flier.setspeedY(5+(int)(Math.random()*15));
				scorekeeper++; //adds one to the scorekeeper
				System.out.println("Hit. New Score is " + scorekeeper); //prints current number of hits
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
		
		// Start object flying at mouse press. Speed is random value between 5 and 20
		flier = new Flier(startx, starty, 5+(int)(Math.random()*15), 5+(int)(Math.random()*15), r);
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
