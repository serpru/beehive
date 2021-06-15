package beehive;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Hive is a colony/manager for bees living in it. It feeds bees nectar the bees collect.
 * @author Serafin Prusik
 *
 */
public class Hive implements IBoardable, INectarable {
	//	Hive is a colony for Bees.

	/**
	 * Board hive lives on.
	 */
	private Board myBoard;
	/**
	 * Position of hive on the Board.
	 */
	private Coordinates position;
	/**
	 * List of bees living in the hive.
	 */
	private ArrayList<Bee> myBees;
	/**
	 * Current nectar storage.
	 */
	private int storage;
	/**
	 * Maximum nectar storage capacity.
	 */
	private int storageMax;
	/**
	 * Progress to the next bee.
	 */
	private float newBeeProgress;
	/**
	 * Rate of how fast new bees are born.
	 */
	private float newBeeRate;
	/**
	 * Color of the hive to be displayed in GUI.
	 */
	private Color c = Color.MAGENTA;
	/**
	 * Random variable for calculating random events.
	 */
	private Random random;
	/**
	 * How fast the bees get hungry.
	 */
	private double hungerMultiplier;
	/**
	 * Type of object on the Board.
	 */
	private ObjectType type = ObjectType.HIVE;
	/**
	 * Size of hive to be displayed in GUI.
	 */
	private int size = 5;
	/**
	 * How fast the bees move on the Board.
	 */
	private double speedMultiplier;
	/**
	 * Status message.
	 */
	private String status = "Hive: all is good!";
	
	//	Stats
	/**
	 * Total nectar gathered by the bees.
	 */
	private int totalStorage = 0;
	/**
	 * Total sum of nectar in the storage through every iteration.
	 */
	private int totalStorageSum = 0;
	/**
	 * Total number of bees that lived in the hive.
	 */
	private int totalBees = 0;
	
	/**
	 * Creates Hive object.
	 * @param board Board the hive will be on.
	 * @param position Position on the Board.
	 * @param storageMax Maximum nectar capacity.
	 * @param startingBees Number of bees living in the hive at the start.
	 * @param hungerMult How fast the bees get hungry.
	 * @param speedMult How fast the bees move on the Board.
	 */
	public Hive(Board board, Coordinates position, int storageMax, int startingBees, double hungerMult, double speedMult)
	{
		random = new Random();
		this.myBoard = board;
		myBoard.Add(this);
		this.position = new Coordinates(position.getX(),position.getY());
		this.myBees = new ArrayList<Bee>();
		this.storageMax = storageMax;
		this.storage = 0;
		this.storage = (int) (storageMax * 0.2);
		this.totalStorage = storage;
		this.newBeeProgress = 0;
		this.newBeeRate = 1;
		this.hungerMultiplier = hungerMult;
		this.speedMultiplier = speedMult;
		
		//	Hive residents are 1/2 WorkerBees and 1/2 NectarBees
		for (int i = 0; i < startingBees/2; i++)
		{
			double speed = CalculateBeeSpeed();
			AddBee(new NectarBee(this,0,hungerMult,board,new Coordinates(this.getCoordinates().getX(),this.getCoordinates().getY()),speed));
			AddBee(new WorkerBee(this,0,hungerMult));
		}
		
		this.totalBees = startingBees;
	}
	
	//	Getters, setters
	/**
	 * Gets the hive's position.
	 */
	@Override
	public Coordinates getCoordinates() {return position;}
	
	/**
	 * Gets the hive's bee list.
	 * @return Returns hive's list of bees.
	 */
	public ArrayList<Bee> getMyBees() {return myBees;}

	/**
	 * Gets the board.
	 */
	@Override
	public Board getBoard() {return myBoard;}

	/**
	 * Gets the color for GUI displaying purpose.
	 */
	@Override
	public Color getColor() {return c;}
	
	/**
	 * Gets field of Random class
	 * @return Returns the Random class variable.
	 */
	public Random getRandom() {return random;}
	
	/**
	 * Gets status of the hive.
	 * @return Returns String message with hive's status, for example "Hive: no more bees!".
	 */
	public String getStatus() {return status;}
	
	/**
	 * Gets total number of bees that lived in the hive throughout simulation.
	 * @return Returns total number of bees that lived.
	 */
	public int getTotalBees() {return totalBees;}
	
	
	/**
	 * Gets number of nectar bees living in the hive.
	 * @return Returns int number of nectar bees living currently in the hive.
	 */
	public int getNectarBees() {
		int x = 0;
		for (int i = 0; i < myBees.size(); i++)
		{
			if (myBees.get(i) instanceof NectarBee) x++;
		}
		return x;
	}

	/**
	 * Gets current storage of hive.
	 * @return Returns int number of current nectar storage in the hive.
	 */
	public int getStorage(){return storage;}
	
	/**
	 * Gets max nectar that can be stored in the hive.
	 * @return Returns int number of storage capacity in the hive.
	 */
	public int getStorageMax(){return storageMax;}
	
	/**
	 * Gets hive's object type.
	 */
	@Override
	public ObjectType getObjectType() {return type;}

	/**
	 * Gets size of hive for GUI displaying purpose.
	 */
	@Override
	public int getSize() {return size;}
	
	/**
	 * Gets total nectar gathered by the bees.
	 * @return Returns the int number of total nectar gathered by bees.
	 */
	public int getTotalGathered(){return this.totalStorage;}
	
	/**
	 * Get total sum of nectar that has been stored in the hive throughout the simulation.
	 * @return Returns the int number of total nectar stored in the hive.
	 */
	public int getTotalStorage(){return this.totalStorageSum;}
	
	//	Other methods
	/**
	 * Hive works, managing its bees and delegating them to work.
	 * @return Returns false if there are no more bees in the hive.
	 */
	public boolean DoStuff() {
		// Hive commands its bees to do their work
		if (myBees.size() == 0) 
		{
			status = "Hive: no more bees!";
			return false;
		}
		
		totalBees += myBees.size();
		totalStorageSum += storage;
		
		//	Random accidents happen, killing bees in the process
		if (myBees.size() > 10)
		{
			int randomEvent = getRandom().nextInt(101);
			int randomBee;
			if (randomEvent < 2) 
			{
				for (int i = 0; i < 3; i++)
				{
					int size = myBees.size();
					randomBee = getRandom().nextInt(size);
					myBees.get(randomBee).Die();
				}
			}
		}

		
		//	All bees are commanded to work
		for (int i = 0; i < myBees.size(); i++)
		{
			myBees.get(i).Work();
		}
		newBeeRate = CalculateBeeRate();
		UpdateNewBeeProgress();
		return true;
	}
	
	/**
	 * Adds bee to the hive's bee population.
	 * @param bee bee to add to the population.
	 */
	public void AddBee(Bee bee)
	{
		myBees.add(bee);
		this.totalBees += 1;
	}
	
	/**
	 * Removes bee from the hive's bee population.
	 * @param bee Bee to be removed from population.
	 */
	public void RemoveBee(Bee bee)
	{
		myBees.remove(bee);
	}
	
	/**
	 * Adds nectar to hive by amount.
	 */
	@Override
	public void AddNectar(int amount) {
		// Adds nectar by amount
		int t = storage + amount;
		if (t > storageMax)
		{
			int r = t - storageMax;
			this.totalStorage = this.totalStorage + amount - r;
			storage = storageMax;
		}
		else
		{
			storage += amount;
			this.totalStorage += amount;
		}
	}

	/**
	 * Removes nectar from hive by amount.
	 */
	@Override
	public int RemoveNectar(int amount) {
		// Removes nectar by amount or less if not enough in storage
		if (storage < amount) 
		{
			int realAmount = storage;
			storage = 0;
			return realAmount;
		}
		storage -= amount;
		return amount;
	}
	
	/**
	 * Calculates bee's speed.
	 * @return Returns bee's speed.
	 */
	private double CalculateBeeSpeed()
	{
		//	Speed of each bee is slightly different
		double speed = 20*speedMultiplier*(0.9+random.nextDouble()/3);
		return speed;
	}
	
	/**
	 * Updates progress for the new bee to be born.
	 */
	private void UpdateNewBeeProgress()
	{
		//	Updates whether or not it's time for new bee to be born
		newBeeProgress += newBeeRate;
		if (newBeeProgress >= 100 ) 
		{
			int workerBees = 1;
			for (int i = 0; i < myBees.size(); i++)
			{
				if (myBees.get(i) instanceof WorkerBee) workerBees++;
			}
			if (myBees.size()/workerBees < 2) 
			{
				double speed = CalculateBeeSpeed();
				AddBee(new NectarBee(this,0,hungerMultiplier,myBoard,new Coordinates(getCoordinates().getX(),getCoordinates().getY()),speed));
			}
			else AddBee(new WorkerBee(this,0,hungerMultiplier));
			newBeeProgress = 0;
		}
	}
	
	/**
	 * Calculates reproduction rate of bees.
	 * @return Returns the float number being the rate of bees' reproduction.
	 */
	private float CalculateBeeRate()
	{
		//	Calculates how fast new bees should be made
		int size = myBees.size();
		if (size == 0) size = 1;
		float rate = storage*6 / size;
		return rate;
	}


}
