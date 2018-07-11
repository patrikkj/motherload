package entities;

import enums.Material;
import utils.Vector2D;
import world.World;

public class Block extends Entity {
	// Static fields
	public static Vector2D offset = new Vector2D(0.5, 0.5);
	
	// Fields
	private final int globalID;

	
	// Constructors
	public Block(Material material, Vector2D position) {
		super(position, offset, 1, 1, material);
		this.globalID = World.vectorToGlobalID(position);
	}
	public Block(Material material, Integer globalID) {
		this(material, World.globalIDToVector(globalID));
	}
	public void setBlock(Material material) {
		setMaterial(material);
	}
	
	public int getGlobalID() {
		return globalID;
	}
}


