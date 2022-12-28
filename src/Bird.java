import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Bird implements Runnable{

	BufferedImage birdImg, birdImg2, birdImg3;
	EasyBoard board; 
	int	birdNum = 7
			,tmp
			,tmpY, tmpX
			,tmpY2, tmpX2
			,tmpY3, tmpX3
			,stop = 400, stop2 = 50
			,inc = 60
			,ys = 80;
	int x[] = new int[birdNum];
	int y[] = new int[birdNum];
	int x2[] = new int[birdNum];
	int y2[] = new int[birdNum];
	int x3[] = new int[birdNum];
	int y3[] = new int[birdNum];
	int collBirds1[] = new int[birdNum];
	int collBirds2[] = new int[birdNum];
	int collBirds3[] = new int[birdNum];
	Rectangle bulletBound = null;
	Rectangle birdBound = null;
	Rectangle birdBound2 = null;
	Rectangle birdBound3 = null;
	boolean xyFull = true, skull = false;
	Thread thread;
	int collNumber = 3;
	int j, i;
	boolean bird3 = true;
	
	public void moveBird() {
///////////first row
		tmpX = x[0];
		tmpY = y[0];
		
////////////move in a row
		if(y[0] > ys && x[0] > ys/2) {
			x[0] += inc;
		}
////////////move from start point
		else if(x[0] < stop ) {
			x[0] += inc;
			y[0] += inc;
		}
////////////stop in row
		if(x[0] > stop) {
			x[0] = stop;
		}	
///////////draw bird in row
		for(int i = 1; i < birdNum; i++) {
			if(collBirds1[i] < collNumber && collBirds1[0] < collNumber) {
				tmp = tmpY;
				tmpY = y[i];
				y[i] = tmp;	
			}
			
			if(x[0] < stop) {
				tmp = tmpX;
				tmpX = x[i];
				x[i] = tmp;
			}
			else{		
				x[i] = x[0]-inc*i;
				}			

		}
	
	}
	
	public void moveBird2() {
///////////first row
		tmpX2 = x2[0];
		tmpY2 = y2[0];
		
////////////move in a row
		if(y2[0] > ys+60) {
			x2[0] -= inc;
		}
////////////move from start point
		else if(x2[0] > stop2 ) {
			x2[0] -= inc;
			y2[0] += inc;
		}
////////////stop in row
		if(x2[0] < stop2) {
			x2[0] = stop2;
		}	
///////////draw bird in row
		for(int i = 1; i < birdNum; i++) {
			if(collBirds2[i] < collNumber && collBirds2[0] < collNumber) {
				tmp = tmpY2;
				tmpY2 = y2[i];
				y2[i] = tmp;	
			}
			
			if(x2[0] > stop2) {
				tmp = tmpX2;
				tmpX2 = x2[i];
				x2[i] = tmp;
			}
			else{		
				x2[i] = x2[0]+inc*i;
			}	
		}
	}
	
	public void moveBird3() {
///////////first row
		tmpX3 = x3[0];
		tmpY3 = y3[0];
		
////////////move in a row
		if(y3[0] < ys+240) {
			x3[0] += inc;
		}
////////////move from start point
		else if(x3[0] < stop ) {
			y3[0] -= inc;
			x3[0] += inc;
			
		}
////////////stop in row
		if(x3[0] > stop) {
			x3[0] = stop;
		}	
///////////draw bird in row
		for(int i = 1; i < birdNum; i++) {
			if(collBirds3[i] < collNumber && collBirds3[0] < collNumber) {
				tmp = tmpY3;
				tmpY3 = y3[i];
				y3[i] = tmp;	
			}
			
			if(x3[0] < stop) {
				tmp = tmpX3;
				tmpX3 = x3[i];
				x3[i] = tmp;
			}
			else{		
				x3[i] = x3[0]-inc*i;
			}			

		}
	
	}
	
	public void paint(Graphics g) {
		g.drawImage(birdImg, x[0], y[0], null);
		for (int i = 1; i < birdNum; i++) {
			g.drawImage(birdImg, x[i], y[i], null);	
		}
		g.drawImage(birdImg2, x2[0], y2[0], null);
		for (int i = 1; i < birdNum; i++) {
			g.drawImage(birdImg2, x2[i], y2[i], null);	
		}
		if(bird3) {
			g.drawImage(birdImg3, x3[0], y3[0], null);
			for (int i = 1; i < birdNum; i++) {
				g.drawImage(birdImg3, x3[i], y3[i], null);	
			}
		}
		
		
		checkBound();		
	}
	
	public Bird(EasyBoard board2) {
		this.board = board2;

		Arrays.fill(x, -10);
		Arrays.fill(y, -30);
		Arrays.fill(x2, 510);
		Arrays.fill(y2, -50);
		Arrays.fill(x3, -20);
		Arrays.fill(y3, 500);
			
		try {				
			birdImg = ImageIO.read(new File("angry1.png"));
			birdImg2 = ImageIO.read(new File("angry2.png"));
			birdImg3 = ImageIO.read(new File("angry3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		thread = new Thread(this);
		thread.start();
	}
		
	public void checkBound() {			
///////////compare collision of each bullet	with each bird	
		for ( i = 0; i < board.bullets.bullNum; i++) {
			if(board.bullets.bullet1) {
				bulletBound = new Rectangle(board.bullets.x[i], board.bullets.y[i],
						board.bullets.bulletImg1.getWidth(),
						board.bullets.bulletImg1.getHeight());	
			}
			else if(board.bullets.bullet2) {
				bulletBound = new Rectangle(board.bullets.x[i], board.bullets.y[i],
						board.bullets.bulletImg2.getWidth(),
						board.bullets.bulletImg2.getHeight());	
			}
			
			for ( j = 0; j < birdNum; j++) {
				birdBound = new Rectangle(x[j], y[j], birdImg.getWidth(), birdImg.getHeight());
				birdBound2 = new Rectangle(x2[j], y2[j], birdImg.getWidth(), birdImg2.getHeight());
				birdBound3 = new Rectangle(x3[j], y3[j], birdImg.getWidth(), birdImg3.getHeight());
				birdBulletColl();
				birdShipColl();
			}		
		}

	}
	
	int count = 0;
	
	public void birdBulletColl(){
		if(bulletBound.intersects(birdBound)) {
			collBirds1[j]++;
/////////////count the bird which blew
			if(collBirds1[j] >= collNumber){
				count++;
			}
			board.player.blow();
		}	
		if(bulletBound.intersects(birdBound2)) {			
			collBirds2[j]++;
			if(collBirds2[j] >= collNumber) {
				count++;
			}
			board.player.blow();
		}
		if(bulletBound. intersects(birdBound3)) {	
			collBirds3[j]++;
			if(collBirds3[j] >= collNumber) {
				count++;
			}
			board.player.blow();
		}
		
	}
	
	public void birdShipColl() {
		board.spaceship.collision = false;
		if(board.spaceship.shipBound().intersects(birdBound)
			|| board.spaceship.shipBound().intersects(birdBound2)
			|| board.spaceship.shipBound().intersects(birdBound3)){
			board.spaceship.collision = true;
			board.player.gameOver();
		}
	}

	@Override
	public void run() {
		while(true) {
			moveBird();
			moveBird2();
			moveBird3();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}
