package SpaceInvaders;

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
import com.almasb.fxgl.entity.control.OffscreenCleanControl;
import com.almasb.fxgl.entity.control.ProjectileControl;
import com.almasb.fxgl.physics.HitBox;

import SpaceInvaders.Controls.MeteorControl;
import SpaceInvaders.Controls.PlayerControl;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public final class EntityCreator
{
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

	private static final AssetLoader assetLoader = GameApplication.getService(ServiceType.ASSET_LOADER);

	private static final Random rand = new Random();

	public static Entity newBackground(double w, double h) 
	{
		GameEntity bg = new GameEntity();
		Texture bgTexture = assetLoader.loadTexture("textures/text.png");
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

		String textureName = "background/meteor" + (rand.nextInt(4) + 1) + ".png";

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

		Texture texture = assetLoader.loadTexture("spaceshipReduced.png");
		texture.setPreserveRatio(true);
		texture.setFitHeight(Config.PLAYER_HEIGHT);

		player.getMainViewComponent().setView(new EntityView(texture), true);

		player.addComponent(new CollidableComponent(true));
		//player.addComponent(new InvincibleComponent());
		player.addControl(new PlayerControl());

		return player;
	}

	public static Entity newEnemy(double x, double y) {
		GameEntity enemy = new GameEntity();
		enemy.getTypeComponent().setValue(EntityType.ENEMY);
		enemy.getPositionComponent().setValue(x, y);

		Texture texture = assetLoader.loadTexture("enemyReduced" + ((int)(Math.random() * 3) + 1) + ".png")
				.toStaticAnimatedTexture(2, Duration.seconds(2));

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
		bullet.getMainViewComponent().setView(new EntityView(assetLoader.loadTexture("tank_bullet.png")), true);
		bullet.addControl(new ProjectileControl(new Point2D(0, Entities.getType(owner).isType(EntityType.PLAYER) ? -1 : 1), 10));
		//bullet.addComponent(new OwnerComponent(Entities.getType(owner).getValue()));
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
		//bullet.addComponent(new OwnerComponent(Entities.getType(owner).getValue()));
		bullet.addControl(new OffscreenCleanControl());
		//bullet.addControl(new BulletControl(500));

		DropShadow shadow = new DropShadow(22, Color.DARKBLUE);
		shadow.setInput(new Glow(0.8));

		EntityView view = new EntityView();
		view.addNode(assetLoader.loadTexture("laser1.png"));

		Texture t = assetLoader.loadTexture("laser2.png");
		t.relocate(-2, -20);

		view.addNode(t);
		view.setEffect(shadow);

		bullet.getMainViewComponent().setView(view);

		return bullet;
	}

	public static Entity newWall(double x, double y) {
		GameEntity wall = new GameEntity();
		wall.getTypeComponent().setValue(EntityType.WALL);
		wall.getPositionComponent().setValue(x, y);
		wall.getMainViewComponent().setView(new EntityView(assetLoader.loadTexture("wall.png")), true);
		wall.addComponent(new CollidableComponent(true));
		//wall.addComponent(new HPComponent(7));

		return wall;
	}

	public static Entity newBonus(double x, double y, BonusType type) {
		GameEntity bonus = new GameEntity();
		bonus.getTypeComponent().setValue(EntityType.BONUS);
		bonus.getPositionComponent().setValue(x, y);
		bonus.getMainViewComponent().setView(new EntityView(assetLoader.loadTexture("life.png")), true);

		//bonus.addComponent(new SubTypeComponent(type));
		bonus.addComponent(new CollidableComponent(true));
		//bonus.addControl(new BonusControl());

		return bonus;
	}

	public static Entity newExplosion(Point2D position) {
		GameEntity explosion = new GameEntity();
		explosion.getPositionComponent().setValue(position.subtract(40, 40));

		Texture animation = assetLoader.loadTexture("explosion.png").toStaticAnimatedTexture(48, Duration.seconds(2));
		animation.setFitWidth(80);
		animation.setFitHeight(80);

		explosion.getMainViewComponent().setGraphics(animation);
		//explosion.addControl(new ExpireCleanControl(Duration.seconds(1.8)));

		return explosion;
	}

	public static Entity newLaserHit(Point2D position) {
		GameEntity laserHit = new GameEntity();
		laserHit.getPositionComponent().setValue(position.subtract(15, 15));

		Texture hit = assetLoader.loadTexture("laser_hit.png");
		hit.setFitWidth(15);
		hit.setFitHeight(15);

		laserHit.getMainViewComponent().setGraphics(hit);
		//laserHit.addControl(new LaserHitControl());

		return laserHit;
	}
}
