package beehive;

import java.util.Random;

/**
 * Abstract class for all bees to inherit from.
 * 
 *
 */
public abstract class Bee {
	//	Every bee inherits from this class

	/**
	 * Hive the bee lives in.
	 */
	protected Hive myHive;
	/**
	 * Current hunger.
	 */
	protected double hunger;
	/**
	 * Maximum hunger level.
	 */
	protected double hungerMax;
	/**
	 * After exceeding hunger threshold, bee looks for food.
	 */
	protected double hungerThreshold;
	/**
	 * How fast bee gets hungry.
	 */
	protected double hungerMultiplier;
	/**
	 * Current age.
	 */
	protected int age;
	/**
	 * Maximum age bee can live.
	 */
	protected int ageMax;
	
	/**
	 * Initialize constructor in subclass.
	 * @param hive Hive bee belongs to.
	 * @param hunger Current hunger.
	 * @param hungerMultiplier How fast bee gets hungry.
	 */
	public Bee(Hive hive, double hunger, double hungerMultiplier)
	{
		Random random = new Random();
		this.myHive = hive;
		this.hunger = myHive.getRandom().nextInt(16);
		this.hungerMax = 100;
		this.hungerThreshold = 60;
		this.hungerMultiplier = hungerMultiplier;
		this.age = 0;
		this.ageMax = 90+random.nextInt(41);
	}
	
	/**
	 * Bee gets to work.
	 */
	public abstract void Work();
	/**
	 * Bee eats nectar from its hive.
	 */
	protected abstract void Eat();
	/**
	 * Bee ages.
	 */
	protected void Age()
	{
		age++;
		if (age > ageMax) Die();
	}
	/**
	 * Bee dies.
	 */
	protected abstract void Die();
	
}
