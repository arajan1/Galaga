import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class Sprite 
{
	float angle;//so sprite can be rotated according to velocity
	int x;
	int y;
	Image img;
	public Sprite()
	{
		angle = 0f;
	}
	
	public Image getImage()
	{
		return img;
	}
	public void display(Graphics g, GamePanel gp)
	{
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.toRadians(angle));
		at.translate(x, y);
		((Graphics2D)g).drawImage(img, at, gp);
	}
	
}

