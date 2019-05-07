package GUI.UsuarioSinCuenta;


import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.*;

/**
 * Esta clase tiene toda la informacion relevante al panel para
 * registrarse en la aplicacion
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class Registro extends JPanel{
	/**
	 * Campo donde escribir el nombre de usuario
	 */
	private JTextField usuario;
	/**
	 * Campo donde escribir la contrasenia
	 */
	private JPasswordField contrasenia;
	/**
	 * Campo donde escribir el nombre de artista
	 */
	private JTextField nombre;
	/**
	 * Zona donde se escoge la fecha de nacimiento
	 */
	private JDateSelect fecha;
	/**
	 * Boton para registrarse
	 */
	private JButton aceptar;
	
	/**
	 * Constructor que crea el formulario para registrarse
	 *
	 */
	public Registro() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		//this.setBackground(Color.GRAY);
		usuario = new JTextField(20);
		contrasenia = new JPasswordField(20);
		nombre = new JTextField(20);
		fecha = new JDateSelect();
		JLabel usuarioLabel = new JLabel("Nombre de usuario:");
		JLabel contraseniaLabel = new JLabel("Contraseña:");
		JLabel nombreLabel = new JLabel("Nombre completo:");
		JLabel fechaLabel = new JLabel("Fecha de nacimiento:");
		aceptar   = new JButton("Aceptar");

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
	
	public String getUsuario() {
		return this.usuario.getText();
	}
	
	public String getContrasenia() {
		return String.valueOf(this.contrasenia.getPassword());
	}
	
	public String getNombre() {
		return this.nombre.getText();
	}
	
	/**
	 * Metodo que establece el controlador de las acciones de los botones
	 * 
	 * @param controlador de los botones de la pestania
	 */
	public void setControlador(ActionListener controlador) {
		aceptar.setActionCommand("REGISTRARSE");
		aceptar.addActionListener(controlador);
	}
	
	/**
	 * Metodo que limpia las zonas de escritura
	 */
	public void limpiarRegistro() {
		usuario.setText("");
		nombre.setText("");
		contrasenia.setText("");
	}
	
	public LocalDate getFecha() {
		return fecha.getDate();
	}
}
