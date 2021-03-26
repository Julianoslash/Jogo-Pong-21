package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	public double x, y;
	public int width, height;
	public double speed;

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 30;
		this.height = 3;
	}
	
	public void tick() {
		
		//speed  = new Random().nextDouble()*0.15;
		speed = Game.ball.speedEnemy;
		x+= (Game.ball.x - x - 15) * speed;
		
		if(x+width > Game.WIDTH - 1) {
			x = Game.WIDTH - width - 1 ;
		}
		else if(x < 1) {
			x = 1;
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, width, height);
	}
}
