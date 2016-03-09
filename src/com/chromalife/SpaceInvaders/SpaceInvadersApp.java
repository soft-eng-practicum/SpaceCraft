package com.chromalife.SpaceInvaders;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.input.*;
import com.almasb.fxgl.input.InputMapping;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;

public class SpaceInvadersApp extends GameApplication
{
	
	
	@Override
	protected void initSettings(GameSettings settings)
	{

		settings.setMenuEnabled(true);

		settings.setTitle("Space Invaders 0.1dev");

		settings.setWidth(480);
		settings.setHeight(800);
		settings.setIntroEnabled(false);
		settings.setMenuEnabled(false);
		
	}
	
	private Texture spaceshipTexture;
	private Texture enemyTexture;

	@Override
	protected void initAssets()
	{
		spaceshipTexture = getAssetLoader().loadTexture("spaceshipReduced.png");
		enemyTexture = getAssetLoader().loadTexture("enemyReduced.png");
		
	}

	@Override
	protected void initGame()
	{
		GameEntity enemy = new GameEntity();
		enemy.getMainViewComponent().setView(new EntityView(enemyTexture));
		
		getGameWorld().addEntity(enemy);
		
		GameEntity spaceship = new GameEntity();
		spaceship.getPositionComponent().setValue(220,600);
		spaceship.getMainViewComponent().setView(new EntityView(spaceshipTexture));

		getGameWorld().addEntity(spaceship);
		
		for(int y = 0; y < 5; y++)
		{
			for(int x = 0; x <10; x++)
			{
				initEnemies(x * 40 + 10, y * 40 + 10);
			}
		}

	}

	@Override
	protected void initInput()
	{
		Input input = getInput();
		
		input.addInputMapping(new InputMapping("Move Left", KeyCode.A));
		input.addInputMapping(new InputMapping("Move Right", KeyCode.D));
        input.addInputMapping(new InputMapping("Shoot", KeyCode.F));
		
	}

	@Override
	protected void initPhysics()
	{
		//PhysicsWorld worldPhysics = getPhysicsWorld();
		//worldPhysics.addCollisionHandler(new BulletPlayerHandler());
		
		
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
	
	private void initEnemies(double x, double y)
	{
		
		
		
		Texture enemyTexture = getAssetLoader().loadTexture("enemy.png");
		enemyTexture.setFitWidth(40);
		enemyTexture.setFitHeight(40);
		
		
	}
	
	public static void main(String [] args)
	{
		launch(args);
	}
	

}
