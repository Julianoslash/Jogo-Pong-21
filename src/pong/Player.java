package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	public boolean right, left;
	public int x, y;
	public int width, height;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 30;
		this.height = 3;
	}
	
	public void tick() {
		if(right) {
			x++;
		}
		else if(left) {
			x--;
		}
		
		if(x+width > Game.WIDTH-1) {
			x = Game.WIDTH - width - 1;
		}
		else if(x < 1) {
			x = 1;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}
