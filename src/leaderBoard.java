/** Source code example for "A Practical Introduction to Data
 Structures and Algorithm Analysis, 3rd Edition (Java)" 
 by Clifford A. Shaffer
 Copyright 2008-2011 by Clifford A. Shaffer
 */

/** LeaderBoardLinked list implementation */
class Leaderboard {
	private LeaderBoardLink head; // Pointer to list header
	private LeaderBoardLink tail; // Pointer to last element
	protected LeaderBoardLink curr; // Access to current element
	int cnt; // Size of list

	/** Constructors */
	Leaderboard(int size) {
		this();
	} // Constructor -- Ignore size

	Leaderboard() {
		curr = tail = head = new LeaderBoardLink(null); // Create header
		cnt = 0;
	}

	/** Remove all elements */
	public void clear() {
		head.setNext(null); // Drop access to LeaderBoardLinks
		curr = tail = head = new LeaderBoardLink(null); // Create header
		cnt = 0;
	}

	/** Insert "it" at current position */
	public void insert(int[] it) {
		curr.setNext(new LeaderBoardLink(it[0], it[1], it[2], curr.next()));
		if (tail == curr)
			tail = curr.next(); // New tail
		cnt++;
	}

	/** Append "it" to list */
	public void append(int[] it) {
		tail = tail.setNext(new LeaderBoardLink(it[0], it[1], it[2], null));
		cnt++;
	}

	/** Remove and return current element */
	public int[] remove() {
		if (curr.next() == null)
			return null; // Nothing to remove
		int[] it = curr.next().element(); // Remember value
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
		LeaderBoardLink temp = head;
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

	/** @return The position of the current element */
	public int currPos() {
		LeaderBoardLink temp = head;
		int i;
		for (i = 0; curr != temp; i++)
			temp = temp.next();
		return i;
	}

	/** Move down list to "pos" position */
	public void moveToPos(int pos) {
		assert (pos >= 0) && (pos <= cnt) : "Position out of range";
		curr = head;
		for (int i = 0; i < pos; i++)
			curr = curr.next();
	}

	/** @return Current element value */
	public int[] getValue() {
		if (curr.next() == null)
			return null;
		return curr.next().element();
	}
	
	

	// Extra stuff not printed in the book.

	/**
	 * Generate a human-readable representation of this list's contents that
	 * looks something like this: < 1 2 3 | 4 5 6 >. The vertical bar represents
	 * the current location of the fence. This method uses toString() on the
	 * individual elements.
	 * 
	 * @return The string representation of this list
	 */
	public String toString() {
		// Save the current position of the list
		int oldPos = currPos();
		int length = length();
		StringBuffer out = new StringBuffer((length() + 1) * 4);

		moveToStart();
		out.append("< ");
		for (int i = 0; i < oldPos; i++) {
			out.append(getValue());
			out.append(" ");
			next();
		}
		out.append("| ");
		for (int i = oldPos; i < length; i++) {
			out.append(getValue());
			out.append(" ");
			next();
		}
		out.append(">");
		moveToPos(oldPos); // Reset the fence to its original position
		return out.toString();
	}
}

/**
 * Source code example for "A Practical Introduction to Data Structures and
 * Algorithm Analysis, 3rd Edition (Java)" by Clifford A. Shaffer Copyright
 * 2008-2011 by Clifford A. Shaffer
 */

/** Singly LeaderBoardLinked list node */
class LeaderBoardLink {
	private int[] element; // Value for this node
	private LeaderBoardLink next; // Pointer to next node in list

	// Constructors
	LeaderBoardLink(int mon1, int mon2, int mon3, LeaderBoardLink nextval) 
	{
		element = new int[3];
		element[0] = mon1;
		element[1] = mon2;
		element[2] = mon3;
		next = nextval;
	}

	LeaderBoardLink(LeaderBoardLink nextval) {
		next = nextval;
	}

	LeaderBoardLink next() {
		return next;
	} // Return next field

	LeaderBoardLink setNext(LeaderBoardLink nextval) // Set next field
	{
		return next = nextval;
	} // Return element field

	int[] element() 
	{
		return element;
	} // Set element field

	void setElement(int[] it) 
	{
		//not used
	}
}
