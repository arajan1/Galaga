//WORK IN PROGRESS - DONT TOUCH
import java.util.LinkedList;
import java.awt.*;

import javax.swing.*;


public class GamePanel extends JPanel
{
	LinkedList<Sprite> sprites = new LinkedList<Sprite>();
	
	
	public void draw()
	{
		for (Sprite s: sprites)
		{
			
		}
	}
	
	@Override
	public void paint(Graphics g)
	{
		
	}
	
	protected void paintComponent(Graphics g, int x, int y, int rotation)//warning: pseudo-code ahead!!
	{
		super.paintComponent(g);
	}
	
}
