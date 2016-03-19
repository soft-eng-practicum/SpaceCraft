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

//config file

import com.chromalife.SpaceInvaders.Config;

public class SpaceInvadersApp extends GameApplication
{
	
	//private GameEntity player;
	//private PlayerControl playerControl;
	
	//private IntegerProperty lives;
	
	//private GameController uiController;
	
	/*
	 * Starts 
	 */
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
	
	private GameEntity player;
	private PlayerControler playerControler;

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
		
		/*
		 * Creates the list of enemies
		 */
		for(int y = 0; y < 5; y++)
		{
			for(int x = 0; x <10; x++)
			{
				spawnEnemies(x * 40 + 10, y * 40 + 10);
			}
		}
		
		//spawn player
		spawnPlayer();

	}

	@Override
	protected void initInput()
	{
		Input input = getInput();
		
		input.addInputMapping(new InputMapping("Move Left", KeyCode.A));
		input.addInputMapping(new InputMapping("Move Right", KeyCode.D));
        input.addInputMapping(new InputMapping("Shoot", KeyCode.SPACE));
		
	}
	
	@OnUserAction(name = "Move Left", type = ActionType.ON_ACTION)
	public void moveLeft() {
		playerControler.left();
	}
	
	@OnUserAction(name = "Move Right", type = ActionType.ON_ACTION)
	public void moveRight() {
		playerControler.right();
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
		
		//getDisplay().showMessageBox("Welcome to SpaceInvaders!");
		//close message box... somehow..
	}
	
	private void spawnEnemies(double x, double y)
	{
		Entity enemy = EntityCreator.newEnemy(x, y);
		
		
		getGameWorld().addEntity(enemy);
	}
	
	private void spawnPlayer() {
		//Create player
		player = EntityCreator.newPlayer(getWidth() / 2 - 20, getHeight() - 40);
		playerControler = player.getControlUnsafe(PlayerControler.class);
		
		getGameWorld().addEntity(player);
	}
	
	public static void main(String [] args)
	{
		launch(args);
	}
	

}
