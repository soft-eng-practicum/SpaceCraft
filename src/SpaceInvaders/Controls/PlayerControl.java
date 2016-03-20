package SpaceInvaders.Controls;

import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.entity.component.BoundingBoxComponent;
import com.almasb.fxgl.entity.component.PositionComponent;

import SpaceInvaders.Config;


public class PlayerControl extends AbstractControl {

	private PositionComponent pos;
	private BoundingBoxComponent boundBox;
	private double dx = 0;

	
	@Override
	public void onUpdate(Entity entity, double numb) {
		dx = Config.PLAYER_MOVE_SPEED * numb;
		
	}
	
	
	//shift player left	
	public void left() {
       try
		{
			 if (pos.getX() - dx >= 0)
            pos.translateX(-dx);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
    }
	
	//shift player right
	public void right() {
       try
		{
			 if (pos.getX() + boundBox.getWidth() + dx <= Config.WIDTH )
            pos.translateX(dx);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
    }
	
}
