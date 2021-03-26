package pong;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 160;
	public static int HEIGHT = 160;
	public static int SCALE = 3;
	
	public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	public static Button button;
	
	public int scoreEnemy = 0, scorePlayer = 0, newGame = 0, winner = 0;
	
	public boolean pressEnter = true;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		this.addKeyListener(this);
		
		button = new Button();

		player = new Player(WIDTH/2 - 15, HEIGHT-20);
		enemy = new Enemy(WIDTH/2 - 15, 15);
		ball = new Ball(WIDTH/2 + 2, HEIGHT/2 - 2);
		
		//play("Pong-a-Long");
		
	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("#-Pong");
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
		
	}
	
	public void tick() {
		
		if(this.newGame == 1) {

			player.tick();
			enemy.tick();
			ball.tick();
			
			if(ball.reset == 1) {
				this.scoreEnemy++;
				
				if(this.scoreEnemy == 5) {
					newGame = 2;
					winner = 1;
					
					this.scoreEnemy = 0;
					this.scorePlayer = 0;
					
					pressEnter = true;
				}
				
				new Game();
				return;
			}
			else if(ball.reset == 2) {
				this.scorePlayer++;
				
				if(this.scorePlayer == 5) {
					newGame = 2;
					winner = 2;
					
					this.scoreEnemy = 0;
					this.scorePlayer = 0;
					
					pressEnter = true;
				}
				
				new Game();
				return;
			}
		}
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(this.newGame == 0) {
			button.render(g);
		}
		
		else if(this.newGame == 1) {
			
			g.setColor(Color.white);
			g.drawRect(0, 13, WIDTH-1, HEIGHT-29);

			player.render(g);
			enemy.render(g);
			ball.render(g);
			
			g.setColor(Color.white);
			g.setFont(new Font("Arial",1, 10));
			g.drawString("Enemy Score: "+this.scoreEnemy, 2, 10);
			
			g.setColor(Color.white);
			g.setFont(new Font("Arial",1, 10));
			g.drawString("Player Score: "+this.scorePlayer, 2, HEIGHT-5);
			
		}
		
		else if(this.newGame == 2) {
			
			if(this.winner == 1) {
				g.setColor(Color.white);
				g.setFont(new Font("Arial", 1, 20));
				g.drawString("-Game Over-", 20, 50);
				
				g.setColor(Color.orange);
				g.setFont(new Font("Arial", 1, 20));
				g.drawString("You Lose!", 35, 80);
				
				g.setColor(Color.white);
				g.setFont(new Font("Arial", 1, 10));
				g.drawString("Press Enter", 50, HEIGHT/2+30);
			}
			else if(this.winner == 2) {
				g.setColor(Color.white);
				g.setFont(new Font("Arial", 1, 20));
				g.drawString("-Game Over-", 20, 50);
				
				g.setColor(Color.orange);
				g.setFont(new Font("Arial", 1, 20));
				g.drawString("You Win!", 35, 80);
				
				g.setColor(Color.white);
				g.setFont(new Font("Arial", 1, 10));
				g.drawString("Press Enter", 50, HEIGHT/2+30);
			}
			
		}

		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		
		bs.show();
	}
	
	
	@Override
	public void run() {
		requestFocus();
		
		while(true) {
			tick();
			render();
			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(pressEnter == true) {
				newGame = 1;
				pressEnter = false;
				new Game();
				return;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void play(String audioName) {

		URL url = getClass().getResource(audioName+".wav");
		AudioClip audio = Applet.newAudioClip(url);
		audio.loop();
		
	}

}
