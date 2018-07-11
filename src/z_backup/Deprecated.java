package z_backup;

import entities.Entity;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import utils.Direction;
import utils.Vector2D;

public class Deprecated {
//	/**
//	 * Returns wether this entity intersects with input entity.
//	 */
//	public static boolean intersects(Ship ship, Block block) {
//		double blockSize = Settings.blockSize.get();
//		Vector2D shipPos = ship.getPos();
//		Vector2D blockPos = block.getPos();
//		return ((Math.abs(blockPos.x - shipPos.x) < (ship.getWidth() + blockSize) / (2d * blockSize)) &&
//				Math.abs(blockPos.y - shipPos.y) < (ship.getHeight() + blockSize) / (2d * blockSize));
//	}
	
//	public void checkCollisions(Ship ship) {
//		Vector2D shipPos = ship.getPos();
//		Collection<Block> domain = getBlocks(shipPos, 1);
//		Collection<Block> sortedDomain = domain.stream()
//				.sorted((b1, b2) -> (int)Math.signum(shipPos.subtractNew(b1.getPos()).magnitude() - 
//													 shipPos.subtractNew(b2.getPos()).magnitude()))
//				.collect(Collectors.toList());
//				
//		Block collisionBlock = sortedDomain.stream()
//				.filter(b -> ship.intersects(b)).findFirst().orElse(null);
//		
//		if (collisionBlock == null) {
//			ship.setCollisionVector(null);
//			return;
//		}
//		
////		System.out.println(collisionBlock.getGlobalID());
//		
//		Vector2D collisionVector = shipPos.subtractNew(collisionBlock.getPos());
////		System.out.println(collisionVector);
//		
//		if (Math.abs(collisionVector.x) > Math.abs(collisionVector.y))
//			collisionVector.set(Math.signum(collisionVector.x), 0);
//		else
//			collisionVector.set(0, Math.signum(collisionVector.y));
//		
////		System.out.println(collisionVector);
//		ship.setCollisionVector(collisionVector);
////		ship.setCollisionElasticity(collisionBlock.getMaterial().getElasticity());
//		ship.setCollisionElasticity(0.8);
//	}
//	public static void main(String[] args) {
//	int x = 17, y = 17;
//	Vector2D vector = new Vector2D(x, y);
//	int id = 1041;
//	
////	System.out.println(coordToGlobalID(x, y));
////	System.out.println(coordToChunkID(x, y));
////	System.out.println(coordToLocalID(x, y));
////
////	System.out.println(vectorToGlobalID(vector));
////	System.out.println(vectorToChunkID(vector));
////	System.out.println(vectorToLocalID(vector));
////
////	System.out.println(globalIDToChunkID(id));
////	System.out.println(globalIDToLocalID(id));
////	System.out.println(globalIDToVector(id));
////	
////	System.out.println(getChunkIDs(1344, 2));
////	System.out.println(getChunkIDsBorder(1075, 1));
//}

///**
// * Converts chunk-specific coordinates to ID. 
// * Chunk will have the same ID as the upper-left block contained within chunk.
// * @param x - Chunk-specific x-coordinate (Number, not coordinate).
// * @param y - Chunk-specific y-coordinate (Number, not coordinate).
// * @return Global ID, used as key in chunk map.
// */
//public static int chunkToID(int x, int y) {
//	return Settings.blocksPerChunk.get() * (x + y * Settings.maxChunksX.get());
//}

///**
// * Converts chunk-specific position vector to ID. 
// * Chunk will have the same ID as the upper-left block contained within chunk.
// * @param vector - Chunk-specific position vector (Number, not coordinate).
// * @return Global ID, used as key in chunk map.
// */
//public static int chunkToID(Vector2D vector) {
//	return chunkToID((int) vector.x, (int) vector.y);
//}

//while(true) {
//gameLoop();
//try {
//	Thread.sleep(0);
//} catch (InterruptedException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}
//}
//AnimationTimer animationTimer = new AnimationTimer() {
//@Override public void handle(long arg0) {
//	gameLoop();
//}
//};
//animationTimer.start();

//long now = 0;
//long lastFrame = System.nanoTime();
//  double passed = 0.0;
//  double FPS = 60;
//  
//  while(true) {
//	  now = System.nanoTime();
//	  passed = (now - lastFrame) / 1000000;
//	  if (passed >= 1000d/FPS) {
//		  passed = 0; 
//		  gameLoop();
//		  lastFrame = now;
//	  }
//  }
	
//	public class Renderer {
//		private final Game game;
//		
//		// Camera management
//		private Vector2D camera; // [16, 16]
//		private Vector2D cameraOffset;  // [8, 6]
//		
//		
//		public Renderer(Game game) {
//			this.game = game;
//			this.camera = new Vector2D(8, 6);
//			this.cameraOffset = new Vector2D(Settings.blocksX.get(), Settings.blocksY.get()).divide(2); // [16, 12]s
//		}
//
//		
//		/**
//		 * Renders all cached entities.
//		 */
//		public void render() {
//			long t1 = System.nanoTime();
//			// Render ship
//			renderAnimated(game.ship);
//			long t2 = System.nanoTime();
//			
//			// Render chunks
//			game.renderChunks.forEach(c -> renderChunk(c));
//			long t3 = System.nanoTime();
//			
//			moveCamera();
//			long t4 = System.nanoTime();
//			
//
//			System.out.printf("Name: %s, Time: %s%n", "renderAnimated()", t2 - t1);
//			System.out.printf("Name: %s, Time: %s%n", "renderChunks()", t3 - t2);
//			System.out.printf("Name: %s, Time: %s%n", "moveCamera()", t4 - t3);
//		}
//		
//		public void renderChunk(Chunk chunk) {
//			chunk.forEach(e -> render(e));
//		}
//		
//		/**
//		 * Updates visual representation of given entity.
//		 */
//		public void render(Entity entity) {
//			Vector2D coords = toLayoutCoords(entity.getPos());
//			entity.relocate(coords.x, coords.y);
//		}
//		
//		/**
//		 * Updates visual representation of given entity.
//		 */
//		public void render(Chunk chunk) {
//			Vector2D coords = toLayoutCoords(chunk.getPos());
//			chunk.relocate(coords.x, coords.y);
//		}
//		
//		/**
//		 * Updates visual representation of given entity, animated.
//		 */
//		public void renderAnimated(Entity entity) {
//			Vector2D coords = toLayoutCoords(entity.getPos());
////			
//			// Transition properties
//			TranslateTransition translateTransition = new TranslateTransition();
//			translateTransition.setNode(entity);
//			translateTransition.setToX(coords.x);
//			translateTransition.setToY(coords.y);
//			translateTransition.setInterpolator(Interpolator.LINEAR);
//			translateTransition.setDuration(Duration.millis((game.deltaTime * 1000) * Settings.animationDuration.get()));
//			translateTransition.setRate(game.fps * Settings.animationRate.get());
//			
//			// Play animation
//			translateTransition.play();
//		}
//
//		/**
//		 * Converts world coordinates to screen pixel coordinates.
//		 */
//		private Vector2D toLayoutCoords(Vector2D pos) {
//			Vector2D screenOrigo = camera.subtractNew(cameraOffset);
//			Vector2D coords = pos.subtractNew(screenOrigo);
//			return coords.multiply(Settings.blockSize.get());
//		}
//		
//		/**
//		 * Gradually moves camera towards ship.
//		 */
//		private void moveCamera() {
//			Vector2D offset = camera.subtractNew(game.ship.getPos());
//			camera = camera.subtract(offset.multiply(Settings.cameraSpeed.get()));
//		}
//		
//		@Override
//		public String toString() {
//			return String.format("Renderer [game=%s, camera=%s, cameraOffset=%s]", game, camera, cameraOffset);
//		}
//		
//	}
	
////Check if figure penetrates top / bottom edge
//if (delta.x > shipWidth  ||  delta.x < blockSize); // N/S
//
////Check if ship penetrates right / left edge
//if (delta.y > shipHeight  ||  delta.y < blockSize); // E/W
	
//	/**
//	 * Checks user input and updates direction
//	 */
//	private void updateDirection() {
//		int dir = 0;
//		
//		dir += (keysPressed.getOrDefault(KeyCode.SHIFT, false)) ? dir + 1 : dir;	// +32
//		dir += (keysPressed.getOrDefault(KeyCode.CONTROL, false)) ? dir + 1 : dir;	// +16
//		dir += (keysPressed.getOrDefault(KeyCode.UP, false)) ? dir + 1 : dir; 		// +8
//		dir += (keysPressed.getOrDefault(KeyCode.DOWN, false)) ? dir + 1 : dir; 	// +4
//		dir += (keysPressed.getOrDefault(KeyCode.RIGHT, false)) ? dir + 1 : dir; 	// +2
//		dir += (keysPressed.getOrDefault(KeyCode.LEFT, false)) ? dir + 1 : dir; 	// +1
//		boolean a = true;
//		boolean b = true;
//		boolean c = false;
//		switch (dir) {
//		case 0: 
//			direction = Direction.NONE; 
//			break;
//		case 1: 
//			direction = Direction.W; 
//			break;
//		case 2: 
//			direction = Direction.E; 
//			break;
//		case 4: 
//			direction = Direction.S; 
//			break;
//		case 5: 
//			direction = Direction.SW; 
//			break;
//		case 6: 
//			direction = Direction.SE; 
//			break;
//		case 8: 
//			direction = Direction.N; 
//			break;
//		case 9: 
//			direction = Direction.NW; 
//			break;
//		case 10: 
//			direction = Direction.NE; 
//			break;
//		}
//	}
}
