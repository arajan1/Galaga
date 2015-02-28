import java.awt.Image;
//Organizational piece to distinguish objects
public class Player extends Objects{
	int lives;
	Player(Image img, int a, int b, int c, int d, int e, double angle) 
	{
		super(img, a, b, c, d, angle);
		lives = e;
	}
	public int getLives(){
		return lives;
	}
	//players action on death
	public void died(){
		this.setX(275);
		this.setY(725);
		lives--;
	}

}
