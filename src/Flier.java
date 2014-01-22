
/**
 * An animated object flying across the scene in a fixed direction
 * PS-1, Dartmouth CS 10, Winter 2014
 * Alexander Tsu & Joshua Wang
 */
public class Flier extends Agent {
	private int speedX; //speed/direction in x
	private int speedY; //speed/direction in y

	public Flier(int x, int y, int dx, int dy, int r) {
		super(x,y); 
		this.r = r;
		speedX = dx;
		speedY = dy;
	}
	
	public void move() {
		x += speedX; //adds speedX onto x
		y += speedY; //adds speedY onto y
	}
	
	public void setspeedX(int setX) { //method sets the new speedX, which allows a random speedX to be generated each time the flier is hit or miss
		speedX = setX;
	}
	
	public void setspeedY(int setY) { //same as setspeedX
		speedX = setY;
	}
}
