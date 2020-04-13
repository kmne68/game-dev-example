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

	protected float x, y;		// coordinate values
	protected ID id;
	protected float velocityX;
	protected float velocityY;
	
	
	public GameObject(float x, float y, ID id) {
		
		this.x 	= x;
		this.y 	= y;
		this.id = id;
		
	}
	
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	public void setX(float x) {
		
		this.x = x;
		
	}
	
	
	public void setY(float y) {
		
		this.y = y;
		
	}
	
	public float getX() {
		
		return x;
		
	}
	
	
	public float getY() {
		
		return y;
		
	}
	
	
	public void setId(ID id) {
		
		this.id = id;
		
	}
	
	public ID getId() {
		
		return id;
		
	}
	

	public void setVelocityX(float velocityX) {
		
		this.velocityX = velocityX;
		
	}
	

	public float getVelocityX() {
		
		return velocityX;
		
	}


	public void setVelocityY(float velocityY) {
		
		this.velocityY = velocityY;
		
	}
	
	
	public float getVelocityY() {
		
		return velocityY;
		
	}

	
}
