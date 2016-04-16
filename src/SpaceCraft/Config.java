package SpaceCraft;

public final class Config
{

	public Config() {}


	public class java
	{

	}

	//Height, Width of App
	public static final int SCREEN_WIDTH = 480;
	public static final int	SCREEN_HEIGHT = 600; //made height shorter so it can show on my screen. 

	//Player Config Settings
	public static final double PLAYER_ATTACK_SPEED = 50;
	public static final double PLAYER_MOVE_SPEED = 500;
	public static final int PLAYER_HEIGHT = 40;
	public static final String SPACE_SHIP_IMAGE = "spaceshipReduced.png";

	//Enemy Config Settings

	public static final String ENEMY_IMAGE = "enemyReduced.png";
	public static final int ENEMY_HEALTH = 5;
	//Bullet Config Settings
	public static final double BULLET_MOVE_SPEED = 500;

	//Wall Config Settings
	public static final int WALL_MIN_HEIGHT = 70 + PLAYER_HEIGHT;
	public static final String WALL_IMAGE = "wall.png";


	//Image Config Settings
	////mainly in the EntityCreator
	
	public static final String ENEMY = "enemyReduced"
			//+ ((int)(Math.random() * 3) + 1) 
			+ 1 //This line is temp
			+ ".png";

	public static final String LAZER_IMAGE = "bullet_red.png";
	public static final String BULLET_IMAGE = "tank_bullet.png";
	public static final String BONUS_LIFE_IMAGE = "life.png";
	public static final String LAZER_HIT_IMAGE = "lazer_hit.png";
	
	//explosion settings
	////Enemy Exxplosion

	public static final String EXPLOSION_IMAGE = "explosion.png";
	public static final int EXPLOSION_IMAGE_SLICES = 50;
	public static final double EXPLOSION_IMAGE_PLAY_TIME = .5; //in seconds
	


}
