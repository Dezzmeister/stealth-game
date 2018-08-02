package math;

/**
 * A 2D ray with a starting Vec2 and direction Vec2.
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
	
	public Ray(float x1, float y1, float x2, float y2) {
		origin = new Vec2(x1,y1);
		direction = new Vec2(x2,y2);
	}

	public void updateLength() {
		length = Vec2.distance(origin, direction);
	}

	public Ray plus(Vec2 translator) {
		return new Ray(origin.plus(translator), direction.plus(translator));
	}

	public Ray minus(Vec2 translator) {
		return new Ray(origin.minus(translator), direction.minus(translator));
	}

	/**
	 * Calculates the position where this ray intersects a Wall. Returns null if it
	 * doesn't.
	 *
	 * @param segment
	 *            a Wall
	 * @return point where the ray hits the Wall, or null
	 */
	public Vec2 hitWall(Wall segment) {
		Vec2 r0 = origin;
		Vec2 r1 = direction;
		Vec2 a = segment.v0;
		Vec2 b = segment.v1;

		Vec2 s1, s2;
		s1 = new Vec2(r1.x - r0.x, r1.y - r0.y);
		s2 = new Vec2(b.x - a.x, b.y - a.y);
		
		float s, t;
		s = (-s1.y * (r0.x - a.x) + s1.x * (r0.y - a.y)) / (-s2.x * s1.y + s1.x * s2.y);
		t = (s2.x * (r0.y - a.y) - s2.y * (r0.x - a.x)) / (-s2.x * s1.y + s1.x * s2.y);
		
		if (s >= 0 && s <= 1 && t >= 0) {
			return new Vec2(r0.x + (t * s1.x), r0.y + (t * s1.y));
		}
		return null;
	}
}
