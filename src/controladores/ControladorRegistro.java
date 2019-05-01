package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

import GUI.GuiAplicacion;
import GUI.PanelesUsuarios;
import GUI.UsuarioSinCuenta.InicioUsuarioSinCuenta;
import GUI.UsuarioSinCuenta.Registro;
import aplicacion.Aplicacion;
import excepciones.ExcepcionLoginBloqueado;
import excepciones.ExcepcionLoginErrorCredenciales;
import excepciones.ExcepcionNombreDeUsuarioNoDisponible;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;

public class ControladorRegistro implements ActionListener{
	private Aplicacion aplicacion;
	private Registro registro;
	private GuiAplicacion gui;
	
	public ControladorRegistro(Registro registro) {
		this.registro = registro;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		
		if (e.getActionCommand().equals("REGISTRARSE")) {
			try {
				aplicacion.aniadirUsuario(registro.getUsuario(), registro.getContrasenia(), registro.getNombre(), registro.getFecha());
				aplicacion.login(registro.getUsuario(), registro.getContrasenia());
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				e1.printStackTrace();
			} catch (ExcepcionNombreDeUsuarioNoDisponible e1) {
				e1.printStackTrace();
			} catch (ExcepcionLoginErrorCredenciales e1) {
				e1.printStackTrace();
			} catch (ExcepcionLoginBloqueado e1) {
				e1.printStackTrace();
			}
			
			if(aplicacion.getUsuarioLogeado() != null) {
				InicioUsuarioSinCuenta ini = gui.getPanelesUsuarios().getPanelUsuarioSinCuenta().getPestanias().getInicio();
				ini.getLogin().limpiarRegistro();
				ini.getRegistro().limpiarRegistro();
				gui.actualizarDatos();
				PanelesUsuarios panelesUsuarios = gui.getPanelesUsuarios();
				panelesUsuarios.cambiarPanel(PanelesUsuarios.REGISTRADO);
			}
		}
	}
}
