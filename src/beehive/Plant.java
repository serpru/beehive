package beehive;

/**
 * Every plant object inherits from this class.
 * @author Przemyslaw Karcz
 *
 */
public abstract class Plant implements IBoardable {
	//	Every plant inherits from this class.
	
	/**
	 * Board plant lives on.
	 */
	protected Board myBoard;
	/**
	 * PlantManager plant belongs to.
	 */
	protected PlantManager myManager;
	/**
	 * Current position.
	 */
	protected Coordinates myPosition;
	/**
	 * Current age.
	 */
	protected double age = 0;
	/**
	 * Maximum age.
	 */
	protected double ageMax = 200;
	
	/**
	 * Initialize it in subclass.
	 * @param myBoard Board plant lives on.
	 * @param myManager PlantManager plant belongs to.
	 * @param myPosition Current position.
	 */
	public Plant(Board myBoard, PlantManager myManager, Coordinates myPosition)
	{
		this.myBoard = myBoard;
		myBoard.Add(this);
		this.myManager = myManager;
		this.myPosition = myPosition;
	}
	
	/**
	 * Plant grows.
	 */
	public abstract void Grow();
	/**
	 * Plant dies.
	 */
	public void Die()
	{
		this.myBoard.Remove(this);
		this.myManager.RemovePlant(this);
	}
}
