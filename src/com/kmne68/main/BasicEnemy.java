package com.kmne68.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	public BasicEnemy(int x, int y, ID id) {
		super(x, y, id);
		
		velocityX = 5;
		velocityY = 5;

	}
	
	
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, 16, 16);
		
	}
	

	
	public void tick() {

		x += velocityX;
		y += velocityY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32)
			velocityY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32)
			velocityX *= -1;
		
	}
	


	public void render(Graphics g) {

		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
		
	}

	
	
}
