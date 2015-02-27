package galagaProjectTemp;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class Sprite 
{
	float angle;//so sprite can be rotated according to velocity
	int x;
	int y;
	Image img;
	public Sprite(Image im, int xc, int yc)
	{
		img = im;
		angle = 0f;
		x = xc;
		y = yc;
	}
	
	//public Image getImage()
	//{
		//return img;
	//}
	public void display(Graphics g, GamePanel gp)
	{
		AffineTransform at = new AffineTransform();
		
		at.setToRotation(angle);
		((Graphics2D)g).translate(x, y);
		((Graphics2D)g).drawImage(img, at, gp);
		((Graphics2D)g).translate(0-x,0-y);
	}
	
}
