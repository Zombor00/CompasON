package GUI.AccesoComun;

import java.awt.FlowLayout;

import javax.swing.*;

public class Informacion extends JPanel{
	
	public Informacion() {
		
		super();
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		Sesion sesion = new Sesion();
		Notificaciones notificaciones = new Notificaciones();
		
		this.add(sesion);
		this.add(notificaciones);
	}

}
