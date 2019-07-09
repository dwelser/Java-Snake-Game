package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class BasicFood extends GameObject
{
	Game game;
	private ImageIcon basic;
	int x, y;
	
	public BasicFood(int x, int y, ID id, Obj_Handler handler, Game game) 
	{
		super(x, y, id);
		this.game = game;
		basic =  new ImageIcon("classic-food1.png");
		this.x = x;
		this.y = y;
		
	}

	
	public void update() 
	{
		
		
	}

	
	public void render(Graphics g) 
	{
		basic.paintIcon(game, g, x, y);
		
	}

	@Override
	public Rectangle getBounds() 
	{
		
		return new Rectangle(x,y,14,14);
	}

}
