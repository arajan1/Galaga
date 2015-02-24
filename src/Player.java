public class Player extends Objects{
	Player(int a, int b, int c, int d) 
	{
		super(a, b, c, d);
	}
	public void move(){
		this.changeX(this.getVelocityX());
		this.changeY(this.getVelocityY());
		if(this.getX()<0)
		{
			this.setX(0);
		}
		if(this.getX()+width>600)
		{
			this.setX(600-width);
		}
	}

}
