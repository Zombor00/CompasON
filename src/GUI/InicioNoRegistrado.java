package GUI;

import java.awt.*;

import javax.swing.*;

public class InicioNoRegistrado extends JPanel {

	public InicioNoRegistrado() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JButton boton   = new JButton("Acceder");
		JButton boton2   = new JButton("Registrarse");
		
		layout.putConstraint(SpringLayout.NORTH, boton, 200, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, boton2, 20, SpringLayout.SOUTH, boton);
		
		this.add(boton);
		this.add(boton2);
		this.setPreferredSize(new Dimension(250,50));
	}
}
