package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import game.Game.STATE;

public class Player extends GameObject
{
	private ImageIcon head = new ImageIcon("player1-head.png");
	private ImageIcon body = new ImageIcon("player1-body.png");
	//t1 = tail tip
	private ImageIcon tailTip = new ImageIcon("player1-t1.png");
	private ImageIcon tailBase = new ImageIcon("player1-t2.png");
	public static double default_Vel = 1.5;
	
	private Obj_Handler handler;
	private Game game;
	private Audio audio;
	private Rectangle bounds;
	
	public Player(int x, int y, ID id, Obj_Handler handler,Game game)
	{
		super(x, y, id);
		this.handler = handler;
		this.game = game;
		audio = new Audio();
		Audio.loadSounds();
	}
	@Override
	public void update() 
	{
		x += VelX;
		y += VelY;
		
		//Player cannot go beyond white boundry box
		x= Game.clamp(x,30, 845);
		y= Game.clamp(y,30, 625);
		
		if(x == 30 || x == 845 || y == 30 || y == 625)
		{
			Audio.getMusic("music").pause();
			Audio.getSound("death").play();
			game.gameState = STATE.DEAD;
			
		}
		
		collision();
	}
	private void collision()
	{
		
		//Check every object in the handler for a collision.
		for(int i = 0;i < handler.object.size();i++)
		{
			GameObject temp = handler.object.get(i);
			
			//repeat for each food type
			if(temp.getId()==ID.BasicFood)//Temp represents a food object.
			{
				//if food intersects player.
				if(getBounds().intersects(temp.getBounds()))
				{
					//Increments HUD.score
					//Makes player larger.
					Audio.getSound("eating").play();
					HUD.score += 25;
					handler.removeObject(temp);
				}
			}
			
		}
	}
	@Override
	public void render(Graphics g) 
	{
		
		head.paintIcon(game, g, x, y);
		
	}
	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle(x,y,70,70);
	}
	

}
