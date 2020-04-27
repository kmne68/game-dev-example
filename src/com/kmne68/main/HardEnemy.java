package com.kmne68.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;


public class HardEnemy extends GameObject {

	
	private BufferedImage enemyImage;
	private Handler handler;
	private Random random = new Random();

	
	public HardEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velocityX = 5;
		velocityY = 5;
		
		SpriteSheet spriteSheet = new SpriteSheet(Game.spriteSheet);
		
		enemyImage = spriteSheet.grabImage( 4, 1, 32, 32 );

	}
	
	
	public Rectangle getBounds() {
		
		return new Rectangle( (int) x, (int) y, 16, 16);
		
	}
	

	
	public void tick() {

		x += velocityX;
		y += velocityY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32) {
			if(velocityY < 0)
				velocityY = (random.nextInt(7) + 1);
			else
				velocityY = -(random.nextInt(7) + 1);
		}
		if(x <= 0 || x >= Game.WIDTH - 32) {
			if(velocityX < 0)
				velocityX = (random.nextInt(7) + 1);
			else
				velocityX = -(random.nextInt(7) + 1);
		}
		
		handler.addObject(new Trail( (int) x, (int) y, ID.Trail, Color.yellow, 16, 16, 0.02f, handler));
		
	}
	


	public void render(Graphics g) {

		// g.setColor( Color.yellow );
		// g.fillRect( ( int) x, (int) y, 16, 16);
		
		g.drawImage( enemyImage, (int) x, (int) y, null );
		
	}

	
	
}
