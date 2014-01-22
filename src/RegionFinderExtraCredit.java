import java.awt.*;
import java.awt.image.*;
import java.util.*;

/**
 * Region growing algorithm: finds and holds regions in an image.
 * Each region is a list of contiguous points with colors similar to a target color.
 * Template for PS-1, Dartmouth CS 10, Winter 2014
 */
public class RegionFinderExtraCredit extends RegionFinder { //save most of our work from the parent class Region Finder

	private ArrayList<ArrayList<Point>> regions = new ArrayList<ArrayList<Point>>();			// a region is a list of points
	// so the identified regions are in a list of lists of points


	/**
	 * Sets regions to the flood-fill regions in the image, similar enough to the color.
	 * @param image
	 * @param color
	 */
	public void findRegions(BufferedImage image, Color color) {
		// YOUR CODE HERE
		resetCheckedPoints(image); //set our checked points 2d array to false
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
	 * choose point to start looking for region in
	 * @param image our image
	 * @param color the color of the pixel
	 * @param startX the x value the mouse clicked
	 * @param startY the y value the mouse clicked
	 */
	public void startRegion(BufferedImage image, Color color, int startX, int startY) {
		// YOUR CODE HERE
		resetCheckedPoints(image); //set our checked points 2d array to false
		Point startPoint = new Point(startX, startY); //get that point
		ArrayList<Point> toCheck = new ArrayList<Point>(); //create a list of points we need to visit
		toCheck.add(startPoint); //add our start point to the arraylist of points we need to visit
		ArrayList<Point> ourRegion = new ArrayList<Point>(); //create a region to hold our points
		while (!toCheck.isEmpty()) { //while we still have points to check
			Point checkPoint = toCheck.get(0); //get the first point we need to check
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



	/** Overrides parent method
	 * Recolors image so that each region is a random uniform color, so we can see where they are
	 * @param image
	 */
	public void recolorRegions(BufferedImage image) {
		// YOUR CODE HERE
		resetColoredPoints(image); //reset colored points array
		int redComponent = (int) (255 * Math.random()); //random red component (255 is max RGB pixel color)
		int greenComponent = (int) (255 * Math.random()); //random green
		int blueComponent = (int) (255 * Math.random()); //random blue
		Color crazyColor = new Color(redComponent, greenComponent, blueComponent); //choose the color for our regions 
		for (ArrayList<Point> region : regions) { //for each region within regions
			for (Point pixel : region) { //for all points within each region
				image.setRGB(pixel.x, pixel.y, crazyColor.getRGB()); //set the color to our color
				coloredPointAt(pixel.x, pixel.y); //make sure we remember we colored that point
			}
		}
	}
}
