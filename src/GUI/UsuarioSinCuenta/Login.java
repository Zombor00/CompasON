package GUI.UsuarioSinCuenta;


import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Esta clase tiene toda la informacion relevante al panel para
 * hacer login
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Login extends JPanel {
	
	/**
	 * Campo donde escribir el nombre de usuario
	 */
	private JTextField usuario;
	/**
	 * Campo donde escribir la contrasenia
	 */
	private JPasswordField contrasenia;
	/**
	 * Boton para hacer login
	 */
	private JButton acceder;
	
	/**
	 * Constructor que crea el formulario para hacer login
	 *
	 */
	public Login() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		//this.setBackground(Color.GRAY);
		this.usuario = new JTextField(20);
		this.contrasenia = new JPasswordField(20);
		JLabel usuarioLabel = new JLabel("Nombre de usuario:");
		JLabel contraseniaLabel = new JLabel("Contraseña:");
		this.acceder = new JButton("Acceder");

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
	
	public String getUsuario() {
		return this.usuario.getText();
	}
	
	public String getContrasenia() {
		return String.valueOf(this.contrasenia.getPassword());
	}
	
	/**
	 * Metodo que establece el controlador de las acciones de los botones
	 * 
	 * @param controlador de los botones de la pestania
	 */
	public void setControlador(ActionListener controlador) {
		acceder.setActionCommand("ACCEDER");
		acceder.addActionListener(controlador);
	}
	
	/**
	 * Metodo que limpia las zonas de escritura
	 */
	public void limpiarRegistro() {
		usuario.setText("");
		contrasenia.setText("");
	}
}
