package beehive;

import java.awt.Color;
import java.util.Random;

public class NectarPlant extends Plant implements INectarable {
	//	NectarPlants produce nectar for Bees to collect
	
	private int storage;
	private int storageMax;
	private double nectarRate;
	private Color c = Color.GREEN;
	private int howManyTargets;
	private ObjectType type = ObjectType.NECTAR_PLANT;
	private int size = 3;
	
	public NectarPlant(Board myBoard, PlantManager myManager, Coordinates myPosition, double nectarRate, int storage)
	{
		super(myBoard,myManager,myPosition);
		this.nectarRate = nectarRate;
		this.storage = storage;
		this.storageMax = 500;
		this.howManyTargets = 1;
	}
	
	public void Grow() {
		//	Plant grows and replenishes nectar
		age += 1;
		if (age > ageMax) Die();
		UpdateNectar();
	}
	
	public void UpdateNectar()
	{
		//	Replenishes nectar
		storage += 10*nectarRate;
		if (storage > storageMax) storage = storageMax;
	}
	
	@Override
	public void AddNectar(int amount) {
		// Adds nectar by amount
		this.storage += amount;
		if (storage > storageMax) storage = storageMax;
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
		age -= 0.5;	//	Plants visited by bees live longer
		return amount;
	}
	
//	Getters, Setters
	public void setNectarRate(int rate)
	{
		this.nectarRate = rate;
	}
	
	public Coordinates getPosition() {return myPosition;}
	
	public int getStorage() {return storage;}
	public void setStorage(int storage)
	{
		if (storage < 0) storage = 0;
		this.storage = storage;
	}
	
	public int getStorageMax() {return storageMax;}

	@Override
	public Coordinates getCoordinates() {return myPosition;}

	@Override
	public Board getBoard() {return myBoard;}

	@Override
	public Color getColor() {return c;}

	public int getHowManyTargets() {
		//	Shows how many bees already target this plant
		return howManyTargets;
	}
	
	public void addTarget()
	{
		//	Adds to how many bees target this plant
		howManyTargets++;
	}
	public void removeTarget()
	{
		howManyTargets--;
	}

	@Override
	public ObjectType getObjectType() {return type;}

	@Override
	public int getSize() {
		return size;
	}

}
