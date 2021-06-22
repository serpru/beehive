package beehive;

import java.util.ArrayList;
import java.util.Random;

/**
 * Plant manager manages plants in the simulation.
 * 
 *
 */
public class PlantManager {
	//	Manages all the plants in the simulation.
	
	/**
	 * List of plants.
	 */
	private ArrayList<Plant> plants;
	/**
	 * Random variable for random events.
	 */
	private Random random;
	/**
	 * Progress for new plant to be created.
	 */
	private double newPlantProgress;
	/**
	 * New plant ratio's component.
	 */
	private double newPlantRate;
	/**
	 * How fast new plants get created.
	 */
	private double newPlantRatio;
	/**
	 * Board plants live on.
	 */
	private Board myBoard;
	/**
	 * How fast plants produce nectar.
	 */
	private double nectarRate;
	/**
	 * Status message.
	 */
	private String status = "PlantManager: all is good!";
	
	
	//	Stats
	/**
	 * Total number of plants that lived.
	 */
	private int totalPlants;
	
	/**
	 * Creates PlantManager object.
	 * @param myBoard Board to put plants on.
	 * @param newPlantRate How fast new plants get created.
	 * @param nectarRate How fast plants generate nectar.
	 * @param startingPlants How many plants PlantManager starts with.
	 */
	public PlantManager(Board myBoard, double newPlantRate, double nectarRate, int startingPlants)
	{
		this.myBoard = myBoard;
		plants = new ArrayList<Plant>();
		this.random = new Random();
		this.newPlantProgress = 0;
		this.newPlantRate = newPlantRate;
		this.nectarRate = nectarRate;
		this.totalPlants = 0;
		
		//	Plants start all as NectarPlants
		for (int i = 0; i < startingPlants; i++)
		{
			Coordinates coords = new Coordinates(random.nextInt(500),random.nextInt(500));
			AddPlant(new NectarPlant(myBoard, this, coords, nectarRate, 100));
		}
		this.totalPlants = startingPlants;
	}

	/**
	 * Adds plant to the list.
	 * @param p Plant to be added.
	 */
	public void AddPlant(Plant p)
	{
		plants.add(p);
		this.totalPlants += 1;
	}
	
	/**
	 * Removes plant from the list.
	 * @param p Plant to be removed.
	 */
	public void RemovePlant(Plant p)
	{
		plants.remove(p);
	}
	
	/**
	 * Gets list of all plants.
	 * @return Returns list of all plants.
	 */
	public ArrayList<Plant> getPlants() {return plants;}

	/**
	 * Plant manager manages plants and delegates them to grow.
	 * @return Returns false if there are no more plants.
	 */
	public boolean DoStuff() {
		//	PlantManager calls all its plants to do their thing
		
		if (plants.size() <= 0)
		{
			status = "PlantManager: no more plants!";
			return false;
		}
			
		UpdateNewPlantProgress();
		
		//	Plants randomly die because of diseases etc
		if (plants.size() > 4)
		{
			int randomEvent = random.nextInt(101);
			int randomPlant;
			if (randomEvent < 1) 
			{
				int size = plants.size();
				randomPlant = random.nextInt(size);
				plants.get(randomPlant).Die();
			}
		}
		
		for (int i = 0; i < plants.size(); i++)
		{
			plants.get(i).Grow();
		}
		return true;
	}
	
	/**
	 * Updates progress to new plant to be created.
	 */
	private void UpdateNewPlantProgress()
	{
		//	Calculates progress for new plant to be born
		CalculatePlantRate();
		this.newPlantProgress += 4*newPlantRatio;
		if (newPlantProgress >= 100 ) 
		{
			int nectarPlants = 0;
			int size = plants.size();
			double ratio;
			for (int i = 0; i < plants.size(); i++)
			{
				if (plants.get(i).getObjectType() == ObjectType.NECTAR_PLANT) nectarPlants+=1;
			}
			if (plants.size() <= 0) size = 1;
			ratio = (double)nectarPlants/(double)size;
			
			//	NectarPlants are majority of plant population
			if (ratio < 0.8)
			{
				Coordinates coords = new Coordinates(random.nextInt(500),random.nextInt(500));
				AddPlant(new NectarPlant(myBoard, this, coords, nectarRate, 100));
			}
			else
			{
				Coordinates coords = new Coordinates(random.nextInt(500),random.nextInt(500));
				AddPlant(new UselessPlant(myBoard, this, coords));
			}
			totalPlants++;
			newPlantProgress = 0;
		}
	}
	
	/**
	 * Calculates how fast new plants get created.
	 */
	private void CalculatePlantRate()
	{
		newPlantRatio = 10*newPlantRate*newPlantRate/plants.size();
	}

	/**
	 * Gets plant manager's status.
	 * @return Returns status.
	 */
	public String getStatus() {return status;}
	
	/**
	 * Gets number of all plants that lived.
	 * @return Returns number of total plants.
	 */
	public int getTotalPlants() {return totalPlants;}
}
