import java.awt.Image;
//Base aspects for all objects being made in project
public abstract class Objects extends Sprite
{
	int width;
	int height;
	int xvelocity = 0;
	int yvelocity = 0;
	Objects(Image img, int a,int b,int c, int d, double angle)
	{
		super(img,a,b,angle);
		width = c;
		height = d;
	}
	//Access and modify variables
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public void setX(int a)
	{
		x = a;
	}
	public void setY(int a)
	{
		y = a;
	}
	public void changeX(int a)
	{
		x += a;
	}
	public void changeY(int a)
	{
		y += a;
	}
	public void changeNegX(int a)
	{
		x -= a;
	}
	public void changeNegY(int a)
	{
		y -= a;
	}
	public int getVelocityX()
	{
		return xvelocity;
	}
	public void setVelocityX(int a)
	{
		xvelocity = a;
	}
	public void changeVelocityX(int a)
	{
		xvelocity += a;
	}	public int getVelocityY()
	{
		return yvelocity;
	}
	public void setVelocityY(int a)
	{
		yvelocity = a;
	}
	public void changeVelocityY(int a)

	{
		yvelocity += a;
	}
	//Makes an object approach a location
	public void approach(int a, int b){
		if (this.getX()<=a+10 && this.getY()<=b+10 &&this.getX()>=a-10 && this.getY()>=b-10);
		else{
			this.setVelocityX((a-(x+width/2))/40);
			this.setVelocityY((b-(y+height/2))/40);
		}
	}
	//moves an object
	
	public void move(){
		this.changeX(this.getVelocityX());
		this.changeY(this.getVelocityY());
	}
//collision detection
	public boolean collidesWith(Objects obj){
		if(getX()+getWidth() >= obj.getX()&&getX() <= obj.getX()+obj.getWidth() &&
				getY()+getHeight() >= obj.getY() && getY() <= obj.getY()+obj.getHeight()){
				  return true;
		}
		return false;
	}	
}

