package terrain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import entities.Block;
import misc.Settings;
import utils.Vector2D;

public class World {
	private Map<Integer, Chunk> chunks = new HashMap<>();
	private long seed;
	
	public World() {
		this.seed = System.nanoTime();
	}
	
	/**
	 * Getter for block represented by global ID.
	 */
	public Block getBlock(int globalID) {
		final int chunkID = globalIDToChunkID(globalID);
		final int localID = globalIDToLocalID(globalID);
		try {
			return chunks.get(chunkID).getBlock(localID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Pos vector: " + World.globalIDToVector(globalID));
			System.out.println("global ID: " + globalID);
			System.out.println("local ID: " + localID);
		}
		return null;
	}

	/**
	 * Getter for block represented by global position vector.
	 */
	public Block getBlock(Vector2D vector) {
		return getBlock(vectorToGlobalID(vector));
	}

	/**
	 * Getter for block represented by global coordinates.
	 */
	public Block getBlock(int x, int y) {
		return getBlock(coordToGlobalID(x, y));
	}
	
	
	/**
	 * Getter for a collection of blocks contained within specified area.
	 * @param globalID - Global ID for center block.
	 * @param radius - radius.
	 * @return Collection of blocks specified by global ID and radius.
	 */
	public Collection<Block> getBlocks(int globalID, int radius) {
		Collection<Block> blocks = new ArrayList<>();
		Vector2D coord = globalIDToVector(globalID);
		
		// Loop range
		int minX = Math.max((int) coord.x - radius, (int) Settings.lowerBoundX.get());
		int maxX = Math.min((int) coord.x + radius, (int) Settings.upperBoundX.get());
		int minY = Math.max((int) coord.y - radius, (int) Settings.lowerBoundY.get());
		int maxY = Math.min((int) coord.y + radius, (int) Settings.upperBoundY.get());
		
		for (int y = minY; y <= maxY; y++)
			for (int x = minX; x <= maxX; x++)
				if (isValidGlobalCoord(x, y))
					blocks.add(getBlock(x, y));
		
		return blocks;
	}
	
	/**
	 * Getter for a collection of blocks contained within specified area.
	 * @param vector - Global position for center block.
	 * @param radius - radius.
	 * @return Collection of blocks specified by global position vector and radius.
	 */
	public Collection<Block> getBlocks(Vector2D vector, int radius) {
		return getBlocks(vectorToGlobalID(vector), radius);
	}
	
	/**
	 * Getter for a collection of blocks contained within specified area.
	 * @param x - Global x-coordinate for center block.
	 * @param y - Global y-coordinate for center block.
	 * @param radius - radius.
	 * @return Collection of blocks specified by global coordinates and radius.
	 */
	public Collection<Block> getBlocks(int x, int y, int radius) {
		return getBlocks(coordToGlobalID(x, y), radius);
	}
	
	
	/**
	 * Getter for all blocks laying at the border with radius {@code radius} from {@code ID}.
	 * @param globalID - global ID.
	 * @param radius - radius.
	 * @return Collection of blocks for given border.
	 */
	public Collection<Block> getBlocksBorder(int globalID, int radius) {
		Collection<Block> blocks = new ArrayList<>();
		Vector2D blockVector = globalIDToVector(globalID);
		
		// Loop range
		int minX = (int) blockVector.x - radius;
		int maxX = (int) blockVector.x + radius;
		int minY = (int) blockVector.y - (radius - 1);
		int maxY = (int) blockVector.y + (radius - 1);
		
		for (int x = minX; x <= maxX; x++) {
			if (isValidGlobalCoord(x, minY))
				blocks.add(getBlock(x, minY));
			if (isValidGlobalCoord(x, maxY))
				blocks.add(getBlock(x, maxY));
		}
		
		for (int y = minY; y <= maxY; y++) {
			if (isValidGlobalCoord(minX, y))
				blocks.add(getBlock(minX, y));
			if (isValidGlobalCoord(maxX, y))
				blocks.add(getBlock(maxX, y));
		}
		
		return blocks;
	}	
	
	/**
	 * Getter for all blocks laying at the border with radius {@code radius} from {@code vector}.
	 * @param vector - global position vector.
	 * @param radius - radius.
	 * @return Collection of blocks for given border.
	 */
	public Collection<Block> getBlocksBorder(Vector2D vector, int radius) {
		return getBlocksBorder(vectorToGlobalID(vector), radius);
	}	
	
	/**
	 * Getter for all blocks laying at the border with radius {@code radius} from given coordinate.
	 * @param x - Global x-coordinate.
	 * @param y - Global y-coordinate.
	 * @param radius - radius.
	 * @return Collection of blocks for given border.
	 */
	public Collection<Block> getBlocksBorder(int x, int y, int radius) {
		return getBlocksBorder(coordToGlobalID(x, y), radius);
	}
	
	
	/**
	 * Getter for a collection of Integers representing chunk IDs within container.
	 * @param chunkID - chunk ID.
	 * @param radius - radius.
	 * @return Collection of chunk IDs for given area.
	 */
	public static Collection<Integer> getChunkIDsWithinRadius(int chunkID, int radius) {
		Collection<Integer> chunks = new ArrayList<>();
		Vector2D chunkVector = Chunk.toChunkVector(chunkID);
		
		// Loop range
		int minX = (int) chunkVector.x - radius;
		int maxX = (int) chunkVector.x + radius;
		int minY = (int) chunkVector.y - radius;
		int maxY = (int) chunkVector.y + radius;
		
		for (int y = minY; y <= maxY; y++)
			for (int x = minX; x <= maxX; x++)
				if (isValidChunkCoord(x, y))
					chunks.add(Chunk.toChunkID(x, y));
		
		return chunks;
	}
	
	/**
	 * Getter for the chunk ID for all chunks laying at the border with radius {@code radius} from {@code ID}.
	 * @param chunkID - global ID or chunk ID.
	 * @param radius - radius.
	 * @return Collection of chunk IDs for given area.
	 */
	public static Collection<Integer> getChunkIDsBorder(int chunkID, int radius) {
		Collection<Integer> chunks = new ArrayList<>();
		Vector2D chunkVector = Chunk.toChunkVector(chunkID);
		
		// Loop range
		int minX = (int) chunkVector.x - radius;
		int maxX = (int) chunkVector.x + radius;
		int minY = (int) chunkVector.y - (radius - 1);
		int maxY = (int) chunkVector.y + (radius - 1);
		
		for (int x = minX; x <= maxX; x++) {
			if (isValidChunkCoord(x, minY))
				chunks.add(Chunk.toChunkID(x, minY));
			if (isValidChunkCoord(x, maxY))
				chunks.add(Chunk.toChunkID(x, maxY));
		}
		
		for (int y = minY; y <= maxY; y++) {
			if (isValidChunkCoord(minX, y))
				chunks.add(Chunk.toChunkID(minX, y));
			if (isValidChunkCoord(maxX, y))
				chunks.add(Chunk.toChunkID(maxX, y));
		}
		
		return chunks;
	}
	
	/**
	 * Getter for seed used in world generation.
	 */
	public long getSeed() {
		return seed;
	}
	
	/**
	 * Returns a hashmap containing all chunks generated.
	 */
	public Map<Integer, Chunk> getChunks() {
		return chunks;
	}
	
	
	
	////Static methods ////
	// From coordinates
	/**
	 * Converts global coordinates to ID.
	 * @param x - Global x-coordinate.
	 * @param y - Global y-coordinate.
	 * @return Global ID.
	 */
	public static int coordToGlobalID(int x, int y) {
		final int chunkID = coordToChunkID(x, y);
		final int localID = coordToLocalID(x, y);
		
		return chunkID + localID;
	}
	
	/**
	 * Converts global coordinate to corresponding chunk ID.
	 * @param x - global x-coordinate
	 * @param y - global y-coordinate
	 * @return chunk ID
	 */
	public static int coordToChunkID(int x, int y) {
		final int chunkX = x - x % Settings.chunkSize.get();
		final int chunkY = y - y % Settings.chunkSize.get();
		
		final int IDFromX = (chunkX / Settings.chunkSize.get()) * Settings.blocksPerChunk.get();
		final int IDFromY = (chunkY / Settings.chunkSize.get()) * Settings.blocksPerRow.get();
		
		return IDFromX + IDFromY;
	}
	
	/**
	 * Converts global coordinate to local ID.
	 * @param x - Global x-coordinate.
	 * @param y - Global y-coordinate.
	 * @return Local ID within chunk.
	 */
	public static int coordToLocalID(int x, int y) {
		final int IDFromX = x % Settings.chunkSize.get();
		final int IDFromY = y % Settings.chunkSize.get() * Settings.chunkSize.get();
		
		return IDFromX + IDFromY;
	}
	
	// From Vector2D
	/**
	 * Converts global position vector to global ID.
	 * @param vector - Global position vector.
	 * @return Global ID.
	 */
	public static int vectorToGlobalID(Vector2D vector) {
		return coordToGlobalID((int) vector.x, (int) vector.y);
	}
	
	/**
	 * Converts global position vector to chunk ID.
	 * @param vector - Global position vector.
	 * @return Chunk ID.
	 */
	public static int vectorToChunkID(Vector2D vector) {
		return coordToChunkID((int) vector.x, (int) vector.y);
	}
	
	/**
	 * Converts global position vector to local ID.
	 * @param vector - Global position vector.
	 * @return Local ID within chunk.
	 */
	public static int vectorToLocalID(Vector2D vector) {
		return coordToLocalID((int) vector.x, (int) vector.y);
	}
	
	// From ID
	/**
	 * Converts ID to corresponding chunk ID.
	 * @param globalID - ID
	 * @return chunk ID
	 */
	public static int globalIDToChunkID(int globalID) {
		return globalID - globalID % Settings.blocksPerChunk.get();
	}
	
	/**
	 * Converts ID to corresponding local ID within chunk.
	 * @param globalID - ID
	 * @return local ID
	 */
	public static int globalIDToLocalID(int globalID) {
		return globalID % Settings.blocksPerChunk.get();
	}

	/**
	 * Converts ID to corresponding global position vector.
	 * @param globalID - ID
	 * @return Vector2D representing global position.
	 */
	public static Vector2D globalIDToVector(int globalID) {
		final int globalX =  (globalID % Settings.blocksPerRow.get()) / Settings.blocksPerChunk.get() * Settings.chunkSize.get();
		final int localX = globalID % Settings.chunkSize.get();
		
		final int globalY = globalID / (Settings.blocksPerRow.get()) * Settings.chunkSize.get();
		final int localY = (globalID % Settings.blocksPerChunk.get()) / Settings.chunkSize.get();

		return new Vector2D(globalX + localX, globalY + localY);
	}
	
	// Validation
	/**
	 * Returns wheter global coordinates are within world bounds.
	 */
	public static boolean isValidGlobalCoord(double x, double y) {
		return (x >= 0  &&  x < Settings.maxBlocksX.get()  &&  
				y >= 0  &&  y < Settings.maxBlocksY.get());
	}

	/**
	 * Returns wheter chunk-specific coordinates are within world bounds.
	 */
	public static boolean isValidChunkCoord(double x, double y) {
		return (x >= 0  &&  x < Settings.maxChunksX.get()  &&  
				y >= 0  &&  y < Settings.maxChunksY.get());
	}
}
