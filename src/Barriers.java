import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Barriers implements Runnable{

	EasyBoard board;
	BufferedImage skull;
	int skullNum = 6;
	int count[] = new int[skullNum];
	int x[] = new int[skullNum]; 
	int y[] = new int[skullNum]; 
	int number[] = new int[skullNum];
	Random random = new Random();
	Rectangle skullBound;
	boolean xy = true;
	int i = 0;
	Thread thread;
	
	public Barriers(EasyBoard board) {
		this.board = board;
		try {
			skull = ImageIO.read(new File("skull.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		for (int i = 0; i < skullNum; i++) {
			number[i] = random.nextInt(board.bird.birdNum-1);
		}
		
		thread = new Thread(this);
		thread.start();
	}

	
	public void paint(Graphics g) {
//////////when bird stop, draw skull 
		if(board.bird.x[board.bird.birdNum-1] > 30) {
			if(xy) {
				for (int i = 0; i < skullNum; i++) {
					x[i] = board.bird.x[number[i]]+20;
					y[i] = board.bird.y[number[i]]+20;
				}	
				xy = false;
			}
			
			for(int i = 0; i < skullNum; i++) {
				g.drawImage(skull, x[i], y[i], null);
			}
			barrierBound();
		}
		
	}

	public void barrierBound() {
		for (int i = 0; i < number.length; i++) {
			 skullBound = new  Rectangle(x[i], y[i], skull.getWidth(), skull.getHeight());
			 shipBarColl();
		}
	}
	
	public void shipBarColl() {
		 if( board.spaceship.shipBound().intersects(skullBound)) {
			board.spaceship.collision = true;
			board.player.gameOver();
		 } 
	}
	
	public void moveSkull() {
		y[i] += 10;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
//////////// set time 
			if(count[i] > 80) {
//////////// if bird blew, skull blow too
				if((board.bird.collBirds1[number[i]] < 3 || y[i] > board.bird.ys+20)) {
					moveSkull();
				}
				else if(i < skullNum-1){
					i++;
				}				
			}
//////////// set time for next skull
			if(count[i]  > 150) {
				if(i < skullNum-1) {
					i++;
					count[i] = 0;
				}			
			}
			try {
				Thread.sleep(80);
				count[i] ++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
