import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.Timer;


public class GUI extends JFrame implements ActionListener, KeyListener
{
	GamePanel gp;
	private Timer t = new Timer(16, this);
	BulletList playerBullets = new BulletList(2);
	BulletList monsterBullets = new BulletList(50);
	
	public GUI()
	{
		gp = new GamePanel();
		add(gp);
		setTitle("Galaga");
		addKeyListener(this);
		setSize(600,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		t.start();
	}
	public static void main(String[] args)
	{
		GUI game = new GUI();
	}
	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
}