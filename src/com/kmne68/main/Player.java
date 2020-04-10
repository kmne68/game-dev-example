package com.kmne68.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {
	
	Random random = new Random();		// testing

	public Player(int x, int y, ID id) {
		super(x, y, id);

	}

	
	@Override
	public void tick() {

		x += velocityX;		// testing
		y += velocityY;		// testing
		
	}

	
	@Override
	public void render(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillRect(x, y, 32, 32);
		
		
	}

}
