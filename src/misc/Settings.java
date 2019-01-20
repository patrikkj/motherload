package misc;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import utils.Vector2D;

public class Settings {
	// Constants
	public static final double G = 9.81;
	
	// Scene details
	public static final DoubleProperty sceneWidth = new SimpleDoubleProperty(1024);
	public static final DoubleProperty sceneHeight = new SimpleDoubleProperty(768);
	public static final ObjectProperty<Color> sceneColor = new SimpleObjectProperty<>(Color.DARKGRAY);

	// Blocks
	public static final DoubleProperty blockSize = new SimpleDoubleProperty(64);// 64, 256 for debug
	public static final DoubleProperty blocksX = new SimpleDoubleProperty();
	public static final DoubleProperty blocksY = new SimpleDoubleProperty();
	public static final IntegerProperty maxBlocksX = new SimpleIntegerProperty();
	public static final IntegerProperty maxBlocksY = new SimpleIntegerProperty();
	public static final IntegerProperty blocksPerChunk = new SimpleIntegerProperty();
	public static final IntegerProperty blocksPerRow = new SimpleIntegerProperty();
	public static final IntegerProperty blocksPerColumn = new SimpleIntegerProperty();

	// Chunks
	public static final IntegerProperty chunkSize = new SimpleIntegerProperty(8);
	public static final IntegerProperty maxChunksX = new SimpleIntegerProperty(256);// 256, 1 for debug
	public static final IntegerProperty maxChunksY = new SimpleIntegerProperty(256);// 256, 1 for debug

	// World
	public static final ObjectProperty<Vector2D> gravity = new SimpleObjectProperty<>(new Vector2D(0 * G, 0 * G));
	public static final DoubleProperty linearDrag = new SimpleDoubleProperty(60);// 60, 6000 for debug
	public static final DoubleProperty angularDrag = new SimpleDoubleProperty(60);// 60, 6000 for debug
	public static final DoubleProperty timeScaleFactor = new SimpleDoubleProperty(1);// 1, 0.5 for debug
	public static final DoubleProperty atmosphereHeight = new SimpleDoubleProperty();
	public static final DoubleProperty lowerBoundX = new SimpleDoubleProperty(0);
	public static final DoubleProperty upperBoundX = new SimpleDoubleProperty();
	public static final DoubleProperty lowerBoundY = new SimpleDoubleProperty();
	public static final DoubleProperty upperBoundY = new SimpleDoubleProperty();
	
	// Rendering
	public static final DoubleProperty targetFramerate = new SimpleDoubleProperty(60);
	public static final IntegerProperty renderRadius = new SimpleIntegerProperty(3);
	public static final DoubleProperty cameraSpeed = new SimpleDoubleProperty(15);
	
	// Ship
	public static final DoubleProperty engineScaleFactor = new SimpleDoubleProperty(3);// 2.5, 1,5 for debug
	public static final IntegerProperty collisionRadius = new SimpleIntegerProperty(1);
	public static final DoubleProperty shipWidthPixels = new SimpleDoubleProperty(64);// 61, 192 for debug 48
	public static final DoubleProperty shipHeightPixels = new SimpleDoubleProperty(64);// 34, 64 for debug 48
	public static final DoubleProperty shipWidthGrid = new SimpleDoubleProperty();
	public static final DoubleProperty shipHeightGrid = new SimpleDoubleProperty();
	
	// Debugging
	public static final IntegerProperty digRadius= new SimpleIntegerProperty(2);
	
	
	// Composite bindings
	static { 
		atmosphereHeight.bind(Bindings.multiply(blocksY, 0.6));
		blocksX.bind(Bindings.divide(sceneWidth, blockSize));
		blocksY.bind(Bindings.divide(sceneHeight, blockSize));
		maxBlocksX.bind(Bindings.multiply(chunkSize, maxChunksX));
		maxBlocksY.bind(Bindings.multiply(chunkSize, maxChunksY));
		blocksPerChunk.bind(Bindings.multiply(chunkSize, chunkSize));
		blocksPerRow.bind(Bindings.multiply(blocksPerChunk, maxChunksX));
		blocksPerColumn.bind(Bindings.multiply(blocksPerChunk, maxChunksY));
		shipWidthGrid.bind(Bindings.divide(shipWidthPixels, blockSize));
		shipHeightGrid.bind(Bindings.divide(shipHeightPixels, blockSize));
		lowerBoundX.set(0);
		upperBoundX.bind(maxBlocksX);
		lowerBoundY.bind(Bindings.multiply(atmosphereHeight, -1));
		upperBoundY.bind(maxBlocksY);
		
	}

}
