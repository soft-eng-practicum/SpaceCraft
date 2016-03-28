package SpaceInvaders.Controls;

import com.almasb.ents.AbstractControl;
import com.almasb.ents.Entity;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.ServiceType;
import com.almasb.fxgl.entity.component.BoundingBoxComponent;
import com.almasb.fxgl.entity.component.PositionComponent;
import com.almasb.fxgl.time.MasterTimer;

import SpaceInvaders.Config;
import SpaceInvaders.EntityCreator;
import SpaceInvaders.Components.ImmuneComponent;


public class PlayerControl extends AbstractControl 
{
	private PositionComponent position;
	private BoundingBoxComponent bbox;
	private ImmuneComponent immune;
	
	private MasterTimer timer;
	
	private double dx = 0;
	private double attackSpeed = Config.PLAYER_MOVE_SPEED;
	private boolean canShoot = true;
	private long lastTimeShot = 0;
	
	@Override
	public void onAdded(Entity entity)
	{
		position = entity.getComponentUnsafe(PositionComponent.class);
		bbox = entity.getComponentUnsafe(BoundingBoxComponent.class);
		immune = entity.getComponentUnsafe(ImmuneComponent.class);
		
		timer = GameApplication.getService(ServiceType.MASTER_TIMER);
	}
	

	@Override
	public void onUpdate(Entity entity, double tpf)
	{
		dx = Config.PLAYER_MOVE_SPEED * tpf;
		
		if(!canShoot)
		{
			if((timer.getNow() - lastTimeShot) / 1000000000.0 >= 1.0 / attackSpeed )
			{
				canShoot = true;
			}
		}
		
	}
	public void left()
	{
		if(position.getX() - dx >=0)
			position.translateX(-dx);
	}
	
	public void right()
	{
		if(position.getX() + bbox.getWidth() + dx <= Config.WIDTH)
			position.translateX(dx);
	}
	 public void shoot() {
		 //passes
	        if (!canShoot)
	            return;

	        canShoot = false;
	        lastTimeShot = timer.getNow();
	    //fails here
	        //Entity bullet = EntityCreator.newLaser(getEntity());

	        //getEntity().getWorld().addEntity(bullet);

	       // GameApplication.getService(ServiceType.AUDIO_PLAYER)
	               // .playSound("shoot" + (int)(Math.random() * 4 + 1) + ".wav");
	    }

	    public void enableInvincibility() {
	        immune.setValue(true);
	    }

	    public void disableInvincibility() {
	        immune.setValue(false);
	    }

	    public boolean isInvincible() {
	        return immune.getValue();
	    }

	    public void increaseAttackSpeed(double value) {
	        attackSpeed += value;
	    }
	
	
}
