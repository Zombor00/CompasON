package GUI;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import GUI.AccesoComun.Sesion;
import GUI.Administrador.Denuncias;
import GUI.Administrador.Opciones;
import GUI.Administrador.PestaniasAdministrador;
import GUI.Administrador.Validar;
import GUI.UsuarioPremium.MisListas;
import GUI.UsuarioPremium.PestaniasUsuarioPremium;
import GUI.UsuarioRegistrado.Pago;
import GUI.UsuarioRegistrado.PestaniasUsuarioRegistrado;
import GUI.AccesoComun.Busqueda;
import GUI.AccesoComun.MisCanciones;
import GUI.AccesoComun.Notificaciones;
import GUI.UsuarioSinCuenta.Login;
import GUI.UsuarioSinCuenta.PestaniasUsuarioSinCuenta;
import GUI.UsuarioSinCuenta.Registro;
import aplicacion.Aplicacion;
import controladores.ControladorBusqueda;
import controladores.ControladorDenuncias;
import controladores.ControladorLogin;
import controladores.ControladorSesion;
import controladores.ControladorValidar;
import controladores.ControladorMisCanciones;
import controladores.ControladorMisListas;
import controladores.ControladorNotificacion;
import controladores.ControladorOpciones;
import controladores.ControladorPago;
import controladores.ControladorRegistro;
import controladores.ControladorReproductor;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import media.Buscable;
import pads.musicPlayer.exceptions.Mp3PlayerException;


public class GuiAplicacion extends JFrame {

	public static GuiAplicacion INSTANCE = null;
	private PanelesUsuarios panelesUsuarios = new PanelesUsuarios();
	private Reproductor reproductor = new Reproductor();
	public static ImageIcon IconoCompasON = new ImageIcon("aux/icono-compason.png");
	public static ImageIcon botonPausa = new ImageIcon("aux/icono-compason.png");

	private GuiAplicacion() {
		super("CompasON");
		this.setIconImage(IconoCompasON.getImage());
		Container contenedor = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		contenedor.setLayout(layout);

		contenedor.add(panelesUsuarios,BorderLayout.CENTER);
		contenedor.add(reproductor,BorderLayout.SOUTH);

		Registro registro = panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getInicio().getRegistro();
		registro.setControlador(new ControladorRegistro(registro));
		
		reproductor.setControlador(new ControladorReproductor());

		Login login = panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getInicio().getLogin();
		login.setControlador(new ControladorLogin(login));

		Sesion sesion1 = panelesUsuarios.getPanelUsuarioRegstrado().getInformacion().getSesion();
		sesion1.setControlador(new ControladorSesion());
		Sesion sesion2 = panelesUsuarios.getPanelAdministrador().getInformacionAdministrador().getSesion();
		sesion2.setControlador(new ControladorSesion());
		Sesion sesion3 = panelesUsuarios.getPanelUsuarioPremium().getInformacion().getSesion();
		sesion3.setControlador(new ControladorSesion());

		Busqueda busqueda1 = panelesUsuarios.getPanelUsuarioRegstrado().getPestanias().getBusqueda();
		busqueda1.setControlador(new ControladorBusqueda(busqueda1));
		Busqueda busqueda2 = panelesUsuarios.getPanelAdministrador().getPestaniasAdministrador().getBusqueda();
		busqueda2.setControlador(new ControladorBusqueda(busqueda2));
		Busqueda busqueda3 = panelesUsuarios.getPanelUsuarioPremium().getPestanias().getBusqueda();
		busqueda3.setControlador(new ControladorBusqueda(busqueda3));
		Busqueda busqueda4 = panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getBusqueda();
		busqueda4.setControlador(new ControladorBusqueda(busqueda4));

		MisCanciones misCanciones1 = panelesUsuarios.getPanelUsuarioRegstrado().getPestanias().getMisCanciones();
		misCanciones1.setControlador(new ControladorMisCanciones(misCanciones1));
		MisCanciones misCanciones2 = panelesUsuarios.getPanelUsuarioPremium().getPestanias().getMisCanciones();
		misCanciones2.setControlador(new ControladorMisCanciones(misCanciones2));



		Notificaciones notificacionesRegistrado = panelesUsuarios.getPanelUsuarioRegstrado().getInformacion().getNotificaciones();
		notificacionesRegistrado.setControlador(new ControladorNotificacion(notificacionesRegistrado));
		Notificaciones notificacionesPremium = panelesUsuarios.getPanelUsuarioPremium().getInformacion().getNotificaciones();
		notificacionesPremium.setControlador(new ControladorNotificacion(notificacionesPremium));

		MisListas misListas = panelesUsuarios.getPanelUsuarioPremium().getPestanias().getMisListas();
		misListas.setControlador(new ControladorMisListas(misListas));

		Opciones opciones = panelesUsuarios.getPanelAdministrador().getInformacionAdministrador().getOpciones();
		opciones.setControlador(new ControladorOpciones(opciones));

		Validar validar = panelesUsuarios.getPanelAdministrador().getPestaniasAdministrador().getValidar();
		validar.setControlador(new ControladorValidar(validar));

		Denuncias denuncias = panelesUsuarios.getPanelAdministrador().getPestaniasAdministrador().getDenuncias();
		denuncias.setControlador(new ControladorDenuncias(denuncias));
		
		Pago pago = panelesUsuarios.getPanelUsuarioRegstrado().getPestanias().getInicio().getPago();
		pago.setControlador(new ControladorPago(pago));

        this.setSize(this.getToolkit().getScreenSize());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.actualizarDatos();		

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
		this.panelesUsuarios.actualizarDatos();
	}

	public void actualizarBusqueda(ArrayList<Buscable> buscables, String actual) {
		switch(actual) {
			case PanelesUsuarios.SIN_CUENTA:
				PestaniasUsuarioSinCuenta pSc = this.panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias();
				pSc.getBusqueda().actualizarBusqueda(buscables);
				pSc.setSelectedIndex(1);
				break;
			case PanelesUsuarios.REGISTRADO:
				PestaniasUsuarioRegistrado pRe = this.panelesUsuarios.getPanelUsuarioRegstrado().getPestanias();
				pRe.getBusqueda().actualizarBusqueda(buscables);
				pRe.setSelectedIndex(1);
				break;
			case PanelesUsuarios.PREMIUM:
				PestaniasUsuarioPremium pPr = this.panelesUsuarios.getPanelUsuarioPremium().getPestanias();
				pPr.getBusqueda().actualizarBusqueda(buscables);
				pPr.setSelectedIndex(1);
				break;
			case PanelesUsuarios.ADMINISTRADOR:
				PestaniasAdministrador pAd = this.panelesUsuarios.getPanelAdministrador().getPestaniasAdministrador();
				pAd.getBusqueda().actualizarBusqueda(buscables);
				pAd.setSelectedIndex(1);
				break;
		}
	}
	
	public void seleccionarInicio(String actual) {
		switch(actual) {
			case PanelesUsuarios.SIN_CUENTA:
				this.panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().setSelectedIndex(0);
				break;
			case PanelesUsuarios.REGISTRADO:
				this.panelesUsuarios.getPanelUsuarioRegstrado().getPestanias().setSelectedIndex(0);;
				break;
			case PanelesUsuarios.PREMIUM:
				this.panelesUsuarios.getPanelUsuarioPremium().getPestanias().setSelectedIndex(0);
				break;
			case PanelesUsuarios.ADMINISTRADOR:
				this.panelesUsuarios.getPanelAdministrador().getPestaniasAdministrador().setSelectedIndex(0);
				break;
		}
	}
	
	public static void actualizarLookAndFeel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		Color verde = new Color(0,130,30);
		Color negro = Color.BLACK;
		Color gris = Color.DARK_GRAY;
		Color blanco = Color.WHITE;
		Color grisClaro = new Color(250,250,250);
		UIManager.put("control", negro); //Fonfo
		UIManager.put("text", blanco); //Texto
		UIManager.put("nimbusBase", gris); //Base 1
		UIManager.put("nimbusBlueGrey", verde); //Base 2
		UIManager.put("nimbusSelection", verde); //Botones de menus
		UIManager.put("textBackground", blanco); //Fondo texto seleccionado
		UIManager.put("nimbusSelectedText", negro); //Texto seleccionado
		UIManager.put("nimbusSelectionBackground", grisClaro); //Seleccion en tablas
		UIManager.put("nimbusFocus", verde); //Foco
		UIManager.put("nimbusLightBackground", gris); //Fondo normal de textos
		UIManager.put("nimbusBorder", negro); //Bordes
		
		//UIManager.getLookAndFeelDefaults().put("OptionPane.background", Color.red);

	}
	
	public static void showMessage(String message) {
		new PopupMessage(message);
	}

	public static void main(String[] args) throws Mp3PlayerException, ExcepcionParametrosDeEntradaIncorrectos, ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		actualizarLookAndFeel();
		Aplicacion a = Aplicacion.cargarDatos();
		GuiAplicacion g = GuiAplicacion.getInstance();
		System.out.println("Ejecutando main de Gui.Aplicaion. "+ a + " " + g);
	}
}
