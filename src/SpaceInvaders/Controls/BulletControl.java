package SpaceInvaders.Controls;

import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.ents.component.ObjectComponent;
import com.almasb.fxgl.entity.component.BoundingBoxComponent;
import com.almasb.fxgl.entity.component.PositionComponent;

import SpaceCraft.Config;
import SpaceCraft.EntityCreator;
import SpaceInvaders.Components.*;

public class BulletControl extends AbstractControl {

	private PositionComponent position;
    private OwnerComponent owner;
    private double speed;
 
 

    public BulletControl(double speed) {
        this.speed = speed;
    }

    @Override
    public void onAdded(Entity entity) {
        owner = entity.getComponentUnsafe(OwnerComponent.class);
        position = entity.getComponentUnsafe(PositionComponent.class);
    }

    @Override
    public void onUpdate(Entity entity, double tpf) {
        position.translateY(owner.isType(EntityCreator.EntityType.PLAYER) ? -tpf * speed : tpf * speed);
    }
}
