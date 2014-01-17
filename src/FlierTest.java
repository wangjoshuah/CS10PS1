import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
//created by Alex Tsu

public class FlierTest extends DrawingFrame {
	private static final int width=400, height=400;		// setup: size of the world

	private Flier flier;								// one test flier
	private ArrayList<ArrayList<Point>> regions;		// one test region
	
	public FlierTest() {
		super("Flier Test", width, height);
		
		// Create the flier.
		// YOUR CODE HERE
		
		// Create a region that the flier might hit.
		ArrayList<Point> region = new ArrayList<Point>();
		for (int y=height/3; y<height*2/3; y++) {
			for (int x=width/3; x<width*2/3; x++) {
				region.add(new Point(x,y));
			}
		}
		regions = new ArrayList<ArrayList<Point>>();
		regions.add(region);
		
		// Move every 100 milliseconds.
		Timer timer = new Timer(100, new AbstractAction("update") {
			public void actionPerformed(ActionEvent e) {
				// Move the flier.
				// YOUR CODE HERE
				// Detect exiting the window (and restart)
				// YOUR CODE HERE
				// Detect hitting the region (and restart)
				// YOUR CODE HERE
				
				canvas.repaint();
			}
		});
		timer.start();
	}
	
	/**
	 * Override method from DrawingFrame, in order to draw the region and flier
	 */
	public void draw(Graphics g) {
		for (ArrayList<Point> region : regions) {
			for (Point point : region) {
				g.drawRect(point.x, point.y, 1, 1);
			}
		}
		
		flier.draw(g);
	}
	
	/**
	 * Main method for the application
	 * 
	 * @param args	ignored
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FlierTest();
			}
		});
	}
}
