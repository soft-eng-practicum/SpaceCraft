package SpaceCraft.Collision;

import com.almasb.ents.Entity;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.ServiceType;
import com.almasb.fxgl.physics.CollisionHandler;

import SpaceCraft.EntityCreator;
import SpaceCraft.Components.ImmuneComponent;
import SpaceCraft.Components.OwnerComponent;
import SpaceCraft.Event.GameEvent;

public class BulletPlayerHandler extends CollisionHandler
{

	public BulletPlayerHandler()
	{
		super(EntityCreator.EntityType.BULLET, EntityCreator.EntityType.PLAYER);
	}
	
	@Override
	protected void onCollisionBegin(Entity bullet, Entity player)
	{
		Object owner = bullet.getComponentUnsafe(OwnerComponent.class).getValue();
		
		// player shoots the bullet so no need to handle collision if hit by own bullet.
		if (owner == EntityCreator.EntityType.PLAYER 
				|| player.getComponentUnsafe(ImmuneComponent.class).getValue())
		{
			return;
		}
		bullet.removeFromWorld();
		
		//GameApplication.getService(ServiceType.EVENT_BUS.fireEvent(new GameEvent(GameEvent.PLAYER_GOT_HIT)));
	}
	
}
