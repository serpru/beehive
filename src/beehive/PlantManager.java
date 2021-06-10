package beehive;

import java.util.ArrayList;
import java.util.Random;

public class PlantManager {
	//	Manages all the plants in the simulation.
	
	private ArrayList<Plant> plants;
	private Random random;
	private double newPlantProgress;
	private double newPlantRate;
	private double newPlantRatio;
	private Board myBoard;
	private double nectarRate;
	private String status = "PlantManager: all is good!";
	
	
	//	Stats
	private int totalPlants;
	
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

	public void AddPlant(Plant p)
	{
		plants.add(p);
		this.totalPlants += 1;
	}
	
	public void RemovePlant(Plant p)
	{
		plants.remove(p);
	}
	
	public ArrayList<Plant> getPlants() {return plants;}

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
	
	private void CalculatePlantRate()
	{
		newPlantRatio = 10*newPlantRate*newPlantRate/plants.size();
	}

	public String getStatus() {return status;}
	
	public int getTotalPlants() {return totalPlants;}
}
