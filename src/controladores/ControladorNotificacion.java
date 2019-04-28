package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.GuiAplicacion;
import aplicacion.Aplicacion;
import gestion.Notificacion;

public class ControladorNotificacion implements ActionListener{

	private Aplicacion aplicacion;
	private Notificacion notificacion;
	private GuiAplicacion gui;
	
	public ControladorNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		
		if (e.getActionCommand().equals("ACCEDER NOTIFICACION")) {
			
		}
	}
}
