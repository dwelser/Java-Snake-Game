package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import game.Game.STATE;

public class Menu extends KeyAdapter
{
	private Game game;
	private HUD hud;
	private Obj_Handler handler;
	private int key;
	private ImageIcon skull;
	
	public Menu(Game game,Obj_Handler handler,HUD hud)
	{
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		skull = new ImageIcon("SkullandCrossbones.png");
	}
	
	public void keyPressed(KeyEvent e)
	{
		key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE && game.gameState == STATE.MENU) 
		{
			
			game.gameState = STATE.ALIVE;
			Audio.loadSounds();
			Audio.getMusic("music").play();
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler,game));
			
		}
		if(key == KeyEvent.VK_SPACE && game.gameState == STATE.DEAD)
		{
			game.gameState = STATE.ALIVE;
			this.update();
			Audio.getMusic("music").play();
			//System.out.println("MENU KEY PRESSED");
			handler.resetHandler();
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler,game));
		}
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics g)
	{
		
		
		
	    
		if(game.gameState == STATE.MENU)
		{
			
			g.setColor(Color.GREEN);
			g.setFont(new Font("Retronoid", Font.BOLD, 40));
			g.drawString("[ANACONDAS]", 300, 130);
			
			/*
			 * g.setColor(Color.WHITE); g.drawRect(270, 220, 320, 250);
			 * 
			 * g.setColor(Color.CYAN); g.setFont(new Font("Retronoid", Font.ITALIC, 30));
			 * g.drawString("HIGH SCORES: ", 330, 200);
			 */
			g.setColor(Color.RED);
			g.setFont(new Font("Retronoid", Font.ITALIC, 28));
			g.drawString("TO BEGIN", 375, 200);
			
			g.setColor(Color.CYAN);
			g.setFont(new Font("Retronoid", Font.ITALIC, 26));
			g.drawString("[PRESS SPACEBAR]", 315, 250);
		}
		
		else if(game.gameState == STATE.DEAD)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			
			g.setColor(Color.WHITE);
			g.drawRect(290, 60, 310, 250);
			
			g.setColor(Color.CYAN);
			g.setFont(new Font("Retronoid", Font.BOLD, 30));
			g.drawString("GAME OVER!!", 350, 100);
			
			skull.paintIcon(game, g, 390, 120);
			
			g.setColor(Color.RED);
			g.setFont(new Font("Retronoid", Font.ITALIC, 20));
			g.drawString("SCORE: "+ hud.getScore(), 405, 225);
			
			g.setColor(Color.CYAN);
			g.setFont(new Font("Retronoid", Font.BOLD, 25));
			g.drawString("[PRESS SPACEBAR]", 330, 275);
			
			hud.render(g);
		}
		
	}
}
