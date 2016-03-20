package com.chromalife.SpaceInvadersControls;

import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.entity.component.MainViewComponent;

public class LaserHitControl extends AbstractControl {

    private EntityView view;

    @Override
    public void onAdded(Entity entity) {
        view = entity.getComponentUnsafe(MainViewComponent.class).getView();
    }

    @Override
    public void onUpdate(Entity entity, double tpf) {
        view.setOpacity(view.getOpacity() - tpf);

        if (view.getOpacity() <= 0) {
            entity.removeFromWorld();
        }
    }
    
}