import java.awt.Image;

public class Player extends Objects{
	int lives;
	Player(Image img, int a, int b, int c, int d, int e, double angle) 
	{
		super(img, a, b, c, d, angle);
		lives = e;
	}
	public void move(){
		this.changeX(this.getVelocityX());
		this.changeY(this.getVelocityY());
	}
	public int getLives(){
		return lives;
	}
	
	public void died(){
		this.setX(275);
		this.setY(725);
		lives--;
	}

}
