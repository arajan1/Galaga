
public class Monster extends Objects
{
	int basex;
	int basey;
	int monsterindex;
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

	public void enter()
	{ //Monsters movement upon entering screen	
		
	}
	
	public void attack()
	{
		
	} //monster shooting bullets
	
	public void moveBackandFowarth()
	{
		if(this.getX()+15<this.getBaseX()&&this.getX()-15>this.getBaseX()&&
				this.getY()==this.getBaseY()){
			if(this.getX()>this.getBaseX()){
				this.changeX(-3);
			}
			if(this.getX()<this.getBaseX()){
				this.changeX(+3);
			}
		}
	} //monster shifting side to side
	
	public void returnToTop()
	{
		this.setY(0);
		this.setX(this.getBaseX());
		this.approach(this.getBaseX(),this.getBaseY());
	}
}
