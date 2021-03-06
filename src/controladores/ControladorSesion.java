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
import pads.musicPlayer.exceptions.Mp3PlayerException;
import usuarios.UsuarioRegistrado;

/**
 * Esta clase tiene toda la informacion relevante al controlador
 * de sesion: todas las acciones que puede haber en ese panel
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class ControladorSesion implements ActionListener {

	/**
	 * Aplicacion con la informacion
	 */
	private Aplicacion aplicacion;
	/**
	 * Panel de sesion donde suceden los eventos
	 */
	private Sesion vista;
	/**
	 * Interfaz grafica de la aplicacion
	 */
	private GuiAplicacion gui;
	
	/**
	 * Constructor del controlador con sesion
	 * @param sesion panel donde actua el controlador
	 */
	public ControladorSesion(Sesion sesion) {
		this.vista = sesion;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null)
			aplicacion = Aplicacion.getInstance();
		
		if (gui == null)
			gui = GuiAplicacion.getInstance();
		
		/* Si se cierra sesion, se hace el logout y se restablece lo necesario */
		if (e.getActionCommand().equals("Cerrar_Sesion")) {
			gui.actualizarDatos();
			try {
				if (aplicacion.getUsuarioLogeado() != null) {
					int reproducidas = aplicacion.getCola().getSongsPlayed();
					aplicacion.getUsuarioLogeado().setReproducidas(aplicacion.getUsuarioLogeado().getReproducidas() + reproducidas);
				}
				aplicacion.getCola().resetSongsPlayed();
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
			if (panelesUsuarios.getActual() == PanelesUsuarios.REGISTRADO) {
				panelesUsuarios.getPanelUsuarioRegstrado().getPestanias().getInicio().noPagar();
				panelesUsuarios.getPanelUsuarioRegstrado().getPestanias().getBusqueda().comentarioDenunciaVisible(false);
			}
			else if (panelesUsuarios.getActual() == PanelesUsuarios.PREMIUM) {
				panelesUsuarios.getPanelUsuarioPremium().getPestanias().getBusqueda().comentarioDenunciaVisible(false);
			}
			gui.seleccionarInicio(panelesUsuarios.getActual());
			panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getInicio().accionIniciarSesion();
			panelesUsuarios.cambiarPanel(PanelesUsuarios.SIN_CUENTA);
			
		/* Si se pulsa dejar de seguir, se deja de seguir el usuario seleccionado */
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
