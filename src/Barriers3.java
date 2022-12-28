import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

public class Barriers3 implements Runnable{

	HardBoard hardBoard;
	BufferedImage skull, shooter, dead;
	int skullNum = 16;
	int count[] = new int[skullNum];
	int x[] = new int[skullNum]; 
	int y[] = new int[skullNum];
	int xShooter = 150; 
	int yShooter = -100;
	Rectangle skullBound;
	Rectangle shooterBound;
	boolean xy = true;
	int i = 0;
	Thread thread;
	
	
	public Barriers3(HardBoard board) {
		this.hardBoard = board;
		Arrays.fill(x, -20);
		try {
			skull = ImageIO.read(new File("skull.png"));
			shooter = ImageIO.read(new File("angry6.png"));
			dead = ImageIO.read(new File("angry5.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void moveShooter() {
		if(yShooter < 20) {
			yShooter += 10;	
		}
	}
	
	public void paint(Graphics g) {
		if(yShooter >= 20) {
			if(xy) {
				for(int i = 0; i < skullNum; i++) {
					y[i] = yShooter+100;
					x[i] = xShooter+60;
				}
				xy = false;
			}
		}		
		for(int i = 0; i < skullNum; i++) {
			g.drawImage(skull, x[i], y[i], null);
		}
		g.drawImage(shooter, xShooter, yShooter, null);
		barrierBound();
		
	}

	public void barrierBound() {
		for (int i = 0; i < skullNum; i++) {
			 skullBound = new  Rectangle(x[i], y[i], skull.getWidth(), skull.getHeight());
			 shipBarColl();
		}
	}
	
	public void shipBarColl() {
		 if(hardBoard.spaceship.shipBound().intersects(skullBound)) {
			 hardBoard.player.loose = true;
			 hardBoard.player.gameOver();
		 } 
	}
	
	public void moveSkull(){
		y[i] += 10;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			if(count[i] > 10) {
				moveSkull();
			
//////////// set time for next skull
				if(count[i]  > 130) {
					if(i < skullNum-1) {
						i++;
						count[i] = 0;
					}			
				}
			}
			moveShooter();
			try {
				Thread.sleep(50);
				count[i] ++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
