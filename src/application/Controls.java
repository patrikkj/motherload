package application;

import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import utils.Direction;

public class Controls {
	// Controls instance
	private static Controls controls = new Controls();
	
	// User input
	private Direction direction;
	private KeyCode actionKey;
	public static Map<KeyCode, Boolean> keysPressed = new HashMap<>();
	public static boolean UP, DOWN, LEFT, RIGHT, CONTROL, SHIFT,
							DIGIT1, DIGIT2, DIGIT3, DIGIT4, DIGIT5;
	
	
	private Controls() {
		
	}
	
	/**
	 * Returns the one instance of this class.
	 */
	public static Controls get() {
		return controls;
	}
	
	/**
	 * Assigns key listeners.
	 */
	protected void setKeyListeners() {
		Scene scene = Game.get().getRoot().getScene();
//		AnchorPane rootPane = new AnchorPane(); 
		
		
		// Eksempel p� key bindings
//		Scene scene = rootPane.getScene();
		
//		// Angir hva som skal skje idet et "KeyEvent" blir registrert.
//		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//			@Override
//			public void handle(KeyEvent event) {
//				KeyCode key = event.getCode();
//				System.out.println("KEYYYYYYYYYYYYYYYYY PRESSED");
//				switch(key) {
//				case DIGIT1:
//					System.out.println("1-tasten ble trykket.");
//					break;
//				case DIGIT2:
//					System.out.println("2-tasten ble trykket.");
//					break;
//				case DIGIT3:
//					System.out.println("3-tasten ble trykket.");
//					break;
//				case RIGHT:
//					System.out.println("H�yre piltast ble trykket.");
//					break;
//				}
//			}
//		});
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				keysPressed.put(key, true);
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCode key = event.getCode();
				keysPressed.put(key, false);
			}
		});
	}
	
	/**
	 * Checks user input and updates direction
	 */
	protected void updateUserInput() {
		// Set action key
		if (keysPressed.getOrDefault(KeyCode.CONTROL, false))
			actionKey = KeyCode.CONTROL;
		else if (keysPressed.getOrDefault(KeyCode.ALT, false))
			actionKey = KeyCode.ALT;
		else if (keysPressed.getOrDefault(KeyCode.SHIFT, false))
			actionKey = KeyCode.SHIFT;
		else
			actionKey = null;
		
		// Set direction
		int dir = 0;
		
		dir += (keysPressed.getOrDefault(KeyCode.UP, false)) ? dir + 1 : dir; 		// +8
		dir += (keysPressed.getOrDefault(KeyCode.DOWN, false)) ? dir + 1 : dir; 	// +4
		dir += (keysPressed.getOrDefault(KeyCode.RIGHT, false)) ? dir + 1 : dir; 	// +2
		dir += (keysPressed.getOrDefault(KeyCode.LEFT, false)) ? dir + 1 : dir; 	// +1
		
		switch (dir) {
		case 0: direction = Direction.NONE; break;
		case 1: direction = Direction.W; break;
		case 2: direction = Direction.E; break;
		case 4: direction = Direction.S; break;
		case 5: direction = Direction.SW; break;
		case 6: direction = Direction.SE; break;
		case 8: direction = Direction.N; break;
		case 9: direction = Direction.NW; break;
		case 10: direction = Direction.NE; break;
		}
	}
	
	public Direction getDirection() {
		return direction;
	}
	public KeyCode getActionKey() {
		return actionKey;
	}
}
