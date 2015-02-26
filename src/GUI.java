import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.LinkedList;


public class GUI extends JFrame implements ActionListener, KeyListener
{
	GamePanel gp;
	private Timer t = new Timer(16, this);
	BulletList playerBullets = new BulletList(2);
	BulletList monsterBullets = new BulletList(50);
	Player player = new Player(275,725,50,50,3);
	LinkedList<Sprite> players = new LinkedList<Sprite>();
	
	public GUI()
	{
		players.add(player);
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
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_SPACE: // Press Space Bar
				Bullet temp = new Bullet(player.getX() + player.getWidth() / 2 - 5,
				player.getY(), 10, 23);
				temp.setVelocityY(-45);
				playerBullets.append(temp);
				break;
			case KeyEvent.VK_D: // Move Right
				player.setVelocityX(10);
				break;
			case KeyEvent.VK_A: // Move Left
				player.setVelocityX(-10);
				break;
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		switch (e.getKeyCode()) 
		{
			case KeyEvent.VK_D: // Move Right
					player.setVelocityX(0);
					break;
			case KeyEvent.VK_A: // Move Left
					player.setVelocityX(0);
					break;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		gp.repaint();
	}
	
}