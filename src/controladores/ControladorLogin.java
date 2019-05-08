package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.stream.Collectors;

import GUI.GuiAplicacion;
import GUI.PanelesUsuarios;
import GUI.UsuarioSinCuenta.Login;
import aplicacion.Aplicacion;
import excepciones.ExcepcionLoginBloqueado;
import excepciones.ExcepcionLoginErrorCredenciales;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;

/**
 * Esta clase tiene toda la informacion relevante al controlador
 * del login: todas las acciones que puede haber en ese panel
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class ControladorLogin implements ActionListener {

	/**
	 * Aplicacion con la informacion
	 */
	private Aplicacion aplicacion;
	/**
	 * Panel de login donde suceden los eventos
	 */
	private Login vista;
	/**
	 * Interfaz grafica de la aplicacion
	 */
	private GuiAplicacion gui;
	
	/**
	 * Constructor del controlador con login
	 * @param vista panel donde actua el controlador
	 */
	public ControladorLogin(Login vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null)
			aplicacion = Aplicacion.getInstance();
		if (gui == null)
			gui = GuiAplicacion.getInstance();
		
		/* Si se pulsa acceder se hace login y si se hace correctamente se muestra el panel correspondiente
		 * al tipo de usuario que acaba de iniciar sesion en la aplicacion
		 */
		if (e.getActionCommand().equals("ACCEDER")) {
			try {
				aplicacion.login(vista.getUsuario(), vista.getContrasenia());
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (ExcepcionLoginErrorCredenciales e1) {
				GuiAplicacion.showMessage("Nombre de usuario o contraseña incorrectos");
			} catch (ExcepcionLoginBloqueado e1) {
				LocalDate bloqueado = aplicacion.getUsuarios().stream().filter(u -> u.getNombreUsuario().equals(vista.getUsuario()))
						.collect(Collectors.toList()).get(0).getBloqueadoHasta();
				GuiAplicacion.showMessage((bloqueado == LocalDate.MAX) ? "Usuario bloqueado para siempre" : "Usuario bloqueado hasta " + bloqueado);
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			}

			if (aplicacion.getAdministradorLogeado()) {
				vista.limpiarRegistro();
				gui.actualizarDatos();
				PanelesUsuarios panelesUsuarios = gui.getPanelesUsuarios();
				panelesUsuarios.cambiarPanel(PanelesUsuarios.ADMINISTRADOR);
			}

			if (aplicacion.getUsuarioLogeado() != null) {
				vista.limpiarRegistro();
				gui.actualizarDatos();
				PanelesUsuarios panelesUsuarios = gui.getPanelesUsuarios();
				if (aplicacion.getUsuarioLogeado().esPremium()) {
					panelesUsuarios.cambiarPanel(PanelesUsuarios.PREMIUM);
				} else {
					panelesUsuarios.cambiarPanel(PanelesUsuarios.REGISTRADO);
				}
			}
		}
	}

}
