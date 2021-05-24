package beehive;

public class Program {

	public static void main(String[] args) {
		System.out.println("Hello, world!");
		
		//	Initialize simulation
		int it = 4;
		Simulation sim = new Simulation(it);
		
		sim.Start();
		
		//	Loop for it-iterations or simulation failure
		for (int i = 0; i < it; i++)
		{
			System.out.print("### Iteration " + (sim.getCount()+1) + " ###\n");
			if(!sim.Next())
			{
				sim.Stop();
				break;
			}
		}
		
		//	Save data and close
		sim.Stop();

	}

}
