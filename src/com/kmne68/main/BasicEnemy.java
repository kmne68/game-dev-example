package com.kmne68.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {
	
	private Handler handler;

	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velocityX = 5;
		velocityY = 5;

	}
	
	
	public Rectangle getBounds() {
		
		return new Rectangle( (int) x, (int) y, 16, 16);
		
	}
	

	
	public void tick() {

		x += velocityX;
		y += velocityY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32)
			velocityY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32)
			velocityX *= -1;
		
		handler.addObject(new Trail( (int) x, (int) y, ID.Trail, Color.RED, 16, 16, 0.02f, handler));
		
	}
	


	public void render(Graphics g) {

		g.setColor(Color.red);
		g.fillRect( ( int) x, (int) y, 16, 16);
		
	}

	
	
}
