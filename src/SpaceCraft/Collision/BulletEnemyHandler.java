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
import SpaceCraft.Components.OwnerComponent;
import SpaceCraft.Event.GameEvent;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BulletEnemyHandler extends CollisionHandler
{


	public BulletEnemyHandler() 
	{
		super(EntityCreator.EntityType.BULLET, EntityCreator.EntityType.ENEMY);

	}


	protected void onCollisionBegin(Entity bullet, Entity enemy)
	{
		try{
			Object owner = bullet.getComponentUnsafe(OwnerComponent.class).getValue();

			if(owner == EntityCreator.EntityType.ENEMY)
			{
				return;
			}

			Point2D hitPosition = bullet.getComponentUnsafe(PositionComponent.class	).getValue();
			bullet.removeFromWorld();

//			HealthComponent health = enemy.getComponentUnsafe(HealthComponent.class);
//			health.setValue(health.getValue() - 1);
//
//			if(health.getValue() <= 0)
//			{
				Entity explosion = EntityCreator.newExplosion(Entities.getBBox(enemy).getCenterWorld());
				enemy.getWorld().addEntity(explosion);

				
				enemy.getWorld().removeEntity(explosion);
				System.out.println("Remove the explosion");
				enemy.removeFromWorld();
//
//				//GameApplication.getService(ServiceType.AUDIO_PLAYER).playSound("explosion.wav");
//				//	GameApplication.getService(ServiceType.EVENT_BUS).fireEvent(new GameEvent(GameEvent.ENEMY_KILLED));
//			} else {
//				Entity laserHit = EntityCreator.newLaserHit(hitPosition);
//
//				enemy.getWorld().addEntity(laserHit);
//
//				enemy.getComponentUnsafe(MainViewComponent.class).getView().setBlendMode(BlendMode.RED);
//
//				GameApplication.getService(ServiceType.MASTER_TIMER)
//				.runOnceAfter(() -> {
//					if(enemy.isActive())
//						enemy.getComponentUnsafe(MainViewComponent.class).getView().setBlendMode(null);
//				}, Duration.seconds(0.33));
//			}
		}catch (NullPointerException npe){

		}
	} 

}
