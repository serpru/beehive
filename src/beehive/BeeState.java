package beehive;

/**
 * Bee's objective state.
 * @author Serafin Prusik
 *
 */
public enum BeeState {
	//	States for bee state machine
	/**
	 * Bee is in the hive.
	 */
	IN_HIVE,
	/**
	 * Bee reached its target.
	 */
	IN_TARGET,
	/**
	 * Bee is flying to its target.
	 */
	FLYING_TO_TARGET,
	/**
	 * Bee is flying to its hive.
	 */
	FLYING_TO_HIVE,
	/**
	 * Bee is idle.
	 */
	IDLE,
}
