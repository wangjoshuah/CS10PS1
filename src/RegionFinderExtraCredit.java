import java.awt.*;
import java.awt.image.*;
import java.util.*;

/**
 * Region growing algorithm: finds and holds regions in an image.
 * Each region is a list of contiguous points with colors similar to a target color.
 * Template for PS-1, Dartmouth CS 10, Winter 2014
 */
public class RegionFinderExtraCredit {
	private static final int maxColorDiff = 20;				// how similar a pixel color must be to the target color, to belong to a region
	private static final int minRegion = 50; 				// how many points in a region to be worth considering
	
	private ArrayList<ArrayList<Point>> regions = new ArrayList<ArrayList<Point>>();			// a region is a list of points
															// so the identified regions are in a list of lists of points
	private boolean[][] checkedPoints;
	private boolean[][] coloredPoints;
	
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
			return true; // replace this -- just there so Eclipse won't complain
		}
	}

	/**
	 * Mark of a point as checked in our 2d array of bools for our image
	 * @param x value
	 * @param y value
	 */
	private void markPointAsChecked(int x, int y) {
//		System.out.println("We just checked point (" + x + ", " + y + ").");
		checkedPoints[x][y] = true;
	}
	
	/**
	 * Ask if we've checked a point
	 * @param x value
	 * @param y value
	 * @return 
	 */
	private boolean isPointunChecked(int x, int y) {
		return !checkedPoints[x][y];
	}
	
	
	/**
	 * Sets regions to the flood-fill regions in the image, similar enough to the color.
	 * @param image
	 * @param color
	 */
	public void findRegions(BufferedImage image, Color color) {
		// YOUR CODE HERE
		
		checkedPoints = new boolean[image.getWidth()][image.getHeight()]; //instantiate the 2d array of booleans to make sure we don't double check
		for (int i = 0; i < image.getWidth(); i ++ ) { //for all columns
			for (int j = 0; j < image.getHeight(); j ++) { //and all rows
				if (isPointunChecked(i, j)) { //if we haven't checked the point yet
					Point startPoint = new Point(i,j); //get that point
					ArrayList<Point> toCheck = new ArrayList<Point>(); //create a list of points we need to visit
					toCheck.add(startPoint); //add our start point to the arraylist of points we need to visit
					ArrayList<Point> ourRegion = new ArrayList<Point>(); //create a region to hold our points
					while (!toCheck.isEmpty()) { //while we still have points to check
						Point checkPoint = toCheck.get(0); //get the first point we need to check
//						System.out.println("Starting while loop with point " + checkPoint);
						ourRegion.add(checkPoint); //add it to our region
						markPointAsChecked((int) checkPoint.getX(), (int) checkPoint.getY()); //don't use the start point again
						for (int x = Math.max(0, (int) checkPoint.getX() - 1); x < Math.min(image.getWidth(), checkPoint.getX() + 2); x ++) { //for the neighboring columns
							for (int y = Math.max(0, (int) checkPoint.getY() - 1); y < Math.min(image.getHeight(), checkPoint.getY() + 2); y++) { //and the neighboring rows
								Color neighborColor = new Color(image.getRGB(x, y)); //get its color
								if (colorMatch(color, neighborColor) && isPointunChecked(x, y)) { //if the color matches and the point is unchecked
									Point neighborPoint = new Point(x, y); //get that point
									toCheck.add(neighborPoint); //add that point to the points we need to check
								}
								markPointAsChecked(x, y); //mark it off
							}
						}
						toCheck.remove(0); //remove the point were just were using as a center for neighbors
					}
					if (ourRegion.size() > minRegion) { //if it is large enough
						regions.add(ourRegion); //add it to our array of regions
					}
				}
			}
		}	
	}

	
	/**
	 * Recolors image so that each region is a random uniform color, so we can see where they are
	 * @param image
	 */
	public void recolorRegions(BufferedImage image) {
		// YOUR CODE HERE
		coloredPoints = new boolean[image.getWidth()][image.getHeight()]; //use a new coloredpoints array every time we recolor 
		int redComponent = (int) (255 * Math.random()); //random red component (255 is max RGB pixel color)
		int greenComponent = (int) (255 * Math.random()); //random green
		int blueComponent = (int) (255 * Math.random()); //random blue
		Color crazyColor = new Color(redComponent, greenComponent, blueComponent); //choose the color for our regions 
		for (ArrayList<Point> region : regions) { //for each region within regions
			for (Point pixel : region) { //for all points within each region
				image.setRGB(pixel.x, pixel.y, crazyColor.getRGB()); //set the color to our c
				coloredPoints[pixel.x][pixel.y] = true; //and mark that color as colored in our boolean 2d array
			}
		}
	}
	
	public boolean checkCatch(int x, int y) {
		return coloredPoints[x][y]; //return the boolean value of whether the point has been colored or not
	}
}
