package com.kmne68.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {
	
	Random random = new Random();		// testing

	public Player(int x, int y, ID id) {
		super(x, y, id);

		velocityX = random.nextInt(5) + 1;
		velocityY = random.nextInt(5);
//		velocityX = 1;		// testing
	}

	
	@Override
	public void tick() {

		x += velocityX;		// testing
		y += velocityY;		// testing
		
	}

	
	@Override
	public void render(Graphics g) {

		g.setColor(Color.DARK_GRAY);		// testing
		g.fillRect(x, y, 32, 32);				// testing
		
		
	}

}
