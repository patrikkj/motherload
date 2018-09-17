package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.EnumMap;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import enums.Resource;
import javafx.scene.image.Image;

public class Loader {
	// Uniform resource locator for default tileset
	private final URL DEFAULT_TILESET_URL;
	
	// Uniform resource identifier, used for resolving absolute file paths
	private final URI DEFAULT_TILESET_URI;
	
	// O(1) mapping from resources to corresponding image
	public final EnumMap<Resource, Image> images = new EnumMap<>(Resource.class);
	
	
	// Constructor
	public Loader() {
		try {
			DEFAULT_TILESET_URL = Loader.class.getResource("../graphics/128px/tilesets/default.xml");
			DEFAULT_TILESET_URI = DEFAULT_TILESET_URL.toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		loadTileSet(DEFAULT_TILESET_URI);
	}
	
	
	// Tilesets
	/**
	 * Returns the image associated with given resource, if present.
	 */
	public Image getImage(Resource resource) {
		return images.get(resource);
	}

	/**
	 * Overwrites image mapping with tileset represented by resource identifier.
	 */
	private void loadTileSet(URI uri) {
		// XML Document reference
		Document doc = parseXML(uri);

		// Read tile entries
		NodeList nodes = doc.getElementsByTagName("tile");
		for (int i = 0; i < nodes.getLength(); i++) {
			Element tile = (Element) nodes.item(i);
			
			// Predicates
			Predicate<Element> hasTypeProperty = e -> e.getAttribute("name").equals("blockType");
			Predicate<Element> hasSourceProperty = e -> e.hasAttribute("source");
			
			// Type properties
			NodeList properties = tile.getElementsByTagName("property");
			Element property = toElementStream(properties).filter(hasTypeProperty).findFirst().orElse(null);
			
			// Image properties
			NodeList images = tile.getElementsByTagName("image");
			Element image = toElementStream(images).filter(hasSourceProperty).findFirst().orElse(null);
			
			// Tile info
			String type = property.getAttribute("value");
			String relativePath = image.getAttribute("source");
			
			// Add entry
			putImage(uri, type, relativePath);
		}
	}
	
	/**
	 * Parses XML file given by {@code identifier} to a Document representation.
	 */
	private static Document parseXML(URI uri) {
		// XML file reference
		File file = new File(uri);
		
		DocumentBuilderFactory dbf = null;
		DocumentBuilder db = null;
		Document doc = null;
		
		try {
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			doc = db.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return doc;
	}
	
	/**
	 * Creates a mapping between resource represented by {@code type} to image
	 * represented by {@code uri} and {@code relativePath}.
	 */
	private void putImage(URI uri, String type, String relativePath) {
		Resource resource = Resource.valueOf(type.toUpperCase());
		URI resolved = uri.resolve(relativePath);
		Image image = null;
		
		try {
			image = new Image(new FileInputStream(new File(resolved)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Add entry
		images.put(resource, image);
	}
	
	// Private helper methods
	/**
	 * Converts a NodeList to a stream of nodes.
	 */
	private static Stream<Node> toNodeStream(NodeList nodeList){
		return IntStream.range(0, nodeList.getLength()).mapToObj(nodeList::item);
	}
	
	/*
	 * Converts a stream of nodes to a stream of elements.
	 */
	private static Stream<Element> toElementStream(Stream<Node> nodeStream){
		Predicate<Node> elementsOnly = n -> n.getNodeType() == Node.ELEMENT_NODE;
		return nodeStream.filter(elementsOnly).map(n -> (Element) n);
	}
	
	/**
	 * Converts a NodeList to a stream of elements.
	 */
	private static Stream<Element> toElementStream(NodeList nodeList){
		return toElementStream(toNodeStream(nodeList));
	}
	
	
}

