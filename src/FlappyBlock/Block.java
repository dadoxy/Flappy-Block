package FlappyBlock;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Block {
	private static int speed = 2;
	private static final int JUMP = -15;
	protected static int x = FlappyBlock.WIDTH / 2 - 20;
	protected static int y = FlappyBlock.HEIGHT / 2;

	BufferedImage playerImg = null;
	{
		try {
			playerImg = ImageIO.read(new File("src/FlappyBlock/player.png"));
		} catch (IOException e) {
			System.out.println("Wrong image");
		}
	}

	public Block(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public void drawBlock(Graphics g, int height, int width) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, height, width);
	}

	public void jump() {
		speed = -17;
		if (FlappyBlock.dead == true) {
			speed = 0;

		}
	}

	public void paint(Graphics g) {
		g.drawImage(playerImg, x - 56, y - 56, null);

	}

	public void move() {
		speed += 1;
		y += speed;
		if (FlappyBlock.dead == true) {
			y += 5;
		}
		if (y >= 530 || y + 20 >= 530) {
			FlappyBlock.dead = true;
			y = 530;
			speed = 0;
		}
	}

	public static Rectangle getColider() {
		return new Rectangle(x, y, 40, 40);
	}
}
