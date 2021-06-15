package beehive;

import java.awt.Color;

/**
 * Every object present on Board must implement this interface.
 * @author Serafin Prusik
 *
 */
public interface IBoardable {
	//	Every object on the board must implement this
	/**
	 * Gets current position.
	 * @return Returns object's coordinates.
	 */
	public Coordinates getCoordinates();
	/**
	 * Gets the board object is present on.
	 * @return Returns board the object is present on.
	 */
	public Board getBoard();
	/**
	 * Gets object's color.
	 * @return Returns object's color.
	 */
	public Color getColor();
	/**
	 * Gets object's type.
	 * @return Returns object's type.
	 */
	public ObjectType getObjectType();
	/**
	 * Gets object's size to be displayed.
	 * @return Returns object's visual size.
	 */
	public int getSize();
}
