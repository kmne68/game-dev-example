package com.kmne68.main.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.kmne68.main.Game;
import com.kmne68.main.GameObject;
import com.kmne68.main.Handler;
import com.kmne68.main.ID;
import com.kmne68.main.Trail;


/**
 * 
 * @author kmne6
 * 
 * SmartEnemy adds a new type of enemy with the ability to aim toward an enemy.
 *
 */
public class SmartEnemy extends GameObject {
	
	private Handler handler;
	private GameObject player;
	
	
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		for(int i = 0; i < handler.getGameObjects().size(); i++) {
			if(handler.getGameObjects().get(i).getId() == id.Player)
				player = handler.getGameObjects().get(i);
				
		}

	}
	
	
	public Rectangle getBounds() {
		
		return new Rectangle( (int) x, (int) y, 16, 16);
		
	}
	

	public void tick() {

		x += velocityX;
		y += velocityY;
		
		float difX = x - player.getX() - 8;
		float difY = y - player.getY() - 8;
		float distance = (float) Math.sqrt( ( x - player.getX() ) * ( x - player.getX() ) + (y - player.getY() ) * ( y - player.getY() ) );
		
		velocityX = (float) ( ( -1.0 / distance ) * difX );
		velocityY = (float) ( ( -1.0 / distance ) * difY );
		
		if(y <= 0 || y >= Game.HEIGHT - 32)
			velocityY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32)
			velocityX *= -1;
		
		handler.addObject(new Trail( (int) x, (int) y, ID.Trail, Color.green, 16, 16, 0.02f, handler));
		
	}
	


	public void render(Graphics g) {

		g.setColor(Color.green);
		g.fillRect( (int) x, (int) y, 16, 16);
		
	}

	
	
}
