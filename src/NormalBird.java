import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class NormalBird extends Bird{

	NormalBoard normalBoard;
	static EasyBoard easyBoard;
	BufferedImage birdImg4;
	int x4[] = new int[birdNum];
	int y4[] = new int[birdNum];
	int collBirds4[] = new int[birdNum];
	int tmpY4, tmpX4;
	Rectangle birdBound4 = null;
	
	public void moveBird() {
///////////first row
		tmpX = x[0];
		tmpY = y[0];
		
////////////move in a row
		if(y[0] < ys+20) {
			x[0] += inc;
		}
////////////move from start point
		else if(x[0] < stop ) {
			y[0] -= inc;
			x[0] += inc;
			
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
				x[i] = x[0]-(inc+40)*i;
			}			

		}
	
	}
	
	public void moveBird2() {
///////////first row
		tmpX2 = x2[0];
		tmpY2 = y2[0];
		
////////////move in a row
		if(y2[0] < ys+20) {
			x2[0] -= inc;
		}
////////////move from start point
		else if(x2[0] > stop2 ) {
			x2[0] -= inc;
			y2[0] -= inc;
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
				x2[i] = x2[0]+(inc+40)*i;
				}			

		}
	}
	
	
	public void moveBird3() {
///////////first row
		tmpX3 = x3[0];
		tmpY3 = y3[0];
		
////////////move in a row
		if(y3[0] < ys+80) {
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
				x3[i] = x3[0]-(inc+40)*i;
			}			

		}
	
	}
	
	public void moveBird4() {
///////////first row
		tmpX4 = x4[0];
		tmpY4 = y4[0];
		
////////////move in a row
		if(y4[0] < ys+80) {
			x4[0] -= inc;
		}
////////////move from start point
		else if(x4[0] > stop2 ) {
			y4[0] -= inc;
			x4[0] -= inc;		
		}
////////////stop in row
		if(x4[0] < stop2) {
			x4[0] = stop2;
		}	
///////////draw bird in row
		for(int i = 1; i < birdNum; i++) {
			if(collBirds4[i] < collNumber && collBirds4[0] < collNumber) {
				tmp = tmpY4;
				tmpY4 = y4[i];
				y4[i] = tmp;	
			}
			
			if(x4[0] > stop2) {
				tmp = tmpX4;
				tmpX4 = x4[i];
				x4[i] = tmp;
			}
			else{		
				x4[i] = x4[0]+(inc+40)*i;
			}			

		}
	
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(birdImg4, x4[0], y4[0], null);
		for (int i = 1; i < birdNum; i++) {
			g.drawImage(birdImg4, x4[i], y4[i], null);	
		}
	}
	
	public NormalBird(NormalBoard board2) {
		super(easyBoard);
		normalBoard = board2;

		birdNum = 4;
		Arrays.fill(x, -20);
		Arrays.fill(y, 300);
		Arrays.fill(x2, 510);
		Arrays.fill(y2, 300);
		Arrays.fill(x3, -20);
		Arrays.fill(y3, 500);
		Arrays.fill(x4, 510);
		Arrays.fill(y4, 500);
			
		try {				
			birdImg = ImageIO.read(new File("angry1.png"));
			birdImg2 = ImageIO.read(new File("angry2.png"));
			birdImg3 = ImageIO.read(new File("angry3.png"));
			birdImg4 = ImageIO.read(new File("angry4.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	
	}
		
	public void checkBound() {			
///////////compare collision of each bullet	with each bird	
		for ( i = 0; i < normalBoard.bullets.bullNum; i++) {
			if(normalBoard.bullets.bullet1) {
				bulletBound = new Rectangle(normalBoard.bullets.x[i], normalBoard.bullets.y[i],
						normalBoard.bullets.bulletImg1.getWidth(),
						normalBoard.bullets.bulletImg1.getHeight());	
			}
			else if(normalBoard.bullets.bullet2) {
				bulletBound = new Rectangle(normalBoard.bullets.x[i], normalBoard.bullets.y[i],
						normalBoard.bullets.bulletImg2.getWidth(),
						normalBoard.bullets.bulletImg2.getHeight());	
			}
			else if(normalBoard.bullets.bullet3) {
				bulletBound = new Rectangle(normalBoard.bullets.x[i], normalBoard.bullets.y[i],
						normalBoard.bullets.bulletImg3.getWidth(),
						normalBoard.bullets.bulletImg3.getHeight());	
			}
			
			for ( j = 0; j < birdNum; j++) {
				birdBound = new Rectangle(x[j], y[j], birdImg.getWidth(), birdImg.getHeight());
				birdBound2 = new Rectangle(x2[j], y2[j], birdImg2.getWidth(), birdImg2.getHeight());
				birdBound3 = new Rectangle(x3[j], y3[j], birdImg3.getWidth(), birdImg3.getHeight());
				birdBound4 = new Rectangle(x4[j], y4[j], birdImg4.getWidth(), birdImg4.getHeight());
				birdBulletColl();
				birdShipColl();
			}		
		}

	}
	
	public void birdBulletColl(){
		if(bulletBound.intersects(birdBound)) {		
			collBirds1[j]++;
			if(collBirds1[j] >= 3) {
				count++;
			}
			normalBoard.player.blow2();
		}	
		if(bulletBound.intersects(birdBound2)) {
			collBirds2[j]++;
			if(collBirds2[j] >= 3) {
				count++;
			}
			normalBoard.player.blow2();
		}
		if(bulletBound. intersects(birdBound3)) {
			collBirds3[j]++;
			if(collBirds3[j] >= 3) {
				count++;
			}
			normalBoard.player.blow2();
		}
		if(bulletBound. intersects(birdBound4)){
			collBirds4[j]++;
			if(collBirds4[j] >= 3) {
				count++;
			}
			normalBoard.player.blow2();
		}
	}
	
	public void birdShipColl() {
		normalBoard.spaceship.collision = false;
		if(normalBoard.spaceship.shipBound().intersects(birdBound)
			|| normalBoard.spaceship.shipBound().intersects(birdBound2)
			|| normalBoard.spaceship.shipBound().intersects(birdBound3)){
			normalBoard.spaceship.collision = true;
			normalBoard.player.gameOver2();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			moveBird();
			moveBird2();		
			moveBird3();
			moveBird4();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
