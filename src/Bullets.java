import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Bullets {

	 EasyBoard easyBoard;
	 NormalBoard normalBoard;
	 HardBoard hardBoard;
	 BufferedImage bulletImg1, bulletImg2, bulletImg3;
	 int  decY = 5,
		  bullNum = 1000
		  ,number = 30
		  ,syncBull = 40; 
	 int count[] = new int[bullNum];
	 int y[] = new int[bullNum];
	 int x[] = new int[bullNum];
	 int i = 0;
	 boolean bullet1 = true, bullet2 = false, bullet3 = false;
	 

	 public void paint(Graphics g) {			
		for ( i = 0; i < bullNum; i++) {
///////////////draw second to last bullets
			if(i > 0){
////////////////make distance between bullets
				if(count[i-1] > number) {
					if(easyBoard.spaceship.left  || easyBoard.spaceship.right) {
						for(int j = i; j < bullNum; j++) {
////////////////change position of bullets which are behind ship
							if(count[j] < 5) {
								x[j] = easyBoard.spaceship.getX()+syncBull;
							}						
							easyBoard.spaceship.left = false;
							easyBoard.spaceship.right = false;	
						}
						
					}
										
				   if(easyBoard.spaceship.up || easyBoard.spaceship.down) {
						for(int j = i; j < bullNum; j++) {
							if(count[j] < 5) {
								y[j] = easyBoard.spaceship.getY();
							}					
						}
						easyBoard.spaceship.up = false;
						easyBoard.spaceship.down = false;
					}

				    y[i] -= decY;
				    if(bullet1) {
				    	g.drawImage(bulletImg1, x[i], y[i], null);
				    }
				    else if(bullet2) {
				    	g.drawImage(bulletImg2, x[i], y[i], null);
				    }
				    else if(bullet3) {
				    	g.drawImage(bulletImg3, x[i], y[i], null);
				    }
					
					count[i] ++;
				}
				
			}
/////////////draw first bullet
			else {
				if(bullet1) {
			    	g.drawImage(bulletImg1, x[i], y[i], null);
			    }
			    else if(bullet2) {
			    	g.drawImage(bulletImg2, x[i], y[i], null);
			    }
			    else if(bullet3) {
			    	g.drawImage(bulletImg3, x[i], y[i], null);
			    }
				y[i] -= decY;
				count[i] ++;
			}
			
		}
				
	}
	
	
	public void paint2(Graphics g) {
		for ( i = 0; i < bullNum; i++) {
///////////////draw second till last bullets
			if(i > 0){
////////////////make distance between bullets
				if(count[i-1] > number) {
					if(normalBoard.spaceship.left  || normalBoard.spaceship.right) {
						for(int j = i; j < bullNum; j++) {
////////////////change position of bullets which are behind ship
							if(count[j] < 5) {
								x[j] = normalBoard.spaceship.getX()+syncBull;
							}						
							normalBoard.spaceship.left = false;
							normalBoard.spaceship.right = false;	
						}
						
					}
										
				   if(normalBoard.spaceship.up || normalBoard.spaceship.down) {
						for(int j = i; j < bullNum; j++) {
							if(count[j] < 5) {
								y[j] = normalBoard.spaceship.getY();
							}					
						}
						normalBoard.spaceship.up = false;
						normalBoard.spaceship.down = false;
					}

				    y[i] -= decY;
				    if(bullet1) {
				    	g.drawImage(bulletImg1, x[i], y[i], null);
				    }
				    else if(bullet2) {
				    	g.drawImage(bulletImg2, x[i], y[i], null);
				    }
				    else if(bullet3) {
				    	g.drawImage(bulletImg3, x[i], y[i], null);
				    }
					
					count[i] ++;
				}
				
			}
/////////////draw first bullet
			else {
				if(bullet1) {
			    	g.drawImage(bulletImg1, x[i], y[i], null);
			    }
			    else if(bullet2) {
			    	g.drawImage(bulletImg2, x[i], y[i], null);
			    }
			    else if(bullet3) {
			    	g.drawImage(bulletImg3, x[i], y[i], null);
			    }
				y[i] -= decY;
				count[i] ++;
			}
			
		}
				
	}
	
	 public void paint3(Graphics g) {			
			for ( i = 0; i < bullNum; i++){
	///////////////draw second till last bullets
				if(i > 0){
	////////////////make distance between bullets
					if(count[i-1] > number) {
						if(hardBoard.spaceship.left  || hardBoard.spaceship.right) {
							for(int j = i; j < bullNum; j++) {
	////////////////change position of bullets which are behind ship
								if(count[j] < 5) {
									x[j] = hardBoard.spaceship.getX()+syncBull;
								}						
								hardBoard.spaceship.left = false;
								hardBoard.spaceship.right = false;	
							}
							
						}
											
					   if(hardBoard.spaceship.up || hardBoard.spaceship.down) {
							for(int j = i; j < bullNum; j++) {
								if(count[j] < 5) {
									y[j] = hardBoard.spaceship.getY();
								}					
							}
							hardBoard.spaceship.up = false;
							hardBoard.spaceship.down = false;
						}

					    y[i] -= decY;
					    if(bullet1) {
					    	g.drawImage(bulletImg1, x[i], y[i], null);
					    }
					    else if(bullet2) {
					    	g.drawImage(bulletImg2, x[i], y[i], null);
					    }
					    else if(bullet3) {
					    	g.drawImage(bulletImg3, x[i], y[i], null);
					    }
						
						count[i] ++;
					}
					
				}
/////////////draw first bullet
				else {
					if(bullet1) {
				    	g.drawImage(bulletImg1, x[i], y[i], null);
				    }
				    else if(bullet2) {
				    	g.drawImage(bulletImg2, x[i], y[i], null);
				    }
				    else if(bullet3) {
				    	g.drawImage(bulletImg3, x[i], y[i], null);
				    }
					y[i] -= decY;
					count[i] ++;
				}
				
			}
					
		}
	

	public Bullets(EasyBoard board) {
		// TODO Auto-generated constructor stub
		easyBoard = board;

		Arrays.fill(y, board.spaceship.getY());
		Arrays.fill(x, board.spaceship.getX()+syncBull);
		
		try {
			bulletImg1 = ImageIO.read(new File("arr5.png"));
			bulletImg2 = ImageIO.read(new File("up-arrow2.png"));
//			bulletImg3 = ImageIO.read(new File("up-arrow3.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	public Bullets(NormalBoard board) {
		// TODO Auto-generated constructor stub
		normalBoard = board;

		Arrays.fill(y, board.spaceship.getY());
		Arrays.fill(x, board.spaceship.getX()+syncBull);
		
		try {
			bulletImg1 = ImageIO.read(new File("arr5.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	
	public Bullets(HardBoard board) {
		// TODO Auto-generated constructor stub
		hardBoard = board;

		Arrays.fill(y, board.spaceship.getY());
		Arrays.fill(x, board.spaceship.getX()+syncBull);
		
		try {
			bulletImg1 = ImageIO.read(new File("arr5.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
}
