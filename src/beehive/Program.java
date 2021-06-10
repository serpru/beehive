package beehive;

import java.util.concurrent.TimeUnit;

public class Program {

	public static void main(String[] args) {
		//	Initialize simulation
		//	This will be changed later to let user enter simulation configuration
		
		// TU ZROBIMY ¯EBY Z PLIKU PROGRAM POBIERAL PARAMETRY STARTOWE
		int it = 500;
		int startingBees = 2;
		int startingPlants = 10;
		double speedMultiplier = 1;
		double hungerMultiplier = 0.3;
		double newPlantRate = 4;
		double nectarRate = 0.3;
		int speedMs = 10;
		
		//	TU STWORZYMY OBIEKT KLASY LOGGER
		//	Logger logger = new Logger()

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
			guiM.plantLabel.setText("Plants: "+Integer.toString(sim.getPlantManager().getPlants().size()));
			guiM.hiveLabel.setText("Hive storage: "+Integer.toString(sim.getHive().getStorage())+"/"+sim.getHive().getStorageMax());
			guiM.statusMessage.setText(sim.getInfo());
			
			String bees = "Bees: "+Integer.toString(sim.getHive().getMyBees().size())+" | Nectar Bees: "+sim.getHive().getNectarBees();
			
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
		//	TU LOGGER ZAPISZE DANE DO PLIKU W STYLU
		//	logger.Log(sim.getInfo) i tak dalej
		sim.Stop();
		guiM.statusMessage.setText(sim.getInfo());
		System.out.println("Total bees: "+sim.getHive().getTotalBees());
		int average = sim.getHive().getTotalBees()/it;
		System.out.println("Average bee population: "+average);
		System.out.println("Total nectar stored: "+sim.getHive().getTotalStorage());
		int averageGathered = sim.getHive().getTotalGathered()/it;
		System.out.println("Average nectar income per step: "+averageGathered);
		int averageStorage = sim.getHive().getTotalStorage()/it;
		System.out.println("Average nectar amount in storage: "+averageStorage);
		System.out.println("Total plants: "+sim.getPlantManager().getTotalPlants());

	}

}
