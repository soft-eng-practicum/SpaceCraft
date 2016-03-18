package com.chromalife.SpaceInvaders;

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
import javafx.fxml.*;
import javafx.scene.control.Label;


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
	public void loseHP()
	{
		/*Texture t = lives.get(lives.size() - 1);
		
		Animation animation = getAnimationLoseHP(t);
		animation.setOnFinished(e -> lives.remove(t));
		animation.play();
		
		Viewport viewport = gameScene.getViewport();
		
		Node blink = new Rectangle(viewport.getWidth(), viewport.getHeight(), Color.rgb());
		*/
	}
	


}