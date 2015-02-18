
public class Bullet extends Objects{
	int yvelocity;
	Bullet(int a, int b, int c, int d) {
		super(a, b, c, d);
	}
	public int getVelocity()
	{
		return yvelocity;
	}
	public void setVelocity(int a)
	{
		yvelocity = a;
	}
	public void changeVelocity(int a)
	{
		yvelocity += a;
	}
	public void moveUp()
	{
		changeNegY(yvelocity);
	}
	public void moveDown(){
		changeY(yvelocity);
	}
}
