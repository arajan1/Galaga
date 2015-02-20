
public class Bullet extends Objects
{
	Bullet(int a, int b, int c, int d) 
	{
		super(a, b, c, d);
	}
	public void moveUp()
	{
		changeNegY(yvelocity);
	}
	public void moveDown()
	{
		changeY(yvelocity);
	}
	
}
