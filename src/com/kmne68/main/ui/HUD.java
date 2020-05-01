package com.kmne68.main.ui;

import java.awt.Color;
import java.awt.Graphics;

import com.kmne68.main.Game;

/**
 * @author kmne6
 *
 */
public class HUD {

	public static float HEALTH = 100;
	
	private float greenValue 	= 255;
	private int bounds				= 0;
	private int level 				= 1;
	private int score 				= 0;
	
	
	public void tick() {
		
		HEALTH = Game.clamp( HEALTH, 0, 100 + bounds / 2 );
		
		greenValue = HEALTH * 2;		
		
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		
		score++;
	
	}
	
	
	public void render(Graphics g) {
		
		g.setColor(Color.GRAY);
		g.fillRect(15,  15, 200 + bounds, 32);
		
		g.setColor(new Color(75, (int) greenValue, 0));
		g.fillRect(15,  15, (int) HEALTH * 2, 32);
		
		g.setColor(Color.WHITE);
		g.drawRect(15,  15, 200 + bounds, 32);
		
		// TODO: generalize this so that it automatically is placed relative to the upper left corner
		g.drawString("Score " + score, 15, 64);
		g.drawString("Level " + level, 15, 80);
		g.drawString("Shop ", 15, 94);
		
	}
	
	
	public int getBounds() {
		
		return bounds;
	}
	
	
	public void setBounds(int boundsDelta) {
		
		this.bounds += boundsDelta;
		
	}
	
	
	public void setScore(int score) {
		
		this.score = score;
		
	}
	
	
	public int getScore() {

		return score;
	}
	
	
	public int getLevel() {
		
		return level;
		
	}
	
	
	public void setLevel(int level) {
		
		this.level = level;
		
	}
	
	
}
