import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener, KeyListener {
	private Timer t = new Timer(20, this);
	Player player = new Player(new ImageIcon("galagaship.png").getImage(), 275,
			725, 50, 50, 3, 0);
	BulletList playerBullets = new BulletList(2);
	int numoflives=0;
	Random rand = new Random();
	BulletList monsterBullets = new BulletList(100);
	LinkedList<Monster> monsters = new LinkedList<Monster>();
	LinkedList<Player> players = new LinkedList<Player>();
	LinkedList<Sprite> livedisplay = new LinkedList<Sprite>();
	Image img;
	Stage stage = new Stage();
	int level = 1;
	GamePanel gp;
	boolean runonce = false;
	boolean hit = false;
	boolean donereturning = true;
	boolean done = false;
	boolean doublefighter = false;
	/*Monster test = new Monster(new ImageIcon("redMonster copy.png").getImage(),
			0, 0, 0, 0, 0, 100, 100, 0);
	Monster test1 = new Monster(new ImageIcon("redMonster copy.png").getImage(),
			0, 0, 0, 0, 1, 282, 100, 0);
	Monster test2 = new Monster(new ImageIcon("redMonster copy.png").getImage(),
			0, 0, 0, 0, 2, 500, 100, 0);*/

	// Program KeyStrokes Here
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE: // Press Space Bar
			for (int i = 0; i < players.size(); i++) {
				Bullet temp = new Bullet(
						new ImageIcon("bullet.png").getImage(), players.get(i)
								.getX() + players.get(i).getWidth() / 2 - 5,
						players.get(i).getY(), 10, 23, 0);
				temp.setVelocityY(-45);
				playerBullets.append(temp);
			}
			break;
		case KeyEvent.VK_D: // Move Right
			for (int i = 0; i < players.size(); i++) {
				players.get(i).setVelocityX(10);
			}
			break;
		case KeyEvent.VK_A: // Move Left
			for (int i = 0; i < players.size(); i++) {
				players.get(i).setVelocityX(-10);
			}
			break;
		}

	}

	// Starts timer
	public void start() {
		t.start();

	}

	// Continually Runs whatever is in this command
	public void actionPerformed(ActionEvent e) {
		livedisplay.clear();
		numoflives=0;
		for(int i = 0; i < players.size(); i++){
			numoflives = players.get(i).getLives();
		}
		for(int i = 0; i < numoflives; i++){
			Sprite temp = new Sprite(new ImageIcon("galagashiplive.png").getImage(), 0,600-30*i, 0);
			livedisplay.add(temp);
		}
			
		System.out.println(level);
		if (playerBullets.traverseListPlayer(monsters)) {
			Player temp = new Player(
					new ImageIcon("galagaship.png").getImage(), player.getX()
							+ player.getWidth(), player.getY(), 50, 50, 1, 0);
			players.add(temp);
		}
		monsterBullets.traverseListMonster(players);
		for (int i = 0; i < players.size(); i++) {
			players.get(i).move();
		}
		for (int i = 0; i < monsters.size(); i++) {
			monsters.get(i).function(player, monsterBullets);
			if (monsters.get(i).collidesWith(player)) {
				player.died();
				monsters.get(i).setX(monsters.get(i).getBaseX());
				monsters.get(i).setY(monsters.get(i).getBaseY());
			}
		}

		if (player.getLives() <= 0) {
			t.stop();
			done = true;
		}
		 if(monsters.size()<=0){
		   level++;
		   monsters = stage.makeMonsterList(level);
		 }
		LinkedList<Sprite> sprites = new LinkedList<Sprite>();
		sprites.addAll(players);
		sprites.addAll(monsters);
		playerBullets.moveToStart();
		for (int i = 0; i < playerBullets.length(); i++) {
			sprites.add(playerBullets.getValue());
			playerBullets.next();
		}
		monsterBullets.moveToStart();
		for (int i = 0; i < monsterBullets.length(); i++) {
			sprites.add(monsterBullets.getValue());
			monsterBullets.next();
		}
		sprites.addAll(livedisplay);
		gp.setSprites(sprites);
		gp.repaint();
		repaint();
	}

	public static void main(String[] args) {
		GUI game = new GUI();
	}

	GUI() {
		gp = new GamePanel();
		players.add(player);
		add(gp);
		setTitle("Galaga");
		addKeyListener(this);
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBackground(Color.BLACK);
		t.start();

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_D: // Move Right
			for (int i = 0; i < players.size(); i++) {
				players.get(i).setVelocityX(0);
			}
			break;
		case KeyEvent.VK_A: // Move Left
			for (int i = 0; i < players.size(); i++) {
				players.get(i).setVelocityX(0);
			}
			break;
		}
	}
}