
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class Sprite //Anything that gets drawn in the gamepanel will extend sprite
{
	double angle;//so sprite can be rotated according to velocity
	int x;
	int y;
	int width;
	int height;
	Image img;
	public Sprite(Image im, int xc, int yc, double angle)
	{
		img = im;
		angle = 0;
		x = xc;
		y = yc;
	}
	
	public void display(Graphics g, GamePanel gp) //tells the GamePanel class how to draw a sprite
	{
		AffineTransform at = new AffineTransform();
		at.setToRotation(angle);//this is how the sprites are rotated
		((Graphics2D)g).translate(x ,y);//unfortunately, the affine transformation can only rotate, so we have to shift, draw at the origin, and shift back
		((Graphics2D)g).drawImage(img, at, gp);
		((Graphics2D)g).translate(0-x,0-y);
	}
	
}
