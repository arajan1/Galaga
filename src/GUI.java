import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JPanel implements ActionListener, KeyListener {
	private Timer t = new Timer(100, this);
	Player player = new Player(275,725,50,50);
	Image img;
	//Program KeyStrokes Here
	public void keyPressed(KeyEvent e) 
	{
		switch (e.getKeyCode()) 
		{
		case KeyEvent.VK_SPACE: // Press Space Bar
			break;
		/*case KeyEvent.VK_W: //Move Up
			y-=6;
			if(y<0){
				y=595;
			}
			break;*/
		case KeyEvent.VK_D: //Move Right
			player.moveRight();
			if(player.getX()>600){
				player.setX(5);
			}
			break;
		case KeyEvent.VK_A: //Move Left
			player.moveLeft();
			if(player.getX()<0){
				player.setX(595);
			}
			break;
		/*case KeyEvent.VK_S: //Move Down
			y+=2;
			if(y>600){
				y=6;
			}
			break;*/
		}	
	}
	private void doDrawing(Graphics g) {
		//DrawPlayer
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, player.getX(), player.getY(), null);
		//AddOtherDrawings
	}
	public void paintComponent(Graphics g) 
	{
		super.paintComponents(g);
		//DrawPlayer
		img = new ImageIcon("galagaship.png").getImage();
		doDrawing(g);
		//AddOtherDrawings
	
		
	}
	//Starts timer
	public void start()
	{
		t.start();
	}
	//Continually Runs whatever is in this command
	public void actionPerformed(ActionEvent e) 
	{
		repaint();
	}

	public static void main(String[] args)
	{
		JFrame f = new JFrame();
		GUI canvas = new GUI();
		f.setTitle("Galaga");
		f.add(canvas);
		f.addKeyListener(canvas);
		f.setSize(600, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);
		canvas.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
