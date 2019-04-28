package GUI.Administrador;


import java.awt.Dimension;

import javax.swing.*;

import GUI.AccesoComun.Sesion;

public class InformacionAdministrador extends JPanel {

	public InformacionAdministrador() {
		super();
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		
		Sesion sesion = new Sesion();
		Opciones opciones = new Opciones();
		
		this.add(sesion);
		this.add(opciones);
		this.setPreferredSize(new Dimension(300,800));
	}
}
