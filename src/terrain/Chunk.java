package terrain;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import entities.Block;
import enums.Material;
import misc.Settings;
import utils.Vector2D;

public class Chunk implements Iterable<Block> {
	// Fields
	private Block[] blocks;
	private Integer chunkID;

	
	public Chunk(Integer chunkID, Block[] blocks) {
		this.chunkID = chunkID;
		this.blocks = blocks;
	}
	

	// Getters
	public Integer getChunkID() {
		return chunkID;
	}
	
	public Block getBlock(int x, int y) {
		return blocks[y * Settings.chunkSize.get()  +  x];
	}
	
	public Block getBlock(int localID) {
		return blocks[localID];
	}
	
	public Collection<Block> getBlocks() {
		return Arrays.asList(blocks);
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
