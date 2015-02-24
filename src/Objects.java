public abstract class Objects 
{
	int x = 0;
	int y = 0;
	int width = 0;
	int height = 0;
	int xvelocity = 0;
	int yvelocity = 0;
	Objects(int a,int b,int c,int d)
	{
		x = a;
		y = b;
		width = c;
		height = d;
	}
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
	public void approach(int a, int b){
		if (this.getX()<=a+10 && this.getY()<=b+10 &&this.getX()>=a-10 && this.getY()>=b-10);
		else{
			this.setVelocityX((a-(x+width/2))/10);
			this.setVelocityY((b-(y+height/2))/10);
		}
	}
	public void move(){
		this.changeX(this.getVelocityX());
		this.changeY(this.getVelocityY());
	}
	
	//test
}

