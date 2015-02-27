import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
public class ScoreBoard
{
int[] scores;
public ScoreBoard()
{
scores = new int[3];
}
public void addKill(int type)
{
if (type > 2 || type < 0)
{
return;
}
scores[type]++;
}
public void display(Graphics g, GamePanel gp)
{
g.drawImage(new ImageIcon("beeEnemy%20copy.png").getImage(), 100, 200, gp);
g.drawImage(new ImageIcon("beeEnemy%20copy.png").getImage(), 100, 400, gp);
g.drawImage(new ImageIcon("beeEnemy%20copy.png").getImage(), 100, 600, gp);
g.drawString(scores[0] + "", 200, 200);
g.drawString(scores[1] + "", 200, 400);
g.drawString(scores[2] + "", 200, 400);
}
}
