package math;

/**
 * A 2D vector.
 *
 * @author Joe Desmond
 */
public class Vec2 {
	
	public static final Vec2 ORIGIN = new Vec2(0, 0);

	public float x;
	public float y;
	public float length;

	public Vec2(float _x, float _y) {
		x = _x;
		y = _y;
		updateLength();
	}

	public void updateLength() {
		length = (float) Math.sqrt((x * x) + (y * y));
	}

	public static float distance(Vec2 v1, Vec2 v2) {
		return (float) Math.sqrt(((v1.x - v2.x) * (v1.x - v2.x)) + ((v1.y - v2.y) * (v1.y - v2.y)));
	}

	public Vec2 plus(Vec2 v) {
		return new Vec2(x + v.x, y + v.y);
	}

	public Vec2 minus(Vec2 v) {
		return new Vec2(x - v.x, y - v.y);
	}

	/**
	 * Does not call <code>updateLength()</code>. You may need to do this yourself.
	 *
	 * @return Vec2 with same direction as this, but length of 1
	 */
	public Vec2 normalize() {
		return new Vec2(x / length, y / length);
	}
	
	public Vec2 scale(float factor) {
		return new Vec2(x * factor, y * factor);
	}

	public boolean isLeftOf(Ray ray) {
		Vec2 v1 = ray.direction.minus(ray.origin);
		Vec2 v2 = this.minus(ray.origin);

		// Cross product
		float z = (v1.x * v2.y) - (v1.y * v2.x);

		return z < 0;
	}
}
