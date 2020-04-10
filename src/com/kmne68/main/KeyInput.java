package com.kmne68.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	
	public KeyInput(Handler handler) {
		
		this.handler = handler;
		
	}
	
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject tempObject = handler.gameObjects.get(i);
			
			if(tempObject.getId() == ID.Player) {
				
				// key events for object
				if(key == KeyEvent.VK_W)
					tempObject.setVelocityY(-5);
				if(key == KeyEvent.VK_S)
					tempObject.setVelocityY(5);
				if(key == KeyEvent.VK_A)
					tempObject.setVelocityX(-5);
				if(key == KeyEvent.VK_D)
					tempObject.setVelocityX(5);
			}
		}
				
	}
	
	
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject tempObject = handler.gameObjects.get(i);
			
			if(tempObject.getId() == ID.Player) {
				
				// key events for object
				if(key == KeyEvent.VK_W)
					tempObject.setVelocityY(0);
				if(key == KeyEvent.VK_S)
					tempObject.setVelocityY(0);
				if(key == KeyEvent.VK_A)
					tempObject.setVelocityX(0);
				if(key == KeyEvent.VK_D)
					tempObject.setVelocityX(0);
			}
		}
		
	}
	

}
