package com.kmne68.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.kmne68.main.Game.STATE;

/**
 * 
 * @author kmne6
 *
 *	Creates the menu that is displayed on game startup.
 *
 */
public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	
	
	public Menu( Game game, Handler handler ) {
		
		this.game = game;
		this.handler = handler;
		
	}
	
	
	public void mousePressed( MouseEvent e ) {
		
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if(mouseOver ( mouseX, mouseY, 210, 100, 200, 64 ) ) {
			
			game.gameState = STATE.Game;
			
		}
		
	}
	
	
	public void mouseReleased( MouseEvent e ) {
		
		
		
	}
	
	
	public void tick() {
		
		
		
	}
	
	
	public void render(Graphics g) {
		
		Font fontArial50 = new Font( "arial", 1, 50);
		Font fontArial30 = new Font( "arial", 1, 30);
		
		g.setFont(fontArial50);
		g.setColor(Color.white);
		g.drawString( "Menu", 245, 75);
		
		g.setFont(fontArial30);
		g.drawRect(210, 100, 200, 64);
		g.drawString( "Play", 280, 145);
		
		g.setFont(fontArial30);
		g.drawRect(210, 200, 200, 64);
		g.drawString( "Help", 280, 245);
		
		g.setFont(fontArial30);
		g.drawRect(210, 300, 200, 64);
		g.drawString( "Quit", 280, 345);
		
	}
	
	
	/* ****************************************** PRIVATE METHODS ****************************************** */
	
	private boolean mouseOver( int mX, int mY, int x, int y, int width, int height ) {
		
		if( ( mX > x ) && ( mX < ( x + width ) ) ) 
		{
			if ( ( mY > y ) && ( mY < ( y + height ) ) )
			{
				return true;
				
			} else 
					return false;
			
		} else 
				return false;
		
	}

}
