package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

import org.junit.platform.commons.util.StringUtils;

import GUI.GuiAplicacion;
import GUI.PanelesUsuarios;
import GUI.UsuarioSinCuenta.InicioUsuarioSinCuenta;
import GUI.UsuarioSinCuenta.Registro;
import aplicacion.Aplicacion;
import excepciones.ExcepcionLoginBloqueado;
import excepciones.ExcepcionLoginErrorCredenciales;
import excepciones.ExcepcionNombreDeUsuarioNoDisponible;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;

/**
 * Esta clase tiene toda la informacion relevante al controlador
 * de registro: todas las acciones que puede haber en ese panel
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class ControladorRegistro implements ActionListener{
	/**
	 * Aplicacion con la informacion
	 */
	private Aplicacion aplicacion;
	/**
	 * Panel de registro donde suceden los eventos
	 */
	private Registro registro;
	/**
	 * Interfaz grafica de la aplicacion
	 */
	private GuiAplicacion gui;
	
	/**
	 * Constructor del controlador con registro
	 * @param registro panel donde actua el controlador
	 */
	public ControladorRegistro(Registro registro) {
		this.registro = registro;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui == null) gui = GuiAplicacion.getInstance();
		
		if(((registro.getUsuario() != null) && StringUtils.isBlank(registro.getUsuario())) || 
				(registro.getContrasenia()!=null && StringUtils.isBlank(registro.getContrasenia())) 
				|| (registro.getNombre() != null && StringUtils.isBlank(registro.getNombre()))) {
			GuiAplicacion.showMessage("Necesario rellenar todos los campos");
			return;
		}
		/* Se registra el usuario en la aplicacion con la informacion indicada */
		if (e.getActionCommand().equals("REGISTRARSE")) {
			try {
				aplicacion.aniadirUsuario(registro.getUsuario(), registro.getContrasenia(), registro.getNombre(), registro.getFecha());
				aplicacion.login(registro.getUsuario(), registro.getContrasenia());
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (ExcepcionParametrosDeEntradaIncorrectos e1) {
				GuiAplicacion.showMessage("Necesario rellenar todos los campos");
			} catch (ExcepcionNombreDeUsuarioNoDisponible e1) {
				GuiAplicacion.showMessage("Nombre de usuario no disponible");
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
