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

public class NormalBoard extends JPanel implements Runnable, KeyListener{

	BufferedImage mainBackImg;
    Spaceship spaceship = new Spaceship(this);
    NormalBird bird = new NormalBird(this);
    Barriers2 barriers = new Barriers2(this);
    Bullets bullets = new Bullets(this);
    Player2 player = new Player2(this);
	Thread gameThread;
	Main main = new Main();
	MainMenu menu = new MainMenu();
	int index;
	
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
		bullets.paint2(g);
		barriers.paint(g);
		spaceship.paint(g);	
		bird.paint(g);
//////////when all bird blew
		if(bird.count > bird.birdNum*4-1){
			spaceship.shipOut2();
			if(spaceship.finish) {
				gameThread.stop();
				Object[] options = {"Restart", "Next", "Home"};
				int n = JOptionPane.showOptionDialog(this, "Great \nYou Win", "Win", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
				if(n == 0) {
					main.normal();
				}
				else if(n == 1){
					main.hard();
				}
				else if(n == 2) {
					menu.main(null);
				}	
		    }
							
	     }
				
		if(player.loose){
			gameThread.stop();
			Object[] options = {"Restart", "Home"};
			int n = JOptionPane.showOptionDialog(this, "OPS! \nYou Lost", "loose", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
			 if(n == 0) {
				main.normal();
			}
			else if(n == 1) {
				menu.main(null);
			}
		}		
	}
	

	public NormalBoard() {
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
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
