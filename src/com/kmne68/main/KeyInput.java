package com.kmne68.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	
	public KeyInput(Handler handler) {
		
		this.handler = handler;
		
		keyDown[0] = false;		// corresponds to W key
		keyDown[1] = false;		// corresponds to S key
		keyDown[2] = false;		// corresponds to D key
		keyDown[3] = false;		// corresponds to A key
		
	}
	
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject tempObject = handler.gameObjects.get(i);
			
			if(tempObject.getId() == ID.Player) {
				
				// key events for object
				if(key == KeyEvent.VK_W) {
					tempObject.setVelocityY(-5);
					keyDown[0] = true;
				}
				if(key == KeyEvent.VK_S) {
					tempObject.setVelocityY(5);
					keyDown[1] = true;
				}
				if(key == KeyEvent.VK_A) {
					tempObject.setVelocityX(-5);
					keyDown[2] = true;
				}
				if(key == KeyEvent.VK_D) {
					tempObject.setVelocityX(5);
					keyDown[3] = true;
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(0);
				
	}
	
	
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject tempObject = handler.gameObjects.get(i);
			
			if(tempObject.getId() == ID.Player) {
				
				// key events for object
				if(key == KeyEvent.VK_W)
					keyDown[0] = false;											// tempObject.setVelocityY(0);
				if(key == KeyEvent.VK_S)
					keyDown[1] = false;											// tempObject.setVelocityY(0);
				if(key == KeyEvent.VK_A)
					keyDown[2] = false;											// tempObject.setVelocityX(0);
				if(key == KeyEvent.VK_D)
					keyDown[3] = false;											// tempObject.setVelocityX(0);
				
				// vertical movement
				if(!keyDown[0] && !keyDown[1])
					tempObject.setVelocityY(0);
				// horizontal movement
				if(!keyDown[2] && !keyDown[3])
					tempObject.setVelocityX(0);
				
			}
		}
		
	}
	

}
