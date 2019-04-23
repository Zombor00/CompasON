package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GuiAplicacion;
import GUI.PanelesUsuarios;
import GUI.UsuarioSinCuenta.Login;
import aplicacion.Aplicacion;

public class ControladorLogin implements ActionListener {
	
	private Aplicacion aplicacion;
	private Login vista;
	private GuiAplicacion gui;
	
	public ControladorLogin (Login vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (aplicacion == null) aplicacion = Aplicacion.getInstance();
		if (gui ==null) gui = GuiAplicacion.getInstance();
		
		/*Hay que comprobar que el usuario y la contrasenia son correctos */
		if (e.getActionCommand().equals("ACCEDER")) {
			PanelesUsuarios panelesUsuarios = gui.getPanelesUsuarios();
			panelesUsuarios.cambiarPanel(PanelesUsuarios.REGISTRADO);
			
			/* gui.actualizarInformacion()
			 * aplicacion.login(vista.getUsuaurio(),vista.getConstrasenia())
			 */
		}
		
	}

}
