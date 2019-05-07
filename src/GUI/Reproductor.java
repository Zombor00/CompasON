package GUI;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Esta clase tiene toda la informacion relevante a la parte de la 
 * ventana en la que se encuentra el reproductor
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Reproductor extends JPanel{
	
	/**
	 * Imagen del icono del boton de pausa
	 */
	public static ImageIcon imagePausa = new ImageIcon("aux/boton-pausa.png");
	/**
	 * Imagen del icono del boton de play
	 */
	public static ImageIcon imagePlay = new ImageIcon("aux/play-button.png");
	/**
	 * Boton para pausar y reproducir desde el comienzo la cola de reproduccion
	 */
	private JButton botonPlay;
	/**
	 * Variable que indica si esta sonando el reproductor
	 */
	private boolean play;
	/**
	 * Variable que indica si es la primera reproduccion de una cola
	 */
	private boolean firstRep;
	
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
		firstRep = true;
		
		this.add(botonPlay);

	}
	
	@Override
	public void paintComponent(Graphics g) {
	    g.drawImage(new ImageIcon("aux/black-gradient-background.png").getImage(), 0, 0,this.getSize().width,this.getSize().height, null);
	}
	
	/**
	 * Metodo que pone el icono indicado dependiendo de si reproduce o no
	 * 
	 * @param play boolean que indica si el reproductor esta sonando
	 */
	public void changeIcon(boolean play) {
		if(!play) {
			botonPlay.setIcon(new ImageIcon(imagePausa.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
			this.play = false;
		}else {
			botonPlay.setIcon(new ImageIcon(imagePlay.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
			this.play = true;
			
		}
	}
	
	/**
	 * Metodo que establece el controlador de las acciones de los botones
	 * 
	 * @param controlador de los botones de la pestania
	 */
	public void setControlador(ActionListener controlador) {
		botonPlay.setActionCommand("PULSADO");
		botonPlay.addActionListener(controlador);
	}
	
	public boolean getPlay() {
		return this.play;
	}
	
	public boolean getFirstRep() {
		return this.firstRep;
	}
	
	public void setFirstRep(boolean b) {
		firstRep = b;
	}
}
