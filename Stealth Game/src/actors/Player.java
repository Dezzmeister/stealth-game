package actors;

import java.util.ArrayList;
import java.util.List;

import level.Room;
import math.Vec2;

/**
 * The Player is treated like a circle in visiblity tests.
 *
 * @author Joe Desmond
 */
public class Player {
	
	public Vec2 pos;
	public float radius;
	public List<Room> rooms = new ArrayList<Room>();

	public Player(Vec2 initialPos, float _radius) {
		pos = initialPos;
		radius = _radius;
	}

	public void moveTo(Vec2 newPos) {
		pos = newPos;
	}

	public Player enterRoom(Room room) {
		rooms.add(room);
		return this;
	}

	public void leaveRoom(Room room) {
		rooms.remove(room);
	}
}
