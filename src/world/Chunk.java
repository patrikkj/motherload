package world;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

import application.Game;
import application.Settings;
import entities.Block;
import enums.Material;
import utils.Vector2D;

public class Chunk implements Iterable<Block> {
	// Fields
	private final Block[] blocks = new Block[Settings.blocksPerChunk.get()];
	private final Integer chunkID;
	private final Vector2D pos;

	
	// Constructor
	public Chunk(Integer chunkID) {
		this.chunkID = chunkID;
		this.pos = World.globalIDToVector(chunkID);
//		if (pos.x != Settings.chunkSize.get()  &&  pos.y != 0)
			generateBlocks();
	}

	
	// Block generation
	private void generateBlocks() {
		for (int localID = 0; localID < Settings.blocksPerChunk.get(); localID++)
			blocks[localID] = generateBlock(chunkID + localID);
	}
	
	private Block generateBlock(Integer globalID) {
		final Vector2D pos = World.globalIDToVector(globalID);
		Material mat = (pos.y == 0) ? Material.GRASS : Material.ORE_DIAMOND;
		return new Block(mat, globalID);
	}

	// Getters
	public Block getBlock(int x, int y) {
		return blocks[y * Settings.chunkSize.get()  +  x];
	}
	
	public Block getBlockLocal(int ID) {
		return blocks[ID];
	}
	
	public Block getBlockGlobal(int ID) {
		return getBlockLocal(World.globalIDToLocalID(ID));
	}
	
	public Collection<Block> getBlocks() {
		return Arrays.asList(blocks);
	}
	
	public Vector2D getPos() {
		return pos;
	}
	


	// Static chunk conversion 
	/**
	 * Converts chunk ID to corresponding chunk vector.
	 * @param chunkID - chunk ID
	 * @return Chunk-specific chunk vector.
	 */
	public static Vector2D toChunkVector(Integer chunkID) {
		return World.globalIDToVector(chunkID).divide(Settings.chunkSize.get());
	}
	
	/**
	 * Converts chunk vector to corresponding chunk ID.
	 * @param chunkVector - Chunk-specific coordinate vector, not global coordinates.
	 * @return Chunk ID.
	 */
	public static Integer toChunkID(Vector2D chunkVector) {
		return World.vectorToChunkID(chunkVector.multiplyNew(Settings.chunkSize.get()));
	}
	
	/**
	 * Converts chunk coordinates to corresponding chunk ID.
	 * @param x - Chunk-specific x-coordinate, not global coordinate.
	 * @param y - Chunk-specific y-coordinate, not global coordinate.
	 * @return Chunk ID.
	 */
	public static Integer toChunkID(int x, int y) {
		return toChunkID(new Vector2D(x, y));
	}
	
	
	@Override
	public Iterator<Block> iterator() {
		return Arrays.stream(blocks).iterator();
	}
}
