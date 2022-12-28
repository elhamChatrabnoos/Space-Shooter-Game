import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Energy implements Runnable{

	EasyBoard board;
	BufferedImage energyImg;
	int energyNum = 1;
	int count[] = new int[energyNum];
	int x[] = new int[energyNum]; 
	int y[] = new int[energyNum]; 
	int number[] = new int[energyNum];
	Random random = new Random();
	Rectangle energyBound;
	boolean xy = true;
	int i, j;
	Thread thread;
	boolean speedUp = false;
	
	public Energy(EasyBoard board) {
		this.board = board;
		try {
			energyImg = ImageIO.read(new File("up3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < energyNum; i++) {
			number[i] = random.nextInt(board.bird.birdNum-1);
		}
		
		x[0] = -100;
		thread = new Thread(this);
		thread.start();
	}

	
	public void paint(Graphics g) {
//////////when birds stop moving, draw skull 
		if(board.bird.y3[board.bird.birdNum-1] < 300) {
			if(xy) {
				for (int i = 0; i < energyNum; i++) {
					 x[i] = board.bird.x3[number[i]]+10;
					 y[i] = board.bird.y3[number[i]]+10;
				}	
				xy = false;
			}
		}
		for(int i = 0; i < energyNum; i++) {
			g.drawImage(energyImg, x[i], y[i], null);
		}
		 j = 0;
		energyBound();
	}

	public void energyBound() {
		for ( j = 0; j < number.length; j++) {
			 energyBound = new  Rectangle(x[j], y[j], energyImg.getWidth(), energyImg.getHeight()); 
			 shipBarColl();
		}
	}
	
	public void shipBarColl() {
		 if( board.spaceship.shipBound().intersects(energyBound)) 			{
			 if(board.bullets.bullet1){
				 board.bullets.bullet1 = false;
				 board.bullets.bullet2 = true;
				 y[j] = -300;
				 speedUp = true;
			}
			else {
				 board.bullets.bullet2 = false;
				 board.bullets.bullet3 = true;
				 y[j] = -300;				
			}
		 }
		 
	}
	
	public void moveEnergy() {
		y[i] +=10;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			for (i = 0; i < energyNum; i++) {
////////////////when collNumber equals 3 and energy is in screen energy moved
				if(board.bird.collBirds3[number[i]] >= board.bird.collNumber && y[i] > -300) {
					moveEnergy();
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
