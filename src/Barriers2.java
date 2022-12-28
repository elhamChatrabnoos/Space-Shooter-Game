import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

public class Barriers2 implements Runnable{

	NormalBoard normalBoard;
	BufferedImage skull;
	int skullNum = 16;
	int count[] = new int[skullNum];
	int x[] = new int[skullNum]; 
	int y[] = new int[skullNum]; 
	Rectangle skullBound;
	boolean xy = true;
	int i = 0, j = 0;
	Thread thread;
	
	public Barriers2(NormalBoard board) {
		this.normalBoard = board;
		Arrays.fill(x, -20);
		try {
			skull = ImageIO.read(new File("skull.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		thread = new Thread(this);
		thread.start();
	}

	
	public void paint(Graphics g) {
//////////when bird stop, draw skull 
		if(normalBoard.bird.y3[normalBoard.bird.birdNum-1] < 200) {
			if(xy) {
				for (int i = 0; i < skullNum/4; i++) {
					x[i] = normalBoard.bird.x[j]+20;
					y[i] = normalBoard.bird.y[j]+20;
					j++;
				}
				j = 0;
				for (int i = skullNum/4; i < skullNum/2; i++) {
					x[i] = normalBoard.bird.x2[j]+20;
					y[i] = normalBoard.bird.y2[j]+20;
					j++;
				}
				j = 0;
				for (int i = skullNum/2; i < skullNum-4; i++) {
					x[i] = normalBoard.bird.x3[j]+20;
					y[i] = normalBoard.bird.y3[j]+20;
					j++;
				}
				j = 0;
				for (int i = skullNum-4; i < skullNum; i++) {
					x[i] = normalBoard.bird.x4[j]+20;
					y[i] = normalBoard.bird.y4[j]+20;
					j++;
				}
				xy = false;
			}
		}
		
		for(int i = 0; i < skullNum; i++) {
			g.drawImage(skull, x[i], y[i], null);
		}
		barrierBound();
		
	}

	public void barrierBound() {
		for (int i = 0; i < skullNum; i++) {
			 skullBound = new  Rectangle(x[i], y[i], skull.getWidth(), skull.getHeight());
			 shipBarColl();
		}
	}
	
	public void shipBarColl() {
		 if(normalBoard.spaceship.shipBound().intersects(skullBound)) {
			normalBoard.spaceship.collision = true;
			normalBoard.player.gameOver2();
		 } 
	}
	int index = 0;
	int j2 = 0;
	public void moveSkull(){
		y[index] += 10;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			j2 = 0;
			for (int i = 0; i < skullNum/4; i++) {
				if(normalBoard.bird.collBirds1[j2] >= normalBoard.bird.collNumber){
					index = i;
					moveSkull();
				}
				j2++;
			}
			j2 = 0;
			for (int i = skullNum/4; i < skullNum/2; i++) {
				if(normalBoard.bird.collBirds2[j2] >= normalBoard.bird.collNumber) {
					index = i;
					moveSkull();
				}
				j2++;				
			}
			
			j2 = 0;
			for (int i = skullNum/2; i < skullNum-4; i++) {
				if(normalBoard.bird.collBirds3[j2] >= normalBoard.bird.collNumber) {
					index = i;
					moveSkull();
				}
				j2++;
			}
			j2 = 0;
			for (int i = skullNum-4; i < skullNum; i++) {
				if(normalBoard.bird.collBirds4[j2] >= normalBoard.bird.collNumber){
					index = i;
					moveSkull();
				}
				j2++;
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
