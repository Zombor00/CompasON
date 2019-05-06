package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import GUI.GuiAplicacion;
import GUI.PanelesUsuarios;
import aplicacion.Aplicacion;
import pads.musicPlayer.exceptions.Mp3PlayerException;

public class ControladorSesion implements ActionListener {

	private Aplicacion aplicacion;
	private GuiAplicacion gui;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null)
			aplicacion = Aplicacion.getInstance();
		
		if (gui == null)
			gui = GuiAplicacion.getInstance();

		if (e.getActionCommand().equals("Cerrar_Sesion")) {
			gui.actualizarDatos();
			try {
				aplicacion.logout();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (Mp3PlayerException e1) {
				e1.printStackTrace();
			}

			try {
				aplicacion.guardarDatos();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			gui.getReproductor().changeIcon(true);
			PanelesUsuarios panelesUsuarios = gui.getPanelesUsuarios();
			gui.seleccionarInicio(panelesUsuarios.getActual());
			panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getInicio().accionIniciarSesion();
			panelesUsuarios.cambiarPanel(PanelesUsuarios.SIN_CUENTA);
		}
	}
}
