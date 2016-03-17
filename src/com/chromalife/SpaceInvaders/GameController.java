package com.chromalife.SpaceInvaders;

import java.util.ArrayList;
import java.util.List;

import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.ui.UIController;
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
	
	
	

	@Override
	public void init()
	{
		// TODO Auto-generated method stub
		
	}

}
