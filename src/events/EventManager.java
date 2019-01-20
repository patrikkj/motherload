package events;

import java.util.ArrayList;
import java.util.List;

import application.Game;
import enums.GameState;
import javafx.scene.paint.Color;
import misc.Constants;
import utils.Vector2D;

public class EventManager {
	private GameState gameState = GameState.ACTIVE;
	
	private List<TextEvent> bufferedEvents = new ArrayList<>();
	private List<TextEvent> activeEvents = new ArrayList<>();
	private List<TextEvent> processedEvents = new ArrayList<>();
	
	
	public EventManager() {
		loadEvents();
	}
	
	private TextEvent event1 = new TextEvent("You are far to the right", 
			Constants.labelfont, 
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
//		switch (gameState) {
//		case ACTIVE:
//			for (TextEvent event : bufferedEvents) 
//				if (event.startPredicate()) {
//					activeEvents.add(event);
//					RenderEngine.get().handle(event); 
//				}
//			break;
//		case FUEL:
//			break;
//		case PAUSED:
//			break;
//		case SHOP:
//			break;
//		default:
//			break;
//		}
//		
//		bufferedEvents.removeAll(activeEvents);
	}
	
}
