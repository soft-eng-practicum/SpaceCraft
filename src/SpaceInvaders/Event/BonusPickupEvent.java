package SpaceInvaders.Event;

import SpaceInvaders.EntityCreator;
import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

public class BonusPickupEvent extends GameEvent
{
	
	public static final EventType<BonusPickupEvent> ANY =
            new EventType<>(GameEvent.ANY, "BONUS_EVENT");

    private EntityCreator.BonusType type;

    public BonusPickupEvent(@NamedArg("eventType") EventType<? extends Event> eventType, EntityCreator.BonusType type) 
    {
        super(eventType);
        this.type = type;
    }

    public SpaceInvaders.EntityCreator.BonusType getType() {
        return type;
    }

   
}
