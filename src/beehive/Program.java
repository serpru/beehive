package beehive;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.io.IOException;

/**
 * Main program class.
 * 
 * 
 */
public class Program {
	/**
	 * Main program method.
	 * @param args Main() arguments.
	 */
	public static void main(String[] args) {
		
		//	Read from input file
		String [] parameters = new String [8];
		BufferedReader reader;
		try 
		{
			reader = new BufferedReader(new FileReader("input.txt"));
			for(int i = 0; i < 8; i++){
				// read next line
				reader.readLine();
				parameters [i] = reader.readLine();
				if(parameters[i] == null){
					System.out.println("brakujace dane w pliku konfig");
					return;
				}
			}
			reader.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}

		//	Initialize input parameters
		int it = 0;
		int startingBees = 0;
		int startingPlants = 0;
		double speedMultiplier = 0;
		double hungerMultiplier = 0;
		double newPlantRate = 0;
		double nectarRate = 0;
		int simSpeed = 0;
		
		//	Assigning values from input file
		try {
			it = Integer.valueOf(parameters [0]);
			startingBees = Integer.valueOf(parameters [1]);
			startingPlants = Integer.valueOf(parameters [2]);
			speedMultiplier = Double.valueOf(parameters [3]);
			hungerMultiplier = Double.valueOf(parameters [4]);
			newPlantRate = Double.valueOf(parameters [5]);
			nectarRate = Double.valueOf(parameters [6]);
			simSpeed = Integer.valueOf(parameters [7]);
		} catch (NumberFormatException e) {
			System.out.println("blad w pliku konfig");
			return;
		}
		
		//	Make sure parameters aren't negative
		if (it < 0) it = 1;
		if (startingBees < 0) startingBees = 1;
		if (startingPlants < 0) startingPlants = 1;
		if (speedMultiplier < 0) speedMultiplier = 1;
		if (hungerMultiplier < 0) hungerMultiplier = 1;
		if (newPlantRate < 0) newPlantRate = 1;
		if (nectarRate < 0) nectarRate = 1;
		if (simSpeed < 0) simSpeed = 1;

		//	Creating simulation object
		Simulation sim = new Simulation(
			it, 
			startingBees, 
			startingPlants, 
			speedMultiplier, 
			hungerMultiplier, 
			newPlantRate, 
			nectarRate);
		
		//	Initialize GUI
		GuiManager guiM = new GuiManager(sim.getBoard());
		guiM.Go();
		
		//	Loop for it-iterations or simulation failure
		for (int i = 0; i < it; i++)
		{
			String text = String.valueOf(sim.getCount()+1);
			guiM.iterationText.setText("Iterarion "+text);
			guiM.plantLabel.setText("Plants: "+Integer.toString(sim.getPlantManager().getPlants().size()));
			guiM.hiveLabel.setText("Hive storage: "+Integer.toString(sim.getHive().getStorage())+"/"+sim.getHive().getStorageMax());
			guiM.statusMessage.setText(sim.getInfo());
			
			String bees = "Bees: "+Integer.toString(sim.getHive().getMyBees().size())+" | Nectar Bees: "+sim.getHive().getNectarBees();
			guiM.beeLabel.setText(bees);
			
			if(!sim.Next())
			{
				break;
			}
			guiM.Draw();
			
			//	Artificial way to slow down the program so the user can see what's happening on the screen
			try {
				TimeUnit.MILLISECONDS.sleep(simSpeed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		//	Stop simulation
		sim.Stop();
		guiM.statusMessage.setText(sim.getInfo());
		
		//	Calculate data for output file
		int average = sim.getHive().getTotalBees()/it;
		int averageGathered = sim.getHive().getTotalGathered()/it;
		int averageStorage = sim.getHive().getTotalStorage()/it;

		//	Saving data to file
		try {
			Logger beeLoger = new Logger("output.txt");
			beeLoger.log("Simulation result: "+sim.getInfo());
			beeLoger.log("Total bees: "+sim.getHive().getTotalBees());
			beeLoger.log("Average bee population: "+average);
			beeLoger.log("Total nectar stored: "+sim.getHive().getTotalStorage());
			beeLoger.log("Average nectar income per step: "+averageGathered);
			beeLoger.log("Average nectar amount in storage: "+averageStorage);
			beeLoger.log("Total plants: "+sim.getPlantManager().getTotalPlants());
			beeLoger.close();
		} catch (FileNotFoundException e) {
			System.out.println("nie mozna utworzyc log");		
		}
	}

}
