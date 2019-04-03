package GUI;

import java.awt.FlowLayout;

import javax.swing.*;

public class Sesion extends JPanel{
	
	public Sesion() {
		
		super();
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		JLabel mensaje = new JLabel("<html>Inicia sesion para ver<br>tus notificaiones.</html>");
		
		this.add(mensaje);
	}

}
