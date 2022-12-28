import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HardBoard extends JPanel implements Runnable, KeyListener{

	BufferedImage mainBackImg;
    Spaceship spaceship = new Spaceship(this);
    HardBird bird = new HardBird(this);
    Bullets bullets = new Bullets(this);
    Player3 player = new Player3(this);
    Barriers3 barriers = new Barriers3(this);
	Thread gameThread;
	int time = 5;
	Main main = new Main();
	MainMenu menu = new MainMenu();
	
	@Override
	public void addNotify() {
		// TODO Auto-generated method stub
		super.addNotify();
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(mainBackImg, 0, 0, 500, 800, null);
		bullets.paint3(g);
		spaceship.paint(g);
		barriers.paint(g);
		bird.paint(g);
				
		if(bird.heart <= 0) {
			gameThread.stop();
			Object[] options = {"Restart", "First Level", "Home"};
			int n = JOptionPane.showOptionDialog(this, "Great \nYou Win", "Win", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
			if(n == 0) {
				main.hard();
			}
			else if(n == 1){
				main.easy();
			}
			else if(n == 2) {
				menu.main(null);
			}
		}
		
		if(player.loose){
			gameThread.stop();
			Object[] options = {"Restart", "Home"};
			int n = JOptionPane.showOptionDialog(this, "OPS! \nYou Lost", "loose", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
			 if(n == 0) {
				main.hard();
			}
			else if(n == 1) {
				menu.main(null);
			}
		}		
	}
	
	
	public HardBoard() {
		try {
			mainBackImg = ImageIO.read(new File("wall2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		spaceship.keyPressed(e);
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			repaint();	
			 try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
