package GUI;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import GUI.AccesoComun.Sesion;
import GUI.AccesoComun.Busqueda;
import GUI.AccesoComun.Notificaciones;
import GUI.UsuarioSinCuenta.Login;
import GUI.UsuarioSinCuenta.Registro;
import aplicacion.Aplicacion;
import controladores.ControladorBuscar;
import controladores.ControladorLogin;
import controladores.ControladorLogout;
import controladores.ControladorNotificacion;
import controladores.ControladorRegistro;
import controladores.ControladorReproducirCancion;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import media.Buscable;
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
		
		Registro registro = panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getInicio().getRegistro();
		registro.setControlador(new ControladorRegistro(registro));
		
		Login login = panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getInicio().getLogin();
		login.setControlador(new ControladorLogin(login));
		
		Sesion sesion1 = panelesUsuarios.getPanelUsuarioRegstrado().getInformacion().getSesion();
		sesion1.setControlador(new ControladorLogout());
		Sesion sesion2 = panelesUsuarios.getPanelAdministrador().getInformacionAdministrador().getSesion();
		sesion2.setControlador(new ControladorLogout());
		Sesion sesion3 = panelesUsuarios.getPanelUsuarioPremium().getInformacion().getSesion();
		sesion3.setControlador(new ControladorLogout());
		
		Busqueda busqueda1 = panelesUsuarios.getPanelUsuarioRegstrado().getPestanias().getBusqueda();
		busqueda1.setControlador(new ControladorBuscar(busqueda1), new ControladorReproducirCancion(busqueda1));
		Busqueda busqueda2 = panelesUsuarios.getPanelAdministrador().getPestaniasAdministrador().getBusqueda();
		busqueda2.setControlador(new ControladorBuscar(busqueda2), new ControladorReproducirCancion(busqueda2));
		Busqueda busqueda3 = panelesUsuarios.getPanelUsuarioPremium().getPestaniasUsuarioPremium().getBusqueda();
		busqueda3.setControlador(new ControladorBuscar(busqueda3), new ControladorReproducirCancion(busqueda3));
		Busqueda busqueda4 = panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getBusqueda();
		busqueda4.setControlador(new ControladorBuscar(busqueda4), new ControladorReproducirCancion(busqueda4));
		
		
		
		Notificaciones notificacionesRegistrado = panelesUsuarios.getPanelUsuarioRegstrado().getInformacion().getNotificaciones();
		notificacionesRegistrado.setControlador(new ControladorNotificacion(notificacionesRegistrado));
		Notificaciones notificacionesPremium = panelesUsuarios.getPanelUsuarioPremium().getInformacion().getNotificaciones();
		notificacionesPremium.setControlador(new ControladorNotificacion(notificacionesPremium));
		
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
	
	public void actualizarBusqueda(ArrayList<Buscable> buscables, String actual) {
		switch(actual) {
			case PanelesUsuarios.SIN_CUENTA:
				this.panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getBusqueda().actualizarBusqueda(buscables);
				break;
			case PanelesUsuarios.ADMINISTRADOR:
				this.panelesUsuarios.getPanelAdministrador().getPestaniasAdministrador().getBusqueda().actualizarBusqueda(buscables);
				break;
			case PanelesUsuarios.REGISTRADO:
				this.panelesUsuarios.getPanelUsuarioRegstrado().getPestanias().getBusqueda().actualizarBusqueda(buscables);
				break;
			case PanelesUsuarios.PREMIUM:
				this.panelesUsuarios.getPanelUsuarioPremium().getPestaniasUsuarioPremium().getBusqueda().actualizarBusqueda(buscables);
				break;
		}
	}
	
	public void limpiarBusqueda(String actual) {
		switch(actual) {
			case PanelesUsuarios.SIN_CUENTA:
				this.panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getBusqueda().limpiarRegistros();
				break;
			case PanelesUsuarios.ADMINISTRADOR:
				this.panelesUsuarios.getPanelAdministrador().getPestaniasAdministrador().getBusqueda().limpiarRegistros();
				break;
			case PanelesUsuarios.REGISTRADO:
				this.panelesUsuarios.getPanelUsuarioRegstrado().getPestanias().getBusqueda().limpiarRegistros();
				break;
			case PanelesUsuarios.PREMIUM:
				this.panelesUsuarios.getPanelUsuarioPremium().getPestaniasUsuarioPremium().getBusqueda().limpiarRegistros();
				break;
		}
	}
	
	public static void main(String[] args) throws Mp3PlayerException, ExcepcionParametrosDeEntradaIncorrectos, ClassNotFoundException, IOException {
		Aplicacion a = Aplicacion.cargarDatos();
		GuiAplicacion g = GuiAplicacion.getInstance();
		System.out.println("Ejecutando main de Gui.Aplicaion. "+ a + " " + g);
	}
}
