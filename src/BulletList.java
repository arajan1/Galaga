import java.awt.Image;
import java.util.LinkedList;
//LinkedList to manage bullets
public class BulletList {
	private Link<Bullet> head; 
	private Link<Bullet> tail; 
	protected Link<Bullet> curr; 
	int cnt;
	int cap;

	BulletList(int a) {//Constructer
		curr = tail = head = new Link<Bullet>(null); // Create header
		cnt = 0;
		cap = a;
	}
	
	//add bullets
	public void append(Bullet it) {
		if (length() < cap) {
			tail = tail.setNext(new Link<Bullet>(it, null));
			cnt++;
		}
	}
	public void setCap(int a){ //Set cap
		cap = a;
	}
	//Remove bullet
	public Bullet remove() {
		if (curr.next() == null)
			return null;
		Bullet it = curr.next().element(); 
		if (tail == curr.next())
			tail = curr; 
		curr.setNext(curr.next().next()); 
		cnt--; 
		return it; 
	}

	public void moveToStart() {
		curr = head;
	}

	//Iterate through list
	public void next() {
		if (curr != tail)
			curr = curr.next();
	}

	//Return length
	public int length() {
		return cnt;
	}
	//Get value
	public Bullet getValue() {
		if (curr.next() == null)
			return null;
		return (Bullet) curr.next().element();
	}
	//Function used for monster bullets
	public void traverseListMonster(LinkedList<Player> players) {
		moveToStart();
		for (int i = 0; i < length(); i++) {
			if (getValue() != null) {
				getValue().move();
			} else if (getValue().getY() > 800) {//removes bullets after leaving screen
				remove();
			}
			for (int k = 0; k < players.size(); k++) {
				if (getValue().collidesWith(players.get(k))) {
					if(players.size()==2){//Deals with two players
						players.get(1).died();
						remove();
						break;
					}
					else{
						players.get(k).died();
						remove();
						break;
					}
				}
			}
			next();
		}
	}

	public boolean traverseListPlayer(LinkedList<Monster> monsters, ScoreBoard board) {
		moveToStart();
		for (int i = 0; i < length(); i++) {
			if (getValue() != null) {
				getValue().move();
			}
			if (getValue().getY() < 0) {
				remove();
			} 
			else {
				for (int k = 0; k < monsters.size(); k++) {
					if (monsters.get(k).collidesWith(getValue())) {
						if (monsters.get(k).died()) {
							if (monsters.get(k).hasCapture()) {
								monsters.remove(k);
								board.addKill(2);
								return true;
							}
							else{
								board.addKill(monsters.get(k).monsterindex);//Adds to score board
								monsters.remove(k);
							}
						}
						remove();
						break;
					}
				}
				next();
			}
		}
		return false;
	}
}

class Link<Bullet> { //Link Class
	private Bullet element; // Value for this node
	private Link<Bullet> next; // Pointer to next node in list

	// Constructors
	Link(Bullet it, Link<Bullet> nextval) {
		element = it;
		next = nextval;
	}

	Link(Link<Bullet> nextval) {
		next = nextval;
	}

	Link<Bullet> next() {
		return next;
	} // Return next field

	Link<Bullet> setNext(Link<Bullet> nextval) // Set next field
	{
		return next = nextval;
	} // Return element field

	Bullet element() {
		return element;
	} // Set element field

	Bullet setElement(Bullet it) {
		return element = it;
	}
}
//Bullet class for organization purposes
class Bullet extends Objects {
	Bullet(Image a, int b, int c, int d, int e, double angle) {
		super(a, b, c, d, e, angle);
	}
}
