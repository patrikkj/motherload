package events;

import java.util.ArrayList;
import java.util.List;

import application.Game;
import application.Properties;
import application.RenderEngine;
import enums.GameState;
import javafx.scene.paint.Color;
import utils.Vector2D;

public class EventManager {
	private static EventManager eventManager = new EventManager();
	private GameState gameState = GameState.ACTIVE;
	
	private List<TextDisplayEvent> bufferedEvents = new ArrayList<>();
	private List<TextDisplayEvent> activeEvents = new ArrayList<>();
	private List<TextDisplayEvent> processedEvents = new ArrayList<>();
	
	
	private EventManager() {
		loadEvents();
	}
	
	/**
	 * Returns the one instance of this class.
	 */
	public static EventManager get() {
		return eventManager;
	}
	
	
	/*
	 * Registered TextDisplayEvents.
	 */
	
	private TextDisplayEvent event1 = new TextDisplayEvent("You are far to the right", 
			Properties.labelfont, 
			Color.BLACK,
			DisplayType.LOCK_COORD, 
			Game.get().getShip().getPosition().copy(), 
			new Vector2D()) {
		
		@Override
		public boolean startPredicate() {
			return Game.get().getShip().getPosition().x >= 20;
		}
		
		@Override
		public boolean endPredicate() {
			return Game.get().getShip().getPosition().x < 20;
		}
	};
	
	
	public void loadEvents() {
		bufferedEvents.add(event1);
	}
	
	public void checkEvents() {
		switch (gameState) {
		case ACTIVE:
			for (TextDisplayEvent event : bufferedEvents) 
				if (event.startPredicate()) {
					activeEvents.add(event);
					RenderEngine.get().handle(event); 
				}
			break;
		case FUEL:
			break;
		case PAUSED:
			break;
		case SHOP:
			break;
		default:
			break;
		}
		
		
		bufferedEvents.removeAll(activeEvents);
	}
	
}
