package com.kmne68.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class MenuParticle extends GameObject {
	
	private Handler handler;
	private Random random = new Random();
	private Color color;
	
	private int red = random.nextInt(255);
	private int green = random.nextInt(255);
	private int blue = random.nextInt(255);	
	

	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;

		velocityX = (random.nextInt(5 - -5) + -5);
		velocityY = (random.nextInt(5 - -5) + -5);
		
		// prevent immobile particles
		if( velocityX == 0 )
			velocityX = 1;
		if( velocityY == 0 )
			velocityY = 1;
		
		color = new Color(red, green, blue, 50);

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
		
		handler.addObject(new Trail( (int) x, (int) y, ID.Trail, color, 16, 16, 0.05f, handler));
		
	}
	


	public void render(Graphics g) {

		g.setColor(color);
		g.fillRect( (int) x, (int) y, 16, 16);
		
	}

	
	
}
