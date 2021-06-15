package beehive;

/**
 * Worker bees work inside the hive, repairing it.
 * @author Przemyslaw Karcz
 *
 */
public class WorkerBee extends Bee{
	//	WorkerBees work inside the Hive

	/**
	 * Creates WorkerBee object.
	 * @param hive Hive bee belongs to.
	 * @param hunger Current hunger.
	 * @param hungerMult How quickly bee gets hungry.
	 */
	public WorkerBee(Hive hive, double hunger, double hungerMult) {
		super(hive, hunger, hungerMult);
	}

	/**
	 * Worker bee works in the hive.
	 */
	@Override
	public void Work() {
		// Bee uses nectar to work in the Hive
		
		Age();
		hunger += 3*hungerMultiplier;
		if (hunger >= hungerThreshold) Eat();
		if (hunger > hungerMax) Die();
			
		myHive.RemoveNectar(1);
	}

	/**
	 * Worker bee eats nectar from hive.
	 */
	@Override
	protected void Eat() {
		//	Bee eats nectar from Hive
		hunger -= myHive.RemoveNectar((int)(hungerThreshold*0.8));
	}

	/**
	 * Worker bee gets older.
	 */
	@Override
	protected void Age() {
		// Bee ages
		age += 3;
		if (age >= ageMax) Die();
	}

	/**
	 * Worker bee dies.
	 */
	@Override
	protected void Die() {
		// Bee dies
		myHive.RemoveBee(this);
	}

}
