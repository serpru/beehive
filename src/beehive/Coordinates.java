package beehive;

/**
 * Coordinates in 2D space.
 * 
 *
 */
public class Coordinates {
	//	Coordinates x and y for 2D interaction.
	
	/**
	 * X coordinate.
	 */
	private int x;
	/**
	 * Y coordinate.
	 */
	private int y;
	
	/**
	 * Creates Coordinates object.
	 * @param x X position.
	 * @param y Y position.
	 */
	public Coordinates(int x, int y)
	{
		setX(x);
		// addads
		setY(y);
	}
	
	/**
	 * Gets object's X coordinate.
	 * @return Returns X position.
	 */
	public int getX() { return x;}
	/**
	 * Sets object's X coordinate.
	 * @param x X position to be set.
	 */
	public void setX(double x)
	{
		if (x-(int)x>= 0.5) this.x = (int)x+1;
		else this.x = (int)x;
	}
	
	/**
	 * Gets object's Y coordinate.
	 * @return Retunrs Y position.
	 */
	public int getY() { return y;}
	/**
	 * Sets object's Y coordinate.
	 * @param y Y position to be set.
	 */
	public void setY(double y)
	{
		if (y-(int)y >= 0.5) this.y = (int)y+1;
		else this.y = (int)y;
	}
	
	/**
	 * Compares own coordinates to provided ones.
	 * @param coords Coordinates to compare with.
	 * @return Returns true if argument coordinates are the same position as own.
	 */
	public boolean Equals(Coordinates coords)
	{
		if (this.getX() == coords.getX() || this.getY() == coords.getY()) return true;
		return false;
	}

}
