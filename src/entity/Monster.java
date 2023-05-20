package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import interfaces.Movable;
import main.GamePanel;

public class Monster extends Entity implements Movable {

	public Monster(GamePanel gp, int x, int y, int speed) {

		this.x = x;
		this.y = y;

		this.worldX = (x * gp.tileSize);
		this.worldY = (y * gp.tileSize);

		this.gp = gp;

		this.speed = speed;
		setDefaultValues();
		getMonsterImage();

	}

	public void getMonsterImage() {
		try {
			System.out.println("Image monster loading started");
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
		System.out.println("Image monster loading ended");
	}

	public void setDefaultValues() {

	}

	public void update() {
		this.move("down");

	}

	@Override
	public void move(String targetDirection) {

		this.collisionOn = false;

		this.direction = targetDirection;

		// CHECK TILE COLLISION
		gp.cChecker.checkTile(this);

		if (!this.collisionOn) {
			this.moving = true;
		}

		if (this.moving) {

			// IF COLLISION IS FALSE; PLAYER CAN MOVE

			switch (targetDirection) {
			case "up":
				if (!updatedCoord) {
					this.y--;
					updatedCoord = true;
				}
				this.worldY -= speed;
				this.moved += speed;

				break;
			case "down":
				if (!updatedCoord) {
					this.y++;
					updatedCoord = true;
				}
				this.worldY += speed;
				this.moved += speed;

				break;
			case "left":
				if (!updatedCoord) {
					this.x--;
					updatedCoord = true;
				}
				this.worldX -= speed;
				this.moved += speed;
				break;
			case "right":
				if (!updatedCoord) {
					this.x++;
					updatedCoord = true;
				}
				this.worldX += speed;
				this.moved += speed;

				break;
			}

			// ENDS MOVEMENT
			if (this.moved >= gp.tileSize) {
				this.moving = false;
				this.moved = 0;
				this.updatedCoord = false;
				this.worldX = this.x * gp.tileSize;
				this.worldY = this.y * gp.tileSize;

			}
			this.spriteCounter += animationSpeed;
			if (this.spriteCounter > 100) {
				if (this.spriteNum == 1)
					this.spriteNum = 2;
				else if (this.spriteNum == 2)
					this.spriteNum = 1;
				this.spriteCounter = 0;
			}
		}
	}
}
