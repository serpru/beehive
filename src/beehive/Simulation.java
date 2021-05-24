package beehive;

import java.util.ArrayList;

public class Simulation {
	ArrayList<ISimObject> objects;
	int count;
	int iterations;
	
	public Simulation(int iterations)
	{
		this.iterations = iterations;
		count = 0;
	}
	
	public boolean Start()
	{
		System.out.println("Simulation.Start()");
		Board board = new Board(new Coordinates(499,499));
		objects = new ArrayList<ISimObject>();
		
		PlantManager plantM = new PlantManager();
		plantM.AddPlant(new NectarPlant(board,plantM,new Coordinates(100,100), 1));
		plantM.AddPlant(new NectarPlant(board,plantM,new Coordinates(400,400), 1));
		
		objects.add(plantM);
		
		return true;
	}
	
	public boolean Next()
	{
		System.out.println("Simulation.Next()");
		count++;
		for (int i = 0; i < objects.size(); i++)
		{
			if(!objects.get(i).DoStuff()) return false;
		}
		return true;
	}
	
	public boolean Stop()
	{
		System.out.println("\nSimulation.Stop()");
		return true;
	}
	
//	Getters, setters
	public int getCount() {return count;}
}
