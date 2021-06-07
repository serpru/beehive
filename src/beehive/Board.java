package beehive;

import java.util.ArrayList;

public class Board {
	//	Playground for simulation.
	ArrayList<IBoardable> boardObjects;
	private Coordinates maxSize;
	
	public Board(Coordinates maxSize)
	{
		this.maxSize = maxSize;
		boardObjects = new ArrayList<IBoardable>();
	}
	
	public void Add(IBoardable obj)
	{
		boardObjects.add(obj);
	}
	
	public void Remove(IBoardable obj)
	{
		boardObjects.remove(obj);
	}
	
	
	//	Getters, setters
	
	public Coordinates getMaxSize() {return maxSize;}
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
