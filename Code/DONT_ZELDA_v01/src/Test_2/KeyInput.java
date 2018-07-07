package Test_2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {

				if(key == KeyEvent.VK_W) tempObject.set_velY(-5);
				if(key == KeyEvent.VK_S) tempObject.set_velY(5);
				if(key == KeyEvent.VK_D) tempObject.set_velX(5);
				if(key == KeyEvent.VK_A) tempObject.set_velX(-5);
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				
				if(key == KeyEvent.VK_W) tempObject.set_velY(0);
				if(key == KeyEvent.VK_S) tempObject.set_velY(0);
				if(key == KeyEvent.VK_D) tempObject.set_velX(0);
				if(key == KeyEvent.VK_A) tempObject.set_velX(0);
			}
		}
	}
}

