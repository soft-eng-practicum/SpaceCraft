package com.chromalife.SpaceInvaders;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.settings.GameSettings;

public class SpaceInvadersApp extends GameApplication
{
	@Override
	protected void initSettings(GameSettings settings)
	{
		settings.setWidth(480);
		settings.setHeight(800);
		settings.setIntroEnabled(false);
		settings.setMenuEnabled(false);
		
	}
	
	private Texture dropletTexture;

	@Override
	protected void initAssets()
	{
		dropletTexture = getAssetLoader().loadTexture("droplet.png");
		
	}

	@Override
	protected void initGame()
	{
		GameEntity droplet = new GameEntity();
		droplet.getPositionComponent().setValue(200,2  00);
		droplet.getMainViewComponent().setView(new EntityView(dropletTexture));
		
		getGameWorld().addEntity(droplet);
		
		
	}

	@Override
	protected void initInput()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initPhysics()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initUI()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onUpdate()
	{
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String [] args)
	{
		launch(args);
	}
	

}
