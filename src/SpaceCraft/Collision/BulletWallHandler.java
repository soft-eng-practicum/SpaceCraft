package SpaceCraft.Collision;

import com.almasb.ents.Entity;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.ServiceType;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.entity.component.MainViewComponent;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.physics.CollisionHandler;

import SpaceCraft.Config;
import SpaceCraft.EntityCreator;
import SpaceCraft.Components.HealthComponent;
import SpaceCraft.Components.OwnerComponent;
import javafx.geometry.Point2D;
import javafx.scene.effect.BlendMode;
import javafx.util.Duration;

public class BulletWallHandler extends CollisionHandler
{
	public BulletWallHandler()
	{
		super(EntityCreator.EntityType.BULLET, EntityCreator.EntityType.WALL);
	}

	protected void onCollisionBegin(Entity bullet, Entity wall)
	{
		Object owner = bullet.getComponentUnsafe(OwnerComponent.class).getValue();

		if (owner == EntityCreator.EntityType.WALL)
		{
			return;//
		}//
		Point2D hitPosition = bullet.getComponentUnsafe(PositionComponent.class	).getValue();
		bullet.removeFromWorld();

		HealthComponent health = wall.getComponentUnsafe(HealthComponent.class);
		health.setValue(health.getValue() - 1);


		if(health.getValue() == 0)
			wall.removeFromWorld();
		else
		{

			int numOfWallImages = Config.WALL_IMAGE_ARRAY.length;
			int healthPerImage = (int) (Config.WALL_HEALTH / numOfWallImages);

			if(healthPerImage < 1)
				healthPerImage = 1;
			try
			{
				for (int i = numOfWallImages; i > 0; i--)
				{
					if(health.getValue() < (healthPerImage * i))
					{


						wall.getComponentUnsafe(
								MainViewComponent.class).setView(
										new EntityView(
												GameApplication.getService(
														ServiceType.ASSET_LOADER).loadTexture(
																Config.WALL_IMAGE_ARRAY[Config.WALL_IMAGE_ARRAY.length - i + 1]
																)
												)
										, true);

					} 
					//I want to change the wallimage here by wall.setImage(Config.Wall_IMAGE_ARRAY[i-1]);
					//it is not .setImage, I am unsure how to do this.

				}
			} catch (NullPointerException e)
			{
				//here to catch two bullets hitting wall at the same time
				wall.removeFromWorld();
			}
			//
			//			Entity laserHit = EntityCreator.newLaserHit(hitPosition);
			//
			//			wall.getWorld().addEntity(laserHit);
			//
			//			wall.getComponentUnsafe(MainViewComponent.class).getView().setBlendMode(BlendMode.RED);
			//
			//			GameApplication.getService(ServiceType.MASTER_TIMER)
			//			.runOnceAfter(() -> {
			//				if(wall.isActive())
			//					wall.getComponentUnsafe(MainViewComponent.class).getView().setBlendMode(null);
			//			}, Duration.seconds(0.33));
		}
	}
}
