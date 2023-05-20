package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;

	public final int tileSize = originalTileSize * scale; // 48*48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldCol;

	// FPS
	int FPS = 60;
	
	
	
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyH);

	public GamePanel(){
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);

	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		/*
		 * double drawInterval = 1000000000/FPS; //0.016667; seconds double nextDrawTime
		 * = System.nanoTime() + drawInterval; while(gameThread != null) {
		 * 
		 * 
		 * update(); repaint(); try { double remainingTime = nextDrawTime -
		 * System.nanoTime(); remainingTime = remainingTime/1000000; if (remainingTime <
		 * 0) { remainingTime = 0; } Thread.sleep((long)remainingTime);
		 * 
		 * nextDrawTime += drawInterval;
		 * 
		 * } catch (InterruptedException e) { e.printStackTrace(); } }
		 */
//		
//		//DELTA ACCUMULATOR METHOD
//		double drawInterval = 1000000000/FPS;
//		double delta = 0;
//		long lastTime = System.nanoTime();
//		long currentTime;
//		
//		while(gameThread!=null) {
//			currentTime = System.nanoTime();
//			delta += (currentTime - lastTime) / drawInterval;
//			lastTime = currentTime;
//			
//			if(delta >= 1) {
//				update();
//				repaint();
//delta--;				
//			}
//		}

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			if (delta >= 1) {
				update();
				render();

				delta = 0;
			} else {
				// Add a small delay to reduce CPU usage
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void update() {
		player.update();

	}

	public void render() {
		Toolkit.getDefaultToolkit().sync();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		tileM.draw(g2);
		player.draw(g2);

		g2.dispose();
	}

}
