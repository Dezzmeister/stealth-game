package level;

import java.util.ArrayList;
import java.util.List;

import actors.Enemy;
import math.Wall;

/**
 * Contains several {@link math.Wall Walls}. Used to optimize visibility testing
 * by only testing enemies that are in the same <code>Room</code>(s) as the
 * player. Does not necessarily represent a physical room in the level. In fact,
 * some <code>Room</code>s may need to overlap to prevent visiblity bugs.
 *
 * <p>
 * TODO: Write an algorithm to generate appropriate Rooms for a level.
 *
 * @author Joe Desmond
 */
public class Room {
	
	public Wall[] walls;
	private List<Enemy> enemies = new ArrayList<Enemy>();

	public Room(Wall... _walls) {
		walls = _walls;
	}

	public Room addEnemies(Enemy... _enemies) {
		for (Enemy _enemie : _enemies) {
			enemies.add(_enemie);
		}

		return this;
	}

	public void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
	}
}
