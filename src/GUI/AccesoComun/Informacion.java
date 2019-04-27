package GUI.AccesoComun;

import java.awt.Dimension;

import javax.swing.*;

public class Informacion extends JPanel{
	
	public Informacion() {
		
		super();
		BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(layout);
		
		Sesion sesion = new Sesion();
		Notificaciones notificaciones = new Notificaciones();
		
		this.add(sesion);
		this.add(notificaciones);
		this.setPreferredSize(new Dimension(200,800));
	}

}
