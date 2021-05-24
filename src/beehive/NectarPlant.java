package beehive;

public class NectarPlant extends Plant {
	private int storage;
	private int storageMax;
	private int nectarRate;
	
	public NectarPlant(Board myBoard, PlantManager myManager, Coordinates myPosition, int nectarRate)
	{
		super(myBoard,myManager,myPosition);
		this.nectarRate = nectarRate;
	}
	
	public void Grow() {
		System.out.println("Plant growing");
		UpdateNectar();
	}
	
	public void UpdateNectar()
	{
		storage += nectarRate;
		if (storage > storageMax) storage = storageMax;
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
}
