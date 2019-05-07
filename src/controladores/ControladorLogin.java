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

public class ControladorLogin implements ActionListener {

	private Aplicacion aplicacion;
	private Login vista;
	private GuiAplicacion gui;

	public ControladorLogin(Login vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null)
			aplicacion = Aplicacion.getInstance();
		if (gui == null)
			gui = GuiAplicacion.getInstance();

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
