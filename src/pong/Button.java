package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;

public class Button{

	JButton button = new JButton("-New Game-");
	
	public int width, height;
	
	public Button() {
		this.width = 100;
		this.height = 20;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.orange);
		g.setFont(new Font("ARIAL", 1, 15));
		g.drawString("# JOGO PONG #", 20, 40);
		
		g.setColor(Color.orange);
		g.setFont(new Font("ARIAL", 1, 15));
		g.drawString("-New Game-", 37, Game.HEIGHT/2-10);
		
		g.setColor(Color.orange);
		g.setFont(new Font("ARIAL", 1, 10));
		g.drawString("Press Enter", 50, Game.HEIGHT/2 + 10);
		
		g.setColor(Color.orange);
		g.setFont(new Font("ARIAL", 1, 10));
		g.drawString("Rules:", 7, Game.HEIGHT/2 + 30);
		g.drawString("Player that does first to", 12, Game.HEIGHT/2 + 42);
		g.drawString("5 points is the WINNER!", 12, Game.HEIGHT/2 + 52);
		
		g.setColor(Color.white);
		g.setFont(new Font("ARIAL", 1, 10));
		g.drawString("- Developed by Juliano Vieira -", 5, Game.HEIGHT - 7);
	}
}
