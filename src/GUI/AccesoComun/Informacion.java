package GUI.AccesoComun;

import java.awt.Dimension;

import javax.swing.*;

public class Informacion extends JPanel{
	
	private Sesion sesion = new Sesion();
	private Notificaciones notificaciones = new Notificaciones();
	
	public Informacion() {
		
		super();
		BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(layout);
		
		this.add(sesion);
		this.add(notificaciones);
		this.setPreferredSize(new Dimension(300,800));
	}
	
	Sesion getSesion() {
		return this.sesion;
	}
	
	Notificaciones getNotificaciones() {
		return this.notificaciones;
	}
	
	public void actualizarDatos() {
		this.sesion.actualizarDatos();
		this.notificaciones.actualizarDatos();
	}

}
