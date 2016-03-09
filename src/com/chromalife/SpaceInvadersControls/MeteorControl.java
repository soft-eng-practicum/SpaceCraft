package com.chromalife.SpaceInvadersControls;

import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.entity.component.RotationComponent;
import javafx.geometry.Point2D;


public class MeteorControl extends AbstractControl {

    private RotationComponent rotation;
    private PositionComponent position;

    private Point2D velocity;

    @Override
    public void onAdded(Entity entity) {
        rotation = entity.getComponentUnsafe(RotationComponent.class);
        position = entity.getComponentUnsafe(PositionComponent.class);

        double w = FXGL.getDouble("settings.width");
        double h = FXGL.getDouble("settings.height");

        velocity = new Point2D(position.getX() < w / 2 ? 1 : -1, position.getY() < h / 2 ? 1 : -1)
            .normalize().multiply(Math.random() * 5 + 50);
    }

    @Override
    public void onUpdate(Entity entity, double tpf) {
        rotation.rotateBy(tpf * 10);

        position.translate(velocity.multiply(tpf));
    }
}