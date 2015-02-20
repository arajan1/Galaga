
public interface Monster 
{
	//Types of Minions: Blue Minion, Purple Minion
	//Yellow Minion, Red Minion, Capture Monster, Space Bug
	
	public void move(); //Monsters path downward

	public void enter(); //Monsters movement upon entering screen	
	
	public void attack(); //monster shooting bullets
	
	public void moveBackandFowarth(); //monster shifting side to side
	
	public void returnToTop();
}
