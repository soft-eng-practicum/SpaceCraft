package com.chromalife.SpaceInvadersControls;

import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.component.BoundingBoxComponent;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.chromalife.SpaceInvaders.Config;


public class PlayerController extends AbstractControl {

	private PositionComponent pos;
	private BoundingBoxComponent boundBox;
	
	private double dx = 0;

	
	@Override
	public void onUpdate(Entity entity, double numb) {
		dx = Config.PLAYER_MOVE_SPEED * numb;
	}
	
	
	//causing error with pos.getX()
	public void left() {
		
		System.out.println(pos.getX()+"");
		
        if (pos.getX() - dx >= 0)
          pos.translateX(-dx);
    }
	
	//causing error
	public void right() {
        if (pos.getX() + boundBox.getWidth() + dx <= Config.WIDTH )
            pos.translateX(dx);
    }
	
}
