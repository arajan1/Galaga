import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JPanel implements ActionListener, KeyListener
	{
	private Timer t = new Timer(1000, this);
	Player player = new Player(275,725,50,50);
	BulletList playerBullets = new BulletList();
	BulletList monsterBullets = new BulletList();
	Image img;
	boolean runonce = false;
	Monster test = new Monster(100, 100, 50, 50, 1, 100, 100);
	//Program KeyStrokes Here
	public void keyPressed(KeyEvent e) 
	{
		switch (e.getKeyCode()) 
		{
		case KeyEvent.VK_SPACE: // Press Space Bar
			Bullet temp = new Bullet(player.getX()+player.getWidth()/2-5,player.getY(),10,23);
			temp.setVelocityY(-45);
			playerBullets.append(temp);
			break;
		case KeyEvent.VK_D: //Move Right
			player.setVelocityX(10);
			break;
		case KeyEvent.VK_A: //Move Left
			player.setVelocityX(-10);
			break;
		}	

	}
	private void doDrawingBackground(Graphics g) //SetBackgroundImage
	{
		Graphics2D background = (Graphics2D) g;
		background.drawImage(img, 0, 0, null);		
	}
	private void doDrawingPlayer(Graphics g) //SetPlayerImage
	{
		Graphics2D galagaship = (Graphics2D) g;
		galagaship.drawImage(img, player.getX(), player.getY(), null);		
	}
	private void doDrawingPlayerBullet(Graphics g) //SetPlayerImage
	{
		Graphics2D bullet = (Graphics2D) g;
		playerBullets.moveToStart();
		for(int i = 0; i < playerBullets.length(); i++){
			bullet.drawImage(img,playerBullets.getValue().getX(), playerBullets.getValue().getY(),
				null);
			playerBullets.next();
		}
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponents(g);
		//DrawPlayer
		//img = new ImageIcon("Background.jpg").getImage();
		//doDrawingBackground(g);
		img = new ImageIcon("galagaship.png").getImage();
		doDrawingPlayer(g);
		img = new ImageIcon("bullet.png").getImage();
		doDrawingPlayerBullet(g);
		//AddOtherDrawings	
		g.drawRect(test.getX(), test.getY(), test.getWidth(), test.getHeight());
	}
	//Starts timer
	public void start()
	{
		t.start();
	}
	//Continually Runs whatever is in this command
	public void actionPerformed(ActionEvent e) 
	{
		/*if(runonce==false)
		{
			test.attack(player.getX(),player.getY(),playerBullets);
			runonce = true;
		} */
		repaint();
		playerBullets.traverseList();
		monsterBullets.traverseList();
		player.move();
		test.move();
		test.enter();
	/* if(test.getY()>800){
			test.setVelocityX(0);
			test.setVelocityY(0);
			test.returnToTop();
		}
		if(test.getBaseX()==test.getX()&&test.getBaseY()==test.getY())
		{
			runonce=false;
		}*/
	}
	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		GUI game = new GUI();
		f.setTitle("Galaga");
		f.add(game);
		f.addKeyListener(game);
		f.setSize(600, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);
		game.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) 
		{
		case KeyEvent.VK_D: //Move Right
			player.setVelocityX(0);
			break;
		case KeyEvent.VK_A: //Move Left
			player.setVelocityX(0);
			break;
		}	
	}
}
