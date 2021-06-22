package beehive;

import java.awt.Color;

/**
 * Useless plant grows, but does not produce nectar.
 * 
 *
 */
public class UselessPlant extends Plant{
	//	UselessPlant doesn't produce nectar, so the Bees aren't going to visit them
	
	/**
	 *  Useless plant's color.
	 */
	private Color c = Color.RED;
	/**
	 * Useless plant's type.
	 */
	private ObjectType type = ObjectType.USELESS_PLANT;
	/**
	 * Useless plant's size for GUI.
	 */
	private int size = 3;

	/**
	 * Creates UselessPlant object.
	 * @param myBoard Board useless plant is on.
	 * @param myManager PlantManager plant belongs to.
	 * @param myPosition Current position.
	 */
	public UselessPlant(Board myBoard, PlantManager myManager, Coordinates myPosition) {
		super(myBoard, myManager, myPosition);
	}
	
	/**
	 * Useless plant grows, getting older.
	 */
	@Override
	public void Grow() {
		age += 1;
		if (age > ageMax) Die();
	}
	
	/**
	 * Gets current position.
	 */
	@Override
	public Coordinates getCoordinates() {
		return myPosition;
	}

	/**
	 * Gets Board plant lives on.
	 */
	@Override
	public Board getBoard() {
		return myBoard;
	}

	/**
	 * Gets plant's color.
	 */
	@Override
	public Color getColor() {
		return c;
	}

	/**
	 * Gets plant's type.
	 */
	@Override
	public ObjectType getObjectType() {return type;}

	/**
	 * Gets plant's size.
	 */
	@Override
	public int getSize() {
		return size;
	}

}
