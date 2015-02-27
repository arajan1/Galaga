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
	int numoflives = 0;
	Random rand = new Random();
	BulletList monsterBullets = new BulletList(100);
	LinkedList<Monster> monsters = new LinkedList<Monster>();
	LinkedList<Player> players = new LinkedList<Player>();
	LinkedList<Sprite> livedisplay = new LinkedList<Sprite>();
	Image img;
	Stage stage = new Stage();
	int level = 4;
	GamePanel gp;
	boolean runonce = false;
	boolean hit = false;
	boolean donereturning = true;
	boolean done = false;
	boolean doublefighter = false;
	boolean godmode = false;

	Monster test = new Monster(new ImageIcon("redMonster copy.png").getImage(),
			0, 0, 0, 0, 0, 100, 100, 0);
	Monster test1 = new Monster(
			new ImageIcon("redMonster copy.png").getImage(), 0, 0, 0, 0, 1,
			282, 100, 0);
	Monster test2 = new Monster(new ImageIcon("Commander.png").getImage(), 0,
			0, 0, 0, 2, 500, 100, 0);

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
		case KeyEvent.VK_G:
			if(godmode==false){
				godmode=true;
				break;
			}
			if(godmode==true){
				godmode=false;
				break;
			}
		}
	}

	// Starts timer
	public void start() {
		t.start();
	}

	// Continually Runs whatever is in this command
	public void actionPerformed(ActionEvent e) {
		livedisplay.clear();
		numoflives = 0;
		if (players.size() == 2) {
			if (players.get(0).getX() > players.get(1).getX()) {
				if (players.get(1).getX() < 0) {
					players.get(0).setX(50);
					players.get(1).setX(0);
				}
				if (players.get(0).getX() > 600 - 50) {
					players.get(0).setX(600 - 50);
					players.get(1).setX(500);
				}
			}
			if (players.get(1).getX() > players.get(0).getX()) {
				if (players.get(0).getX() < 0) {
					players.get(1).setX(50);
					players.get(0).setX(0);
				}
				if (players.get(1).getX() > 600 - 50) {
					players.get(1).setX(600 - 50);
					players.get(0).setX(500);
				}
			}
		} else {
			if (players.get(0).getX() < 0) {
				players.get(0).setX(0);
			}
			if (players.get(0).getX() > 600 - 50) {
				players.get(0).setX(600 - 50);
			}
		}
		for (int i = 0; i < players.size(); i++) {
			numoflives += players.get(i).getLives();
			players.get(i).move();
			if(players.get(i).getLives()<=0 && godmode==false){
				players.remove(i);
			}
		}
		for (int i = 0; i < numoflives; i++) {
			Sprite temp = new Sprite(
					new ImageIcon("galagashiplive.png").getImage(), 0,
					600 - 30 * i, 0);
			livedisplay.add(temp);
		}
		if (playerBullets.traverseListPlayer(monsters)) {
			Player temp = new Player(
					new ImageIcon("galagaship.png").getImage(), player.getX()
							+ player.getWidth(), player.getY(), 50, 50, 1, 0);
			players.add(temp);
		}
		monsterBullets.traverseListMonster(players);
		for (int i = 0; i < monsters.size(); i++) {
			monsters.get(i).function(player, monsterBullets);
			if (monsters.get(i).collidesWith(player)) {
				if (players.size() == 2) {
					players.get(1).died();
				} else {
					player.died();
				}
				monsters.get(i).setX(monsters.get(i).getBaseX());
				monsters.get(i).setY(monsters.get(i).getBaseY());
			}
		}
		if (numoflives <= 0 && godmode == false) {
			t.stop();
			done = true;
		}
		if (level==4) {
			//monsters = stage.makeMonsterList(level);
			monsters.add(test2);
			level++;
		}
		LinkedList<Sprite> sprites = new LinkedList<Sprite>();
		sprites.addAll(players);
		for (int i = 0; i < monsters.size(); i++) {
			if (monsters.get(i).drawCapture() == true) {
				Sprite temp = new Sprite(
						new ImageIcon("capture.png").getImage(), 200, 450, 0);
				sprites.add(temp);
			}
			if (monsters.get(i).hasCapture() == true
					&& monsters.get(i).getLives() >= 0) {
				Sprite temp2 = new Sprite(new ImageIcon(
						"CommanderHasCapture.png").getImage(), monsters.get(i)
						.getX(), monsters.get(i).getY(), monsters.get(i).angle);
				sprites.addFirst(temp2);
			} else {
				sprites.add(monsters.get(i));
			}
		}
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