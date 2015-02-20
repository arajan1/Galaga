
public class Player extends Objects{
	Player(int a, int b, int c, int d) 
	{
		super(a, b, c, d);
	}
	public void move(){
		this.changeX(this.getVelocityX());
		this.changeY(this.getVelocityY());
		if(this.getX()<0){
			this.setX(595);
		}
		if(y>600){
			y=6;
		}
		if(y<0){
			y=595;
		}
		if(this.getX()>600){
			this.setX(5);
		}
	}

}
