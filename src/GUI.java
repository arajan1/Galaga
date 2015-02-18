import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JPanel implements ActionListener, KeyListener {
	private Timer t = new Timer(200, this);
	int x = 400;
	int y = 300;
	public GUI() 
	{
		
	}
	//Program KeyStrokes Here
	public void keyPressed(KeyEvent e) 
	{
		switch (e.getKeyCode()) 
		{
		case KeyEvent.VK_SPACE: // Press Space Bar
			System.out.println("test");
			break;
		case KeyEvent.VK_W: //Move Up
			y-=2;
			if(y<0){
				y=595;
			}
			System.out.println("test2");
			break;
		case KeyEvent.VK_D: //Move Right
			x+=2;
			if(x>800){
				x=5;
			}
			break;
		case KeyEvent.VK_A: //Move Left
			x-=2;
			if(x<0){
				x=795;
			}
			break;
		case KeyEvent.VK_S: //Move Down
			y+=2;
			if(y>600){
				y=7;
			}
			break;
		}	
	}
	//Add Images here.  There positions will be dictated by logic
	public void paintComponent(Graphics g) 
	{
		super.paintComponents(g);
		//Testcode for adding componenets.  Will constantly repaint
		 g.drawRect(x, y, 100, 100);
		 
	}
	//Starts timer
	public void start()
	{
		t.start();
	}
	//Continually Runs whatever is in this command
	public void actionPerformed(ActionEvent e) 
	{
		repaint();
	}

	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		GUI canvas = new GUI();
		f.setTitle("Galaga");
		f.add(canvas);
		f.addKeyListener(canvas);
		f.setSize(800, 600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);
		canvas.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
