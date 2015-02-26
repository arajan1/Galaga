import java.util.Random;

public class Monster extends Objects {
	int basex;
	int basey;
	int monsterindex;
	int lives;
	Random rand = new Random();
	double angle = 0;
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

	Monster(int a, int b, int c, int d, int e, int f, int g) {
		super(a, b, c, d);
		basex = f;
		basey = g;
		monsterindex = e;
		if(e==1){
			lives = 1;
		}
		if(e==2){
			lives = 2;
		}
	}

	//RED MINION = MONSTER INDEX 1
	//YELLOW MINION = MONSTER INDEX 2
	//CAPTURE MINION = MONSTER INDEX 3\
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

	public boolean isCapturing(){
		return iscapturing;
	}
	
	public void move() {
		this.changeX(this.getVelocityX());
		this.changeY(this.getVelocityY());
	}

	public void enter() { // Monsters movement upon entering screen
		if (monsterindex == 1 && endingover == false) {
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
			if (startcircle == true && angle < Math.PI * 3 && basex<300) {
				setVelocityX(((int) (-20 * Math.cos(angle + Math.PI))));
				setVelocityY(((int) (20 * Math.sin(angle + Math.PI))));
			}
			if (startcircle == true && angle < Math.PI * 3 && basex>300) {
				setVelocityX(((int) (20 * Math.cos(angle + Math.PI))));
				setVelocityY(((int) (20 * Math.sin(angle + Math.PI))));
			}
			if (angle > Math.PI * 3 && startending == false) {
				approach(basex, basey);
				startending = true;
			}
			if ((this.getX() + 40 > this.getBaseX() && this.getX() - 40 < this
					.getBaseX())
					&& ((this.getY() + 40 > this.getBaseY() && this.getY() - 40 < this
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
			}
		}
	}

	public void attack(int playerx, int playery, BulletList list) {
		if (endingover == true && attack == false) {
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
		attack = true;
	} // monster shooting bullets

	public void moveBackandFowarth() {
		if (endingover == true) {
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
		}
	} // monster shifting side to side

	public boolean returnToTop() {
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

	public int getLives(){
		return lives;
	}
	
	public boolean died()
	{
		lives--;
		if(lives<=0){
			return true;
		}
		return false;
	}
/*	public void captureMonster(Player player) {
		if (endingover == true) {
			if (captureapproach == false) {
				xvelocity=0;
				yvelocity=0;
				approach(300 - width / 2, 400);
				captureapproach = true;
			}
			if ((this.getX() + 15 > 300 - width / 2 && this.getX() - 15 < 300 - width / 2)
					&& ((this.getY() + 15 > 400 && this.getY() - 15 < 400))
					&& madeplace == false && captureapproach == true) {
				this.setX(300 - width / 2);
				this.setY(400);
				xvelocity=0;
				yvelocity=0;
				madeplace = true;
			}
			if(iscapturing == false){
				if(player.getX()>=200 && player.getX()<=400){
					hascapture=true;
				}
				iscapturing = true;
			}
			
			
		}
	}*/
	
	public void function(Player player, BulletList monster){
		move();
		enter();
		if(attack==false){
			moveBackandFowarth();
		}
		if(rand.nextInt(200)<10&&endingover==true){
			attack(player.getX(),player.getY(), monster);
		}
		if(y>800){
			y=0;
			needtoreturn = true;
		}
		if(needtoreturn == true)
		{
			if(returnToTop()){
				xvelocity=0;
				yvelocity=0;
				needtoreturn = false;
			}
		}
		System.out.println(attack);
		incrementAngle();
	}
}

