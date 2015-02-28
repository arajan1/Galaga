import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class ScoreBoard {
	int[] scores;
	int level;
	public ScoreBoard() {
		scores = new int[3];//This stores the number of kills for each type of monster
		int level = 0;//Stores the level that the player has achieved
	}

	public void addKill(int type) {//Adds a kill of a given monster type
		if (type > 2 || type < 0) {
			return;
		}
		scores[type]++;
	}
	public void addLevel(){//Indicates that the player has reached the next level
		level++;
	}

	public void display(Graphics g, GamePanel gp) {//Tells the GamePanel how to display the scoreBoard
		g.drawImage(new ImageIcon("redMonster copy.png").getImage(), 100, 125,
				gp);
		g.drawImage(new ImageIcon("beeEnemy copy.png").getImage(), 100, 325,
				gp);
		g.drawImage(new ImageIcon("Commander.png").getImage(), 100, 525,
				gp);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
		g.setColor(Color.RED);
		g.drawString("Game Over :(", 200,50);
		g.setColor(Color.WHITE);
		g.drawString(scores[0] + "", 200, 150);
		g.drawString(scores[1] + "", 200, 350);
		g.drawString(scores[2] + "", 200, 550);
		g.drawString("Level: " + level + "", 200, 725);
	}
}
