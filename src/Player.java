
public class Player extends Objects{
	int xvelocity = 25;
	Player(int a, int b, int c, int d) 
	{
		super(a, b, c, d);
	}
	public void moveLeft()
	{
		changeNegX(xvelocity);
	}
	public void  moveRight()
	{
		changeX(xvelocity);
	}
}
