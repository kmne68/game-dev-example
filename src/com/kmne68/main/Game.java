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
 * This is the main class for this project. The project is based on the YouTube tutorial:
 * https://www.youtube.com/watch?v=1gir2R7G9ws&list=PLWms45O3n--6TvZmtFHaCWRZwEqnz2MHa
 *
 */

public class Game extends Canvas implements Runnable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 7981268470181882770L;
	
	
	public static final int WIDTH  = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Random random;
	private Handler handler;
	private HUD hud;
	private Spawn spawn;
	
	
	public Game() {
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "Game Dev Example", this);
		
		hud = new HUD();
		spawn = new Spawn(handler, hud);
		random = new Random();		// testing
		
		handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler));		// testing
		handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH),
																			 random.nextInt(Game.HEIGHT),
																			 ID.BasicEnemy,
																			 handler));
		//handler.addObject(new BasicEnemy(WIDTH/2 - 32, HEIGHT/2 - 32, ID.BasicEnemy, handler));

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
		} catch(Exception e) {
			
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
			
			while(delta >= 1) {
				tick();
				delta--;
			}
			
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				
				timer += 1000;
	//			System.out.println("FPS: " + frames);
				frames = 0;
			}
			
		}
		stop();
	}
	
	
	private void tick() {
		
		handler.tick();
		hud.tick();
		spawn.tick();
		
	}
	
	
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);		// the parameter is the number of buffers created
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		hud.render(g);
				
		g.dispose();
		bs.show();
		
	}
	
	
	public static float clamp(float var, float min, float max) {
		
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
		
	public static void main(String[] args) {

		new Game();
		
	}
}
