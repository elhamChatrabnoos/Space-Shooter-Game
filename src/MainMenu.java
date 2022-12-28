import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu extends JFrame implements ActionListener, Runnable{
	
	JButton btnEasy, btnNormal, btnHard, btnStart;
	JPanel menu, start;
	BufferedImage btnImg, mainBackImg;
	Thread mainThread;
	Color btnColor = new Color(245, 215, 0);
	Color panelColor = new Color(235, 237, 146);
	
	public MainMenu() {
		 
		setSize(500,800);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);		
		
		start = new JPanel();
		start.setBounds(0, 0, 500, 800);
		start.setBackground(panelColor);
		start.setLayout(null);
		add(start);
			
		btnStart = new JButton("Start");
		btnStart.setBounds(90, 400, 300, 50);
		btnStart.addActionListener(this);
		btnStart.setBackground(btnColor);
		start.add(btnStart);
		
		menu = new JPanel();
		menu.setBounds(0, 0, 500, 800);
		menu.setBackground(panelColor);
		menu.setLayout(null);
		add(menu);
		menu.setVisible(false);
		
		btnEasy = new JButton("EASY");
		btnEasy.setBounds(90, 350, 300, 50);
		btnEasy.addActionListener(this);
		btnEasy.setBackground(btnColor);
		menu.add(btnEasy);
		
		btnNormal = new JButton("NORMAL");
		btnNormal.setBounds(90, 410, 300, 50);
		btnNormal.addActionListener(this);
		btnNormal.setBackground(btnColor);
		menu.add(btnNormal);
		
		btnHard = new JButton("HARD");
		btnHard.setBounds(90, 470, 300, 50);
		btnHard.addActionListener(this);
		btnHard.setBackground(btnColor);
		menu.add(btnHard);
		
		setVisible(true);	
		
		try {
			btnImg = ImageIO.read(new File("ship1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void addNotify() {
		// TODO Auto-generated method stub
		super.addNotify();
		mainThread = new Thread(this);
		mainThread.start();
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(btnImg, 100, 50, 300, 300, null);
	}

	public static void main(String[] args) {
		new MainMenu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStart) {
			start.setVisible(false);
			menu.setVisible(true);
		}
		if(e.getSource() == btnEasy) {
			dispose();
			Main main = new Main();
			main.easy();
		}
		if(e.getSource() == btnNormal) {
			dispose();
			Main main = new Main();
			main.normal();
		}
		if(e.getSource() == btnHard) {
			dispose();
			Main main = new Main();
			main.hard();
		}
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			repaint();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
