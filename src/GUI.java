/*import java.awt.event.*;
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
	
} */

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.*;

public class GUI extends JPanel implements ActionListener, KeyListener {
	private Timer t = new Timer(20, this);
	Player player = new Player(275, 725, 50, 50, 3);
	BulletList playerBullets = new BulletList(2);
	Random rand = new Random();
	BulletList monsterBullets = new BulletList(100);
	LinkedList<Monster> monsters = new LinkedList<Monster>();
	Image img;
	boolean runonce = false;
	boolean hit = false;
	boolean donereturning = true;
	boolean done = false;
	boolean doublefighter = false;
	Monster test = new Monster(0, 0, 0, 0, 0, 100, 100);
	Monster test2 = new Monster(0, 0, 0, 0, 1, 500, 100);		
	Monster test3 = new Monster(100, 100, 0, 0, 2, 100, 100);
	// Program KeyStrokes Here
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
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
	private void doDrawingPlayer(Graphics g) // SetPlayerImage

	{

		Graphics2D galagaship = (Graphics2D) g;

		galagaship.drawImage(img, player.getX(), player.getY(), null);

	}
	private void doDrawingBullet(Graphics g) // SetBulletImage

	{

		Graphics2D bullet = (Graphics2D) g;

		playerBullets.moveToStart();

		for (int i = 0; i < playerBullets.length(); i++) {

			bullet.drawImage(img, playerBullets.getValue().getX(),

			playerBullets.getValue().getY(), null);

			playerBullets.next();

		}

		monsterBullets.moveToStart();

		for (int i = 0; i < monsterBullets.length(); i++) {

			bullet.drawImage(img, monsterBullets.getValue().getX(),

			monsterBullets.getValue().getY(), null);

			monsterBullets.next();

		}

	}
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		img = new ImageIcon("galagaship.png").getImage();
		doDrawingPlayer(g);
		img = new ImageIcon("bullet.png").getImage();
		doDrawingBullet(g);
		// AddOtherDrawings
		for(int i = 0; i < monsters.size(); i++){
			g.drawRect(monsters.get(i).getX(), monsters.get(i).getY(), 
					monsters.get(i).getWidth(), monsters.get(i).getHeight());
		}
		g.drawString("Lives Left:"+player.getLives(), 500, 700);
		if (done == true) {
			if(player.getLives()<=0){
				g.drawString("Game Over", 300, 400);
			}
			if(monsters.size()==0){
				g.drawString("Winning", 300, 400);
			}
		}
	}
	// Starts timer
	public void start() {
		monsters.add(test);
		monsters.add(test2);
		monsters.add(test3);
		t.start();

	}
	// Continually Runs whatever is in this command
	public void actionPerformed(ActionEvent e) {
		repaint();
		playerBullets.traverseListPlayer(monsters);
		monsterBullets.traverseListMonster(player);
		player.move();
		for(int i = 0; i < monsters.size(); i++){
			monsters.get(i).function(player, monsterBullets);
			if(monsters.get(i).collidesWith(player)){
				player.died();
			}
		}
		if (player.getLives() <= 0 || monsters.size()==0) {
			t.stop();
			done = true;
		}
		
	}
	public static void main(String[] args) {

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
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D: // Move Right
			player.setVelocityX(0);
			break;
		case KeyEvent.VK_A: // Move Left
			player.setVelocityX(0);
			break;
		}
	}
}