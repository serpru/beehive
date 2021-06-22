package beehive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Simulation manages entire simulation process.
 * 
 *
 */
public class Simulation {
	/**
	 * How many iterations passed.
	 */
	private int count;
	/**
	 * Total number of iterations to go through.
	 */
	private int iterations;
	/**
	 * Board to put simulation objects on.
	 */
	private Board board;
	/**
	 * Status message.
	 */
	private String info;
	
	/**
	 * Plant manager to manage plants.
	 */
	private PlantManager plantM;
	/**
	 * Hive to manage bees.
	 */
	private Hive hive;
	
	/**
	 * Creates Simulation object.
	 * @param iterations Iterations to simulate.
	 * @param startingBees Number of bees to start the simulation with.
	 * @param startingPlants Number of plants to start the simulation with.
	 * @param speedMult How fast the bees move on the Board.
	 * @param hungerMult How fast the bees get hungry.
	 * @param newPlantRate How fast plants reproduce.
	 * @param nectarRate How fast plants generate nectar.
	 */
	public Simulation(int iterations, int startingBees, int startingPlants, double speedMult, double hungerMult, double newPlantRate, double nectarRate)
	{
		this.iterations = iterations;
		count = 0;
		board = new Board(new Coordinates(499,499));
		info = "Ready to start!";
		plantM = new PlantManager(board, newPlantRate, nectarRate, startingPlants);
		hive = new Hive(board, new Coordinates(250,250), 1000, startingBees, hungerMult, speedMult);
	}
	
	/**
	 * Next iteration.
	 * @return Returns false if end condition is met: no more bees, no more plants, reached all iterations.
	 */
	public boolean Next()
	{
		//	Next step/frame/iteration in the simulation. All objects are told to DoStuff
		//	Returns false if any object doesn't finish their job
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
	
	/**
	 * Writes the end status message.
	 * @return Returns true if simulation stopped.
	 */
	public boolean Stop()
	{
		//	Ends simulation. Writes message with info how simulation ended (success, failure)
		if (count == iterations) info = "Simulation ended succesfully!";
		return true;
	}
	
	//	Getters, setters
	/**
	 * Gets current iteration number.
	 * @return Returns count representing current iteration number.
	 */
	public int getCount() {return count;}
	/**
	 * Gets number of iterations simulation has to simulate.
	 * @return Returns total number of iterations simulation has to simulate.
	 */
	public int getIterations() {return iterations;}
	/**
	 * Gets the board object the simulation takes place on.
	 * @return Returns the board object all simulation objects live on.
	 */
	public Board getBoard() {return board;}
	/**
	 * Gets status message.
	 * @return Returns status message of simulation object.
	 */
	public String getInfo() {return info;}
	/**
	 * Gets simulation's Hive object.
	 * @return Returns Hive object.
	 */
	public Hive getHive() {return hive;}
	/**
	 * Gets simulation's PlantManager object.
	 * @return Returns PlantManager object.
	 */
	public PlantManager getPlantManager() {return plantM;}
}
