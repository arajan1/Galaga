import java.util.*;
public class Monster extends Objects
{
	int basex;
	int basey;
	int monsterindex;
	boolean rightside = false;
	Monster(int a, int b, int c, int d, int e, int f, int g) {
		super(a, b, c, d);
			basex = f;
			basey = g;
			monsterindex = e;
		// TODO Auto-generated constructor stub
	}

	//Types of Minions: Blue Minion, Purple Minion
	//Yellow Minion, Red Minion, Capture Monster, Space Bug
	public int getBaseX()
	{
		return basex;
	}
	public int getBaseY(){
		return basey;
	}
	public void setBaseX(int a){
		basex = a;
	}
	public void setBaseY(int a){
		basey = a;
	}
	public void move(){
		this.changeX(this.getVelocityX());
		this.changeY(this.getVelocityY());
	}
	public void enter()
	{ //Monsters movement upon entering screen	
		
	}
	
	public void attack(int playerx, int playery, BulletList list)
	{
		Random rand = new Random();
		this.approach(playerx, playery);
		Bullet temp = new Bullet(this.getX()+this.getWidth()/2-5,this.getY(),10,23);
		temp.approach(playerx+50, playery);
		temp.setVelocityY(temp.getVelocityY()*-1);
		list.append(temp);
	} //monster shooting bullets
	
	public void moveBackandFowarth()
	{
		if(rightside==false){
			this.changeX(5);
			if(this.getX()>=this.getBaseX()+15){
				rightside=true;
			}
		}
		if(rightside==true){
			this.changeX(-5);
			if(this.getX()<=this.getBaseX()-15){
				rightside=false;
			}
		}
	} //monster shifting side to side
	
	public void returnToTop()
	{
		this.setY(0);
		this.setX(this.getBaseX());
		this.setVelocityY(5);
	}
}
