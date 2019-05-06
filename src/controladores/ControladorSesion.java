package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JTable;

import GUI.GuiAplicacion;
import GUI.PanelesUsuarios;
import GUI.AccesoComun.Sesion;
import aplicacion.Aplicacion;
import excepciones.ExcepcionUsuarioNoSeguido;
import media.Cancion;
import pads.musicPlayer.exceptions.Mp3PlayerException;
import usuarios.UsuarioRegistrado;

public class ControladorSesion implements ActionListener {

	private Aplicacion aplicacion;
	private Sesion vista;
	private GuiAplicacion gui;
	
	public ControladorSesion(Sesion sesion) {
		this.vista = sesion;
	}

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
			if(panelesUsuarios.getActual() == PanelesUsuarios.REGISTRADO) {
				panelesUsuarios.getPanelUsuarioRegstrado().getPestanias().getInicio().noPagar();
			}
			gui.seleccionarInicio(panelesUsuarios.getActual());
			panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getInicio().accionIniciarSesion();
			panelesUsuarios.cambiarPanel(PanelesUsuarios.SIN_CUENTA);
			
		} else if (e.getActionCommand().equals("Dejar_Seguir")) {
			JTable tabla = vista.getTablaSeguidos();
			int fila = tabla.getSelectedRow();
			if (fila == -1) {
				return;
			}

			UsuarioRegistrado u = (UsuarioRegistrado) tabla.getValueAt(fila, 0);
			if (u == null) {
				System.out.println("EE" + fila);
				return;
			}
			try {
				aplicacion.getUsuarioLogeado().dejarSeguirUsuario(u);
				gui.actualizarDatos();
			} catch (ExcepcionUsuarioNoSeguido e1) {
				e1.printStackTrace();
			}
			
		}
	}
}
