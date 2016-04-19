package SpaceCraft.Collision;

import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.physics.CollisionHandler;

import SpaceCraft.Config;
import SpaceCraft.EntityCreator;
import SpaceCraft.Components.HealthComponent;
import SpaceCraft.Components.OwnerComponent;
import javafx.geometry.Point2D;

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

//		int numOfWallImages = Config.WALL_IMAGE_ARRAY.length;
//		int healthPerImage = (int) (Config.WALL_HEALTH / numOfWallImages);
//		
//		if(healthPerImage < 1)
//			healthPerImage = 1;
//		
//		for (int i = numOfWallImages; i > 0; i--)
//		{
//			if(health.getValue() < (healthPerImage * i))
//				;//I want to change the wallimage here by wall.setImage(Config.Wall_IMAGE_ARRAY[i-1]);
//			//it is not .setImage, I am unsure how to do this.
//				
//		}
		
		if(health.getValue() == 0)
			wall.removeFromWorld();

	}
}
