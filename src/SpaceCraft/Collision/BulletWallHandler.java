package SpaceCraft.Collision;

import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.physics.CollisionHandler;

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

//			HealthComponent health = wall.getComponentUnsafe(HealthComponent.class);
//			health.setValue(health.getValue() - 1);
//			if(health.getValue() == 0)
				wall.removeFromWorld();
		//}
	}
}
