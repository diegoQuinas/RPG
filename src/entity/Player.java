package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.CollisionChecker;

public class Player extends Entity {

	KeyHandler keyH;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		this.isVisible = true;

//		solidArea = new Rectangle();
//		solidArea.x = 0;
//		solidArea.y = 0;
//		solidArea.width = gp.tileSize;
//		solidArea.height = gp.tileSize;
		// solidArea.x = 8;
		// solidArea.y = 16;
		// solidArea.width = 32;
		// solidArea.height = 32;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		x = 23;
		y = 21;
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
		moving = false;
		moved = 0;

	}

	public void getPlayerImage() {
		try {
			System.out.println("Image loading started");
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Image loading ended");
	}

	public void update() {
		super.update();
		collisionOn = false;
		if (moving)
			collisionOn = false;

		if (keyH.upPressed == true && !moving) {
			direction = "up";
			gp.cChecker.checkTile(this);
			if (!collisionOn) {
				moving = true;
				y -= 1;
			}

		} else if (keyH.downPressed == true && !moving) {
			direction = "down";

			gp.cChecker.checkTile(this);
			if (!collisionOn) {
				moving = true;
				y += 1;
			}
		} else if (keyH.leftPressed == true && !moving) {
			direction = "left";

			gp.cChecker.checkTile(this);
			if (!collisionOn) {
				moving = true;
				x -= 1;
			}

		} else if (keyH.rightPressed == true && !moving) {
			direction = "right";

			gp.cChecker.checkTile(this);
			if (!collisionOn) {
				moving = true;
				x += 1;
			}
		} else if (keyH.controlPressed && keyH.upPressed == true && !moving) {
			direction = "up";

		} else if (keyH.controlPressed && keyH.downPressed == true && !moving) {
			direction = "down";

		} else if (keyH.controlPressed && keyH.leftPressed == true && !moving) {
			direction = "left";

		} else if (keyH.controlPressed && keyH.rightPressed == true && !moving) {
			direction = "right";
		}

		// if (keyH.upPressed || keyH.downPressed || keyH.leftPressed ||
		// keyH.rightPressed || keyH.controlPressed) {
		if (moving) {

			// CHECK TILE COLLISION

			// IF COLLISION IS FALSE; PLAYER CAN MOVE

			if (collisionOn == false) {
				switch (direction) {
				case "up":
					worldY -= speed;
					moved += speed;

					break;
				case "down":
					worldY += speed;
					moved += speed;

					break;
				case "left":
					worldX -= speed;
					moved += speed;
					break;
				case "right":
					worldX += speed;
					moved += speed;

					break;
				}
			}
			if (moved >= gp.tileSize) {
				moving = false;
				moved = 0;
				worldX = x * gp.tileSize;
				worldY = y * gp.tileSize;

			}
			if (!keyH.controlPressed)
				spriteCounter += animationSpeed;
			if (spriteCounter > 100) {
				if (spriteNum == 1)
					spriteNum = 2;
				else if (spriteNum == 2)
					spriteNum = 1;
				spriteCounter = 0;
			}
		}
	}
//
//	public void draw(Graphics2D g2) {
////
//		//g2.setColor(Color.white);
//		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
//
//		BufferedImage image = null;
//
//		switch (direction) {
//		case "up":
//			if (spriteNum == 1)
//				image = up1;
//
//			if (spriteNum == 2)
//				image = up2;
//			break;
//		case "down":
//			if (spriteNum == 1)
//				image = down1;
//
//			if (spriteNum == 2)
//				image = down2;
//			break;
//		case "left":
//			if (spriteNum == 1)
//				image = left1;
//
//			if (spriteNum == 2)
//				image = left2;
//			break;
//		case "right":
//			if (spriteNum == 1)
//				image = right1;
//
//			if (spriteNum == 2)
//				image = right2;
//			break;
//		}
//
//		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//	}

}
