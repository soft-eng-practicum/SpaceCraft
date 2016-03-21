package SpaceInvaders;

import java.util.ArrayList;
import java.util.List;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.ServiceType;
import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.scene.GameScene;
import com.almasb.fxgl.scene.Viewport;
import com.almasb.fxgl.ui.UIController;
import com.almasb.fxgl.ui.UIFactory;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class GameController implements UIController
{
	@FXML
	private Label scoreLabel;
	
	@FXML
	private Label highScoreLabel;
	
	@FXML
	private double livesX;
	
	@FXML
	private double livesY;
	
	private List<Texture> lives = new ArrayList<>();
	
	private GameScene gameScene;
	
	public GameController(GameScene gameScene)
	{
		this.gameScene = gameScene;
	}
	
	@Override
	public void init()
	{
		highScoreLabel.setFont(UIFactory.newFont(18));
		scoreLabel.setFont(UIFactory.newFont(18));
	}
	
	public Label getScoreLabel()
	{
		return scoreLabel;
	}
	
	public Label getHighScoreLabel()
	{
		return highScoreLabel;
	}
	
	public void addLife()
	{
		int numLives = lives.size();
		
		Texture texture = GameApplication.getService(ServiceType.ASSET_LOADER).loadTexture("life.png");
		texture.setTranslateX(livesX + 32 * numLives);
		texture.setTranslateY(livesY);
		texture.setFitHeight(16);
		texture.setFitWidth(16);
		
		lives.add(texture);
		gameScene.addUINode(texture);
		
	}
	 public void loseLife() {
	        Texture t = lives.get(lives.size() - 1);

	        Animation animation = getAnimationLoseLife(t);
	        animation.setOnFinished(e -> lives.remove(t));
	        animation.play();

	        Viewport viewport = gameScene.getViewport();

	        Node flash = new Rectangle(viewport.getWidth(), viewport.getHeight(), Color.rgb(190, 10, 15, 0.5));

	        gameScene.addUINode(flash);

	        GameApplication.getService(ServiceType.MASTER_TIMER)
	                .runOnceAfter(() -> gameScene.removeUINode(flash), Duration.seconds(1));
	    }

	    private Animation getAnimationLoseLife(Texture texture) {
	        texture.setFitWidth(64);
	        texture.setFitHeight(64);

	        Viewport viewport = gameScene.getViewport();

	        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.66), texture);
	        tt.setToX(viewport.getWidth() / 2 - texture.getFitWidth() / 2);
	        tt.setToY(viewport.getHeight() / 2 - texture.getFitHeight() / 2);

	        ScaleTransition st = new ScaleTransition(Duration.seconds(0.66), texture);
	        st.setToX(0);
	        st.setToY(0);

	        return new SequentialTransition(tt, st);
	    }
	
}