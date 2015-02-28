import java.awt.Image;
import java.util.LinkedList;

public class BulletList {
	private Link<Bullet> head; // Pointer to list header
	private Link<Bullet> tail; // Pointer to last element
	protected Link<Bullet> curr; // Access to current element
	int cnt; // Size of list
	int cap;

	/** Constructors */
	BulletList(int a) {
		curr = tail = head = new Link<Bullet>(null); // Create header
		cnt = 0;
		cap = a;
	}

	/** Remove all elements */
	public void clear() {
		head.setNext(null); // Drop access to links
		curr = tail = head = new Link<Bullet>(null); // Create header
		cnt = 0;
	}

	/** Append "it" to list */
	public void append(Bullet it) {
		if (length() < cap) {
			tail = tail.setNext(new Link<Bullet>(it, null));
			cnt++;
		}
	}
	public void setCap(int a){
		cap = a;
	}
	/** Remove and return current element */
	public Bullet remove() {
		if (curr.next() == null)
			return null; // Nothing to remove
		Bullet it = curr.next().element(); // Remember value
		if (tail == curr.next())
			tail = curr; // Removed last
		curr.setNext(curr.next().next()); // Remove from list
		cnt--; // Decrement count
		return it; // Return value
	}

	/** Set curr at list start */
	public void moveToStart() {
		curr = head;
	}

	/** Set curr at list end */
	public void moveToEnd() {
		curr = tail;
	}

	/** Move curr one step left; no change if now at front */
	public void prev() {
		if (curr == head)
			return; // No previous element
		Link<Bullet> temp = head;
		// March down list until we find the previous element
		while (temp.next() != curr)
			temp = temp.next();
		curr = temp;
	}

	/** Move curr one step right; no change if now at end */
	public void next() {
		if (curr != tail)
			curr = curr.next();
	}

	/** @return List length */
	public int length() {
		return cnt;
	}

	public Bullet getValue() {
		if (curr.next() == null)
			return null;
		return (Bullet) curr.next().element();
	}

	public void traverseListMonster(LinkedList<Player> players) {
		moveToStart();
		for (int i = 0; i < length(); i++) {
			if (getValue() != null) {
				getValue().move();
			} else if (getValue().getY() > 800) {
				remove();
			}
			for (int k = 0; k < players.size(); k++) {
				if (getValue().collidesWith(players.get(k))) {
					if(players.size()==2){
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
			} else if (getValue().getY() > 800) {
				remove();
			} else {
				for (int k = 0; k < monsters.size(); k++) {
					if (monsters.get(k).collidesWith(getValue())) {
						if (monsters.get(k).died()) {
							if (monsters.get(k).hasCapture()) {
								monsters.remove(k);
								board.addKill(2);
								return true;
							}
							else{
								board.addKill(monsters.get(k).monsterindex);
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

class Link<Bullet> {
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

class Bullet extends Objects {
	Bullet(Image a, int b, int c, int d, int e, double angle) {
		super(a, b, c, d, e, angle);
	}
}
