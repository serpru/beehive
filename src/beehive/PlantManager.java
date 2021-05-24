package beehive;

import java.util.ArrayList;

public class PlantManager implements ISimObject {
	
	private ArrayList<Plant> plants;
	
	public PlantManager()
	{
		plants = new ArrayList<Plant>();
	}

	public void AddPlant(Plant p)
	{
		plants.add(p);
	}
	
	public void RemovePlant(Plant p)
	{
		plants.remove(p);
	}
	
	public ArrayList<Plant> getPlants() {return plants;}

	@Override
	public boolean DoStuff() {
		// TODO Auto-generated method stub
		System.out.print("PlantManager: Doing things!\n");
		for (int i = 0; i < plants.size(); i++)
		{
			plants.get(i).Grow();
		}
		return true;
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return "PlantManager reporting status";
	}
}
