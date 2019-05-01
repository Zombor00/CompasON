package GUI.Administrador;


import java.awt.Dimension;

import javax.swing.*;

import GUI.AccesoComun.Sesion;

public class InformacionAdministrador extends JPanel {
	private Sesion sesion;
	private Opciones opciones;
	
	public InformacionAdministrador() {
		super();
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
		
		sesion = new Sesion();
		opciones = new Opciones();
		
		this.add(sesion);
		this.add(opciones);
		this.setPreferredSize(new Dimension(300,800));
	}
	
	public Sesion getSesion() {
		return this.sesion;
	}
	
	public Opciones getOpciones() {
		return this.opciones;
	}

	public void actualizarDatos() {
		this.opciones.actualizarDatos();		
	}

}
