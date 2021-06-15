package beehive;

/**
 * Every object that stores nectar must implement this.
 * @author Serafin Prusik.
 *
 */
public interface INectarable {
	//	Every object that stores nectar must implement this
	/**
	 * Adds nectar by amount.
	 * @param amount Amount of nectar to add.
	 */
	public void AddNectar(int amount);
	/**
	 * Removes nectar by amount.
	 * @param amount Amount of nectar to remove.
	 * @return Returns the amount of nectar asked, or as much as the class can provide.
	 */
	public int RemoveNectar(int amount);
}
