package com.chromalife.SpaceInvaders;

import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.component.BoundingBoxComponent;
import com.almasb.fxgl.entity.component.PositionComponent;

import com.chromalife.SpaceInvaders.Config.*;


public class PlayerControler extends AbstractControl {

	private PositionComponent pos;
	private BoundingBoxComponent boundBox;
	private double dx = 0;

	
	@Override
	public void onUpdate(Entity entity, double arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	 
	public void left() {
        if (pos.getX() - dx >= 0)
            pos.translateX(-dx);
    }
	
	public void right() {
        if (pos.getX() + boundBox.getWidth() + dx <= Config.HEIGHT )
            pos.translateX(dx);
    }
	
}
