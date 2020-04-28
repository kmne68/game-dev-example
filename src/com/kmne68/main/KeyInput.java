package com.kmne68.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.kmne68.main.Game.STATE;


public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean[] keyDown = new boolean[4];

	Game game;
	

	public KeyInput(Handler handler, Game game) {

		this.handler = handler;
		this.game = game;;

		keyDown[0] = false; // corresponds to W key
		keyDown[1] = false; // corresponds to S key
		keyDown[2] = false; // corresponds to D key
		keyDown[3] = false; // corresponds to A key

	}
	

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject tempObject = handler.gameObjects.get(i);

			if (tempObject.getId() == ID.Player) {

				// key events for object
				if (key == KeyEvent.VK_W) {
					tempObject.setVelocityY( -handler.getSpeed() );
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setVelocityY( handler.getSpeed() );
					keyDown[1] = true;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelocityX( -handler.getSpeed() );
					keyDown[2] = true;
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setVelocityX( handler.getSpeed() );
					keyDown[3] = true;
				}
			}
		}
		
		if (key == KeyEvent.VK_P) {

			if (game.gameState == STATE.Game) {
				if (Game.paused)
					Game.paused = false;
				else
					Game.paused = true;
			}
		}
		
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		
		if(key == KeyEvent.VK_SPACE) {
			
			if(Game.gameState == STATE.Game)
				Game.gameState = STATE.Shop;
			else if( Game.gameState == STATE.Shop )
				Game.gameState = STATE.Game;
		}		

	}
	

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject tempObject = handler.gameObjects.get(i);

			if (tempObject.getId() == ID.Player) {

				// key events for object
				if (key == KeyEvent.VK_W)
					keyDown[0] = false; // tempObject.setVelocityY(0);
				if (key == KeyEvent.VK_S)
					keyDown[1] = false; // tempObject.setVelocityY(0);
				if (key == KeyEvent.VK_A)
					keyDown[2] = false; // tempObject.setVelocityX(0);
				if (key == KeyEvent.VK_D)
					keyDown[3] = false; // tempObject.setVelocityX(0);

				// vertical movement
				if (!keyDown[0] && !keyDown[1])
					tempObject.setVelocityY(0);
				// horizontal movement
				if (!keyDown[2] && !keyDown[3])
					tempObject.setVelocityX(0);

			}
		}

	}

}
