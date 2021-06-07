package beehive;

public abstract class Plant implements IBoardable {
	//	Every plant inherits from this class.
	
	protected Board myBoard;
	protected PlantManager myManager;
	protected Coordinates myPosition;
	protected double age = 0;
	protected double ageMax = 200;
	
	public Plant(Board myBoard, PlantManager myManager, Coordinates myPosition)
	{
		this.myBoard = myBoard;
		myBoard.Add(this);
		this.myManager = myManager;
		this.myPosition = myPosition;
	}
	
	public abstract void Grow();
	public void Die()
	{
		this.myBoard.Remove(this);
		this.myManager.RemovePlant(this);
	}
}
