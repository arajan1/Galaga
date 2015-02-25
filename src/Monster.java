import java.util.*;

public class Monster extends Objects {
	int basex;
	int basey;
	int monsterindex;
	boolean approachdone = false;
	boolean startcircle = false;
	boolean rightside = false;
	double angle = 0;
	boolean startending = false;
	boolean endingover = false;

	Monster(int a, int b, int c, int d, int e, int f, int g) {
		super(a, b, c, d);
		basex = f;
		basey = g;
		monsterindex = e;
	}

	// Types of Minions: Blue Minion, Purple Minion
	// Yellow Minion, Red Minion, Capture Monster, Space Bug
	public int getBaseX() {
		return basex;
	}

	public int getBaseY() {
		return basey;
	}

	public void setBaseX(int a) {
		basex = a;
	}

	public void setBaseY(int a) {
		basey = a;
	}

	public void incrementAngle() {
		if (startcircle == true) {
			angle += (2 * Math.PI) / 30;
		}
	}

	public void move() {
		this.changeX(this.getVelocityX());
		this.changeY(this.getVelocityY());
	}

	public void enter() { // Monsters movement upon entering screen
		if (monsterindex == 1 && endingover == false) {
			if (approachdone == false) {
				if (this.basex > 300) {
					this.setX(600 - width);
					this.setY(600);
					approach(400, 400);
				} else {
					this.setX(0);
					this.setY(600);
					approach(200, 400);
				}
				approachdone = true;
			}
			if (((this.getX() + 10 > 200 && this.getX() - 10 < 200) || ((this
					.getX() + 10 > 400 && this.getX() - 10 < 400)))
					&& (startcircle == false)) {
				setVelocityX(0);
				setVelocityY(0);
				startcircle = true;
			}
			if (startcircle == true && angle < Math.PI * 3) {
				setVelocityX(((int) (-25 * Math.cos(angle + Math.PI))));
				setVelocityY(((int) (25 * Math.sin(angle + Math.PI))));
			}
			if (angle > Math.PI * 3 && startending == false) {
				approach(basex, basey);
				startending = true;
			}
			if ((this.getX() + 20 > this.getBaseX() && this.getX() - 20 < this
					.getBaseX())
					&& ((this.getY() + 20 > this.getBaseY() && this.getY() - 20 < this
							.getBaseY())) && startending == true) {
				setVelocityX(0);
				setVelocityY(0);
				this.setX(this.getBaseX());
				this.setY(this.getBaseY());
				endingover = true;
			}
		} else if (monsterindex == 2 && endingover == false) {
			if (approachdone == false) {
				if (this.basex > 300) {
					this.setX(450);
					this.setY(0);
					approach(450, 200);
				} else {
					this.setX(150);
					this.setY(0);
					approach(150, 200);
				}
				approachdone = true;
			}
			if (((this.getY() + 10 > 200 && this.getY() - 10 < 200) && (startcircle == false))) {
				setVelocityX(0);
				setVelocityY(0);
				startcircle = true;
			}
			if (startcircle == true && angle < Math.PI * 2 - .75) {
				setVelocityX(((int) (-15 * Math.cos(angle))));
				setVelocityY(((int) (25 * Math.sin(angle))));
			}
			if (angle > Math.PI * 2 - .75 && startending == false) {
				approach(basex, basey);
				startending = true;
			}
			if ((this.getX() + 30 > this.getBaseX() && this.getX() - 30 < this
					.getBaseX())
					&& ((this.getY() + 30 > this.getBaseY() && this.getY() - 30 < this
							.getBaseY())) && startending == true) {
				setVelocityX(0);
				setVelocityY(0);
				this.setX(this.getBaseX());
				this.setY(this.getBaseY());
				endingover = true;
			}
		}
	}

	public void attack(int playerx, int playery, BulletList list) {
		// Random rand = new Random();
		if (endingover == true) {
			this.approach(playerx, playery);
			Bullet temp = new Bullet(this.getX() + this.getWidth() / 2 - 5,
					this.getY(), 10, 23);
			if (this.getX() > playerx) {
				temp.approach(playerx - 50, playery);
				temp.setVelocityY(temp.getVelocityY() + 25);
				temp.setVelocityX(temp.getVelocityX() - 5);
				list.append(temp);
			}
			if (this.getX() < playerx) {
				temp.approach(playerx + 50, playery);
				temp.setVelocityY(temp.getVelocityY() + 5);
				temp.setVelocityX(temp.getVelocityX() + 5);
				list.append(temp);
			}
		}
	} // monster shooting bullets

	public void moveBackandFowarth() {
		if (rightside == false) {
			this.changeX(5);
			if (this.getX() >= this.getBaseX() + 15) {
				rightside = true;
			}
		}
		if (rightside == true) {
			this.changeX(-5);
			if (this.getX() <= this.getBaseX() - 15) {
				rightside = false;
			}
		}
	} // monster shifting side to side

	public boolean returnToTop() {
		this.setX(this.getBaseX());
		this.setVelocityY(15);
		if((this.getY() + 15 > this.getBaseY() && this.getY() - 15< this
				.getBaseY())){
			this.setX(basex);
			this.setY(basey);
			return true;
		}
		return false;
		
	}
}
