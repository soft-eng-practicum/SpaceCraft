package SpaceInvaders.Event;

/**
 * Class: 
 * 
 * @author Xavier Lazo
 * @version 1.0 
 *
 */

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event {

	public static final EventType<GameEvent> ANY =
			new EventType<>(Event.ANY, "GAME_EVENT");

	public static final EventType<GameEvent> PLAYER_GOT_HIT =
			new EventType<>(ANY, "PLAYER_GOT_HIT");

	public static final EventType<GameEvent> ENEMY_KILLED =
			new EventType<>(ANY, "ENEMY_KILLED");

	public GameEvent(@NamedArg("eventType") EventType<? extends Event> eventType) {
		super(eventType);
	}
	
	//@test
	public void testPlayerGotHit()
	{
		
	}
}
