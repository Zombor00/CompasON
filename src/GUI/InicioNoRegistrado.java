package GUI;

import javax.swing.*;

public class InicioNoRegistrado extends JPanel {

	public InicioNoRegistrado() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JTextField usuario = new JTextField(10);
		JTextField contrasenia = new JTextField(10);
		JButton acceder   = new JButton("Acceder");
		JButton registrarse   = new JButton("Registrarse");
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, usuario, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, contrasenia, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, acceder, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, registrarse, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, usuario, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.NORTH, contrasenia,5,SpringLayout.SOUTH, usuario);
		layout.putConstraint(SpringLayout.NORTH, acceder,5,SpringLayout.SOUTH, contrasenia);
		layout.putConstraint(SpringLayout.NORTH, registrarse,5,SpringLayout.SOUTH, acceder);
		
		this.add(usuario);
		this.add(contrasenia);
		this.add(acceder);
		this.add(registrarse);
	}
}
