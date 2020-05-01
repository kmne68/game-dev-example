package com.kmne68.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.kmne68.main.ui.HUD;


public class Shop extends MouseAdapter {
	
	
	private Handler handler;
	private HUD hud;
	
	private int B1 = 100;
	private int B2 = 100;
	private int B3 = 100;
	
	
	public Shop(Handler handler, HUD hud) {
		
		this.handler = handler;
		this.hud = hud;
	}
	
	
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.setFont(new Font ( "arial", 0, 48 ) );
		g.drawString( "SHOP", Game.WIDTH / 2 - 100, 50 );
		
		// box 1
		g.setFont(new Font ( "arial", 0, 12 ) );
		g.drawString( "Upgrade Health:", 110, 120);
		g.drawString( "Cost: " + B1, 110, 140 );
		g.drawRect(100, 100, 110, 80);
		
		// box 2
		g.drawString( "Upgrade Speed:", 260, 120);
		g.drawString( "Cost: " + B2, 260, 140 );
		g.drawRect(250, 100, 110, 80);		
		
		// box 3
		g.drawString( "Refill Health:", 410, 120);
		g.drawString( "Cost: " + B3, 410, 140 );
		g.drawRect(400, 100, 110, 80);
		
		g.drawString( "SCORE: " + hud.getScore(), Game.WIDTH / 2 - 50, 300 );
		g.drawString( "Press Space to go back", Game.WIDTH / 2 - 50, 350 );
		
	}
	
	
	public void mousePressed(MouseEvent e) {
		
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		// box 1 select, upgrade health
		if ( mouseX >= 100 && mouseX <= 210 ) {
			if( mouseY >= 100 && mouseY <= 180 ) {
				if( hud.getScore() >= B1 ) {					
					hud.setScore( hud.getScore() - B1);
					B1 += 100;
					hud.setBounds(20);
					hud.HEALTH = ( 100 + ( hud.getBounds() / 2 ) );
				}
			}
		}
		
		// box 2 select, upgrade speed
		if ( mouseX >= 250 && mouseX <= 360 ) {
			if( mouseY >= 100 && mouseY <= 180 ) {
				if( hud.getScore() >= B2 ) {					
					hud.setScore( hud.getScore() - B2);
					B2 += 100;
					handler.setSpeed( 1 );
				}
				
			}
		}
		
		// box 3 select, reset health
		if ( mouseX >= 400 && mouseX <= 510 ) {
			if( mouseY >= 100 && mouseY <= 180 ) {
				if( hud.getScore() >= B3 ) {					
					hud.setScore( hud.getScore() - B3);
					hud.HEALTH = ( 100 + ( hud.getBounds() / 2 ) );
				}
				
			}
		}
		
	}

}
