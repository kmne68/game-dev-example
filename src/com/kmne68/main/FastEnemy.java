package com.kmne68.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class FastEnemy extends GameObject {
	
	
	private BufferedImage enemyImage;
	private Handler handler;
	

	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velocityX = 2;
		velocityY = 9;
		
		SpriteSheet spriteSheet = new SpriteSheet(Game.spriteSheet);
		
		enemyImage = spriteSheet.grabImage( 3, 1, 32, 32 );

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
		
		// TODO: in customization, make the enemy trails optional
		// handler.addObject(new Trail( (int) x, (int) y, ID.Trail, Color.cyan, 16, 16, 0.02f, handler));
		
	}
	


	public void render(Graphics g) {

		// g.setColor(Color.cyan);
		// g.fillRect( (int) x, (int) y, 16, 16);
				
		g.drawImage( enemyImage, (int) x, (int) y, null );
		
	}

	
	
}
