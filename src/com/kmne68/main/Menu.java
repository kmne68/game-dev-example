package com.kmne68.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.kmne68.main.Game.STATE;
import com.kmne68.main.enemies.BasicEnemy;
import com.kmne68.main.enemies.HardEnemy;

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
	private Random random = new Random();
	private HUD hud;
	
	
	public Menu( Game game, Handler handler, HUD hud ) {
		
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		
	}
	
	
	public void mousePressed( MouseEvent e ) {
		
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		
		if( game.gameState == STATE.Menu ) {
			
			// Play button
			if(mouseOver ( mouseX, mouseY, 210, 100, 200, 64 ) ) {
				/*
				*/
				game.gameState = STATE.Select;
				
				AudioPlayer.getSound("menu_sound").play();
				return;
				
			}
			
			// Help button
			if( mouseOver( mouseX, mouseY, 210, 300, 200, 64 ) ) {
				
				game.gameState = STATE.Help;
				AudioPlayer.getSound("menu_sound").play();
				
			}			
			
			// Quit button
			if( mouseOver(mouseX, mouseY, 210, 200, 200, 64 ) ) {
				
				System.exit(1);
				
			}
			
		}

		
		/**
		 * GameState = Select
		 */
		if( game.gameState == STATE.Select ) {
			
			// Normal button
			if(mouseOver ( mouseX, mouseY, 210, 100, 200, 64 ) ) {

				game.gameState = STATE.Game;
				
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH),
																				 random.nextInt(Game.HEIGHT),
																				 ID.BasicEnemy,
																				 handler));
				game.difficulty = 0;
				
				AudioPlayer.getSound("menu_sound").play();
				return;
				
			}
			
			// Hard button
			if( mouseOver( mouseX, mouseY, 210, 200, 200, 64 ) ) {

				game.gameState = STATE.Game;
				
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH),
																				 random.nextInt(Game.HEIGHT),
																				 ID.BasicEnemy,
																				 handler));
				game.difficulty = 1;
				
				AudioPlayer.getSound("menu_sound").play();
				return;
				
			}			
			
			// Back button
			if( mouseOver(mouseX, mouseY, 210, 300, 200, 64 ) ) {
				
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
				
			}
			
		}	
		
		
		
		// Back button for Help
		if( game.gameState == STATE.Help ) {
			if(mouseOver( mouseX, mouseY, 210, 200, 200, 64 ) ) {
				
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
				
			}
			
		}
		
		
		// Play Again button
		if( game.gameState == STATE.End ) {
			if(mouseOver( mouseX, mouseY, 210, 200, 200, 64 ) ) {
				
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);

				AudioPlayer.getSound("menu_sound").play();
			}
			
		}
		
	}
	
	
	public void mouseReleased( MouseEvent e ) {
		
		
		
	}
	
	
	public void tick() {
		
		
		
	}
	
	
	public void render(Graphics g) {
		

		Font fontArial50 = new Font( "arial", 1, 50);
		Font fontArial30 = new Font( "arial", 1, 30);
		Font fontArial15 = new Font( "arial", 1, 15);
		
		if( game.gameState == STATE.Menu ) {		
			
			g.setFont(fontArial50);
			g.setColor(Color.white);
			g.drawString( "Wave", 245, 75);
			
			g.setFont(fontArial30);
			g.drawRect(210, 100, 200, 64);
			g.drawString( "Play", 280, 145);
			
			g.setFont(fontArial30);
			g.drawRect(210, 200, 200, 64);
			g.drawString( "Quit", 280, 245);
			
			g.setFont(fontArial30);
			g.drawRect(210, 300, 200, 64);
			g.drawString( "Help", 280, 345);
		
		} else if (game.gameState == STATE.Help ) {

			g.setFont(fontArial50);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);
			
			g.setFont(fontArial15);
			g.drawString("Use the WASD keys to move and dodge enemies.", 150, 150);
			
			g.setFont(fontArial30);
			g.drawRect(210, 200, 200, 64);
			g.drawString("Back", 280, 245);
			
		} else if (game.gameState == STATE.End ) {

			g.setFont(fontArial50);
			g.setColor(Color.white);
			g.drawString("Game Over", 185, 100);
			
			g.setFont(fontArial15);
			g.drawString("You lost with a score of: " + hud.getScore(), 210, 160);
			
			g.setFont(fontArial30);
			g.drawRect(210, 210, 200, 74);
			g.drawString("Try Again", 240, 255);
			
		} else if( game.gameState == STATE.Select ) {		
			
			g.setFont(fontArial50);
			g.setColor(Color.white);
			g.drawString( "Select Difficulty", 135, 75);
			
			g.setFont(fontArial30);
			g.drawRect(210, 100, 200, 64);
			g.drawString( "Normal", 255, 145);
			
			g.setFont(fontArial30);
			g.drawRect(210, 200, 200, 64);
			g.drawString( "Hard", 280, 245);
			
			g.setFont(fontArial30);
			g.drawRect(210, 300, 200, 64);
			g.drawString( "Back", 280, 345);
		
		}
		
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
