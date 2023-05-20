package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public abstract class Entity {
	public GamePanel gp;

	public int worldX, worldY;

	public int x;
	public int y;

	public int tileX, tileY;
	public int speed;

	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;

	public int spriteCounter = 0;
	public int spriteNum = 1;
	public double animationSpeed = 4;

	public boolean collisionOn = false;
	public boolean isVisible = true;
	
	public boolean moving;
	int moved;
	public boolean updatedCoord = false;

	public void drawRelative(Graphics2D g2, GamePanel gp, int screenX, int screenY) {
		//
		// g2.setColor(Color.white);
		// g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		BufferedImage image = down1;

		image = switchImage(image, direction);

		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = down1;

		image = switchImage(image, direction);

		int screenX = worldX - gp.player.worldX + gp.screenX;
		int screenY = worldY - gp.player.worldY + gp.screenY;
		if (isVisible)
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

	}

	private BufferedImage switchImage(BufferedImage image, String direction) {
		if (direction != null) {
			switch (direction) {
			case "up":
				if (spriteNum == 1)
					image = up1;

				if (spriteNum == 2)
					image = up2;
				break;
			case "down":
				if (spriteNum == 1)
					image = down1;

				if (spriteNum == 2)
					image = down2;
				break;
			case "left":
				if (spriteNum == 1)
					image = left1;

				if (spriteNum == 2)
					image = left2;
				break;
			case "right":
				if (spriteNum == 1)
					image = right1;

				if (spriteNum == 2)
					image = right2;
				break;
			default:
				image = down1;
			}
		} else {
			image = down1;
		}
		return image;
	}


	public void updateAnimationSpeed() {
		this.animationSpeed = 2.5 * speed;
	}
	
	public void update() {
		
		updateAnimationSpeed();
	}
}
