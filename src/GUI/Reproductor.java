package GUI;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Reproductor extends JPanel{
	
	public static ImageIcon imagePausa = new ImageIcon("aux/boton-pausa.png");
	public static ImageIcon imagePlay = new ImageIcon("aux/play-button.png");
	private JButton botonPlay;
	private boolean play;
	public Reproductor() {
		
		super();
		
		botonPlay = new JButton();
		botonPlay.setIcon(new ImageIcon(imagePlay.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		botonPlay.setOpaque(false);
		botonPlay.setContentAreaFilled(false);
		botonPlay.setBorderPainted(false);
		botonPlay.setPressedIcon(new ImageIcon(new ImageIcon("aux/play-button2.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
		botonPlay.setLocation(0, 0);
		play = true;
		
		this.add(botonPlay);

	}
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(new ImageIcon("aux/black-gradient-background.png").getImage(), 0, 0,this.getSize().width,this.getSize().height, null);
	}
	
	public void changeIcon(boolean play) {
		if(!play) {
			botonPlay.setIcon(new ImageIcon(imagePausa.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
			this.play = false;
		}else {
			botonPlay.setIcon(new ImageIcon(imagePlay.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
			this.play = true;
			
		}
	}
	
	public void setControlador(ActionListener controlador) {
		botonPlay.setActionCommand("PULSADO");
		botonPlay.addActionListener(controlador);
	}
	
	public boolean getPlay() {
		return this.play;
	}
}
