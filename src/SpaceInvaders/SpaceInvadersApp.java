package SpaceInvaders;

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
import SpaceInvaders.Config;
import SpaceInvaders.Collision.BulletPlayerHandler;
import SpaceInvaders.Controls.PlayerControl;

public class SpaceInvadersApp extends GameApplication
{

	private GameEntity player;
	private PlayerControl playerControl;

	private Texture spaceshipTexture;
	private Texture enemyTexture;

	private IntegerProperty lives;

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



	@Override
	protected void initAssets()
	{
		getAssetLoader().cache();


	}

	@Override
	protected void initGame()
	{
		/*initGame(highScore == 0
	                ? new SaveData("CPU", ACHIEVEMENT_MASTER_SCORER)
	                : new SaveData(highScoreName, highScore));
		 */
		initInput();
		initLevel();
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
		playerControl.left();
		System.out.println("Moved player to the left");
	}

	@OnUserAction(name = "Move Right", type = ActionType.ON_ACTION)
	public void moveRight() {

		playerControl.right();
		System.out.println("Moved Player to the right");
	}

	@OnUserAction(name = "Shoot", type = ActionType.ON_ACTION)
	public void shoot()
	{
		/*
		playerControl.shoot();
		*/
	}

	@Override
	protected void initPhysics()
	{
		PhysicsWorld worldPhysics = getPhysicsWorld();
		worldPhysics.addCollisionHandler(new BulletPlayerHandler());


	}

	@Override
	protected void initUI()
	{
		//uiController = new GameController(getGameScene());



	}

	@Override
	protected void onUpdate()
	{


	}

	private void spawnEnemies(double x, double y)
	{
		Entity enemy = EntityCreator.newEnemy(x, y);


		getGameWorld().addEntity(enemy);
	}

	private void spawnWall(double x, double y) {
		getGameWorld().addEntity(EntityCreator.newWall(x, y));
	}
	private void initLevel() {
		for (int y = 0; y < 5; y++) {
			for (int x = 0; x < 8; x++) {
				spawnEnemies(x * (40 + 20), 100 + y * (40 + 20));
			}
		}

		spawnWall(40, getHeight() - 100);
		spawnWall(120, getHeight() - 100);

		spawnWall(getWidth() - 160, getHeight() - 100);
		spawnWall(getWidth() - 80, getHeight() - 100);

		//getInput().setProcessInput(true);
	}

	private void spawnPlayer() {
		//Create player
		player = EntityCreator.newPlayer(getWidth() / 2 - 20, getHeight() - 40);
		playerControl = player.getControlUnsafe(PlayerControl.class);

		getGameWorld().addEntity(player);
	}



	public static void main(String [] args)
	{
		launch(args);
	}


}
