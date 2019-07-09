package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Obj_Handler 
{
	//Handler class
	//This class keeps track of all objects in the game
	//As well as adding and removing them when needed.
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void update()
	{
		for(int i = 0; i < object.size();i++)
		{
			GameObject tempObj = object.get(i);
			
			tempObj.update();
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size();i++)
		{
			GameObject tempObj = object.get(i);
			
			tempObj.render(g);
		}
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	public void resetHandler()
	{
		object.clear();
		System.out.println(object);
	}
}
