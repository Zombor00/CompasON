package GUI;

import java.awt.Image;

import javax.swing.*;

public class Inicio extends JPanel {

	public Inicio() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JTextField usuario = new JTextField(10);
		JPasswordField contrasenia = new JPasswordField(10);
		JButton acceder   = new JButton("Acceder");
		JButton registrarse   = new JButton("Registrarse");
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(new ImageIcon("aux/logo-compason.png").getImage().getScaledInstance(200, 75, Image.SCALE_DEFAULT)));
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, usuario, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, contrasenia, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, acceder, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, registrarse, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logo, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, usuario, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, logo, -100, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, contrasenia,5,SpringLayout.SOUTH, usuario);
		layout.putConstraint(SpringLayout.NORTH, acceder,5,SpringLayout.SOUTH, contrasenia);
		layout.putConstraint(SpringLayout.NORTH, registrarse,5,SpringLayout.SOUTH, acceder);
		
		this.add(usuario);
		this.add(contrasenia);
		this.add(acceder);
		this.add(registrarse);
		this.add(logo);
	}
}
