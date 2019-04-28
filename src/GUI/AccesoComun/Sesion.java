package GUI.AccesoComun;

import java.awt.Font;

import javax.swing.*;

import aplicacion.Aplicacion;

public class Sesion extends JPanel {
	
	private JLabel nombre = new JLabel("Nombre");

	public Sesion() {
		super();
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		nombre.setFont(new Font(nombre.getFont().getFontName(), nombre.getFont().getStyle(), 20));
		JButton cerrar = new JButton("Cerrar Sesión");
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
}
