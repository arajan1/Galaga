import java.util.LinkedList;
import java.awt.*;

import javax.swing.*;


public class GamePanel extends JPanel//Modified JPanel that is coded to display a list of sprites in its overridden paint method
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
	
	public void setSprites(LinkedList<Sprite> slist)//We use this to set the list of sprites that we will draw each frame
	{
		sprites = slist;
		repaint();
	}
	
	public void gameOver(){//tells the GamePanel to display the scoreboard
		gameover=true;
	}
	
	@Override
	public void paint(Graphics g)//Modified paint method that displays a list of sprites
	{
		if(gameover==false){//How we display the sprites while the game is still in progress
			for (Sprite s: sprites)
			{
				s.display(g, this);
			}
		}
		else{//How we display the scoreBoard at the end of the game
			board.display(g, this);
		}
	}
}
