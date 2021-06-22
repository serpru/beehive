package beehive;

import java.util.ArrayList;

/**
 * Board acts as a map for objects to move on.
 * 
 *
 */
public class Board {
	//	Playground for simulation.
	/**
	 * List of all objects present on board.
	 */
	private ArrayList<IBoardable> boardObjects;
	/**
	 * Board's bounds.
	 */
	private Coordinates maxSize;
	
	/**
	 * Creates Board object.
	 * @param maxSize Bounds of the map.
	 */
	public Board(Coordinates maxSize)
	{
		this.maxSize = maxSize;
		boardObjects = new ArrayList<IBoardable>();
	}
	
	/**
	 * Adds object to the list of objects on the board.
	 * @param obj Object to be added.
	 */
	public void Add(IBoardable obj)
	{
		boardObjects.add(obj);
	}
	
	/**
	 * Removes object from the board.
	 * @param obj Object to be removed.
	 */
	public void Remove(IBoardable obj)
	{
		boardObjects.remove(obj);
	}
	
	
	//	Getters, setters
	
	/**
	 * Gets board's bounds.
	 * @return Returns board's bounds.
	 */
	public Coordinates getMaxSize() {return maxSize;}
	/**
	 * Gets board's list of objects.
	 * @return Returns a copy of the list of objects present on board.
	 */
	public ArrayList<IBoardable> getObjects() 
	{
		ArrayList<IBoardable> objects = new ArrayList<IBoardable>();
		for (int i = 0; i < boardObjects.size(); i++)
		{
			objects.add(boardObjects.get(i));
		}
		
		return objects;
	}
}
