package SpaceInvaders;

public final class Config
{

	public Config() {}


	public class java
	{

	}

	//Height, Width of App
	public static final int WIDTH = 480;
	public static final int	HEIGHT = 600; //made height shorter so it can show on my screen. 

	//Player Config Settings
	public static final double PLAYER_MOVE_SPEED = 50;
	public static final int PLAYER_HEIGHT = 40;

	//Bullet Config Settings
	public static final double BULLET_MOVE_SPEED = 500;

	//Wall Config Settings
	public static final int WALL_MIN_HEIGHT = 110;



	//Image Config Settings
	////mainly in the EntityCreator
	public static final String ENEMY_REDUCED = "enemyReduced"
			+ ((int)(Math.random() * 3) + 1) 
			+ ".png";

	public static final String SPACE_SHIP = "spaceshipReduced.png";
	public static final String LAZER = "bullet_red.png";
	public static final String BULLET = "tank_bullet.png";
	public static final String WALL = "wall.png";
	public static final String BONUS_LIFE = "life.png";
	public static final String EXPLOSION = "explosion.png";
	public static final String LAZER_HIT = "lazer_hit.png";
	
	


}
