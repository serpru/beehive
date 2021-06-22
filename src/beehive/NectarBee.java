package beehive;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Nectar bees collect nectar to store in their hive.
 * 
 *
 */
public class NectarBee extends Bee implements IBoardable, INectarable{
	//	Collects nectar for Hive

	/**
	 * Board bee moves on.
	 */
	protected Board myBoard;
	/**
	 * Position on the Board.
	 */
	protected Coordinates position;
	/**
	 * Current nectar storage.
	 */
	protected int storage;
	/**
	 * Maximum nectar storage capacity.
	 */
	protected int storageMax;
	/**
	 * How fast bee moves on the Board.
	 */
	protected double speed;
	/**
	 * Current target to move to.
	 */
	protected IBoardable target;
	/**
	 * Color to display as in GUI.
	 */
	protected Color c = Color.YELLOW;
	/**
	 * Current objective.
	 */
	protected BeeState myState;
	/**
	 * Object type on the Board.
	 */
	private ObjectType type = ObjectType.NECTAR_BEE;
	/**
	 * Size to display in GUI.
	 */
	private int size = 3;
	
	/**
	 * Creates NectarBee object.
	 * @param hive Hive bee belongs to.
	 * @param hunger Current hunger.
	 * @param hungerMult How fast bee gets hungry.
	 * @param board Board bee moves on.
	 * @param position Position on the Board.
	 * @param speed How fast bee moves on the Board.
	 */
	public NectarBee(Hive hive, double hunger, double hungerMult, Board board, Coordinates position, double speed)
	{
		super(hive, hunger, hungerMult);
		this.myBoard = board;
		this.position = new Coordinates(position.getX(),position.getY());
		this.speed = speed;
		this.target = myHive;
		this.myState = BeeState.IDLE;
		this.storageMax = 100;
		this.storage = 0;
		myBoard.Add(this);
	}

	/**
	 * Bee works for the hive.
	 */
	@Override
	public void Work() {
		// NectarBee flies to the plants collecting nectar and bringing it back to the Hive
		
		Age();
		hunger += 2*hungerMultiplier;
		if (hunger >= hungerThreshold) Eat();
		if (hunger > hungerMax) Die();
		
		switch (myState)
		{
		case FLYING_TO_HIVE:
			//	NectarBee comes back to Hive with nectar to store
			Move(myHive);
			if (position.Equals(myHive.getCoordinates()))	{myState = BeeState.IN_HIVE;}
			break;
		case FLYING_TO_TARGET:
			//	Bee flies to the target. If there are no targets it goes back to the hive
			if (target == null)
			{
				myState = BeeState.FLYING_TO_HIVE;
				break;
			}
			Move(target);
			if (position.Equals(target.getCoordinates()))	{myState = BeeState.IN_TARGET;}
			break;
		case IN_HIVE:
			//	If NectarBee is in the Hive, store nectar and choose a NectarPlant target
			myHive.AddNectar(this.RemoveNectar(storage));
			target = FindClosestPlant(myBoard.getObjects());
			myState = BeeState.FLYING_TO_TARGET;
			break;
		case IN_TARGET:
			//	NectarBee takes nectar from the plant
			NectarPlant plant = (NectarPlant)target;
			int amount = 55;
			AddNectar(plant.RemoveNectar(amount));
			plant.removeTarget();
			myState = BeeState.FLYING_TO_HIVE;
			break;
		case IDLE:
			//	Default state when NectarBee is created
			target = FindClosestPlant(myBoard.getObjects());
			myState = BeeState.FLYING_TO_TARGET;
			break;
		}	
	}

	/**
	 * Eats nectar from hive.
	 */
	@Override
	protected void Eat() {
		// NectarBee eats from its storage, then from hive (how it gets hive's nectar across the map is its secret, shhhh!)
		if (storage > 0) hunger -= RemoveNectar((int)(hungerThreshold*0.8));
		else hunger -= myHive.RemoveNectar((int)(hungerThreshold*0.8));
	}

	/**
	 * Bee dies.
	 */
	@Override
	protected void Die() {
		//	NectarBee dies. Gets removed from Hive and Board to lose references
		myBoard.Remove(this);
		myHive.RemoveBee(this);
	}
	
	/**
	 * Moves to the target.
	 * @param t Target to move to.
	 * @return Returns false if the bee have not reached the target yet.
	 */
	protected boolean Move(IBoardable t)
	{
		//	Moves to the target in a straight line
		
		//	Calculates distance to the target in 2D space
		double xdist = t.getCoordinates().getX()-this.position.getX();
		double ydist = t.getCoordinates().getY()-this.position.getY();
		double xpow = Math.pow(xdist, 2);
		double ypow = Math.pow(ydist, 2);
		double distance = Math.sqrt(xpow+ypow);
		
		//	Prevent dividing by 0
		if (distance == 0)
		{
			return true;
		}
		
		//	To prevent overshooting the target
		if (distance < speed)
		{
			position.setX(t.getCoordinates().getX());
			position.setY(t.getCoordinates().getY());
			return true;
		}
		
		//	Normalizing vector and multiplying by speed to move in the target direction
		double xnorm = xdist/distance;
		double ynorm = ydist/distance;
		
		int x = position.getX();
		int y = position.getY();
		
		position.setX(x+xnorm*speed);
		position.setY(y+ynorm*speed);
		return false;
	}

	/**
	 * Adds nectar to storage by amount.
	 */
	@Override
	public void AddNectar(int amount) {
		// Adds nectar by amount
		this.storage += amount;
		if (storage > storageMax) storage = storageMax;
	}

	/**
	 * Removes nectar from storage by amount.
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
	 * Finds the plant with highest nectar-to-distance ratio.
	 * @param list List of objects on the Board.
	 * @return Returns NectarPlant most suited to fly to.
	 */
	public NectarPlant FindClosestPlant(ArrayList<IBoardable> list)
	{
		//	Finds the closest Plant by calculating vector distance and choosing the smallest
		NectarPlant plant = null;
		double highest = Double.NEGATIVE_INFINITY;
		double attractiveness;
		ArrayList<NectarPlant> plants = new ArrayList<NectarPlant>();
		
		//	Filters objects list to only contain NectarPlant
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).getObjectType() == ObjectType.NECTAR_PLANT)
			{
				NectarPlant p = (NectarPlant)list.get(i);
				plants.add(p);
			}
		}
		
		for (int i = 0; i < plants.size(); i++)
		{
			NectarPlant obj = plants.get(i);
			
			//	Calculating vector distance between Bee and Plant
			double xpow = Math.pow(obj.getCoordinates().getX()-this.position.getX(), 2);
			double ypow = Math.pow(obj.getCoordinates().getY()-this.position.getY(), 2);
			double distance = Math.sqrt(xpow+ypow);
			int plantTargets = obj.getHowManyTargets();
			
			attractiveness = obj.getStorage()/(2*plantTargets*plantTargets*distance);
			
			//	Picks the plant with the highest attractiveness factor to it
			if (attractiveness > highest) 
			{
				plant = obj;
				highest = attractiveness;
			}
		}
		if (plant != null) plant.addTarget();
		return plant;
	}

	//	Getters, setters
	/**
	 * Gets current position.
	 */
	@Override
	public Coordinates getCoordinates() {return position;}

	/**
	 * Gets the Board bee moves on.
	 */
	@Override
	public Board getBoard() {return myBoard;}

	/**
	 * Gets the color to display bee as in GUI.
	 */
	@Override
	public Color getColor() {return c;}
	
	/**
	 * Gets bee's object type.
	 */
	@Override
	public ObjectType getObjectType() {return type;}

	/**
	 * Gets bee's size to be displayed in GUI.
	 */
	@Override
	public int getSize() {return size;}
}
