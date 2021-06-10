package beehive;

import java.util.Random;

public abstract class Bee {
	//	Every bee inherits from this class

	protected Hive myHive;
	protected double hunger;
	protected double hungerMax;
	protected double hungerThreshold;
	protected double hungerMultiplier;
	protected int age;
	protected int ageMax;
	
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
	
	public abstract void Work();
	protected abstract void Eat();
	protected void Age()
	{
		age++;
		if (age > ageMax) Die();
	}
	protected abstract void Die();
	
}
