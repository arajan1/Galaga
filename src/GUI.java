//import java.util.*;
//import java.awt.*;
import java.awt.event.*;

public class GUI implements KeyListener
{
	public static void main (String[] args)
	{
		GUI test = new GUI();    
	}
	
	public GUI()
	{
		
	}

	@Override
	public void keyPressed(KeyEvent evt) 
	{
		int kp = evt.getKeyCode();
		switch(kp)
		{
			case KeyEvent.VK_SPACE: 
				System.out.println("test");
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		
	}
	
	
}
