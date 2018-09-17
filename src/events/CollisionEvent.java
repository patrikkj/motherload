package events;

import entities.Entity;
import utils.Collision;

public class CollisionEvent extends Event {
	private final Collision collision;
	private final Entity e1, e2;
	
	public CollisionEvent(Collision collision, Entity e1, Entity e2) {
		this.collision = collision;
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Collision getCollision() {
		return collision;
	}
	
	public Entity getEntity1() {
		return e1;
	}
	
	public Entity getEntity2() {
		return e2;
	}
}
