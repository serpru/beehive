package beehive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Simulation {
	ArrayList<ISimObject> objects;
	int count;
	int iterations;
	private Board board;
	private String info;
	
	PlantManager plantM;
	Hive hive;
	
	public Simulation(int iterations, int startingBees, int startingPlants, double speedMult, double hungerMult, double newPlantRate, double nectarRate)
	{
		this.iterations = iterations+1;
		count = 1;
		board = new Board(new Coordinates(499,499));
		info = "Ready to start!";
		plantM = new PlantManager(board, newPlantRate, nectarRate, startingPlants);
		hive = new Hive(board, new Coordinates(250,250), 1000, startingBees, hungerMult, speedMult);
	}
	
	public boolean Start()
	{
		//	Readies the simulation for start
		System.out.println("Simulation.Start()");
		
		return true;
	}
	
	public boolean Next()
	{
		//	Next step/frame/iteration in the simulation. All objects are told to DoStuff
		//	Returns false if any object doesn't finish their job
		
		
		//info = "";
		System.out.println("Simulation.Next()");
		count++;
		
		if (!plantM.DoStuff()) 
		{
			info = "Simulation ended prematurely: "+plantM.getStatus();
			return false;
		}
			
		if (!hive.DoStuff()) 
		{
			info = "Simulation ended prematurely: "+hive.getStatus();
			return false;
		}
		info = "Simulation running";
		
		return true;
	}
	
	public boolean Stop()
	{
		//	Ends simulation. Writes message with info how simulation ended (success, failure)
		System.out.println("\nSimulation.Stop()");
		if (count == iterations) info = "Simulation ended succesfully!";
		return true;
	}
	
//	Getters, setters
	public int getCount() {return count;}
	public Board getBoard() {return board;}
	public String getInfo() {return info;}
}
