package beehive;

public class Program {

	public static void main(String[] args) {
		System.out.println("Hello, world!");
		
		int it = 4;
		Simulation sim = new Simulation(it);
		
		sim.Start();
		for (int i = 0; i < it; i++)
		{
			System.out.print("### Iteration " + (sim.getCount()+1) + " ###\n");
			if(!sim.Next())
			{
				sim.Stop();
				break;
			}
		}
		sim.Stop();

	}

}
