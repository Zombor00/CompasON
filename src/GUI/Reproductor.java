package GUI;

import java.awt.*;

import javax.swing.*;

public class Reproductor extends JPanel{
	
	public Reproductor() {
		
		super();
		
		JButton botonPlay = new JButton("PLAY");
		/*botonPlay.setIcon(new ImageIcon(new ImageIcon("aux/play-button.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		botonPlay.setOpaque(false);
		botonPlay.setContentAreaFilled(false);
		botonPlay.setBorderPainted(false);
		botonPlay.setPressedIcon(new ImageIcon(new ImageIcon("aux/play-button2.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		botonPlay.setLocation(0, 0);*/
		
		this.add(botonPlay);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(new ImageIcon("aux/black-gradient-background.png").getImage(), 0, 0,this.getSize().width,this.getSize().height, null);
	}
	
	

}
