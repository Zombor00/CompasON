package GUI;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import GUI.AccesoComun.Sesion;
import GUI.UsuarioSinCuenta.Login;
import aplicacion.Aplicacion;
import controladores.ControladorLogin;
import controladores.ControladorLogout;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import pads.musicPlayer.exceptions.Mp3PlayerException;


public class GuiAplicacion extends JFrame {
	
	public static GuiAplicacion INSTANCE = null;
	private PanelesUsuarios panelesUsuarios = new PanelesUsuarios();
	private Reproductor reproductor = new Reproductor();

	private GuiAplicacion() {
		super("CompasON");
		this.setIconImage(new ImageIcon("aux/icono-compason.png").getImage());
		Container contenedor = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		contenedor.setLayout(layout);
		
		contenedor.add(panelesUsuarios,BorderLayout.CENTER);
		contenedor.add(reproductor,BorderLayout.SOUTH);
		
		Login login = panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getInicio().getLogin();
		login.setControlador(new ControladorLogin(login));
		
		Sesion sesion1 = panelesUsuarios.getPanelUsuarioRegstrado().getInformacion().getSesion();
		sesion1.setControlador(new ControladorLogout());
		Sesion sesion2 = panelesUsuarios.getPanelAdministrador().getInformacionAdministrador().getSesion();
		sesion2.setControlador(new ControladorLogout());
		Sesion sesion3 = panelesUsuarios.getPanelUsuarioPremium().getInformacion().getSesion();
		sesion3.setControlador(new ControladorLogout());
		
        this.setSize(this.getToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	

	}
	
	public static GuiAplicacion getInstance() {
		if (INSTANCE == null) INSTANCE = new GuiAplicacion();
		return INSTANCE;
	}
	
	public PanelesUsuarios getPanelesUsuarios() {
		return this.panelesUsuarios;
	}
	
	public Reproductor getReproductor() {
		return this.reproductor;
	}
	
	public void actualizarDatos() {
		/* TODO Ojo con el login del admin */
		this.panelesUsuarios.actualizarDatos();
	}
	
	public static void main(String[] args) throws Mp3PlayerException, ExcepcionParametrosDeEntradaIncorrectos, ClassNotFoundException, IOException {
		Aplicacion a = Aplicacion.cargarDatos();
		GuiAplicacion g = GuiAplicacion.getInstance();
		System.out.println("Ejecutando main de Gui.Aplicaion. "+ a + " " + g);
	}
}
