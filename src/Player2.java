import javax.swing.JOptionPane;

public class Player2{

	NormalBoard normalBoard;
	boolean loose = false;
	Thread thread;
	
	public Player2(NormalBoard board) {
		normalBoard = board;
	}
	
	public void gameOver2() {
		if(normalBoard.spaceship.collision) {
			normalBoard.spaceship.setY(-200);
/////////////change position of bullets with ship
			for (int i = normalBoard.bird.j; i < normalBoard.bullets.bullNum; i++) {
				normalBoard.bullets.y[i] = normalBoard.spaceship.getY();
			}	
			loose = true;
		}
	}


	public void blow2() {
		if(normalBoard.bird.collBirds1[normalBoard.bird.j] >= normalBoard.bird.collNumber) {		
			normalBoard.bird.y[normalBoard.bird.j] = 800;	
		 }
		
		if(normalBoard.bird.collBirds2[normalBoard.bird.j] >= normalBoard.bird.collNumber) {		
			normalBoard.bird.y2[normalBoard.bird.j] = 800;
		}
		
		if(normalBoard.bird.collBirds3[normalBoard.bird.j] >= normalBoard.bird.collNumber) {		
			normalBoard.bird.y3[normalBoard.bird.j] = 800;
		}
		if(normalBoard.bird.collBirds4[normalBoard.bird.j] >= normalBoard.bird.collNumber) {		
			normalBoard.bird.y4[normalBoard.bird.j] = 800;
		}
		
		normalBoard.bullets.y[normalBoard.bird.i] = -20;
		
	}

}
