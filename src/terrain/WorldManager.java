package terrain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import application.Game;
import entities.Ship;
import enums.Material;
import misc.Settings;
import utils.Vector2D;

public class WorldManager {
	
	private int currentChunkID;
	private Collection<Chunk> activeChunks = new ArrayList<>();
	private World world;
	
	
	public WorldManager(World world) {
		this.world = world;
	}
	
	/**
	 * Returns a collection containing all chunks currently rendered.
	 */
	public Collection<Chunk> getActiveChunks(){
		return activeChunks;
	}

	/**
	 * Updates list of chunks to be passed to the render engine.
	 */
	public void updateActiveChunks(Vector2D position) {
		int lastChunkID = currentChunkID;
		currentChunkID = World.vectorToChunkID(position);
		
		// Executed upon entering new or initialization
		if (lastChunkID != currentChunkID  ||  world.getChunks().isEmpty()) {
			// IDs to be rendered this cycle
			Collection<Integer> renderIDs = World.getChunkIDsWithinRadius(currentChunkID, Settings.renderRadius.get());
			
			// Convert to list of chunks
			activeChunks = renderIDs.stream().map(ID -> assertChunk(ID)).collect(Collectors.toList());
		}
	}
	
	/**
	 * Asserts that cache contains chunk represented by chunk ID.
	 * If there is no chunk mapping for given chunk ID, a new chunk is generated and returned.
	 * @param chunkID - chunk ID.
	 * @return Chunk mapping corresponding to the given chunk ID.
	 */
	public Chunk assertChunk(int chunkID) {
		return world.getChunks().computeIfAbsent(chunkID, ID -> WorldGenerator.generateChunk(world, ID));
	}
	
}
