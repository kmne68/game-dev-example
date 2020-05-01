package com.kmne68.main;

import java.util.Random;

import com.kmne68.main.enemies.BasicEnemy;
import com.kmne68.main.enemies.EnemyBoss;
import com.kmne68.main.enemies.FastEnemy;
import com.kmne68.main.enemies.HardEnemy;
import com.kmne68.main.enemies.SmartEnemy;
import com.kmne68.main.ui.HUD;

/**
 * 
 * @author kmne6
 * 
 *         The Spawn class is responsible for spawning enemies
 *
 */
public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random random = new Random();
	private Game game;

	private int scoreKeep = 0;

	public Spawn(Handler handler, HUD hud, Game game) {

		this.handler = handler;
		this.hud = hud;
		this.game = game;

	}

	public void tick() {

		scoreKeep++;

		if (scoreKeep >= 100) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);

			if (game.difficulty == 0) {

				if (hud.getLevel() == 2) {
					handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 3) {
					handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 4) {
					handler.addObject(
							new FastEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				} else if (hud.getLevel() == 5) {
					handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 6) {
					handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 7) {
					handler.addObject(
							new FastEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				} else if (hud.getLevel() == 8) {
					handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 10) {
					handler.clearEnemies();
					handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
				}

			} else if (game.difficulty == 1) {

				if (hud.getLevel() == 2) {
					handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 3) {
					handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 4) {
					handler.addObject(
							new FastEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				} else if (hud.getLevel() == 5) {
					handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 6) {
					handler.addObject(new HardEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.BasicEnemy, handler));
				} else if (hud.getLevel() == 7) {
					handler.addObject(
							new FastEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				} else if (hud.getLevel() == 8) {
					handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
							ID.SmartEnemy, handler));
				} else if (hud.getLevel() == 10) {
					handler.clearEnemies();
					handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
				}

			}
		}

	}

}
