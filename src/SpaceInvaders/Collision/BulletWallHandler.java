package SpaceInvaders.Collision;

import com.almasb.ents.Entity;
import com.almasb.fxgl.physics.CollisionHandler;

import SpaceCraft.EntityCreator;
import SpaceInvaders.Components.HealthComponent;
import SpaceInvaders.Components.OwnerComponent;

public class BulletWallHandler extends CollisionHandler
{
	public BulletWallHandler()
	{
		super(EntityCreator.EntityType.BULLET, EntityCreator.EntityType.WALL);
	}

	protected void onCollisionBegin(Entity bullet, Entity wall)
	{
		Object owner = bullet.getComponentUnsafe(OwnerComponent.class).getValue();

		if (owner == EntityCreator.EntityType.ENEMY)
		{
			bullet.removeFromWorld();

			HealthComponent health = wall.getComponentUnsafe(HealthComponent.class);
			health.setValue(health.getValue() - 1);
			if(health.getValue() == 0)
				wall.removeFromWorld();
		}
	}
}
