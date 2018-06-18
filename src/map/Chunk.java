package map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import application.Settings;
import entities.Block;
import items.Material;
import javafx.scene.Group;
import utils.Vector2D;

public class Chunk extends Group implements Iterable<Block> {
	private final List<Block> blocks = new ArrayList<>();
	private Vector2D pos;
	
	
	public Chunk(double worldX, double worldY) {
		pos = new Vector2D(worldX, worldY);
		generateBlocks();
	}
	
	public List<Block> getBlocks(){
		return blocks;
	}
	
	private void generateBlocks() {
		for (int y = 0; y < Settings.chunkSize.get(); y++)
			for (int x = 0; x < Settings.chunkSize.get(); x++) {
				Material mat = (y == 0) ? Material.GRAVEL : Material.ORE_3;
				Block block = new Block(mat, pos.copy().add(x, y));
				blocks.add(block);
			}
		
		getChildren().setAll(blocks);
	}

	@Override
	public Iterator<Block> iterator() {
		return blocks.iterator();
	}
	
}
