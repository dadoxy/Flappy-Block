package FlappyBlock;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Obstacle {
	BufferedImage obstacleImg = null;
	{
		try {
			obstacleImg = ImageIO
					.read(new File("src/FlappyBlock/obstacle.png"));
		} catch (IOException e) {
			System.out.println("Wrong image");
		}
	}
	BufferedImage obstacle2Img = null;
	{
		try {
			obstacle2Img = ImageIO.read(new File(
					"src/FlappyBlock/obstaclee.png"));
		} catch (IOException e) {
			System.out.println("Wrong image");
		}
	}

	private Random rand = new Random();

	protected int x;
	protected int y = rand.nextInt(FlappyBlock.HEIGHT) + 100;

	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void paint(Graphics g) {
		g.fillRect(x, y, 40, 500);
		g.drawImage(obstacleImg, x, y, null);
		g.fillRect(x, 0, 40, -FlappyBlock.HEIGHT + y - 200);
		g.drawImage(obstacle2Img, x, -FlappyBlock.HEIGHT + y - 700, null);
	}

	public void setPosition() {
		if (x <= 0 - FlappyBlock.WIDTH) {
			x = 900;
			y = rand.nextInt(y) + 150;

		}
	}

	public void colide() {
		Rectangle colide1 = new Rectangle(x, y, 40, 500);
		Rectangle colide2 = new Rectangle(x, 0, 40, -FlappyBlock.HEIGHT + y
				- 200);

		if (colide1.intersects(Block.getColider())
				|| colide2.intersects(Block.getColider())) {
			FlappyBlock.dead = true;
		}
	}
	public void move(){
		x -= 4;
	}
}
