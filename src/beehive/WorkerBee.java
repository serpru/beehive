package beehive;

public class WorkerBee extends Bee{
	//	WorkerBees work inside the Hive

	public WorkerBee(Hive hive, double hunger, double hungerMult) {
		super(hive, hunger, hungerMult);
	}

	@Override
	public void Work() {
		// Bee uses nectar to work in the Hive
		
		Age();
		hunger += 3*hungerMultiplier;
		if (hunger >= hungerThreshold) Eat();
		if (hunger > hungerMax) Die();
			
		myHive.RemoveNectar(1);
	}

	@Override
	protected void Eat() {
		//	Bee eats nectar from Hive
		hunger -= myHive.RemoveNectar((int)(hungerThreshold*0.8));
	}

	@Override
	protected void Age() {
		// Bee ages
		age += 3;
		if (age >= ageMax) Die();
	}

	@Override
	protected void Die() {
		// Bee dies
		myHive.RemoveBee(this);
	}

}
