import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Spaceship implements KeyListener{
	
	EasyBoard board;
	NormalBoard normalBoard;
	HardBoard hardBoard;
	BufferedImage spaceshipImg;
	int x = 200, y = 590, yt = 30, xt = 30;
	boolean up = false, down = false, right = false, left = false;
	boolean collision = false;
	boolean finish = false;
	int index = 0;
	char skullCoords[] = new char[16];
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void paint(Graphics g) {		
		g.drawImage(spaceshipImg, x, y, null);
	
	}
	
/////////when game was finished	
	public void shipOut(){
		for (int i = 0; i < board.bullets.bullNum; i++) {
			board.bullets.y[i] = -100;
		}
		board.energy.speedUp = false;
		board.time = 10;
		y -= 10;	
		if(y < -100) {
			finish = true;
		}	
	}
	
	public void shipOut2(){
		index = 0;
		for(int i = 0; i < normalBoard.barriers.skullNum; i++) {
			if(normalBoard.barriers.y[i] >= 800) {
				skullCoords[i] = 'a';
			}
			else {
				skullCoords[i] = 'b';
				index ++;
			}
		}
		if(index == 0) {
			for (int i = 0; i < normalBoard.bullets.bullNum; i++) {
				normalBoard.bullets.y[i] = -100;
			}
			y -= 10;	
			if(y < -100) {
				finish = true;
			}
		}
	}

	public Spaceship(EasyBoard board) {
		this.board = board;
		
		try {
			spaceshipImg = ImageIO.read(new File("spaceship.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Spaceship(NormalBoard board) {
		normalBoard = board;	
		try {
			spaceshipImg = ImageIO.read(new File("spaceship.png"));			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public Spaceship(HardBoard board) {
		hardBoard = board;		
		try {
			spaceshipImg = ImageIO.read(new File("spaceship.png"));
			
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
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(x > 5) {
				x -= xt;
				left = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(x < 390) {
				x += xt;
				right = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(y > 20) {
				y -= yt;
				up = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(y < 650) {
				y += yt;
				down = true;
			}
		}	
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Rectangle shipBound() {
		return new Rectangle(x, y, spaceshipImg.getWidth(), spaceshipImg.getHeight());
	}
}
