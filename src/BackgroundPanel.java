import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image bg;
	//Image bj;
	boolean isBlackJackTitle;
	
	public BackgroundPanel(Image bg) {
		this.bg = bg;
		//this.bj = bj;
		isBlackJackTitle = true;
		this.setOpaque(true);
		this.setBackground(Color.white);
		
	}
	
	public void mainBg() {
		isBlackJackTitle = false;
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.setColor(Color.WHITE);
		//g.fillRect(0, 0, getWidth(), getHeight());
		if (isBlackJackTitle) {
			//System.out.println("blackjack title");
			//g.drawImage(bj, 40, 10, bj.getWidth(null)-100, bj.getHeight(null), null);
			g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
		}
		else {
			//System.out.println("other title");
			g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
		}
	}
}
