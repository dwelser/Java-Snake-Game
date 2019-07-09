package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class HUD 
{
	//This class is to control the view of extra content-
	//- on screen score, borders and game title
	public static int score = 0;
	
	public void update()
	{
		
	}
	public void render(Graphics g)
	{
		//Game Title
		g.setColor(Color.GREEN);
		g.setFont(new Font("Retronoid", Font.BOLD, 18));
		g.drawString("ANACONDAS!", 385, 23);
		
		//draw scores
		g.setColor(Color.GREEN);
		g.setFont(new Font("Retronoid", Font.BOLD, 18));
		g.drawString("SCORE: " + score, 30, 23);
		
		//boundries
		g.setColor(Color.WHITE);
		g.drawRect(30, 30, 845, 625);
		
	}
	
	public void score(int score)
	{
		this.score = score;
	}
	
	public int getScore()
	{
		return score;
	}
	public void resetScore()
	{
		score = 0;
	}
}
