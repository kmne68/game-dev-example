package com.kmne68.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 
 * @author kmne6
 * 
 * Base class for all game objects
 *
 */
public abstract class GameObject {

	protected int x, y;		// coordinate values
	protected ID id;
	protected int velocityX, velocityY;
	
	
	public GameObject(int x, int y, ID id) {
		
		this.x 	= x;
		this.y 	= y;
		this.id = id;
		
	}
	
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	public void setX(int x) {
		
		this.x = x;
		
	}
	
	
	public void setY(int y) {
		
		this.y = y;
		
	}
	
	public int getX() {
		
		return x;
		
	}
	
	
	public int getY() {
		
		return y;
		
	}
	
	
	public void setId(ID id) {
		
		this.id = id;
		
	}
	
	public ID getId() {
		
		return id;
		
	}
	

	public void setVelocityX(int velocityX) {
		
		this.velocityX = velocityX;
		
	}
	

	public int getVelocityX() {
		
		return velocityX;
		
	}


	public void setVelocityY(int velocityY) {
		
		this.velocityY = velocityY;
		
	}
	
	
	public int getVelocityY() {
		
		return velocityY;
		
	}

	
}
