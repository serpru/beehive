package beehive;

import java.util.concurrent.TimeUnit;

public class Program {

	public static void main(String[] args) {
		//	Initialize simulation
		//	This will be changed later to let user enter simulation configuration
		int it = 500;
		int startingBees = 2;
		int startingPlants = 10;
		double speedMultiplier = 1;
		double hungerMultiplier = 0.5;
		double newPlantRate = 4;
		double nectarRate = 0.1;
		int speedMs = 50;

		Simulation sim = new Simulation(
			it, 
			startingBees, 
			startingPlants, 
			speedMultiplier, 
			hungerMultiplier, 
			newPlantRate, 
			nectarRate);
		
		sim.Start();
		
		//	Initialize GUI
		GuiManager guiM = new GuiManager(sim.getBoard());
		guiM.Go();
		
		//	Loop for it-iterations or simulation failure
		for (int i = 0; i < it; i++)
		{
			String text = String.valueOf(sim.getCount());
			guiM.iterationText.setText("Iterarion "+text);
			guiM.plantLabel.setText("Plants: "+Integer.toString(sim.plantM.getPlants().size()));
			guiM.hiveLabel.setText("Hive storage: "+Integer.toString(sim.hive.getStorage())+"/"+sim.hive.getStorageMax());
			guiM.statusMessage.setText(sim.getInfo());
			
			String bees = "Bees: "+Integer.toString(sim.hive.getMyBees().size())+" | Nectar Bees: "+sim.hive.getNectarBees();
			
			guiM.beeLabel.setText(bees);
			System.out.print("### Iteration " + (sim.getCount()+1) + " ###\n");
			if(!sim.Next())
			{
				break;
			}
			guiM.Draw();
			
			//	Artificial way to slow down the program so the user can see what's happening on the screen
			try {
				TimeUnit.MILLISECONDS.sleep(speedMs);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//	Save data and close
		sim.Stop();
		guiM.statusMessage.setText(sim.getInfo());

	}

}
