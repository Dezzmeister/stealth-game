package math;

/**
 * A Wall in the level. May belong to one or more {@link level.Room Rooms}.
 *
 * @author Joe Desmond
 */
public class Wall {
	
	/**
	 * One endpoint of the Wall
	 */
	public Vec2 v0;
	/**
	 * The other endpoint of the Wall
	 */
	public Vec2 v1;

	public Wall(Vec2 endpoint1, Vec2 endpoint2) {
		v0 = endpoint1;
		v1 = endpoint2;
	}
}
