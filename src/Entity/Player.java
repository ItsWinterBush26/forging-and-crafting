package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GamePanel;
import game.KeyHandler;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler KeyH;
	
	public Player(GamePanel gp, KeyHandler KeyH) {
		
		this.gp = gp;
		this.KeyH = KeyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		x = 640;
		y = 320;
		speed = 4;
		direction = "down";
	}
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		if(KeyH.upPressed == true || KeyH.downPressed == true || KeyH.upPressed == true ||
				KeyH.leftPressed == true || KeyH.rightPressed == true) {
			if(KeyH.upPressed == true) {
				y -= speed;
				direction = "up";
			}
			else if(KeyH.downPressed == true) {
				y += speed;
				direction = "down";
			}
			else if(KeyH.leftPressed == true) {
				x -= speed;
				direction = "left";
			}
			else if(KeyH.rightPressed == true) {
				x += speed;
				direction = "right";
		}
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
}
	public void draw(Graphics2D g2) {
		//g2.setColor(Color.white);
		
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, x,  y,  gp.tileSize,  gp.tileSize, null);
	}

}
