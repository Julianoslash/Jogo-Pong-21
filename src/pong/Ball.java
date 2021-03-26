package pong;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;
import java.util.Random;

public class Ball {

	public int width, height;
	public double x, y;
	
	public double dx, dy;
	public double speed = 1;
	public double speedEnemy = 0.05;
	
	public int reset = 0;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = this.width;
		
		int angle = new Random().nextInt((120 - 45) + 45);
		while(angle < 45 || angle > 135) {
			angle = new Random().nextInt((120 - 45) + 45);
		}
		
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		
		if(x+(dx*speed) + width >= Game.WIDTH) {
			play("/AMFMBEEP");
			dx*= -1;
		}else if(x+(dx*speed) < 0) {
			play("/AMFMBEEP");
			dx*= -1;
		}
		
		if(y >= Game.HEIGHT) {
			this.reset = 1;
		}else if(y < 0) {
			this.reset = 2;
		}
		
		Rectangle bounds = new Rectangle((int) (x+(dx*speed)), (int) (y+(dy*speed)), width, height);
		
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int) Game.enemy.x, (int) Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			play("/AMFMBEEP");
			int angle = new Random().nextInt((120 - 45) + 45);
			
			while(angle < 45 || angle > 135) {
				angle = new Random().nextInt((120 - 45) + 45);
			}
			
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy > 0) {
				dy*=-1;
			}
			
			speed+=0.05;
		}else if(bounds.intersects(boundsEnemy)) {
			play("/AMFMBEEP");
			
			int angle = new Random().nextInt((120 - 45) + 45);
			
			while(angle < 45 || angle > 135) {
				angle = new Random().nextInt((120 - 45) + 45);
			}
			
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy < 0) {
				dy*=-1;
			}
			
			speed+=0.05;
			speedEnemy  = new Random().nextDouble()*0.2 + 0.02;
		}
		
		x+=dx*speed;
		y+=dy*speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int) x,(int) y, width, height);
	}
	
	public void play(String audioName) {
		URL url = getClass().getResource(audioName+".wav");
		AudioClip audio = Applet.newAudioClip(url);
		audio.play();
	}
	
}
