package beehive;

import java.awt.Color;

public class UselessPlant extends Plant{
	//	UselessPlant doesn't produce nectar, so the Bees aren't going to visit them
	
	private Color c = Color.RED;
	private ObjectType type = ObjectType.USELESS_PLANT;
	private int size = 3;

	public UselessPlant(Board myBoard, PlantManager myManager, Coordinates myPosition) {
		super(myBoard, myManager, myPosition);
	}
	
	@Override
	public void Grow() {
		age += 1;
		if (age > ageMax) Die();
	}
	
	@Override
	public Coordinates getCoordinates() {
		return myPosition;
	}

	@Override
	public Board getBoard() {
		return myBoard;
	}

	@Override
	public Color getColor() {
		return c;
	}

	@Override
	public ObjectType getObjectType() {return type;}

	@Override
	public int getSize() {
		return size;
	}

}
