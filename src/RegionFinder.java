import java.awt.*;
import java.awt.image.*;
import java.util.*;

/**
 * Region growing algorithm: finds and holds regions in an image.
 * Each region is a list of contiguous points with colors similar to a target color.
 * Template for PS-1, Dartmouth CS 10, Winter 2014
 */
public class RegionFinder {
	private static final int maxColorDiff = 20;				// how similar a pixel color must be to the target color, to belong to a region
	private static final int minRegion = 50; 				// how many points in a region to be worth considering
	
	private ArrayList<ArrayList<Point>> regions = new ArrayList<ArrayList<Point>>();			// a region is a list of points
															// so the identified regions are in a list of lists of points
	private boolean[][] checkedPoints;
	private ArrayList<Point> toDoList;
	
	/**
	 * Accesses the currently-identified regions.
	 * @return
	 */
	public ArrayList<ArrayList<Point>> getRegions() {
		return regions;
	}
	
	/**
	 * Tests whether the two colors are "similar enough" (your definition, subject to the static threshold).
	 * @param c1 the original color
	 * @param c2 the neighbor color
	 * @return
	 */
	private static boolean colorMatch(Color c1, Color c2) {
		// YOUR CODE HERE
		System.out.println("Test Color");
		if (c2.getRed() - c1.getRed() > maxColorDiff) { //if the neighbor color is 20 greater than the original
			return false; //go away
		}
		else if (c2.getRed() - c1.getRed() < -maxColorDiff) { //if the neighbor color is 20 less than the original
			return false; //still not true
		}
		else if (c2.getBlue() - c1.getBlue() > maxColorDiff) { //repeat for blue
			return false;
		}
		else if (c2.getBlue() - c1.getBlue() < -maxColorDiff) {
			return false;
		}
		else if (c2.getGreen() - c1.getGreen() > maxColorDiff) { //repeat for green
			return false;
		}
		else if (c2.getGreen() - c1.getGreen() < -maxColorDiff) {
			return false;
		}
		else { //it completely works now and isn't outside of any bounds
			System.out.println("Matches!");
			return true; // replace this -- just there so Eclipse won't complain
		}
	}

	/**
	 * Sets regions to the flood-fill regions in the image, similar enough to the color.
	 * @param image
	 * @param color
	 */
	public void findRegions(BufferedImage image, Color color) {
		// YOUR CODE HERE
		System.out.println("start finding regions");
		checkedPoints = new boolean[image.getWidth()][image.getHeight()]; //get a new 2d array of points to check
		for (int x = 0; x < image.getWidth(); x++) { //for all x's
			for (int y = 0; y < image.getHeight(); y ++) { //for all y's
				Point currentPixel = new Point(x,y); //get the pixel at that point
				Color pixelColor = new Color(image.getRGB(x,y)); //get the color for that
				if (!checkedPoints[currentPixel.x][currentPixel.y] && colorMatch(color, pixelColor)) { //if the pixel is of the correct color we're looking for,
					ArrayList<Point> queueToCheck = new ArrayList<Point>(); //create a queue of pixels to check
					queueToCheck.add(currentPixel); //add the current pixel to that list
					ArrayList<Point> region = new ArrayList<Point>(); //start a new region to hold it
					while (!queueToCheck.isEmpty()) { //while we still have points to check
						System.out.println("while looping");
						Point neighbor = queueToCheck.get(0); //get the first one int the ArrayList to check add it
						region.add(neighbor); //add it to the region
//						checkedPoints[neighbor.x][neighbor.y] = true; //make add it to the checked region
						addNeighbors(image, neighbor, queueToCheck, color); //add its neighbors to the region we need to check
						System.out.println("Queue size is " + queueToCheck.size());
						queueToCheck.remove(0); //remove the point we just checked
					}
					System.out.println("Region size is " + region.size());
					if (region.size() >= minRegion) { //if the region we just checked is greater than the required size
						regions.add(region); //add it to the list of regions we maintain
						System.out.println("Add this region to regions");
					}
				}
				checkedPoints[currentPixel.x][currentPixel.y] = true; //make sure we've checked that pixel
			}
		}
	}
	
	private void addNeighbors(BufferedImage image, Point pixel, ArrayList<Point> toDoList, Color matchColor) {
		System.out.println("Adding Neighbors");
		for (int x = Math.max(0,(int) pixel.getX() - 1); x <= Math.min(image.getWidth(), (int) pixel.getX()); x ++) { //loop through neighboring columns
			System.out.println("X is " + x + "End X is " + Math.min(image.getWidth(), (int) pixel.getX()));
			for (int y = Math.max(0, (int) pixel.getY() - 1); y <= Math.min(image.getHeight(), (int) pixel.getY()); y ++) { //loop through neighboring rows
				System.out.println("Y is " + y + "End Y is " + Math.min(image.getHeight(), (int) pixel.getY()));
				if (!checkedPoints[x][y]) { //if we haven't checked it yet
//					checkedPoints[x][y] = true; //tell our algorithm we've checked it so we don't come back
					Color pixelColor = new Color(image.getRGB(x,y)); //get the color for that
					System.out.println("Color we are trying to look at is " + pixelColor.toString());
					if (colorMatch(matchColor, pixelColor)) { //if it's the right color we're looking for,
						toDoList.add(pixel); //add it to our to do list
					}	
				}
			}
		}
		System.out.println("To DO List Size is " + toDoList.size());
	}
	
	/**
	 * Recolors image so that each region is a random uniform color, so we can see where they are
	 * @param image
	 */
	public void recolorRegions(BufferedImage image) {
		// YOUR CODE HERE
		System.out.println("Recoloring");
		Color crazyColor = new Color(255, 0, 0);
		for (ArrayList<Point> region : regions) {
			for (Point pixel : region) {
				image.setRGB(pixel.x, pixel.y, crazyColor.getRGB());
			}
		}
	}
}
