package GUI.AccesoComun;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

import aplicacion.Aplicacion;

public class Sesion extends JPanel {
	
	private JLabel nombre;
	private JButton cerrar;

	public Sesion() {
		super();
		nombre = new JLabel("Nombre");
		cerrar = new JButton("Cerrar Sesión");
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		nombre.setFont(new Font(nombre.getFont().getFontName(), nombre.getFont().getStyle(), 20));
		
		layout.putConstraint(SpringLayout.NORTH, nombre, 25, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, cerrar, 25, SpringLayout.NORTH, nombre);
		
		layout.putConstraint(SpringLayout.WEST, nombre, 25, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, cerrar, 25, SpringLayout.WEST, this);
		
		this.add(nombre);
		this.add(cerrar);
	}
	
	public void actualizarDatos() {
		if (Aplicacion.getInstance().getUsuarioLogeado() != null)
			nombre.setText(Aplicacion.getInstance().getUsuarioLogeado().getNombre());
	}
	
	public void setControlador(ActionListener controlador) {
		cerrar.setActionCommand("Cerrar Sesion");
		cerrar.addActionListener(controlador);
	}
}
