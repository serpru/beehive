package beehive;

import java.awt.Color;

public interface IBoardable {
	//	Every object on the board must implement this
	public Coordinates getCoordinates();
	public Board getBoard();
	public Color getColor();
	public ObjectType getObjectType();
	public int getSize();
}
