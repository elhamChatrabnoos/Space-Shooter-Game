import javax.swing.JOptionPane;

public class Player{

	EasyBoard board;
	boolean loose = false;
	
	public Player(EasyBoard board) {
		this.board = board;
	}
	
	public void gameOver() {
		if(board.spaceship.collision) {
			board.spaceship.setY(-200);
/////////////change position of bullets with ship
			for (int i = board.bird.j; i < board.bullets.bullNum; i++) {
				board.bullets.y[i] = board.spaceship.getY();
			}	
			loose = true;
		}
	}
	
	public void blow() {
		if(board.bird.collBirds1[board.bird.j] >= board.bird.collNumber) {		
////////////sync skull position with bird position
			for (int i = 0; i < board.barriers.number.length ; i++) {
////////////skull is behind which bird
				if(board.barriers.number[i] == board.bird.j){
////////////if skull i out of the bird dont blew
					if(board.barriers.y[i] < board.bird.ys+70) {
						board.barriers.y[i] = 800;
					}
					
				}
			}
			board.bird.y[board.bird.j] = 800;	
		 }		
		if(board.bird.collBirds2[board.bird.j] >= board.bird.collNumber) {		
			board.bird.y2[board.bird.j] = 800;
		}		
		if(board.bird.collBirds3[board.bird.j] >= board.bird.collNumber) {		
			board.bird.y3[board.bird.j] = 800;
		}
////////move bullet out of screen when have collision with a bird		
		board.bullets.y[board.bird.i] = -20;
		
	}


}
