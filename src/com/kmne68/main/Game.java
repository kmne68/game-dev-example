package com.kmne68.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import com.kmne68.main.assetmanagement.AudioPlayer;
import com.kmne68.main.assetmanagement.BufferedImageLoader;
import com.kmne68.main.ui.HUD;
import com.kmne68.main.ui.KeyInput;
import com.kmne68.main.ui.Window;

/**
 * 
 * @author kmne6
 * 
 *         This is the main class for this project. The project is based on the
 *         YouTube tutorial:
 *         https://www.youtube.com/watch?v=1gir2R7G9ws&list=PLWms45O3n--6TvZmtFHaCWRZwEqnz2MHa
 *
 */

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7981268470181882770L;

	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static BufferedImage spriteSheet;

	public static boolean paused = false;
	public int difficulty = 0;					// 0 = normal, 1 = hard

	private boolean running = false;

	private Thread thread;
	private Random random;
	private Handler handler;
	private Handler particleHandler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;

	public enum STATE {

		// TODO: Create an About menu option and corresponding screen
		Menu, Help, Shop, Game, End, Select

	};

	public static STATE gameState = STATE.Menu;

	
	public Game() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			System.out.println("loaded");
		} catch (Exception e) {
			e.printStackTrace();
		}

		handler 				= new Handler();
		particleHandler = new Handler();
		hud 						= new HUD();
		shop 						= new Shop(handler, hud);
		menu 						= new Menu(this, handler, hud);
		
		this.addKeyListener( new KeyInput ( handler, this ) );
		this.addMouseListener(menu);
		this.addMouseListener(shop);

		AudioPlayer.loadSound();
		AudioPlayer.getMusic("music").loop();

		new Window( WIDTH, HEIGHT, "Game Dev Example", this );

		spawner = new Spawn(handler, hud, this);
		random = new Random(); // testing

		if (gameState == STATE.Game) {

			// NOTE: in the tutorial, the code commented out below remains in the Game constructor, but is not necessary
			// handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player,
			// handler));
			// handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH),
			// random.nextInt(Game.HEIGHT),
			// ID.BasicEnemy,
			// handler));

		} else if (gameState == STATE.Menu) {
			for (int i = 0; i < 20; i++) {
				// changed handler to particleHandler
				particleHandler.addObject(
						new MenuParticle(random.nextInt(WIDTH), random.nextInt(HEIGHT), ID.MenuParticle, particleHandler));

			}
		}
	}

	// start our thread, this refers to the current instance of Game
	public synchronized void start() {

		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {

		try {

			thread.join();
			running = false;
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void run() {

		// set up game loop
		this.requestFocus();

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				tick();
				delta--;
			}

			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {

				timer += 1000;
				// System.out.println("FPS: " + frames);
				frames = 0;
			}

		}
		stop();
	}

	private void tick() {

		particleHandler.tick();

		if (gameState == STATE.Game) {

			if (!paused) {

				hud.tick();
				spawner.tick();
				handler.tick();

				if (HUD.HEALTH <= 0) {

					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemies();
					particleHandler.clearEnemies(); // added to remove particles when the game state is entered

					for (int i = 0; i < 20; i++) {
						// changed handler to particleHandler
						particleHandler.addObject(
								new MenuParticle(random.nextInt(WIDTH), random.nextInt(HEIGHT), ID.MenuParticle, particleHandler));
					}

				}

			}

		} else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select) {

			menu.tick();
			handler.tick();

		}

	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3); // the parameter is the number of buffers created
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

//		handler.render(g);
		
		if(paused) {
			
			g.setColor( Color.orange );
			g.drawString( "PAUSED", 100, 100 );
			
		}

		if (gameState == STATE.Game) {

			hud.render(g);
			handler.render(g);

		} 
		else if ( gameState == STATE.Shop ) {
			
			shop.render(g);
			
		}		
		else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select ) {

			menu.render(g);
			particleHandler.render(g);
			handler.render(g);

		}

		g.dispose();
		bs.show();

	}

	public static float clamp(float var, float min, float max) {

		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public static void main(String[] args) {

		new Game();

	}
}
