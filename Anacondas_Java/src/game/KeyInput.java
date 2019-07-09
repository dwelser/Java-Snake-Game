package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.Game.STATE;

public class KeyInput extends KeyAdapter
{
	private Obj_Handler handler;
	private boolean uP = false;
	private boolean dP = false;
	private boolean lP = false;
	private boolean rP = false;

	Game game;
	
	public KeyInput(Obj_Handler handler, Game game)
	{
		this.handler = handler;
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e)
	{
		//Set greater forward velocity (pressing VK_Up)
		//Change direction VK_Left or VK_Right
		int key = e.getKeyCode();
		
		if(game.gameState == STATE.ALIVE)
		{
			for(int i=0;i< handler.object.size();i++)
			{
				GameObject temp = handler.object.get(i);
				
				if(temp.getId() == ID.Player)
				{
					temp.setVelY(-1);
					if(key == KeyEvent.VK_LEFT)
					{
						lP=true;
						temp.setVelX(-6);
					}
					if(key == KeyEvent.VK_RIGHT)
					{
						rP = true;
						temp.setVelX(6);
					}
					if(key == KeyEvent.VK_UP)
					{
						uP=true;
						temp.setVelY(-6);
					}
					if(key == KeyEvent.VK_DOWN)
					{
						dP = true;
						temp.setVelY(6);
					}
				}
			}
		}
		
		if(key == KeyEvent.VK_SPACE)
		{
			if(game.gameState == STATE.ALIVE)
			{
				if(Game.paused == true)
				{
					Game.paused = false;
					Audio.getMusic("music").resume();
				}
				else if(Game.paused == false)
				{
					Game.paused = true;
					Audio.getMusic("music").pause();
				}
			}
			else
			{
				HUD.score = 0;
			}
			
		}

		if(key == KeyEvent.VK_Q)
		{
			System.exit(1);
		}
	}
	public void keyReleased(KeyEvent e)
	{
		//Return to original Velocity if previously VK_Up
		int key = e.getKeyCode();
		
		for(int i=0;i< handler.object.size();i++)
		{
			GameObject temp = handler.object.get(i);
			
			if(key == KeyEvent.VK_LEFT)
			{
				temp.setVelX(0);
			}
			if(key == KeyEvent.VK_RIGHT)
			{
				temp.setVelX(0);
			}
			if(key == KeyEvent.VK_UP)
			{
				temp.setVelY(0);
			}
			if(key == KeyEvent.VK_DOWN)
			{
				temp.setVelY(0);
			}
		}
	}
}
