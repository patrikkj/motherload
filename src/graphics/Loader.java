package graphics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class Loader {
	public static final Map<String, Image> images = new HashMap<>();
//	private static final String SHIP = "ship";
	
	static {
		loadImages();
	}
	
	private static void loadImages() {
    	File importFolder = new File(Loader.class.getResource("").getPath());
    	FilenameFilter filter = (dir, fname) -> fname.endsWith(".png");
		
    	for (File imageFile : importFolder.listFiles(filter))
			try {
				images.put(imageFile.getName(), new Image(new FileInputStream(imageFile)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
}
