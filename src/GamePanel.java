import java.util.LinkedList;
import java.awt.*;

import javax.swing.*;


public class GamePanel extends JPanel
{
	LinkedList<Sprite> sprites = new LinkedList<Sprite>();
	
	public void setSprites(LinkedList<Sprite> slist)
	{
		sprites = slist;
		repaint();
	}
	
	@Override
	public void paint(Graphics g)
	{
		for (Sprite s: sprites)
		{
			s.display(g, this);
		}
	}
	
}
