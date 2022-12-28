import javax.swing.JFrame;

public class Main extends JFrame{

	public void makeFrame() {
		setSize(500,800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setVisible(true);
	}
	
	public void easy() {
		EasyBoard board = new EasyBoard();
		makeFrame();
		add(board);
		addKeyListener(board);
	}
	
	public void normal() {
		NormalBoard board = new NormalBoard();
		makeFrame();
		add(board);
		addKeyListener(board);
	}
	
	public void hard() {
		HardBoard board = new HardBoard();
		makeFrame();
		add(board);
		addKeyListener(board);
	}
	
	

}
