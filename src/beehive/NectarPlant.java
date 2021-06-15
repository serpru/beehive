package beehive;

import java.awt.Color;
import java.util.Random;

/**
 * Nectar plants grow and produce nectar.
 * @author Przemyslaw Karcz
 *
 */
public class NectarPlant extends Plant implements INectarable {
	//	NectarPlants produce nectar for Bees to collect
	
	/**
	 * Current nectar storage.
	 */
	private int storage;
	/**
	 * Maximum nectar storage capacity.
	 */
	private int storageMax;
	/**
	 * How much nectar gets generated.
	 */
	private double nectarRate;
	/**
	 * Color to display in GUI.
	 */
	private Color c = Color.GREEN;
	/**
	 * How many objects are targetting this plant.
	 */
	private int howManyTargets;
	/**
	 * Plant object type.
	 */
	private ObjectType type = ObjectType.NECTAR_PLANT;
	/**
	 * Size to display in GUI.
	 */
	private int size = 3;
	
	/**
	 * Creates NectarPlant object.
	 * @param myBoard Board plant lives on.
	 * @param myManager PlantManager plant belongs to.
	 * @param myPosition Current position on Board.
	 * @param nectarRate How much nectar gets generated.
	 * @param storage Current storage.
	 */
	public NectarPlant(Board myBoard, PlantManager myManager, Coordinates myPosition, double nectarRate, int storage)
	{
		super(myBoard,myManager,myPosition);
		this.nectarRate = nectarRate;
		this.storage = storage;
		this.storageMax = 500;
		this.howManyTargets = 1;
	}
	
	/**
	 * Plant grows and produces nectar.
	 */
	public void Grow() {
		//	Plant grows and replenishes nectar
		age += 1;
		if (age > ageMax) Die();
		UpdateNectar();
	}
	
	/**
	 * Produce nectar,
	 */
	private void UpdateNectar()
	{
		//	Replenishes nectar
		storage += 10*nectarRate;
		if (storage > storageMax) storage = storageMax;
	}
	
	/**
	 * Adds nectar by amount.
	 */
	@Override
	public void AddNectar(int amount) {
		// Adds nectar by amount
		this.storage += amount;
		if (storage > storageMax) storage = storageMax;
	}

	/**
	 * Removes nectar by amount. If less is in storage, remove as much as it can.
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
		age -= 0.5;	//	Plants visited by bees live longer
		return amount;
	}
	
	/**
	 * Increases the count of how many object target this plant.
	 */
	public void addTarget()
	{
		//	Adds to how many bees target this plant
		howManyTargets++;
	}
	
	/**
	 * Decreases the count of how many object target this plant.
	 */
	public void removeTarget()
	{
		howManyTargets--;
	}

	//	Getters, Setters
	/**
	 * Sets the rate of how much nectar is generated.
	 * @param rate New rate to be set.
	 */
	public void setNectarRate(int rate)
	{
		this.nectarRate = rate;
	}
	
	/**
	 * Gets current nectar storage.
	 * @return Returns plant's storage.
	 */
	public int getStorage() {return storage;}
	/**
	 * Sets current storage.
	 * @param storage Amount of nectar to be in storage.
	 */
	public void setStorage(int storage)
	{
		if (storage < 0) storage = 0;
		this.storage = storage;
	}
	
	/** Gets total nectar storage capacity.
	 * @return Returns storage capacity.
	 */
	public int getStorageMax() {return storageMax;}

	/**
	 * Gets current position.
	 */
	@Override
	public Coordinates getCoordinates() {return myPosition;}

	/**
	 * Gets the Board plant lives on.
	 */
	@Override
	public Board getBoard() {return myBoard;}

	/**
	 * Gets plant's color.
	 */
	@Override
	public Color getColor() {return c;}

	/**
	 * Gets how many objects already target this plant.
	 * @return Returns number of objects targetting this plant.
	 */
	public int getHowManyTargets() {
		//	Shows how many bees already target this plant
		return howManyTargets;
	}
	


	/**
	 * Gets plant's object type.
	 */
	@Override
	public ObjectType getObjectType() {return type;}

	/**
	 * Gets plant's size to be displayed in GUI.
	 */
	@Override
	public int getSize() {
		return size;
	}

}
