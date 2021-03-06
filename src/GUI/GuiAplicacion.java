package GUI;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import controladores.ControladorMisCanciones;
import controladores.ControladorMisListas;
import controladores.ControladorNotificacion;
import controladores.ControladorOpciones;
import controladores.ControladorPago;
import controladores.ControladorRegistro;
import controladores.ControladorReproductor;
import excepciones.ExcepcionCancionModificable;
import excepciones.ExcepcionCancionNoValidada;
import excepciones.ExcepcionCancionYaNoModificable;
import excepciones.ExcepcionCancionYaValidada;
import excepciones.ExcepcionDuracionLimiteSuperada;
import excepciones.ExcepcionErrorCreandoAlbum;
import excepciones.ExcepcionInsercionInvalida;
import excepciones.ExcepcionLimiteReproducidasAlcanzado;
import excepciones.ExcepcionLoginBloqueado;
import excepciones.ExcepcionLoginErrorCredenciales;
import excepciones.ExcepcionMp3NoValido;
import excepciones.ExcepcionNoAptoParaMenores;
import excepciones.ExcepcionNombreDeUsuarioNoDisponible;
import excepciones.ExcepcionParametrosDeEntradaIncorrectos;
import excepciones.ExcepcionReproducibleNoValido;
import excepciones.ExcepcionReproducirProhibido;
import excepciones.ExcepcionSeguirseASiMismo;
import excepciones.ExcepcionUsuarioNoPremium;
import excepciones.ExcepcionUsuarioNoSeguido;
import excepciones.ExcepcionUsuarioSinCuenta;
import excepciones.ExcepcionUsuarioYaSeguido;
import media.Buscable;
import media.Cancion;
import pads.musicPlayer.exceptions.Mp3PlayerException;
import test.GeneraDatos;

/**
 * Esta clase tiene toda la informacion relevante a la ventana de la
 * aplicacion creada usando un patron Singleton
 * @author Alejandro Bravo(alejandro.bravodela@estudiante.uam.es)
 * 		   Antonio Garcia (antonio.garcian@estudiante.uam.es)
 * 		   Alvaro Zaera (alvaro.zaeradela@estudiante.uam.es)
 *         Grupo CompasON
 *
 */
public class GuiAplicacion extends JFrame {
	
	/**
	 * Unica instancia de la ventana de la aplicacion
	 */
	public static GuiAplicacion INSTANCE = null;
	/**
	 * Variable con los paneles que tienen los distintos usuarios
	 */
	private PanelesUsuarios panelesUsuarios = new PanelesUsuarios();
	/**
	 * Reproductor para pausar y reproducir desde el inicio canciones
	 */
	private Reproductor reproductor = new Reproductor();
	/**
	 * Logo de la aplicacion
	 */
	public static ImageIcon IconoCompasON = new ImageIcon("aux/icono-compason.png");
	
	/**
	 * Constructor que crea la ventana con el reproductor y los paneles de
	 * todos los usuarios, estableciendo todos los controladores de cada panel
	 * 
	 */
	private GuiAplicacion() {
		super("COMPASON");
		this.setIconImage(IconoCompasON.getImage());
		Container contenedor = this.getContentPane();
		BorderLayout layout = new BorderLayout();
		contenedor.setLayout(layout);

		contenedor.add(panelesUsuarios, BorderLayout.CENTER);
		contenedor.add(reproductor, BorderLayout.SOUTH);

		Registro registro = panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getInicio().getRegistro();
		registro.setControlador(new ControladorRegistro(registro));

		reproductor.setControlador(new ControladorReproductor());

		Login login = panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().getInicio().getLogin();
		login.setControlador(new ControladorLogin(login));

		Sesion sesion1 = panelesUsuarios.getPanelUsuarioRegstrado().getInformacion().getSesion();
		sesion1.setControlador(new ControladorSesion(sesion1));
		Sesion sesion2 = panelesUsuarios.getPanelAdministrador().getInformacionAdministrador().getSesion();
		sesion2.setControlador(new ControladorSesion(sesion2));
		Sesion sesion3 = panelesUsuarios.getPanelUsuarioPremium().getInformacion().getSesion();
		sesion3.setControlador(new ControladorSesion(sesion3));

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

		Notificaciones notificacionesRegistrado = panelesUsuarios.getPanelUsuarioRegstrado().getInformacion()
				.getNotificaciones();
		notificacionesRegistrado.setControlador(new ControladorNotificacion(notificacionesRegistrado));
		Notificaciones notificacionesPremium = panelesUsuarios.getPanelUsuarioPremium().getInformacion()
				.getNotificaciones();
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

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				try {
					Aplicacion a = Aplicacion.getInstance();
					int reproducidas = a.getCola().getSongsPlayed();
					if(a.getUsuarioLogeado() != null) {
						a.getUsuarioLogeado().setReproducidas(a.getUsuarioLogeado().getReproducidas() + reproducidas);					
					}
					a.getCola().resetSongsPlayed();	
					a.logout();
					a.guardarDatos();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Mp3PlayerException e) {
					e.printStackTrace();
				}
			}
		});

		this.setSize(this.getToolkit().getScreenSize());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.actualizarDatos();

	}

	public static GuiAplicacion getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GuiAplicacion();
		return INSTANCE;
	}

	public PanelesUsuarios getPanelesUsuarios() {
		return this.panelesUsuarios;
	}

	public Reproductor getReproductor() {
		return this.reproductor;
	}
	
	/**
     * Metodo que actualiza los datos de todos los paneles de la aplicacion
     * 
     */
	public void actualizarDatos() {
		this.panelesUsuarios.actualizarDatos();
	}
	
	/**
     * Metodo que muestra la ventana de busqueda al usuario correspondiente y 
     * actualiza los buscables que aparecen en ella
     * 
     * @param buscables a mostrar en la busqueda
     * @param actual cadena que indica el panel actual a modificar
     */
	public void actualizarBusqueda(ArrayList<Buscable> buscables, String actual) {
		switch (actual) {
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
	
	/**
     * Metodo que muestra la cancion que recibe en la pestania mis canciones
     * del usuario indicado
     * 
     * @param cancion a resaltar
     * @param actual cadena que indica el panel actual a modificar
     */
	public void mostrarDenunciada(Cancion cancion, String actual) {
		switch (actual) {
		case PanelesUsuarios.REGISTRADO:
			PestaniasUsuarioRegistrado pRe = this.panelesUsuarios.getPanelUsuarioRegstrado().getPestanias();
			JTable tabla = pRe.getMisCanciones().getTablaCanciones();
			for (int i=0; i < tabla.getRowCount(); i++) {
				Cancion c = (Cancion)tabla.getModel().getValueAt(i, 0);
				if (c.equals(cancion)) tabla.addRowSelectionInterval(i, i);
			}
			pRe.setSelectedIndex(2);
			pRe.getMisCanciones().getCanciones().doClick();
			break;
		case PanelesUsuarios.PREMIUM:
			PestaniasUsuarioPremium pPr = this.panelesUsuarios.getPanelUsuarioPremium().getPestanias();
			tabla = pPr.getMisCanciones().getTablaCanciones();
			for (int i=0; i < tabla.getSize().height; i++) {
				Cancion c = (Cancion)tabla.getModel().getValueAt(i, 0);
				if (c.equals(cancion)) tabla.addRowSelectionInterval(i, i);
			}
			pPr.setSelectedIndex(2);
			pPr.getMisCanciones().getCanciones().doClick();
			break;
		}

	}
	
	/**
     * Metodo que selecciona la pestania de inicio del panel indicado
     * 
     * @param actual cadena que indica el panel actual a modificar
     */
	public void seleccionarInicio(String actual) {
		switch (actual) {
		case PanelesUsuarios.SIN_CUENTA:
			this.panelesUsuarios.getPanelUsuarioSinCuenta().getPestanias().setSelectedIndex(0);
			break;
		case PanelesUsuarios.REGISTRADO:
			this.panelesUsuarios.getPanelUsuarioRegstrado().getPestanias().setSelectedIndex(0);
			break;
		case PanelesUsuarios.PREMIUM:
			this.panelesUsuarios.getPanelUsuarioPremium().getPestanias().setSelectedIndex(0);
			break;
		case PanelesUsuarios.ADMINISTRADOR:
			this.panelesUsuarios.getPanelAdministrador().getPestaniasAdministrador().setSelectedIndex(0);
			break;
		}
	}
	
	/**
     * Metodo que actualiza la apariencia de la aplicacion
     * 
     */
	public static void actualizarLookAndFeel() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		Color verde = new Color(0, 130, 30);
		Color negro = Color.BLACK;
		Color gris = Color.DARK_GRAY;
		Color blanco = Color.WHITE;
		Color grisClaro = new Color(250, 250, 250);
		UIManager.put("control", negro); // Fondo
		UIManager.put("text", blanco); // Texto
		UIManager.put("nimbusBase", gris); // Base 1
		UIManager.put("nimbusBlueGrey", verde); // Base 2
		UIManager.put("nimbusSelection", verde); // Botones de menus
		UIManager.put("textBackground", blanco); // Fondo texto seleccionado
		UIManager.put("nimbusSelectedText", negro); // Texto seleccionado
		UIManager.put("nimbusSelectionBackground", grisClaro); // Seleccion en tablas
		UIManager.put("nimbusFocus", verde); // Foco
		UIManager.put("nimbusLightBackground", gris); // Fondo normal de textos
		UIManager.put("nimbusBorder", negro); // Bordes

	}
	
	/**
     * Metodo que crea un popup con el mensaje indicado
     * 
     * @param message cadena que aparece en el popup
     */
	public static void showMessage(String message) {
		new PopupMessage(message);
	}

	/**
     * Metodo main que inicia la aplicacion
     * 
     */
	public static void main(String[] args)
			throws Mp3PlayerException, ExcepcionParametrosDeEntradaIncorrectos, ClassNotFoundException,
			InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, InvalidCardNumberException, FailedInternetConnectionException, NoSuchAlgorithmException, InterruptedException, ExcepcionLimiteReproducidasAlcanzado, ExcepcionNoAptoParaMenores, ExcepcionLoginErrorCredenciales, ExcepcionLoginBloqueado, ExcepcionDuracionLimiteSuperada, ExcepcionCancionModificable, ExcepcionCancionYaValidada, ExcepcionNombreDeUsuarioNoDisponible, IOException, ExcepcionReproducirProhibido, ExcepcionUsuarioYaSeguido, ExcepcionUsuarioNoSeguido, ExcepcionErrorCreandoAlbum, OrderRejectedException, ExcepcionUsuarioNoPremium, ExcepcionCancionYaNoModificable, ExcepcionMp3NoValido, ExcepcionUsuarioSinCuenta, ExcepcionInsercionInvalida, ExcepcionCancionNoValidada, ExcepcionReproducibleNoValido, ExcepcionSeguirseASiMismo {
		actualizarLookAndFeel();
		Aplicacion a = null;
		// Si no hay datos se genera con funcionalidades basicas introducidas
		try {
			a = Aplicacion.cargarDatos();
		} catch (FileNotFoundException e) {
			GeneraDatos.main(null);
		}
		GuiAplicacion g = GuiAplicacion.getInstance();
		System.out.println("Ejecutando main de Gui.Aplicaion. " + a + " " + g);
	}

}
