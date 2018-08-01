package actors;

import level.Room;
import math.Ray;
import math.Vec2;
import math.Wall;

/**
 * An enemy with a flashlight FoV and a larger "ping" FoV.
 *
 * @author Joe Desmond
 */
public class Enemy {
	
	// Rays are defined relative to the origin.
	/**
	 * Flashlight ray 1
	 */
	private Ray flashlight1;
	/**
	 * Flashlight ray 2
	 */
	private Ray flashlight2;
	/**
	 * Ping ray 1
	 */
	private Ray ping1;
	/**
	 * Ping ray 2
	 */
	private Ray ping2;

	public Vec2 pos;

	// TODO: Write a constructor and calculate rays from FoVs

	public boolean canSee(Player player) {
		
	}

	private boolean pingCheck(Player player) {

	}
}
