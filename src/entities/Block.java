package entities;

import enums.Material;
import misc.Settings;
import utils.Vector2D;

public class Block extends Entity {
	private Material material;
	
	
	public Block(Vector2D position, Material material) {
//		super(position, Settings.blockSize.get(), Settings.blockSize.get(), null);
		super(position, 1, 1, null);
		this.material = material;
	}
	
	
	public Material getMaterial() {
		return material;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}


	@Override
	public String toString() {
		return "Block [material=" + material + ", getPosition()=" + getPosition() + ", getBounds()=" + getBounds()
				+ ", getCenter()=" + getCenter() + "]";
	}
}


