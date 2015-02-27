import java.util.Random;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Stage {

	private static int randPick(int a) {// picks random number 0 through a-1
		Random generator = new Random();
		return generator.nextInt(a);
	}

	private static int totHelp(int n) {// determines the added monsters in a given stage from the base stage condition
		if (n == 0)
			return 0;
		return n + totHelp(n - 1);
	}

	private static int redHelp(int n) {// determines amount of red monsters in a
										// given stage
		if (n >= 10)
			return 0;
		else
			return 10 - n;
	}

	private static int valIndex(ArrayList<Integer> arr, int srch) {// finds the index of a given value in an array list
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i) == srch)
				return i;
		}
		return -1;
	}

	/*
	 * Given a zero-indexed stage, makeMonsterList determines a difficulty (how
	 * many of each type of monster there are), and then creates a randomly
	 * ordered linked list of monsters based on the difficulty. This allows for
	 * a different game experience each time. These monsters are also put given
	 * base x and y values which distribute them in the GUI in evenly-spaced
	 * lines.
	 */
	public static LinkedList<Monster> makeMonsterList(int stg) {
		LinkedList<Monster> mash = new LinkedList<Monster>();
		// Part 1: Determine Difficulty
		boolean stg4 = false;
		if (stg >= 3)
			stg4 = true; // Later determines whether the "cap" type monster is placed in list
		int tot = 15 + totHelp(stg);// Total monsters
		int red = redHelp(stg);// Number of red monsters
		int cap = (stg - 2) / 2; // Determine number of cap monsters
		if (!stg4)
			cap = 0;
		int ylw = tot - red - cap;// Number of yellow monsters
		// Part 2: Create Randomized List and Assign Monsters to the Grid
		int bx;// base x value
		int by;// base y value
		int bxspace;// horizontal space between each monster on a row
		int tothold = tot;
		int place;// "index" of monster in the linked list
		int row;// rows are zero indexed from the top down
		int inlastrow = tot % 10;// amount of monsters in last row
		int rowplace;// index in a given row
		Monster temp;
		ArrayList<Integer> opts = new ArrayList<Integer>();// array of choices of types of monsters
		opts.add(0);
		opts.add(1);
		opts.add(2);
		int pick;// will be a random number 0-1 or 0-2 depending on "choice." This makes it so a cap cannot be made before stage4
		int choice = 2;
		if (stg4)
			choice++;
		for (int i = 0; i < tot; tot--) {// creates "tot" monsters
			place = tothold - tot;
			row = place / 10;
			rowplace = place % 10;
			by = 40 + row * 60;// determine y coord
			bxspace = 15;
			if (row == tothold / 10)
				bxspace = (520 - inlastrow * 35) / (inlastrow + 1);// changes spacing in last row
			bx = 40 + bxspace + (bxspace + 35) * rowplace;// determine x coord
			pick = randPick(choice);
			if (pick == 0 && red!=0) {// For red monster
				temp = new Monster(new ImageIcon("redMonster copy.png").getImage(), 0, 0, 35, 28, 0, bx, by, 0);// creates a monster of type red with base coords "(bx,by)"
				mash.add(temp);// appends to returned list
				red--;
			}
			if (pick == 1 && ylw!=0) {// same as above, but for yellow
				temp = new Monster(new ImageIcon("beeEnemy copy.png").getImage(),0, 0, 35, 28, 1, bx, by, 0);
				mash.add(temp);
				ylw--;
			}
			if (pick == 2 && cap!=0) {// same as above but for cap
				temp = new Monster(new ImageIcon("Commander.png").getImage(),0, 0, 35, 38, 2, bx, by, 0);
				mash.add(temp);
				cap--;
			}
		}
		// System.out.println("Stage: "+stg+"\nTotal Monsters: "+tot+"\nRatio R:Y:C: "+red+":"+ylw+":"+cap+"\n");//toString for stage
		return mash;
	}
}