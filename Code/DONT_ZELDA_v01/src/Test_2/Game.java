package Test_2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 2064690791601536156L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private HUD hud;
	
	private Random r;
	
	public Game() {
		handler = new Handler();
		
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "DONT_ZELDA", this);
		
		hud = new HUD();
		r = new Random();
		
		for(int i = 0; i <= 5; i++) {
			handler.add_Object(new Basic_Enemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Basic_Enemy));
		}
		handler.add_Object(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler));
	}
	
	public synchronized void start() {
		if(running)
			return;
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + "\nTicks: " + updates);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
