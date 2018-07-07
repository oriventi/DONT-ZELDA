package Test_2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Basic_Enemy extends GameObject{

	public Basic_Enemy(int x, int y, ID id) {
		super(x, y, id);
		
		velX = 5;
		velY = 5;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 20) velX *= -1;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}

}
