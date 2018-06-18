package entities;

import application.Settings;
import items.Material;
import javafx.scene.image.ImageView;
import utils.Vector2D;

public class Block extends Entity {
	private Material material;
	
	public Block(Material material, Vector2D pos) {
		super(pos, null, null, 0, Settings.blockSize.get() / 2, Settings.blockSize.get() / 2);
		this.material = material;
		setImage(material.getImage());
	}
	
	public void render() {
		
	}
}

