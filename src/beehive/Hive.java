package beehive;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Hive implements IBoardable, INectarable {
	//	Hive is a colony for Bees.

	private Board myBoard;
	private Coordinates position;
	private ArrayList<Bee> myBees;
	private int storage;
	private int storageMax;
	private float newBeeProgress;
	private float newBeeRate;
	private Color c = Color.MAGENTA;
	private Random random;
	private double hungerMultiplier;
	private ObjectType type = ObjectType.HIVE;
	private int size = 5;
	private double speedMultiplier;
	private String status = "Hive: all is good!";
	
	//	Stats
	private int totalStorage = 0;
	private int totalStorageSum = 0;
	private int totalBees = 0;
	
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
	@Override
	public Coordinates getCoordinates() {return position;}
	
	public ArrayList<Bee> getMyBees() {return myBees;}

	@Override
	public Board getBoard() {return myBoard;}

	@Override
	public Color getColor() {return c;}
	
	public Random getRandom() {return random;}
	
	public String getStatus() {return status;}
	
	public int getTotalBees() {return totalBees;}
	
	
	public int getNectarBees() {
		int x = 0;
		for (int i = 0; i < myBees.size(); i++)
		{
			if (myBees.get(i) instanceof NectarBee) x++;
		}
		return x;
	}

	public int getStorage(){return storage;}
	
	public int getStorageMax(){return storageMax;}
	
	@Override
	public ObjectType getObjectType() {return type;}

	@Override
	public int getSize() {return size;}
	
	public int getTotalGathered(){return this.totalStorage;}
	
	public int getTotalStorage(){return this.totalStorageSum;}
	
	//	Other methods
	public boolean DoStuff() {
		// Hive commands its bees to do their work
		if (myBees.size() == 0) 
		{
			status = "Hive: no more bees!";
			return false;
		}
		
		totalBees += myBees.size();
		totalStorageSum += storage;
		
		System.out.println("Bee rate: "+newBeeRate);
		
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
	
	public void AddBee(Bee bee)
	{
		myBees.add(bee);
		this.totalBees += 1;
	}
	
	public void RemoveBee(Bee bee)
	{
		myBees.remove(bee);
	}
	
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
	
	private double CalculateBeeSpeed()
	{
		//	Speed of each bee is slightly different
		double speed = 20*speedMultiplier*(0.9+random.nextDouble()/3);
		return speed;
	}
	
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
	
	private float CalculateBeeRate()
	{
		//	Calculates how fast new bees should be made
		int size = myBees.size();
		if (size == 0) size = 1;
		float rate = storage*6 / size;
		return rate;
	}


}
