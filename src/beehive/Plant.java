package beehive;

public abstract class Plant {
	protected Board myBoard;
	protected PlantManager myManager;
	protected Coordinates myPosition;
	
	public Plant(Board myBoard, PlantManager myManager, Coordinates myPosition)
	{
		this.myBoard = myBoard;
		//myBoard.Add
		this.myManager = myManager;
		this.myPosition = myPosition;
	}
	
	public abstract void Grow();
}
