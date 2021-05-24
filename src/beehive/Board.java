package beehive;

import java.util.ArrayList;

public class Board {
	ArrayList<IBoardable> boardObjects;
	private Coordinates maxSize;
	
	public Board(Coordinates maxSize)
	{
		this.maxSize = maxSize;
	}
	
	public void Add(IBoardable obj)
	{
		boardObjects.add(obj);
	}
	
	public void Remove(IBoardable obj)
	{
		boardObjects.remove(obj);
	}
}
