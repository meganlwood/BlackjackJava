import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Card {
	private int value;
	private String type;
	
	public Card(int v, String t) {
		value = v;
		type = t;
	}
	
	public static Image getCardBackImage() throws IOException {
		BufferedImage i = resize(ImageIO.read(new File("assets/otherImages/cardback.png")), 100,145);
		//BufferedImage i = ImageIO.read(new File("cards/10_of_clubs.png"));
		
		return i;		
	}
	
	public Image getImage() throws IOException {
		//1-ace
		//11-jack
		//12-queen
		//13-king
		String valueString = "";
		switch (value) {
		case 1: valueString = "ace";
				break;
		case 11:valueString = "jack";
				break;
		case 12:valueString = "queen";
				break;
		case 13:valueString = "king";
				break;
		default: valueString = "" + value;
		}
		BufferedImage i = resize(ImageIO.read(new File("assets/cards/" + valueString + "_of_" + type + ".png")), 100,145);
		//BufferedImage i = ImageIO.read(new File("cards/10_of_clubs.png"));
		
		return i;		
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {  
	    int w = img.getWidth();  
	    int h = img.getHeight();  
	    BufferedImage dimg = new BufferedImage(newW, newH, img.getType());  
	    Graphics2D g = dimg.createGraphics();  
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	    RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
	    g.dispose();  
	    return dimg;  
	} 
	
	public int getValue() {
		if (value == 1) return 11;
		else if (value == 11 || value == 12 || value == 13) return 10;
		return value;
	}
	
	public String getType() {
		return type;
	}
}
