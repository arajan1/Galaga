public class Player extends Objects{
	int lives;
	Player(int a, int b, int c, int d, int e) 
	{
		super(a, b, c, d);
		lives = e;
	}
	@Override
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
	public int getLives(){
		return lives;
	}
	public void died(){
		lives--;
	}

}
