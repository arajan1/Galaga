
public class Player extends Objects{
	int xvelocity = 10;
	Player(int a, int b, int c, int d) 
	{
		super(a, b, c, d);
	}
	public int getVelocity()
	{
		return xvelocity;
	}
	public void setVelocity(int a)
	{
		xvelocity = a;
	}
	public void changeVelocity(int a)
	{
		xvelocity += a;
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
