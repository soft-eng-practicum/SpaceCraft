package com.chromalife.SpaceInvaders;

import com.almasb.ents.Entity;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.input.*;
import com.almasb.fxgl.input.InputMapping;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.settings.GameSettings;

import javafx.beans.property.IntegerProperty;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;

public class SpaceInvadersApp extends GameApplication
{
	
	//private GameEntity player;
	//private PlayerControl playerControl;
	
	//private IntegerProperty lives;
	
	//private GameController uiController;
	
	
	@Override
	protected void initSettings(GameSettings settings)
	{

		settings.setMenuEnabled(true);

		settings.setTitle("Space Invaders 0.1dev");

		settings.setWidth(Config.WIDTH);
		settings.setHeight(Config.HEIGHT);
		settings.setIntroEnabled(false);
		settings.setMenuEnabled(false);
		
	}
	
	private Texture spaceshipTexture;
	private Texture enemyTexture;

	@Override
	protected void initAssets()
	{
		spaceshipTexture = getAssetLoader().loadTexture("spaceshipReduced.png");
		
		
	}

	@Override
	protected void initGame()
	{
		/*GameEntity enemy = new GameEntity();
		enemy.getMainViewComponent().setView(new EntityView(enemyTexture));
		
		getGameWorld().addEntity(enemy);
		
		GameEntity spaceship = new GameEntity();
		spaceship.getPositionComponent().setValue(220,600);
		spaceship.getMainViewComponent().setView(new EntityView(spaceshipTexture));

		getGameWorld().addEntity(spaceship);
		*/
		for(int y = 0; y < 5; y++)
		{
			for(int x = 0; x <10; x++)
			{
				spawnEnemies(x * 40 + 10, y * 40 + 10);
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
		//uiController = new GameController(getGameScene());
		
		
		
	}

	@Override
	protected void onUpdate()
	{
		// TODO Auto-generated method stub
		
	}
	
	private void spawnEnemies(double x, double y)
	{
		Entity enemy = EntityCreator.newEnemy(x, y);
		
		
		getGameWorld().addEntity(enemy);
	}
	
	public static void main(String [] args)
	{
		launch(args);
	}
	

}
