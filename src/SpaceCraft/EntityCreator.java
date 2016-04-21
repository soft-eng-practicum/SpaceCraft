package SpaceCraft;

import java.util.Random;

import com.almasb.ents.Entity;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.ServiceType;
import com.almasb.fxgl.asset.AssetLoader;
import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.EntityView;
import com.almasb.fxgl.entity.GameEntity;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.entity.control.ExpireCleanControl;
import com.almasb.fxgl.entity.control.OffscreenCleanControl;
import com.almasb.fxgl.entity.control.ProjectileControl;
import com.almasb.fxgl.physics.HitBox;

import SpaceCraft.Components.HealthComponent;
import SpaceCraft.Components.ImmuneComponent;
import SpaceCraft.Components.OwnerComponent;
import SpaceCraft.Controls.BulletControl;
import SpaceCraft.Controls.EnemyControl;
import SpaceCraft.Controls.LaserHitControl;
import SpaceCraft.Controls.MeteorControl;
import SpaceCraft.Controls.PlayerControl;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public final class EntityCreator
{

	private static final AssetLoader assetLoader = Config.ASSET_LOADER; //this is here for error preventage

	private static final Random rand = new Random();
	
	public enum EntityType
	{
		PLAYER, ENEMY, BULLET, WALL, BONUS;
	}

	public enum BonusType
	{
		ATTACK_RATE, LIFE;
	}

	private enum RenderLayer implements com.almasb.fxgl.entity.RenderLayer
	{
		BACKGROUND(100), METEORS(200);
		private final int index;

		RenderLayer(int index)
		{
			this.index = index;
		}

		@Override
		public int index()
		{
			return index;
		}

	}


	public static Entity newBackground(double w, double h) 
	{
		GameEntity bg = new GameEntity();
		Texture bgTexture = assetLoader.loadTexture("text.png");
		bgTexture.setFitWidth(w);
		bgTexture.setFitHeight(h);

		bg.getMainViewComponent().setGraphics(bgTexture);
		bg.getMainViewComponent().setRenderLayer(RenderLayer.BACKGROUND);

		return bg;
	}
	/**
	 * 
	 * @return
	 */
	public static Entity newMeteor() {
		double w = FXGL.getDouble("settings.width");
		double h = FXGL.getDouble("settings.height");
		double x = 0, y = 0;

		// these are deliberately arbitrary to create illusion of randomness
		/* What does the value 50 effect?
		 * 
		 * 
		 * 
		 */
		if (rand.nextBoolean()) {
			// left or right
			if (rand.nextBoolean()) {
				x = -50;
			} else {
				x = w + 50;
			}

			y = rand.nextInt((int)h);
		} else {
			// top or bot
			if (rand.nextBoolean()) {
				y = -50;
			} else {
				y = h + 50;
			}

			x = rand.nextInt((int) w);
		}

		GameEntity meteor = new GameEntity();
		meteor.getPositionComponent().setValue(x, y);

		String textureName = Config.METEOR_IMAGE;

		meteor.getMainViewComponent().setTexture(textureName);
		meteor.getMainViewComponent().setRenderLayer(RenderLayer.METEORS);

		meteor.addControl(new MeteorControl());

		// add offscreen clean a bit later so that they are not cleaned from start
		GameApplication.getService(ServiceType.MASTER_TIMER)
		.runOnceAfter(() -> {
			meteor.addControl(new OffscreenCleanControl());
		}, Duration.seconds(5));

		return meteor;
	}

	public static GameEntity newPlayer(double x, double y) {
		GameEntity player = new GameEntity();
		player.getTypeComponent().setValue(EntityType.PLAYER);
		player.getPositionComponent().setValue(x, y);

		Texture texture = assetLoader.loadTexture(Config.SPACE_SHIP_IMAGE);
		texture.setPreserveRatio(true);
		texture.setFitHeight(Config.PLAYER_HEIGHT);

		player.getMainViewComponent().setView(new EntityView(texture), true);

		player.addComponent(new CollidableComponent(true));
		player.addComponent(new HealthComponent(Config.PLAYER_HEALTH));
		player.addComponent(new ImmuneComponent());
		player.addControl(new PlayerControl());

		return player;
	}

	public static Entity newEnemy(double x, double y) {
		GameEntity enemy = new GameEntity();
		enemy.getTypeComponent().setValue(EntityType.ENEMY);
		enemy.getPositionComponent().setValue(x, y);
		enemy.addComponent(new HealthComponent(Config.ENEMY_HEALTH));
		enemy.addControl(new EnemyControl());
		

		String enemyTexture;
		if(Config.ENEMIES_RANDOMIZE)
		{
			enemyTexture = Config.ENEMY_IMAGE_ARRAY[rand.nextInt(3)];
		}
		else 
		{
			enemyTexture =  Config.ENEMY_IMAGE;
		}
		Texture texture = assetLoader.loadTexture(enemyTexture);				//.toStaticAnimatedTexture(Config.ENEMY_IMAGE_SLICES, Duration.seconds(Config.ENEMY_IMAGE_PLAY_TIME));
				

		enemy.getMainViewComponent().setView(new EntityView(texture), true);
		enemy.addComponent(new CollidableComponent(true));
		

		return enemy;
	}
	
	
	

	public static Entity newBullet(Entity owner) {
		GameEntity bullet = new GameEntity();
		bullet.getTypeComponent().setValue(EntityType.BULLET);

		Point2D center = Entities.getBBox(owner)
				.getCenterWorld()
				.add(-8, 20 * (Entities.getType(owner).isType(EntityType.PLAYER) ? -1 : 1));

		bullet.getPositionComponent().setValue(center);

		bullet.addComponent(new CollidableComponent(true));
		bullet.getMainViewComponent().setView(new EntityView(assetLoader.loadTexture(Config.BULLET_IMAGE)), true);
		bullet.addControl(new ProjectileControl(new Point2D(0, Entities.getType(owner).isType(EntityType.PLAYER) ? -1 : 1), 10));
		bullet.addComponent(new OwnerComponent(Entities.getType(owner).getValue()));
		bullet.addControl(new OffscreenCleanControl());

		return bullet;
	}

	public static Entity newLaser(Entity owner) {
		GameEntity bullet = new GameEntity();
		bullet.getTypeComponent().setValue(EntityType.BULLET);

		Point2D center = Entities.getBBox(owner)
				.getCenterWorld()
				.add(-4.5, -20);

		bullet.getPositionComponent().setValue(center);

		bullet.getBoundingBoxComponent().addHitBox(new HitBox("HIT", new BoundingBox(0, 0, 9, 20)));
		bullet.addComponent(new CollidableComponent(true));
		bullet.addComponent(new OwnerComponent(Entities.getType(owner).getValue()));
		bullet.addControl(new OffscreenCleanControl());
		bullet.addControl(new BulletControl(500));

		DropShadow shadow = new DropShadow(22, Color.DARKBLUE);
		shadow.setInput(new Glow(0.8));

		EntityView view = new EntityView();
		view.addNode(assetLoader.loadTexture(Config.LAZER_IMAGE));

		Texture t = assetLoader.loadTexture(Config.LAZER_IMAGE);
		t.relocate(-2, -20);

		view.addNode(t);
		view.setEffect(shadow);

		bullet.getMainViewComponent().setView(view);

		return bullet;
	}

	public static Entity newWall(double x, double y) {
		GameEntity wall = new GameEntity();
		wall.addComponent(new HealthComponent(Config.WALL_HEALTH));
		wall.getTypeComponent().setValue(EntityType.WALL);
		wall.getPositionComponent().setValue(x, y);
		if(Config.WALL_HEALTH < Config.WALL_IMAGE_ARRAY.length)
			wall.getMainViewComponent().setView(new EntityView(assetLoader.loadTexture(Config.WALL_IMAGE_ARRAY[Config.WALL_IMAGE_ARRAY.length - Config.WALL_HEALTH])), true);
		else
			wall.getMainViewComponent().setView(new EntityView(assetLoader.loadTexture(Config.WALL_IMAGE)), true);
		wall.addComponent(new CollidableComponent(true));

		return wall;
	}

	public static Entity newBonus(double x, double y, BonusType type) {
		GameEntity bonus = new GameEntity();
		bonus.getTypeComponent().setValue(EntityType.BONUS);
		bonus.getPositionComponent().setValue(x, y);
		bonus.getMainViewComponent().setView(new EntityView(assetLoader.loadTexture(Config.BONUS_LIFE_IMAGE)), true);

		//bonus.addComponent(new SubTypeComponent(type));
		bonus.addComponent(new CollidableComponent(true));
		//bonus.addControl(new BonusControl());

		return bonus;
	}

	public static Entity newExplosion(Point2D position) {
		GameEntity explosion = new GameEntity();
		explosion.getPositionComponent().setValue(position.subtract(40, 40));

		Texture animation = assetLoader.loadTexture(Config.EXPLOSION_IMAGE).toStaticAnimatedTexture(Config.EXPLOSION_IMAGE_SLICES, Duration.seconds(Config.EXPLOSION_IMAGE_PLAY_TIME));
		animation.setFitWidth(80);
		animation.setFitHeight(80);

		explosion.getMainViewComponent().setGraphics(animation);
		explosion.addControl(new ExpireCleanControl(Duration.seconds(Config.EXPLOSION_IMAGE_PLAY_TIME)));

		return explosion;
	}

	public static Entity newLaserHit(Point2D position) {
		GameEntity laserHit = new GameEntity();
		laserHit.getPositionComponent().setValue(position.subtract(15, 15));

		Texture hit = assetLoader.loadTexture(Config.LAZER_HIT_IMAGE);
		hit.setFitWidth(15);
		hit.setFitHeight(15);

		laserHit.getMainViewComponent().setGraphics(hit);
		laserHit.addControl(new LaserHitControl());

		return laserHit;
	}
}
