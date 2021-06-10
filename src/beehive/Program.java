package beehive;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.IOException;
import java.net.URL;
public class Program {
	public static void main(String[] args) {
		//	Initialize simulation
		//	This will be changed later to let user enter simulation configuration
		String [] parameters = new String [8];
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("siemka.txt"));
			for(int i = 0; i < 8; i++){
				// read next line
				parameters [i] = reader.readLine();
				if(parameters[i] == null){
					System.out.println("brakujace dane w pliku konfig");
					return;
				}
			//	System.out.println(parameters[i]);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TU ZROBIMY �EBY Z PLIKU PROGRAM POBIERAL PARAMETRY STARTOWE
		int it = 0;
		int startingBees = 0;
		int startingPlants = 0;
		double speedMultiplier = 0;
		double hungerMultiplier = 0;
		double newPlantRate = 0;
		double nectarRate = 0;
		int speedMs = 0;
		try {
			it = Integer.valueOf(parameters [0]);
			startingBees = Integer.valueOf(parameters [1]);
			startingPlants = Integer.valueOf(parameters [2]);
			speedMultiplier = Double.valueOf(parameters [3]);
			hungerMultiplier = Double.valueOf(parameters [4]);
			newPlantRate = Double.valueOf(parameters [5]);
			nectarRate = Double.valueOf(parameters [6]);
			speedMs = Integer.valueOf(parameters [7]);
		} catch (NumberFormatException e) {
			System.out.println("blad w pliku konfig");
			return;
		}

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

		try {
			PrintStream pencil = new PrintStream("filename.txt");
			pencil.println("Simulation result: "+sim.getInfo());
			pencil.println("Iteriations run: "+sim.getIterations());
			pencil.println("Total bees: "+sim.getHive().getTotalBees());
			pencil.println("Average bee population: "+average);
			pencil.println("Total nectar stored: "+sim.getHive().getTotalStorage());
			pencil.println("Average nectar income per step: "+averageGathered);
			pencil.println("Average nectar amount in storage: "+averageStorage);
			pencil.println("Total plants: "+sim.getPlantManager().getTotalPlants());
			pencil.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

}
