package GUI.AccesoComun;

import java.awt.Font;

import javax.swing.*;

public class Sesion extends JPanel {

	public Sesion() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JLabel mensaje = new JLabel("Nombre");
		mensaje.setFont(new Font(mensaje.getFont().getFontName(), mensaje.getFont().getStyle(), 20));
		JButton cerrar = new JButton("Cerrar Sesión");
		layout.putConstraint(SpringLayout.NORTH, mensaje, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, cerrar, 25, SpringLayout.NORTH, mensaje);
		
		layout.putConstraint(SpringLayout.WEST, mensaje, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, cerrar, 25, SpringLayout.WEST, this);
		
		this.add(mensaje);
		this.add(cerrar);
	}
}
