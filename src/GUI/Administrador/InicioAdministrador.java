package GUI.Administrador;

import java.awt.Image;

import javax.swing.*;

public class InicioAdministrador extends JPanel {

	public InicioAdministrador() {

		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		JLabel logo = new JLabel();
		JLabel bienvenido = new JLabel("Bienvenido Admin!");
		JLabel validaciones = new JLabel("Canciones por validar: _");
		JLabel denuncias = new JLabel("Denuncias a tramitar: _");
		logo.setIcon(new ImageIcon(new ImageIcon("aux/logo-compason.png").getImage().getScaledInstance(200, 75, Image.SCALE_DEFAULT)));
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bienvenido, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logo, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, denuncias, 0, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, validaciones, 0, SpringLayout.WEST, denuncias);
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, bienvenido, 0, SpringLayout.VERTICAL_CENTER, this);
		layout.putConstraint(SpringLayout.SOUTH, logo, -100, SpringLayout.NORTH, bienvenido);
		layout.putConstraint(SpringLayout.NORTH, validaciones, 10, SpringLayout.SOUTH, bienvenido);
		layout.putConstraint(SpringLayout.NORTH, denuncias, 5, SpringLayout.SOUTH, validaciones);
		
		this.add(logo);
		this.add(bienvenido);
		this.add(validaciones);
		this.add(denuncias);
		
	}
}