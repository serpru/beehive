package beehive;

public class Coordinates {
	//	Coordinates x and y for 2D interaction.
	
	private int x;
	private int y;
	
	public Coordinates(int x, int y)
	{
		setX(x);
		setY(y);
	}
	
	public int getX() { return x;}
	public void setX(double x)
	{
		if (x-(int)x>= 0.5) this.x = (int)x+1;
		else this.x = (int)x;
	}
	
	public int getY() { return y;}
	public void setY(double y)
	{
		if (y-(int)y >= 0.5) this.y = (int)y+1;
		else this.y = (int)y;
	}
	
	public boolean Equals(Coordinates coords)
	{
		if (this.getX() == coords.getX() || this.getY() == coords.getY()) return true;
		return false;
	}

}
