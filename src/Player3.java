import javax.swing.JOptionPane;

public class Player3{

	HardBoard hardBoard;
	boolean loose = false;
	boolean win = false;
	
	public Player3(HardBoard board) {
		hardBoard = board;
	}
	
	public void gameOver() {
		if(loose) {
			hardBoard.spaceship.setY(-200);
/////////////change position of bullets with ship
			for (int i = hardBoard.bird.j; i < hardBoard.bullets.bullNum; i++) {
				hardBoard.bullets.y[i] = hardBoard.spaceship.getY();
			}	
		}
	}


	public void blow() {
		if(hardBoard.bird.collBirds1[hardBoard.bird.j] >= hardBoard.bird.collNumber) {		
			hardBoard.bird.y[hardBoard.bird.j] = 800;	
		 }	
		if(hardBoard.bird.collBirds2[hardBoard.bird.j] >= hardBoard.bird.collNumber) {		
			hardBoard.bird.y2[hardBoard.bird.j] = 800;
		}
		hardBoard.bullets.y[hardBoard.bird.i] = -20;
		
	}

}
