package beehive;

public interface INectarable {
	//	Every object that stores nectar must implement this
	public void AddNectar(int amount);
	public int RemoveNectar(int amount);
}
