package SpaceCraft.Collision;

import com.almasb.ents.Entity;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.ServiceType;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.component.MainViewComponent;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.physics.CollisionHandler;

import SpaceCraft.EntityCreator;
import SpaceCraft.Components.HealthComponent;
import SpaceCraft.Components.ImmuneComponent;
import SpaceCraft.Components.OwnerComponent;
import SpaceCraft.Event.GameEvent;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.util.Duration;

public class BulletPlayerHandler extends CollisionHandler
{

	public BulletPlayerHandler()
	{
		super(EntityCreator.EntityType.BULLET, EntityCreator.EntityType.PLAYER);
	}
	
	@Override
	protected void onCollisionBegin(Entity bullet, Entity player)
	{
		try
		{
			Object owner = bullet.getComponentUnsafe(OwnerComponent.class).getValue();
		
		// player shoots the bullet so no need to handle collision if hit by own bullet.
		//or if the player is immune
		if (owner == EntityCreator.EntityType.PLAYER 
				|| player.getComponentUnsafe(ImmuneComponent.class).getValue())
		{
			return;
		}
		
		Point2D hitPosition = bullet.getComponentUnsafe(PositionComponent.class	).getValue();
		bullet.removeFromWorld();

		HealthComponent health = player.getComponentUnsafe(HealthComponent.class);
		health.setValue(health.getValue() - 1);
		
		
		if(health.getValue() <= 0)
		{
			Entity explosion = EntityCreator.newExplosion(Entities.getBBox(player).getCenterWorld());
			player.getWorld().addEntity(explosion);

			
			player.removeFromWorld();
			
			

			//GameApplication.getService(ServiceType.AUDIO_PLAYER).playSound("explosion.wav");
			//	GameApplication.getService(ServiceType.EVENT_BUS).fireEvent(new GameEvent(GameEvent.ENEMY_KILLED));
		} else {
			Entity laserHit = EntityCreator.newLaserHit(hitPosition);

			player.getWorld().addEntity(laserHit);

			player.getComponentUnsafe(MainViewComponent.class).getView().setBlendMode(BlendMode.RED);

			GameApplication.getService(ServiceType.MASTER_TIMER)
			.runOnceAfter(() -> {
				if(player.isActive())
					player.getComponentUnsafe(MainViewComponent.class).getView().setBlendMode(null);
			}, Duration.seconds(0.33));
		}
		
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		//GameApplication.getService(ServiceType.EVENT_BUS.fireEvent(new GameEvent(GameEvent.PLAYER_GOT_HIT)));
	}
	
}
