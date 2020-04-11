package com.kmne68.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
	
	Random random = new Random();		// testing
	Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;
	}
	
	
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, 32, 32);
	}

	
	public void tick() {

		x += velocityX;		// testing
		y += velocityY;		// testing
		
		x = Game.clamp(x,  0, Game.WIDTH - 36);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
		collision();
		
	}
	
	
	private void collision() {
		
		for(int i = 0; i < handler.gameObjects.size(); i++) {
			
			GameObject tempObject = handler.gameObjects.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					// collision code
					HUD.HEALTH -= 2;
				}
				
				
			}
		}
	}

	
	public void render(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(x, y, 32, 32);		
		
	}

}
