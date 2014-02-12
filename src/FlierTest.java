import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class FlierTest extends DrawingFrame {
	private static final int width=600, height=600;		// setup: size of the world

	private Agent agentf;								// one test flier
	private ArrayList<ArrayList<Point>> regions;		// one test region

	private int startx = 0, starty = 0;
	
	public FlierTest() {
		super("Flier Test", width, height);
		
		agentf = new Flier(0,0, 10, startx, startx);
		
		// Create the flier.
		canvas.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				int x = event.getPoint().x, y = event.getPoint().y;
				agentf = new Flier(500,10, 10, y, y);
			}
	
		});

		
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

				agentf.move();
				//Out of bounds detection
				if (agentf.getX() > width - 10) {
					agentf.setX(startx);
					agentf.setY(starty);
				}
				else if (agentf.getX() < 10) {
					agentf.setX(startx);
					agentf.setY(starty);
				}
				if (agentf.getY() > height - 10) {
					agentf.setX(startx);
					agentf.setY(starty);
				}
				else if (agentf.getY() < 10) {
					agentf.setX(startx);
					agentf.setY(starty);
				}
				
				for(ArrayList<Point> region : regions) {
					for(Point point : region) {
						if(agentf.getX() == point.getX() && agentf.getY() == point.getY()) {
							agentf.setX(0);
							agentf.setY(0);
						}
					}
				}

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
		
		agentf.draw(g);
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
