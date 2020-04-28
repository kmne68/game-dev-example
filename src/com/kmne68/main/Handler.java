package com.kmne68.main;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 * 
 * @author kmne6
 * 
 * Handler loops through all of the game objects, updates them and renders them to the screen.
 *
 */
public class Handler {
	
	private int speed = 5;
	
	LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
	
	
	public void tick() {
		
		for(int i = 0; i < gameObjects.size(); i++) {
			
			GameObject tempObject = gameObjects.get(i);
			
			tempObject.tick();
		}
		
	}
	
	
	public void render(Graphics g) {
		
		for(int i = 0; i < gameObjects.size(); i++) {
			
			GameObject tempObject = gameObjects.get(i);
			
			tempObject.render(g);
			
		}
		
	}
	
	
	public void clearEnemies() {
		
		for(int i = 0; i < gameObjects.size(); i++) {
			
			GameObject tempObject = gameObjects.get(i);
			
			if ( tempObject.getId() == ID.Player )
			{
				gameObjects.clear();
				if(Game.gameState != Game.STATE.End )
					addObject(new Player( (int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this ) );
			}
			
		}
		
	}
	
	
	public void addObject(GameObject object) {
		
		this.gameObjects.add(object);
		
	}
	
	
	public void removeObject(GameObject object) {
		
		this.gameObjects.remove(object);
		
	}
	
	
	public int getSpeed() {
		
		return speed;
		
	}
	
	
	public void setSpeed(int speedDelta) {
		
		this.speed += speedDelta;
		
	}

}
