package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import GUI.GuiAplicacion;
import GUI.PanelesUsuarios;
import aplicacion.Aplicacion;
import pads.musicPlayer.exceptions.Mp3PlayerException;

public class ControladorLogout implements ActionListener{
	
	private Aplicacion aplicacion;
	private GuiAplicacion gui;	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		
		if (e.getActionCommand().equals("Cerrar Sesion")) {
			try {
				aplicacion.logout();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (Mp3PlayerException e1) {
				e1.printStackTrace();
			}
			
			if(aplicacion.getUsuarioLogeado() == null) {
				gui.actualizarDatos();
				PanelesUsuarios panelesUsuarios = gui.getPanelesUsuarios();
				panelesUsuarios.cambiarPanel(PanelesUsuarios.SIN_CUENTA);
			}
		}
	}
}
