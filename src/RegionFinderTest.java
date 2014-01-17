import java.awt.*;
import javax.swing.*;

/**
 * Testing code for PS-1 RegionFinder
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Winter 2014
 */
public class RegionFinderTest extends DrawingFrame {
	/**
	 * Test your RegionFinder by passing an image filename and a color to find.
	 * @param filename
	 * @param color
	 */
	public RegionFinderTest(String filename, Color color) {
		super("Region finder test", filename);
		System.out.println("put up the picture");
		
		// Do the region finding and recolor the image.
		RegionFinder finder = new RegionFinder();
		System.out.println("created the region finder");
		finder.findRegions(image, color);
		finder.recolorRegions(image);
	}
	
	/**
	 * Main method for the application
	 * @param args		command-line arguments (ignored)
	 */
	public static void main(String[] args) { 
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new RegionFinderTest("pictures/smiley.png", new Color(0, 0, 0));
				new RegionFinderTest("pictures/baker.jpg", new Color(130, 100, 100));
			}
		});
	}
	
}
