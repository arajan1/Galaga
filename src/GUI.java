import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class GUI extends JPanel implements ActionListener, KeyListener {
	private Timer t = new Timer(20, this);
	Player player = new Player(275, 725, 50, 50, 3);
	BulletList playerBullets = new BulletList(2);
	Random rand = new Random();
	BulletList monsterBullets = new BulletList(100);
	Image img;
	boolean runonce = false;
	boolean hit = false;
	boolean donereturning = true;
	boolean done = false;
	boolean doublefighter = false;

	Monster test = new Monster(100, 100, 50, 50, 1, 100, 100);

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

	private void doDrawingBackground(Graphics g) // SetBackgroundImage
	{
		Graphics2D background = (Graphics2D) g;
		background.drawImage(img, 0, 0, null);
	}

	private void doDrawingPlayer(Graphics g) // SetPlayerImage
	{
		Graphics2D galagaship = (Graphics2D) g;
		galagaship.drawImage(img, player.getX(), player.getY(), null);
	}

	private void doDrawingBullet(Graphics g) // SetPlayerImage
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
		// DrawPlayer
		// img = new ImageIcon("Background.jpg").getImage();
		// doDrawingBackground(g);
		img = new ImageIcon("galagaship.png").getImage();
		doDrawingPlayer(g);
		img = new ImageIcon("bullet.png").getImage();
		doDrawingBullet(g);
		// AddOtherDrawings
		g.drawRect(test.getX(), test.getY(), test.getWidth(), test.getHeight());
		if (done == true) {
			g.drawString("Game Over", 300, 400);
		}
	}

	// Starts timer
	public void start() {
		t.start();
	}

	// Continually Runs whatever is in this command
	public void actionPerformed(ActionEvent e) {
		repaint();
		playerBullets.traverseList();
		monsterBullets.traverseList();
		player.move();
		test.move();
		test.enter();
		test.incrementAngle();
		playerBullets.moveToStart();
		for (int i = 0; i < playerBullets.length(); i++) {
			if (playerBullets.getValue().collidesWith(test)) {
				if (test.isCapturing() == true) {
					doublefighter = true;
				}
			}
			playerBullets.next();
		}
		monsterBullets.moveToStart();
		for (int i = 0; i < monsterBullets.length(); i++) {

			if (monsterBullets.getValue().collidesWith(player)) {
				hit = true;
			}
			monsterBullets.next();
		}
		if (player.collidesWith(test)) {
			hit = true;
		}
		if (test.getY() > 800) {
			test.setY(0);
			donereturning = false;
		}
		if (donereturning == false) {
			if (test.returnToTop()) {
				test.setVelocityX(0);
				test.setVelocityY(0);
				donereturning = true;
				runonce = false;
			}
		}
		// test.captureMonster(player);
		// if(test.isCapturing()==true){
		// hit = true;
		// }
		System.out.println(runonce);
		if (rand.nextInt(200) < 10) {
			if (runonce == false) {
				test.attack(player.getX(), player.getY(), monsterBullets);
				runonce = true;
			}
		}
		else if(runonce == false){
			test.moveBackandFowarth();
		}
		if (hit == true) {
			//player.died();
			hit = false;
			test.setY(0);
			donereturning = false;
		}
		if (player.getLives() <= 0) {
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
