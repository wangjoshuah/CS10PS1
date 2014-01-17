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
	
	private ArrayList<ArrayList<Point>> regions;			// a region is a list of points
															// so the identified regions are in a list of lists of points

	/**
	 * Accesses the currently-identified regions.
	 * @return
	 */
	public ArrayList<ArrayList<Point>> getRegions() {
		return regions;
	}
	
	/**
	 * Tests whether the two colors are "similar enough" (your definition, subject to the static threshold).
	 * @param c1
	 * @param c2
	 * @return
	 */
	private static boolean colorMatch(Color c1, Color c2) {
		// YOUR CODE HERE
		return true; // replace this -- just there so Eclipse won't complain
	}

	/**
	 * Sets regions to the flood-fill regions in the image, similar enough to the color.
	 * @param image
	 * @param color
	 */
	public void findRegions(BufferedImage image, Color color) {
		// YOUR CODE HERE
	}
	
	/**
	 * Recolors image so that each region is a random uniform color, so we can see where they are
	 * @param image
	 */
	public void recolorRegions(BufferedImage image) {
		// YOUR CODE HERE
	}
}
