package GUI;

import java.awt.*;

import javax.swing.*;

import GUI.UsuarioNoRegistrado.PanelUsuarioNoRegistrado;
import GUI.UsuarioRegistrado.PanelUsuarioRegistrado;

public class ConjuntoPestanias extends JPanel {
	
	final static String NOMBRENOREGISTRADO = "No registrado";
	final static String NOMBREREGISTRADO = "Registrado";
	
	public ConjuntoPestanias() {
		super();
		CardLayout layout = new CardLayout();		
		this.setLayout(layout);
		
		PanelUsuarioNoRegistrado panelUsuarioNoRegistrado = new PanelUsuarioNoRegistrado();
		PanelUsuarioRegistrado panelUsuarioRegistrado = new PanelUsuarioRegistrado();
		
		this.add(panelUsuarioNoRegistrado, NOMBRENOREGISTRADO);
		this.add(panelUsuarioRegistrado, NOMBREREGISTRADO);
		
		layout.show(this, NOMBRENOREGISTRADO);
		
		
		
	}
}
