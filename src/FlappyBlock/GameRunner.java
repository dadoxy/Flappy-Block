package FlappyBlock;

import java.awt.Color;

import javax.swing.JFrame;

public class GameRunner {

	public static void main(String[] args) {
		FlappyBlock flappy = new FlappyBlock(800, 600);
		JFrame window = new JFrame("Flappy Block !!");
		window.setSize(800, 600);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setContentPane(flappy);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

}
