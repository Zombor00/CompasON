package GUI.UsuarioSinCuenta;

//import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Login extends JPanel {

	public Login() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		//this.setBackground(Color.GRAY);
		JTextField usuario = new JTextField(20);
		JPasswordField contrasenia = new JPasswordField(20);
		JLabel usuarioLabel = new JLabel("Nombre de suario:");
		JLabel contraseniaLabel = new JLabel("Contrasenia:");
		JButton acceder   = new JButton("Acceder");

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, usuario, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, contrasenia, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, acceder, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, usuario, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, contrasenia,5,SpringLayout.SOUTH, usuario);
		layout.putConstraint(SpringLayout.NORTH, acceder,5,SpringLayout.SOUTH, contrasenia);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, usuarioLabel,0,SpringLayout.VERTICAL_CENTER, usuario);
		layout.putConstraint(SpringLayout.EAST, usuarioLabel,-75,SpringLayout.WEST, usuario);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, contraseniaLabel,0,SpringLayout.VERTICAL_CENTER, contrasenia);
		layout.putConstraint(SpringLayout.WEST, contraseniaLabel,0,SpringLayout.WEST, usuarioLabel);
		layout.putConstraint(SpringLayout.WEST, acceder,0,SpringLayout.WEST, usuario);
				
		this.add(usuario);
		this.add(contrasenia);
		this.add(acceder);
		this.add(usuarioLabel);
		this.add(contraseniaLabel);
		this.setPreferredSize(new Dimension(1000,150));
	}
}
