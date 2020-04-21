package com.kmne68.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;


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

	private Thread thread;
	private boolean running = false;

	private Random random;
	private Handler handler;
	private Handler particleHandler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;

	public enum STATE {

		// TODO: Create an About menu option and corresponding screen
		Menu, Help, Game, End

	};


	public static STATE gameState = STATE.Menu;

	public Game() {

		handler = new Handler();
		particleHandler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		AudioPlayer.loadSound();
		AudioPlayer.getMusic("music").loop();

		new Window(WIDTH, HEIGHT, "Game Dev Example", this);

		spawner = new Spawn(handler, hud);
		random = new Random(); // testing


		  if( gameState == STATE.Game) {
		  
		//  handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler)); 
		//  handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH),
		//  																 random.nextInt(Game.HEIGHT),
		//  																 ID.BasicEnemy, 
		//  																 handler));
		  
		  } else if (gameState == STATE.Menu) {
		  	for (int i = 0; i < 20; i++) {
		  		// changed handler to particleHandler
		  		particleHandler.addObject(new MenuParticle(random.nextInt(WIDTH), random.nextInt(HEIGHT), ID.MenuParticle, particleHandler));

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

		handler.tick();
		particleHandler.tick();

		if (gameState == STATE.Game) {

			hud.tick();
			spawner.tick();
			
			if ( HUD.HEALTH <= 0 ) {
				
				HUD.HEALTH = 100;
				gameState = STATE.End;				
				handler.clearEnemies();
	  		particleHandler.clearEnemies();		// added to remove particles when the game state is entered
	  		
		  	for (int i = 0; i < 20; i++) {
		  		// changed handler to particleHandler
		  		particleHandler.addObject(new MenuParticle(random.nextInt(WIDTH), random.nextInt(HEIGHT), ID.MenuParticle, particleHandler));

		  	}

				
			}

		} else if (gameState == STATE.Menu || gameState == STATE.End ) {

			menu.tick();

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

		handler.render(g);

		if (gameState == STATE.Game) {

			hud.render(g);

		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			
			menu.render(g);
			particleHandler.render(g);

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
