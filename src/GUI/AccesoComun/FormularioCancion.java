package GUI.AccesoComun;

import java.awt.Dimension;

import javax.swing.*;

public class FormularioCancion extends JPanel{
	
	public FormularioCancion() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JTextField nombre = new JTextField(20);
		JTextField fichero = new JTextField(20);
		JLabel nombreLabel = new JLabel("Nombre de la cancion:");
		JLabel ficheroLabel = new JLabel("Fichero de audio:");
		JButton aceptar   = new JButton("Aceptar");

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombre, 0, SpringLayout.VERTICAL_CENTER, this);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fichero, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, fichero, 5, SpringLayout.SOUTH, nombre);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombreLabel,0,SpringLayout.VERTICAL_CENTER, nombre);
		layout.putConstraint(SpringLayout.EAST, nombreLabel,-75,SpringLayout.WEST, nombre);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, ficheroLabel,0,SpringLayout.VERTICAL_CENTER, fichero);
		layout.putConstraint(SpringLayout.WEST, ficheroLabel,0,SpringLayout.WEST, nombreLabel);

		layout.putConstraint(SpringLayout.NORTH, aceptar,5,SpringLayout.SOUTH, fichero);
		layout.putConstraint(SpringLayout.EAST, aceptar,0,SpringLayout.EAST, nombre);
		
		

		this.add(nombre);
		this.add(nombreLabel);
		this.add(fichero);
		this.add(ficheroLabel);
		this.add(aceptar);
		this.setPreferredSize(new Dimension(1000,300));
	}

}
