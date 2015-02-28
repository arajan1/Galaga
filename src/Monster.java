import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Monster extends Objects {
	int basex;
	int basey;
	int monsterindex;
	int lives;
	int attackvariable;
	Random rand = new Random();
	int pausefactor = 0;
	boolean approachdone = false;
	boolean captureapproach = false;
	boolean startcircle = false;
	boolean rightside = false;
	boolean startending = false;
	boolean endingover = false;
	boolean hascapture = false;
	boolean madeplace = false;
	boolean iscapturing = false;
	boolean attack = false;
	boolean needtoreturn = false;
	boolean startcapture = false;
	boolean drawcapture = false;
	boolean isattacking = false;

	Monster(Image img, int a, int b, int c, int d, int e, int f, int g, double angle) {
		super(img, a, b, c, d, angle);
		basex = f;
		basey = g;

		monsterindex = e;
		if (e == 0) {
			lives = 1;
			width = 35;
			height = 26;
			attackvariable = 8000;
		}
		if (e == 1) {
			lives = 2;
			width = 35;
			height = 27;
			attackvariable = 6000;
		}
		if (e == 2) {
			lives = 3;
			width = 35;
			height = 38;
			attackvariable = 4000;
		}
	}

	// RED MINION = MONSTER INDEX 0
	// YELLOW MINION = MONSTER INDEX 1
	// CAPTURE MINION = MONSTER INDEX 2\
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
		if (startcircle == true && startending==false) {
			angle += (2 * Math.PI) / 30;
		}
	}

	public boolean isCapturing() {
		return iscapturing;
	}

	public void move() {
		this.changeX(this.getVelocityX());
		this.changeY(this.getVelocityY());
	}

	public void enter() { // Monsters movement upon entering screen
		if (monsterindex <= 1 && endingover == false) {
			if (approachdone == false) {
				if (this.basex > 300) {
					this.setX(600);
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
			if (startcircle == true && angle < Math.PI * 3 && basex < 300) {
				setVelocityX(((int) (-20 * Math.cos(angle + Math.PI + .25))));
				setVelocityY(((int) (20 * Math.sin(angle + Math.PI + .25))));
			}
			if (startcircle == true && angle < Math.PI * 3 && basex > 300) {
				setVelocityX(((int) (20 * Math.cos(angle + Math.PI + .25))));
				setVelocityY(((int) (20 * Math.sin(angle + Math.PI + .25))));
			}
			if (angle > Math.PI * 3 && startending == false) {
				approach(basex, basey);
				startending = true;
			}
			if ((this.getX() + 70 > this.getBaseX() && this.getX() - 70 < this
					.getBaseX())
					&& ((this.getY() + 70 > this.getBaseY() && this.getY() - 70 < this
							.getBaseY())) && startending == true) {
				setVelocityX(0);
				setVelocityY(0);
				this.setX(this.getBaseX());
				this.setY(this.getBaseY());
				endingover = true;
				angle=0;
			}
		}
		if (monsterindex == 2 && endingover == false) {
			if (approachdone == false) {
				if (this.basex > 300) {
					this.setX(100);
					this.setY(0);
					approach(200, 150);
				} else {
					this.setX(500);
					this.setY(0);
					approach(400, 150);
				}
				approachdone = true;
			}
			if (((this.getY() + 30 > 150 && this.getY() - 30 < 150))
					&& (startcircle == false)) {
				setVelocityX(0);
				setVelocityY(0);
				startcircle = true;
			}
			if (startcircle == true && angle < Math.PI * 3 && basex <= 300) {
				setVelocityX(((int) (-15 * Math.cos(angle + Math.PI + .25))));
				setVelocityY(((int) (-25 * Math.sin(angle + Math.PI + .25))));
			}
			if (startcircle == true && angle < Math.PI * 3 && basex > 300) {
				setVelocityX(((int) (15 * Math.cos(angle + Math.PI + .25))));
				setVelocityY(((int) (-25 * Math.sin(angle + Math.PI + .25))));
			}
			if (angle > Math.PI * 3 && startending == false) {
				approach(basex, basey);
				startending = true;
			}
			if ((this.getX() + 70 > this.getBaseX() && this.getX() - 70 < this
					.getBaseX())
					&& ((this.getY() + 70 > this.getBaseY() && this.getY() - 70 < this
							.getBaseY())) && startending == true) {
				setVelocityX(0);
				setVelocityY(0);
				this.setX(this.getBaseX());
				this.setY(this.getBaseY());
				endingover = true;
				angle=0;
			}
		}
	}

	public void attack(int playerx, int playery, BulletList list) {
		if (endingover == true && attack == false) {
			this.approach(playerx, playery);
			Bullet temp = new Bullet(new ImageIcon("bullet.png").getImage(),this.getX() + this.getWidth() / 2 - 5,
					this.getY(), 10, 23, 0);
			if (this.getX() > playerx) {
				temp.approach(playerx - 50, playery);
				temp.setVelocityY(temp.getVelocityY() + 25);
				temp.setVelocityX(temp.getVelocityX() - 5);
				if(yvelocity!=0){
					angle = Math.PI - Math.atan(((double)xvelocity)/((double)yvelocity));
				if(temp.yvelocity!=0){
					temp.angle = Math.PI - Math.atan(((double)temp.xvelocity)/((double)temp.yvelocity));
				}
				list.append(temp);
			}
			if (this.getX() < playerx) {
				temp.approach(playerx + 50, playery);
				temp.setVelocityY(temp.getVelocityY() + 5);
				temp.setVelocityX(temp.getVelocityX() + 5);
				if(yvelocity!=0){
					angle = Math.PI - Math.atan(((double)xvelocity)/((double)yvelocity));

				}
				if(temp.yvelocity!=0){
					temp.angle = Math.PI - Math.atan(((double)temp.xvelocity)/((double)temp.yvelocity));
				}
				list.append(temp);
			}
			}
		attack = true;
		}
	} // monster shooting bullets

	public void moveBackandFowarth() {
		if (endingover == true) {
			if (rightside == false) {
				this.changeX(2);
				if (this.getX() >= this.getBaseX() + 10) {
					rightside = true;
				}
			}
			if (rightside == true) {
				this.changeX(-2);
				if (this.getX() <= this.getBaseX() - 10) {
					rightside = false;
				}
			}
		}
	} // monster shifting side to side

	public boolean returnToTop() {
		angle=0;
		this.setX(this.getBaseX());
		this.setVelocityY(15);
		if ((this.getY() + 15 > this.getBaseY() && this.getY() - 15 < this
				.getBaseY())) {
			this.setX(basex);
			this.setY(basey);
			attack = false;
			return true;
		}
		return false;
	}

	public int getLives() {
		return lives;
	}

	public boolean died() {
		lives--;
		if (lives <= 0) {
			return true;
		}
		return false;
	}

	public boolean drawCapture(){
		return drawcapture;
	}
	
	public boolean hasCapture(){
		return hascapture;
	}
	
	public void captureMonster(Player player) {
		if (endingover == true && hascapture == false) {
			if (captureapproach == false) {
				xvelocity = 0;
				yvelocity = 0;
				approach(300 - width / 2, 400);
				captureapproach = true;
			}
			if ((this.getX() + 45 > 300 - width / 2 && this.getX() - 45 < 300 - width / 2)
					&& ((this.getY() + 45 > 400 && this.getY() - 45 < 400))
					&& madeplace == false && captureapproach == true) {
				this.setX(300 - width / 2);
				this.setY(400);
				xvelocity = 0;
				yvelocity = 0;
				madeplace = true;
			}
			if (hascapture == false && madeplace == true) {
				drawcapture=true;
				pausefactor++;
				if (player.getX() >= 200 && player.getX() <= 400&&pausefactor>100) {
					hascapture = true;
					player.died();
					y=801;
					pausefactor=201;
				}
				if(pausefactor>200){
					yvelocity=50;
					madeplace=false;
					drawcapture=false;
					pausefactor=0;
				}
			}
		}
	}

	public void function(Player player, BulletList monster) {
		move();
		enter();
		if (attack == false && startcapture==false) {
			moveBackandFowarth();
		}
		if (rand.nextInt(attackvariable) < 10 && endingover == true) {
			if(monsterindex==2&&rand.nextInt(5)<1){
				angle=0;
				startcapture=true;
			}
			else if(startcapture == false){
				attack(player.getX(), player.getY(), monster);	
			}
		}
		if(startcapture==true){
			captureMonster(player);
		}
		if (y > 800) {
			y = 0;
			needtoreturn = true;
			startcapture=false;
			captureapproach=false;
		}
		if (needtoreturn == true) {
			if (returnToTop()) {
				xvelocity = 0;
				yvelocity = 0;
				needtoreturn = false;
			}
		}
		incrementAngle();
	}
}
