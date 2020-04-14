package com.kmne68.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


/**
 * 
 * @author kmne6
 * 
 * This class adds a boss enemy when the player gets to level 10
 *
 */

public class EnemyBoss extends GameObject {
	
	
	private Handler handler;
	private Random random = new Random();
	
	private int entranceTimer = 80;
	private int effectTimer 	= 50;
	

	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velocityX = 0;
		velocityY = 2;

	}
	
	
	public Rectangle getBounds() {
		
		return new Rectangle( (int) x, (int) y, 96, 96);
		
	}
	

	
	public void tick() {

		x += velocityX;
		y += velocityY;
				
		if( entranceTimer <= 0 )
			velocityY = 0;
		else entranceTimer--;
		
		if(entranceTimer <= 0)
			effectTimer--;
		if(effectTimer <= 0)
		{
			if(velocityX == 0)
				velocityX = 2;
			
			if(velocityX > 0)
				velocityX += 0.005f;
			else if(velocityX < 0)
				velocityX -= 0.005f;
			
			velocityX = Game.clamp(velocityX, -10, 10);
			
			int spawn = random.nextInt(10);
			if( spawn == 0 )
				handler.addObject(new EnemyBossBullet( (int) x + 48, (int) y + 90, ID.BasicEnemy, handler ) );
			
		}
		
//		if(y <= 0 || y >= Game.HEIGHT - 32)
//			velocityY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 96)
			velocityX *= -1;
		
//		handler.addObject(new Trail( (int) x, (int) y, ID.Trail, Color.RED, 96, 96, 0.008f, handler));
		
	}
	


	public void render(Graphics g) {

		g.setColor(Color.red);
		g.fillRect( ( int) x, (int) y, 96, 96);
		
	}

	
	
}
