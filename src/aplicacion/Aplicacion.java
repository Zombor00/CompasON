package aplicacion;

import java.io.*;
import java.time.*;
import java.util.*;

import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import es.uam.eps.padsof.telecard.TeleChargeAndPaySystem;
import excepciones.*;
import media.*;
import gestion.*;
import usuarios.*;
import pads.musicPlayer.*;
import pads.musicPlayer.exceptions.*;

/**
 * Implementamos la clase Aplicacion utilizando el Patron Singleton
 *
 * @author Antonio Garcia antonio.garcian@estudiante.uam.es
 * @version 1.0 (05-03-2019)
 */
public class Aplicacion implements Serializable {

	final String nombreAdministrador = "admin";
	final String contraseniaAdministrador = "admin";

	/**
	 * Limite de reproducciones mensuales de la aplicacion
	 */
	private int limiteReproducciones;

	/**
	 * Cuota mensual (en euros) que se debe pagar para contratar la version premium
	 */
	private int precioPremium;

	/**
	 * Numero de reproducciones que se deben alcanzar para poder obtener la version
	 * premium de forma gratuita
	 */
	private int reproduccionesPremium;

	/**
	 * Lista de usuarios de la aplicacion
	 */
	private List<UsuarioRegistrado> usuarios;

	/**
	 * Lista de canciones y albumes de la aplicacion
	 */
	private List<Buscable> buscables;

	/**
	 * Administrador de la aplicacion
	 */
	private Administrador administrador;

	/**
	 * Indica si el administrador ha iniciado sesion en la aplicacion
	 */
	private boolean administradorLogeado;

	/**
	 * Usuario que esta actualmente logeado en la aplicacion
	 */
	private UsuarioRegistrado usuarioLogeado;

	/**
	 * Cola de reproduccion de la aplicacion
	 */
	private Mp3Player cola;

	/**
	 * Instancia de la clase Aplicacion
	 */
	private static Aplicacion INSTANCE;

	/**
	 * Constructor de la clase (privado para impedir la instanciacion desde otras
	 * clases)
	 *
	 * @param limiteReproducciones
	 * @param precioPremium
	 * @param reproduccionesPremium
	 */
	private Aplicacion(int limiteReproducciones, int precioPremium, int reproduccionesPremium) {
		this.limiteReproducciones = limiteReproducciones;
		this.precioPremium = precioPremium;
		this.reproduccionesPremium = reproduccionesPremium;
		this.usuarios = new ArrayList<UsuarioRegistrado>();
		this.buscables = new ArrayList<Buscable>();
		this.administrador = new Administrador(nombreAdministrador, contraseniaAdministrador);
		this.administradorLogeado = false;
		this.usuarioLogeado = null;
		try {
			this.cola = new Mp3Player();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (Mp3PlayerException e) {
			System.out.println(e);
		}
	}

	/**
	 * Getter de la instancia de Aplicacion
	 *
	 * @return la instancia de Aplicacion
	 */
	public static Aplicacion getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Aplicacion(10, 10, 10);
		return INSTANCE;
	}

	/**
	 * Getter para el atributo administradorLogeado Con privacidad de paquete para
	 * que solo sea usado por AplicacionTest
	 */
	boolean getAdministradorLogeado() {
		return administradorLogeado;
	}

	/**
	 * Getter para el atributo cola
	 */
	public Mp3Player getCola() {
		return cola;
	}

	/**
	 * Aniade un usario a la aplicacion
	 *
	 * @param nombreUsuario   Nombre del usuario
	 * @param contraseña      Contrasenia del usuario
	 * @param nombreCompleto  Nombre completo del usuario
	 * @param fechaNacimiento Fecha de nacimiento del usuario
	 */
	public void aniadirUsuario(String nombreUsuario, String contrasenia, String nombreCompleto,
			LocalDate fechaNacimiento) {
		this.usuarios.add(new UsuarioRegistrado(nombreUsuario, contrasenia, nombreCompleto, fechaNacimiento));
	}

	/**
	 * Getter del atributo usuarioLogeado
	 * 
	 * @return usuario logeado
	 */
	public UsuarioRegistrado getUsuarioLogeado() {
		return usuarioLogeado;
	}

	/**
	 * Aniade una cancion a la aplicacion
	 *
	 * @param titulo  Titulo de la cancion
	 * @param fichero Fichero de audio de la cancion
	 */
	public void aniadirCancion(String titulo, String fichero) {
		this.buscables.add(new Cancion(titulo, fichero, this.usuarioLogeado));
	}

	/**
	 * Borra una cancion de la aplicacion
	 *
	 * @param cancion Cancion que se pretende borrar
	 */
	public void borrarCancion(Cancion cancion) {
		this.buscables.remove(cancion);
	}

	/**
	 * Aniade un album a la aplicacion
	 *
	 * @param titulo    Titulo del album
	 * @param anio      Anio del album
	 * @param canciones Canciones que forman el album
	 */
	public void aniadirAlbum(String titulo, LocalDate anio, ArrayList<Cancion> canciones) {
		this.buscables.add(new Album(titulo, anio, canciones));
	}

	/**
	 * Borra un album de la aplicacion
	 *
	 * @param album album que se pretende borrar
	 */
	public void borrarAlbum(Album album) {
		this.buscables.remove(album);
	}

	/**
	 * Actualiza el usuario logeado
	 *
	 * @param nombreUsuario Nombre del usuario que inicia sesion
	 * @param contrasenia   Contrasenia del usuario que inicia sesion
	 * @return true si se inicia sesion correctamente
	 */
	public void login(String nombreUsuario, String contrasenia)
			throws ExcepcionLoginErrorCredenciales, ExcepcionLoginBloqueado {
		if (nombreUsuario == nombreAdministrador && contrasenia == contraseniaAdministrador) {
			administradorLogeado = true;
			return;
		}

		/* Intentamos un login normal de un usuario registrado */
		for (UsuarioRegistrado u : usuarios) {
			if (u.getNombreUsuario() == nombreUsuario && u.getContrasenia() == contrasenia) {
				usuarioLogeado = u;

				/* Si el usuario esta bloqueado, login falla */
				if (u.getBloqueadoHasta() != null && u.getBloqueadoHasta().isAfter(LocalDate.now())) {
					usuarioLogeado = null;
					throw (new ExcepcionLoginBloqueado());
				}
				u.setBloqueadoHasta(null);

				/* Si es la primera vez que inicia sesio este mes... */
				if (u.getUltimoLogin() != null
						&& u.getUltimoLogin().getMonth() == LocalDate.now().minusMonths(1).getMonth()) {

					/* Ponemos el contador de reproducidas a cero */
					u.setReproducidas(0);

					/*
					 * Comprobamos si en el mes anterior obtuvo reproducciones suficientes como para
					 * pasar a ser premium
					 */
					if (u.reproduccionesUltimoMes() >= reproduccionesPremium) {
						u.setPremiumHasta(LocalDate.now().plusMonths(1));
					}
				}

				/* Comprobamos si debe dejar de ser premium */
				if (u.getPremiumHasta() != null && u.getPremiumHasta().isBefore(LocalDate.now())) {
					u.setPremiumHasta(null);
				}

				return;
			}
		}
		/* Si no se consigue iniciar sesion, login falla */
		throw (new ExcepcionLoginErrorCredenciales());
	}

	/**
	 * Cierra la sesion del usuario que esta logeado
	 */
	public void logout() {
		actualizarCanciones();
		if (administradorLogeado == false && usuarioLogeado == null) {
			return;
		}
		if (administradorLogeado == true) {
			administradorLogeado = false;
		} else {
			this.usuarioLogeado.setUltimoLogin(LocalDate.now());
			this.usuarioLogeado = null;
		}
	}

	/**
	 * Implementa la busqueda por titulo
	 *
	 * @param s Parametro de la busqueda
	 * @return Devuelve la lista de canciones y albumes que empiezan por s
	 */
	public ArrayList<Buscable> buscarPorTitulo(String s) {
		ArrayList<Buscable> coincidencias = new ArrayList<>();
		for (Buscable buscable : this.buscables) {
			if (buscable.getTitulo().startsWith(s)) {
				coincidencias.add(buscable);
			}
		}
		return coincidencias;
	}

	/**
	 * Implementa la busqueda por autor
	 *
	 * @param s Parametro de la busqueda
	 * @return Devuelve la lista de canciones y albumes cuyo autor tiene un nombre
	 *         que empieza por s
	 */
	public ArrayList<Buscable> buscarPorAutor(String s) {
		ArrayList<Buscable> coincidencias = new ArrayList<>();
		for (UsuarioRegistrado autor : this.usuarios) {
			if (autor.getNombreUsuario().startsWith(s)) {
				coincidencias.addAll(autor.getBuscables());
			}
		}
		return coincidencias;
	}

	/**
	 * Reproduce un elemento
	 *
	 * @param reproducible Elemento que se pretende reproducir
	 * @throws Mp3PlayerException
	 * @throws FileNotFoundException
	 * @throws ExcepcionLimiteReproducidasAlcanzado
	 * @throws ExcepcionNoAptoParaMenores
	 */
	public void reproducirReproducible(Reproducible reproducible) throws FileNotFoundException, Mp3PlayerException,
			ExcepcionLimiteReproducidasAlcanzado, ExcepcionNoAptoParaMenores {
		this.cola.stop();
		this.cola = new Mp3Player();
		if (usuarioLogeado != null && usuarioLogeado.getReproducidas() >= limiteReproducciones
				&& usuarioLogeado.getPremiumHasta() == null) {
			throw new ExcepcionLimiteReproducidasAlcanzado();
		}

		if (usuarioLogeado != null && usuarioLogeado.esMenor() && reproducible.esAptoParaMenores() == false) {
			throw new ExcepcionNoAptoParaMenores();
		}

		reproducible.reproducir(cola);
		if (usuarioLogeado != null) {
			usuarioLogeado.aniadirReproducida();
		}
		this.cola.play();
	}

	/**
	 * Bloquea la cancion denunciada y crea una denuncia que sera tramitada por el
	 * administrador Bloquea la cancion y todos los albumes en los que aparezca
	 *
	 * @param cancion Cancion que se denuncia
	 */
	public void denunciarPlagio(Cancion cancion, String comentario) {
		for (Buscable buscable : cancion.getAutor().getBuscables()) {
			if (buscable.contieneReproducible(cancion)) {
				buscable.setEstado(Estado.BLOQUEADO);
			}
		}
		this.administrador.aniadirDenuncia(new Denuncia(this.usuarioLogeado, cancion, comentario));
	}

	/**
	 * Actualiza la base de datos de canciones de la aplicacion, añadiendo las las
	 * nuevas canciones que hayan sido validadas por el administrador
	 *
	 * Borra las canciones no validadas por el administrador que han excedido el
	 * tiempo establecido para su modificacion
	 */
	public void actualizarCanciones() {
		List<Cancion> validadas = new ArrayList<>();
		List<Cancion> noValidadas = new ArrayList<>();

		for (Cancion cancion : administrador.getCancionesNuevas()) {
			if (cancion.getEstadoValidacion() != EstadoValidacion.NOVALIDADA) {
				validadas.add(cancion);
			}
		}
		for (Cancion cancion : validadas) {
			buscables.add(cancion);
			cancion.getAutor().getBuscables().add(cancion);
		}
		administrador.getCancionesNuevas().removeAll(validadas);

		for (Cancion cancion : administrador.getCancionesNuevas()) {
			if (cancion.getModificableHasta().isBefore(LocalDate.now())) {
				noValidadas.add(cancion);
			}
		}
		administrador.getCancionesNuevas().removeAll(noValidadas);
	}

	/**
	 * Guarda los datos de la aplicacion
	 */
	public void guardarDatos() throws IOException {
		ObjectOutputStream salidaObjetos = new ObjectOutputStream(new FileOutputStream("aplicacion.objectData"));

		salidaObjetos.writeObject(this);
		salidaObjetos.close();
	}

	/**
	 * Carga los datos de la aplicacion
	 */
	public void cargarDatos() {
		ObjectInputStream entradaObjetos = null;
		try {
			entradaObjetos = new ObjectInputStream(new FileInputStream("aplicacion.objectData"));
			INSTANCE = (Aplicacion) entradaObjetos.readObject();
			entradaObjetos.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Borra los usuarios y los buscables de la aplicacion
	 */
	public void borrarDatos() {
		this.usuarios = new ArrayList<UsuarioRegistrado>();
		this.buscables = new ArrayList<Buscable>();
	}

	/**
	 * Gestiona el pago que hace un usuario para pasar a ser premium
	 */
	public void pagarPremium(UsuarioRegistrado usuario, String cardNumStr, String subject) {
		try {
			TeleChargeAndPaySystem.charge(cardNumStr, subject, precioPremium, true);
		} catch (InvalidCardNumberException e) {
			System.out.println("Numero de tarjeta incorrecto.");
		} catch (FailedInternetConnectionException e) {
			System.out.println("Fallo al conectarse a Internet.");
		} catch (OrderRejectedException e) {
			System.out.println("Pago rechazado");
		}

		usuario.setPremiumHasta(LocalDate.now().plusDays(30));

	}

}
