import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class HardBird extends Bird{
	HardBoard hardBoard; 
	static EasyBoard easyBoard;
	int p = 0;
	Rectangle shooterBound;
	int heart = 50;
	
	public HardBird(HardBoard board2){
		super(easyBoard);
		hardBoard = board2;
		bird3 = false;
		inc = 50;
		Arrays.fill(x, 500);
		Arrays.fill(y, 350);
		Arrays.fill(x2, 0);
		Arrays.fill(y2, 350);

		try {				
			birdImg = ImageIO.read(new File("angry1.png"));
			birdImg2 = ImageIO.read(new File("angry3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		thread = new Thread(this);
		thread.start();
	
	}
	
	
	public void moveBird() {
/////////control bird moving
		System.out.println();
	
		tmpX = x[0];
		tmpY = y[0];
//////// control moving in first part		
		if((x[0] <= 500 && x[0] > 300) && (y[0] <= 350 && y[0] > 100)) {
			x[0] -= inc;
			y[0] -= inc;
		}
////////control moving in second part		
		if((x[0] <= 300 && x[0] >= 150) && (y[0] >= 100 && y[0] < 300)) {
			x[0] -= inc;
			y[0] += inc;
		}
////////control moving in third part		
		if((x[0] >= 150 && x[0] <= 350) && (y[0] >= 300 && y[0] < 500)) {
			x[0] += inc;
			y[0] += inc;
		}
////////control moving in fourth part		
		if((x[0] >= 300 && x[0] <= 500) && (y[0] > 300 && y[0] <= 500)) {
			x[0] += inc;
			y[0] -= inc;
		}
//////// control moving other bird based on first bird
		for(int i = 1; i < birdNum; i++) {
			if(collBirds1[i] < collNumber && collBirds1[0] < collNumber
					&& y[i] < 800) {
				tmp = tmpY;
				tmpY = y[i];
				y[i] = tmp;	
				
				tmp = tmpX;
				tmpX = x[i];
				x[i] = tmp;
			}
			
		}
	
	}
	
	public void moveBird2() {
		tmpX2 = x2[0];
		tmpY2 = y2[0];		
		
		if((x2[0] >= 0 && x2[0] < 200) && (y2[0] <= 350 && y2[0] > 100)) {
			x2[0] += inc;
			y2[0] -= inc;
		}
		if((x2[0] >= 200 && x2[0] < 350) && (y2[0] >= 100 && y2[0] < 300)) {
			x2[0] += inc;
			y2[0] += inc;
		}
		if((x2[0] <= 350 && x2[0] >= 200) && (y2[0] >= 300 && y2[0] < 500)) {
			x2[0] -= inc;
			y2[0] += inc;
		}
		if((x2[0] > 0 && x2[0] < 200) && (y2[0] > 300 && y2[0] <= 500)) {
			x2[0] -= inc;
			y2[0] -= inc;
		}
		for(int i = 1; i < birdNum; i++) {
			if(collBirds2[i] < collNumber && collBirds2[0] < collNumber
					&& y2[i] < 800 ) {		
				tmp = tmpY2;
				tmpY2 = y2[i];
				y2[i] = tmp;
				
				tmp = tmpX2;
				tmpX2 = x2[i];
				x2[i] = tmp;
			}			
		}
			
	}
			
	public void checkBound() {			
///////////compare collision of each bullet	with each bird	and shooter
		for ( i = 0; i < hardBoard.bullets.bullNum; i++) {
			if(hardBoard.bullets.bullet1) {
				bulletBound = new Rectangle(hardBoard.bullets.x[i], hardBoard.bullets.y[i],
				hardBoard.bullets.bulletImg1.getWidth(),
				hardBoard.bullets.bulletImg1.getHeight());	
			}
			shooterBound = new Rectangle(hardBoard.barriers.xShooter, 
					hardBoard.barriers.yShooter,
					hardBoard.barriers.shooter.getWidth(),
					hardBoard.barriers.shooter.getHeight());
			shooterBullColl();
			for ( j = 0; j < birdNum; j++) {
				birdBound = new Rectangle(x[j], y[j], birdImg.getWidth(), birdImg.getHeight());
				birdBound2 = new Rectangle(x2[j], y2[j], birdImg2.getWidth(), birdImg2.getHeight());
				birdBulletColl();
				birdShipColl();
			}
			
		}

	}
	
	public void shooterBullColl() {
		if(bulletBound.intersects(shooterBound)){
			heart --;
			hardBoard.bullets.y[i] = -20;
			if(heart == 2) {
				hardBoard.barriers.shooter = hardBoard.barriers.dead;
			}
			
		}
	}
	
	boolean win ;
	public void birdBulletColl(){
		if(bulletBound.intersects(birdBound)) {		
			collBirds1[j]++;
			hardBoard.player.blow();
		}	
		if(bulletBound.intersects(birdBound2)) {
			collBirds2[j]++;
			hardBoard.player.blow();
		}
	}

	public void birdShipColl() {
		hardBoard.spaceship.collision = false;
		if(hardBoard.spaceship.shipBound().intersects(birdBound)
			|| hardBoard.spaceship.shipBound().intersects(birdBound2)
			|| hardBoard.spaceship.shipBound().intersects(shooterBound)){
			hardBoard.player.loose = true;
			hardBoard.player.gameOver();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			moveBird();
			moveBird2();		
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
