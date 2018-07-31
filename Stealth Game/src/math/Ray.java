package math;

/**
 * 
 *
 * @author Joe Desmond
 */
public class Ray {
	public Vec2 origin;
	public Vec2 direction;
	public float length;
	
	public Ray(Vec2 _origin, Vec2 _direction) {
		origin = _origin;
		direction = _direction;
		updateLength();
	}
	
	public void updateLength() {
		length = Vec2.distance(origin, direction);
	}
}
