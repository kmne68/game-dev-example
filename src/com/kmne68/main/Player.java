package com.kmne68.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Player extends GameObject {
	
	
	private BufferedImage playerImage;
	private Random random = new Random();		// testing
	private Handler handler;

	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;
		
		SpriteSheet spriteSheet = new SpriteSheet(Game.spriteSheet);
		
		playerImage = spriteSheet.grabImage(1, 1, 32, 32);
		
	}
	
	
	public Rectangle getBounds() {
		
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	
	public void tick() {

		x += velocityX;		// testing
		y += velocityY;		// testing
		
		x = Game.clamp(x,  0, Game.WIDTH - 36);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		

		// TODO: in customization, make the enemy trails optional
		// handler.addObject(new Trail( x, y, ID.Trail, Color.WHITE, 32, 32, 0.08f, handler));
		
		collision();
		
	}
	
	
	private void collision() {
		
		for(int i = 0; i < handler.getGameObjects().size(); i++) {
			
			GameObject tempObject = handler.getGameObjects().get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					// collision code
					HUD.HEALTH -= 2;
				}
				
				
			}
		}
	}

	
	public void render(Graphics g) {

		// g.setColor(Color.WHITE);
		// g.fillRect( (int) x, (int) y, 32, 32);		
		
		g.drawImage(playerImage, (int) x, (int) y, null);
		
	}

}
