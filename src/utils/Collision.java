package utils;

import enums.Material;

public class Collision {
	private final Material material;
	private final Direction direction;
	private final double deltaTime;
	
	
	public Collision(Material material, Direction direction, double deltaTime) {
		this.material = material;
		this.direction = direction;
		this.deltaTime = deltaTime;
	}
	
	
	public Material getMaterial() {
		return material;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public double getDeltaTime() {
		return deltaTime;
	}


	@Override
	public String toString() {
		return "Collision [material=" + material + ", direction=" + direction + ", deltaTime=" + deltaTime + "]";
	}
}
