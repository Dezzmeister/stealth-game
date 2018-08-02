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
	
	private Ray right = new Ray(0,0,1,0);

	public Vec2 pos;

	// TODO: Write a constructor and calculate rays from FoVs
	
	/**
	 * Returns true if this enemy can see the player in the flashlight range.
	 * 
	 * @param player
	 * @return
	 */
	public boolean canSeeInFlashLight(Player player) {
		// The two points to check for visiblity
		Wall visiblePoints = findTangentPoints(player);
		
		Ray f1 = flashlight1.plus(pos);
		Ray f2 = flashlight2.plus(pos);
		
		boolean pt1LeftOfFlashlight1 = visiblePoints.v0.isLeftOf(f1);
		boolean pt1LeftOfFlashlight2 = visiblePoints.v0.isLeftOf(f2);
		
		boolean pt2LeftOfFlashlight1 = visiblePoints.v1.isLeftOf(f1);
		boolean pt2LeftOfFlashlight2 = visiblePoints.v1.isLeftOf(f2);
		
		Vec2 visiblePoint;
		
		if (pt1LeftOfFlashlight1 != pt1LeftOfFlashlight2) {
			visiblePoint = visiblePoints.v0;
		} else if (pt2LeftOfFlashlight1 != pt2LeftOfFlashlight2) {
			visiblePoint = visiblePoints.v1;
		} else {
			return false;
		}
		
		return true;
		
		// TODO: Add wall intersection tests
	}
	
	/**
	 * Returns true if this enemy can see the player in the ping range. Will <b>NOT</b>
	 * return false if the player is also visible within the flashlight range.
	 * 
	 * @param player Player to be tested
	 * @return true if the player can be seen in the ping range
	 */
	public boolean canSeeInPingRange(Player player) {
		Wall visiblePoints = findTangentPoints(player);
		
		Ray p1 = ping1.plus(pos);
		Ray p2 = ping2.plus(pos);
		
		boolean pt1LeftOfPing1 = visiblePoints.v0.isLeftOf(p1);
		boolean pt1LeftOfPing2 = visiblePoints.v0.isLeftOf(p2);
		
		boolean pt2LeftOfPing1 = visiblePoints.v1.isLeftOf(p1);
		boolean pt2LeftOfPing2 = visiblePoints.v1.isLeftOf(p2);
		
		return (pt1LeftOfPing1 != pt1LeftOfPing2) || (pt2LeftOfPing1 != pt2LeftOfPing2);
		
		// TODO: Add wall intersection tests
	}
	
	/**
	 * Returns a Wall, the endpoints of which are the two points tangent to the Player and intersecting the center of this Enemy.
	 * 
	 * @param player A Player that is treated as a circle
	 * @return A Wall from one tangent point to the other
	 */
	private Wall findTangentPoints(Player player) {
		float b = Vec2.distance(player.pos, pos);
		float a = player.radius;
		
		float sinA = a/b;
		float cosA = (float) Math.sqrt(1 - (sinA * sinA));
		
		Vec2 original = player.pos.minus(this.pos).scale(cosA);
		
		return new Wall(new Vec2((original.x * cosA) - (original.y * sinA),(original.y * cosA) + (original.x * sinA)).plus(this.pos),
						new Vec2((original.x * cosA) + (original.y * sinA),(original.y * cosA) - (original.x * sinA)).plus(this.pos));
	}
}
