package terrain;

import java.util.Random;

import entities.Block;
import enums.Material;
import misc.Settings;
import utils.Vector2D;

public class WorldGenerator {
	private static Random random = new Random();
	
	/*
	 * Ore generation table
	 */
	//				
	private static double getNoise(int x, int y, long seed) {
		random.setSeed(x*1000 * y + seed);
		return random.nextFloat();
	}
	
	public static Chunk generateChunk(World world, int chunkID) {
		long seed = world.getSeed();
		Block[] blocks = new Block[Settings.blocksPerChunk.get()];
		Vector2D chunkCoord = World.globalIDToVector(chunkID);
		
		int x = (int) chunkCoord.x;
		int y = (int) chunkCoord.y;
		int chunkSize = Settings.chunkSize.get();
		int localID = 0;
		
		for (int dy = 0; dy < chunkSize; dy++) {
			for (int dx = 0; dx < chunkSize; dx++) {
				Material material = assignMaterial(x + dx, y + dy, seed);
				blocks[localID++] = new Block(new Vector2D(x + dx, y + dy), material);
			}
		}
		return new Chunk(chunkID, blocks);
	}
	
	private static Material assignMaterial(int x, int y, long seed) {
		double noise = getNoise(x, y, seed);
//		System.out.println(noise);
		if (y < 0) return Material.AIR;
		if (y == 0) return Material.GRASS;
		
		noise +=  (1-noise)*((double)y/1000);
		if (noise < 0.95) return Material.DIRT;
		else if (noise < 0.97) return Material.ORE_EMERALD; 
		else if (noise < 0.985) return Material.ORE_RUBY; 
		else if (noise < 0.995) return Material.ORE_AMETHYST; 
		else return Material.ORE_DIAMOND; 
	}
}
