package GUI.UsuarioSinCuenta;

//import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Registro extends JPanel{
	
	public Registro() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		//this.setBackground(Color.GRAY);
		JTextField usuario = new JTextField(20);
		JPasswordField contrasenia = new JPasswordField(20);
		JTextField nombre = new JTextField(20);
		JDateSelect fecha = new JDateSelect();
		JLabel usuarioLabel = new JLabel("Nombre de suario:");
		JLabel contraseniaLabel = new JLabel("Contrasenia:");
		JLabel nombreLabel = new JLabel("Nombre completo:");
		JLabel fechaLabel = new JLabel("Fecha de nacimiento:");
		JButton aceptar   = new JButton("Aceptar");

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, usuario, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, contrasenia, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nombre, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, fecha, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, aceptar, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, usuario, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, contrasenia,5,SpringLayout.SOUTH, usuario);
		layout.putConstraint(SpringLayout.NORTH, nombre,5,SpringLayout.SOUTH, contrasenia);
		layout.putConstraint(SpringLayout.NORTH, fecha,5,SpringLayout.SOUTH, nombre);
		layout.putConstraint(SpringLayout.NORTH, aceptar,5,SpringLayout.SOUTH, fecha);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, usuarioLabel,0,SpringLayout.VERTICAL_CENTER, usuario);
		layout.putConstraint(SpringLayout.EAST, usuarioLabel,-75,SpringLayout.WEST, usuario);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, contraseniaLabel,0,SpringLayout.VERTICAL_CENTER, contrasenia);
		layout.putConstraint(SpringLayout.WEST, contraseniaLabel,0,SpringLayout.WEST, usuarioLabel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, nombreLabel,0,SpringLayout.VERTICAL_CENTER, nombre);
		layout.putConstraint(SpringLayout.WEST, nombreLabel,0,SpringLayout.WEST, usuarioLabel);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, fechaLabel,0,SpringLayout.VERTICAL_CENTER, fecha);
		layout.putConstraint(SpringLayout.WEST, fechaLabel,0,SpringLayout.WEST, usuarioLabel);
		layout.putConstraint(SpringLayout.WEST, aceptar,0,SpringLayout.WEST, usuario);
		
		
		this.add(usuario);
		this.add(contrasenia);
		this.add(nombre);
		this.add(fecha);
		this.add(usuarioLabel);
		this.add(contraseniaLabel);
		this.add(nombreLabel);
		this.add(fechaLabel);
		this.add(aceptar);
		this.setPreferredSize(new Dimension(1000,300));
		
	}
}
