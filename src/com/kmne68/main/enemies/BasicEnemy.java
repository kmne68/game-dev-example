package com.kmne68.main.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.kmne68.main.Game;
import com.kmne68.main.GameObject;
import com.kmne68.main.Handler;
import com.kmne68.main.ID;
import com.kmne68.main.SpriteSheet;


public class BasicEnemy extends GameObject {
	
	
	private BufferedImage enemyImage;
	private Handler handler;

	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velocityX = 5;
		velocityY = 5;

		SpriteSheet spriteSheet = new SpriteSheet(Game.spriteSheet);
		
		enemyImage = spriteSheet.grabImage( 2, 1, 32, 32 );
		
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
		// handler.addObject(new Trail( (int) x, (int) y, ID.Trail, Color.RED, 16, 16, 0.02f, handler));
		
	}
	


	public void render(Graphics g) {

		// g.setColor(Color.red);
		// g.fillRect( ( int) x, (int) y, 16, 16);
		
		g.drawImage(enemyImage, (int) x, (int) y, null);
		
	}

	
	
}
