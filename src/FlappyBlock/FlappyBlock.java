package FlappyBlock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyBlock extends JPanel {
	private static final long serialVersionUID = -6107195907273009271L;

	private int height;
	private int width;
	private Random rand = new Random();
	private int y = rand.nextInt(400) + 150;
	private int imgX;
	private int imgX2;
	private int y2 = rand.nextInt(400) + 150;
	private int y3 = rand.nextInt(400) + 150;
	protected static String deathMsg = "Press SPACE to start !";
	protected Font font = new Font("Serif", Font.BOLD, 32);
	protected static int score = 0;
	protected Timer timer;
	protected static boolean dead = false;
	private Block player;
	protected static Obstacle obstacle1;
	protected static Obstacle obstacle2;
	protected static Obstacle obstacle3;
	
	
	BufferedImage img = null;
	{
		try {
			img = ImageIO.read(new File("src/FlappyBlock/background.jpg"));
		} catch (IOException e) {
			System.out.println("Wrong image");
		}
	}
	
	BufferedImage img2 = null;
	{
		try {
			img2 = ImageIO.read(new File("src/FlappyBlock/backgroundd.jpg"));
		} catch (IOException e) {
			System.out.println("Wrong image");
		}
	}

	public FlappyBlock(int height, int width) {
		this.height = height;
		this.width = width;
		this.player = new Block(width / 4, height / 4 + 40);
		obstacle1 = new Obstacle(700, y);
		obstacle2 = new Obstacle(1000, y2);
		obstacle3 = new Obstacle(1300, y3);
		setFocusable(true);
		addKeyListener(new PlayerControl());

		timer = new Timer(1000 / 50, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player.move();
				imgX -= 4;
				imgX2 -= 4;
				obstacle1.move();
				obstacle2.move();
				obstacle3.move();
				repaint();

				if (imgX == -800) {
					imgX = 800;
				}
				if (imgX2 == -1600) {
					imgX2 = 0;
				}
				if (obstacle1.x == height / 4 - 40 || obstacle2.x == height / 4 - 40
						|| obstacle3.x == height / 4 - 40) {
					if(dead == false){
						score ++;
					}
				}
				obstacle1.setPosition();
				obstacle2.setPosition();
				obstacle3.setPosition();

				obstacle1.colide();
				obstacle2.colide();
				obstacle3.colide();
				
				if (dead == true || player.y == 555) {
					setFont(font);
					deathMsg = "You died !! \n Press Enter for restart !  Score: " + score;
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		player.drawBlock(g, 40, 40);
		g.drawImage(img, imgX, 0, null);
		g.drawImage(img2, imgX2 + 800, 0, null);
		obstacle1.paint(g);
		obstacle2.paint(g);
		obstacle3.paint(g);
		player.paint(g);
		setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("" + score, 50, 50);
		int strlenght = (int) g.getFontMetrics(font)
				.getStringBounds(deathMsg, g).getWidth();
		int start = width / 2 - strlenght / 2;
		g.drawString(deathMsg, start + width / 2 - 180, height / 4 + 40);

	}

	private class PlayerControl extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if (player.y > 10) {
					player.jump();
					timer.start();
					deathMsg = "";

				}
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				timer.stop();
				y = rand.nextInt(400) + 150;
				y2 = rand.nextInt(400) + 150;
				y3 = rand.nextInt(400) + 150;
				player.y = height / 4 + 40;
				obstacle1.x = 800;
				obstacle1.y = y;
				obstacle2.x = 1100;
				obstacle2.y = y2;
				obstacle3.x = 1400;
				obstacle3.y = y3;
				deathMsg = "Press SPACE to start !";
				dead = false;
				score = 0;
				imgX = 0;
				imgX2 = 0;
			}
			repaint();

		}
	}

}