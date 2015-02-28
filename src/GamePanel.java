import java.util.LinkedList;
import java.awt.*;

import javax.swing.*;


public class GamePanel extends JPanel
{
	LinkedList<Sprite> sprites = new LinkedList<Sprite>();
	boolean gameover = false;
	ScoreBoard board;
	
	public GamePanel(){
		board = new ScoreBoard();
	}
	public ScoreBoard getBoard(){
		return board;
	}
	
	public void setSprites(LinkedList<Sprite> slist)
	{
		sprites = slist;
		repaint();
	}
	
	public void gameOver(){
		gameover=true;
	}
	
	@Override
	public void paint(Graphics g)
	{
		if(gameover==false){
			for (Sprite s: sprites)
			{
				//System.out.println("test");
				s.display(g, this);
			}
		}
		else{
			board.display(g, this);
		}
	}
}
