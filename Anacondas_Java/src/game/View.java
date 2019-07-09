package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends Canvas
{
	
	public View(int width, int height, String title, Game game)
	{
		JFrame view = new JFrame(title);
		
		view.setPreferredSize(new Dimension(width,height));
		view.setMaximumSize(new Dimension(width,height));
		view.setMinimumSize(new Dimension(width,height));
		
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setResizable(false);
		view.setLocationRelativeTo(null);
		
		view.add(game); 
		view.setVisible(true);
		
		game.start();
	}
	
}
