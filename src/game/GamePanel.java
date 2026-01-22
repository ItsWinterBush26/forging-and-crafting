package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;
import Tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	//Screen Settings
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 32;
	public final int maxScreenRow = 16;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	int FPS = 60;
	int currentFPS = 0;
	int drawCount = 0;

	TileManager tileM = new TileManager(this);
	KeyHandler KeyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this,KeyH);
	
	int playerX = 640;
	int playerY = 320;
	int playerSpeed = 4;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(KeyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}

	@Override
	public void run() {

	    double drawInterval = 1000000000 / FPS;
	    double nextDrawTime = System.nanoTime() + drawInterval;
	    
	    long timer = 0;
	    long lastTime = System.nanoTime();

	    while(gameThread != null) {
	        
	        long currentTime = System.nanoTime();
	        
	        update();
	        repaint();
	        
	        timer += (currentTime - lastTime);
	        lastTime = currentTime;
	        drawCount++;

	        if (timer >= 1000000000) {
	        	currentFPS = drawCount;
	            System.out.println("FPS: " + drawCount);
	            drawCount = 0;
	            timer = 0;
	        }

	        try {
	            double remainingTime = nextDrawTime - System.nanoTime();
	            remainingTime = remainingTime / 1000000;
	            
	            if(remainingTime < 0) {
	                remainingTime = 0;
	            }
	                
	            Thread.sleep((long) remainingTime);
	            nextDrawTime += drawInterval;
	            
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}

	public void update() {
		player.update();
}
	public void paintComponent(Graphics g) {
	
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2);
		g2.setColor(Color.white);
		g2.drawString("FPS: " + currentFPS, tileSize, tileSize);
		player.draw(g2);
		
		g2.dispose();
	}
}