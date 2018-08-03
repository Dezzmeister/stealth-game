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
	
	/**
	 * Gets a point on this Wall for a specified normalized distance on the Wall. v0 is 0, and v1 is 1.
	 * 
	 * @param norm normalized distance on the Wall
	 * @return point on the Wall that is norm*length distance from v0
	 */
	public Vec2 getPointAt(float norm) {
		float xResult;
		float yResult;
		
		float xDiff = v1.x - v0.x;
		
		if (xDiff == 0) {
			xResult = v0.x;
		} else {
			xResult = (norm/xDiff) + v0.x;
		}
		
		float yDiff = v1.y - v0.y;
		
		if (yDiff == 0) {
			yResult = v0.y;
		} else {
			yResult = (norm/yDiff) + v0.y;
		}
		
		return new Vec2(xResult,yResult);
	}
}
