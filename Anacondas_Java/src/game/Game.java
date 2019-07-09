package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends Canvas implements Runnable
{
	
	public static final int WIDTH = 905, HEIGHT = 700;
	
	private Thread thread;
	private Random r;
	private boolean running = false;
	
	public static boolean paused = false;
	private Obj_Handler handler;
	private HUD hud;
	private Spawn spawn;
	private Menu menu;
	public enum STATE{MENU,ALIVE,DEAD};
	public STATE gameState = STATE.MENU;
	private int basicFood_count;
	private int randX, randY;
	
	public Game()
	{
		handler = new Obj_Handler();
		hud = new HUD();
		menu = new Menu(this,handler,hud);
		spawn = new Spawn(handler,hud);
		this.addKeyListener(new KeyInput(handler,this));
		this.addKeyListener(menu);
		
		Audio.loadSounds();
		r = new Random();
		basicFood_count = 0;
		
		new View(WIDTH, HEIGHT, "Java_Anaconda_Game", this);
		this.setFocusable(true);
		
		if(gameState == STATE.ALIVE)
		{
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler, this));
		}
		
		//replicate code above for food spawning
	}
	
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
		hud.update();
	}
	
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() 
	{
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running)
		{
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while(delta >= 1)
			{
				update();
				delta--;
			}
			if(running)
			{
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				frames = 0;
			}
			
		}
		stop();
	}
	
	private void update()
	{
		System.out.println("Game Update "+ gameState);
		if(gameState == STATE.ALIVE)
		{
			
			if(!paused)
			{
				basicFood_count += 5;
				
				hud.update();
				spawn.update();
				handler.update();
				
				if(basicFood_count == 650)
				{
					basicFood_count = 0;
					
					randX = r.nextInt(846);
					randY = r.nextInt(626);
					
					while(randX < 30 ||randY < 30 )
					{
						randX = r.nextInt(846);
						randY = r.nextInt(626);
					}
					
					handler.addObject(new BasicFood(randX, randY, ID.BasicFood, handler, this));
				}
				
			}
			
		}
		
		if(gameState == STATE.MENU)
		{
			menu.update();
			handler.update();
			
		}
		
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		//background-color
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		
		if(paused)
		{
			hud.render(g);
			
			g.setFont(new Font("Retronoid", Font.BOLD, 30));
			g.setColor(Color.GREEN);
			g.drawString("GAME-PAUSED", 350, HEIGHT/2-32);
		}
		
		handler.render(g);
		if(gameState == STATE.ALIVE && paused == false)
		{
			hud.render(g);
			handler.render(g);
			
			
		}
		else if(gameState == STATE.MENU || gameState == STATE.DEAD)
		{
			
			//If player is dead the menu will render with the DEAD state and display gameover.
			menu.render(g);
			hud.update();
			handler.render(g);
			//System.out.println("Game Render " + gameState);
		}
		
		g.dispose();
		bs.show();
	}
	//Credit to clamp method to Youtube channel: RealTutsGML
	//if our X value (var) is greater than room width,
	//return var = room width so you cant surpass
	public static int clamp(int var, int min, int max)
	{
		if(var >= max)
		{
			return var = max;
		}
		else if(var <= min)
		{
			return var = min;
		}
		else
			return var;
	}
	
	public static void main(String[] args) 
	{
		new Game();
	}
	
	
}
