package game;

public class Spawn 
{
	private Obj_Handler handler;
	private HUD hud;
	
	public Spawn(Obj_Handler handler,HUD hud)
	{
		this.handler = handler;
		this.hud = hud;
	}
	
	public void update()
	{
		//could set a timer in this class to auto spawn and remove food
		//after a fixed period of time.
	}
}
