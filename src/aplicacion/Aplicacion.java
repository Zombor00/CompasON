package aplicacion;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.file.*;
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
     * Cuota mensual (en euros) que se debe pagar para contratar
     * la version premium
     */
    private int precioPremium;

    /**
     * Numero de reproducciones que se deben alcanzar para poder
     * obtener la version premium de forma gratuita
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
     * Constructor de la clase (privado para impedir la instanciacion
     * desde otras clases)
     *
     * @param limiteReproducciones
     * @param precioPremium
     * @param reproduccionesPremium
     * @throws Mp3PlayerException 
     * @throws FileNotFoundException 
     * @throws ExcepcionParametrosDeEntradaIncorrectos 
     */
    private Aplicacion(int limiteReproducciones, int precioPremium,
                       int reproduccionesPremium) throws FileNotFoundException, Mp3PlayerException, ExcepcionParametrosDeEntradaIncorrectos {
    	if (limiteReproducciones < 0 || precioPremium < 0 || reproduccionesPremium < 0) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
        this.limiteReproducciones = limiteReproducciones;
        this.precioPremium = precioPremium;
        this.reproduccionesPremium = reproduccionesPremium;
        this.usuarios = new ArrayList<UsuarioRegistrado>();
        this.buscables = new ArrayList<Buscable>();
        this.administrador = new Administrador(nombreAdministrador,contraseniaAdministrador);
        this.administradorLogeado = false;
        this.usuarioLogeado = null;
        this.cola = new Mp3Player();
    }

    /**
     * Getter de la instancia de Aplicacion
     *
     * @return la instancia de Aplicacion 
     * @throws Mp3PlayerException 
     * @throws FileNotFoundException 
     * @throws ExcepcionParametrosDeEntradaIncorrectos 
     */
    public static Aplicacion getInstance(int limiteReproducciones, int precioPremium, int reproduccionesPremium) throws FileNotFoundException, Mp3PlayerException, ExcepcionParametrosDeEntradaIncorrectos {
        if (INSTANCE==null) INSTANCE = new Aplicacion(limiteReproducciones,precioPremium,reproduccionesPremium);
        return INSTANCE; 
    }





    /**
     * Getter para el atributo administradorLogeado
     * Con privacidad de paquete para que solo sea usado por AplicacionTest
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
     * Getter para el atributo cancionesNuevas
     */
    public List<Buscable> getBuscables() {
    	return buscables;
    }
    
    /**
     * Getter para el atributo usuarios
     */
    public List<UsuarioRegistrado> getUsuarios() {
    	return usuarios;
    }
    
    /**
     * Getter del atributo usuarioLogeado
     * @return usuario logeado
     */
    public UsuarioRegistrado getUsuarioLogeado() {
    	return usuarioLogeado;
    }
    
    /**
     * Getter del atributo administrador
     * @return usuario logeado
     */
    public Administrador getAdministrador() {
    	return administrador;
    }
    
    /**
     * Aniade un usario a la aplicacion
     *
     * @param nombreUsuario Nombre del usuario
     * @param contraseña Contrasenia del usuario
     * @param nombreCompleto Nombre completo del usuario
     * @param fechaNacimiento Fecha de nacimiento del usuario
     * @throws ExcepcionParametrosDeEntradaIncorrectos 
     * @throws ExcepcionNombreDeUsuarioNoDisponible 
     * @throws NoSuchAlgorithmException 
     */
    public void aniadirUsuario(String nombreUsuario,
                               String contrasenia,
                               String nombreCompleto,
                               LocalDate fechaNacimiento) throws ExcepcionParametrosDeEntradaIncorrectos, ExcepcionNombreDeUsuarioNoDisponible, NoSuchAlgorithmException {
    	if (nombreUsuario == null || contrasenia == null || nombreCompleto == null || fechaNacimiento == null) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
    	if (nombreUsuario == "admin") {
    		throw new ExcepcionNombreDeUsuarioNoDisponible();
    	}
    	for (UsuarioRegistrado usuario : usuarios) {
    		if (usuario.getNombreUsuario() == nombreUsuario) {
    			throw new ExcepcionNombreDeUsuarioNoDisponible();
    		}
    	}
        this.usuarios.add(new UsuarioRegistrado(nombreUsuario,
                                                this.hashContrasenia(contrasenia),
                                                nombreCompleto,
                                                fechaNacimiento));
    }
    
    /**
     * Envia la cancion al administrador para que la valide
     *
     * @param titulo Titulo de la cancion
     * @param fichero Fichero de audio de la cancion
     * @throws ExcepcionDuracionLimiteSuperada 
     * @throws Mp3InvalidFileException 
     * @throws ExcepcionParametrosDeEntradaIncorrectos 
     * @throws IOException 
     */
    public void subirCancion(String titulo, String fichero) throws ExcepcionDuracionLimiteSuperada, Mp3InvalidFileException, ExcepcionParametrosDeEntradaIncorrectos, IOException {
    	if (titulo == null || fichero == null) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
    	if (Mp3Player.isValidMp3File(fichero) == false) {
    		throw new Mp3InvalidFileException("");
    	}
    	if (Mp3Player.getDuration(fichero) > 30*60) {
    		throw new ExcepcionDuracionLimiteSuperada();
    	}
    	
        Files.copy(Paths.get(fichero), Paths.get("canciones/"+ titulo + ".mp3"), StandardCopyOption.REPLACE_EXISTING);
    	
    	Cancion cancion = new Cancion(titulo,"canciones/"+ titulo + ".mp3",this.usuarioLogeado);
        this.administrador.aniadirCancion(cancion);
        this.usuarioLogeado.aniadirCancion(cancion);
    }

    /**
     * Aniade una cancion a la aplicacion
     *
     * @param titulo Titulo de la cancion
     * @param fichero Fichero de audio de la cancion
     * @throws ExcepcionParametrosDeEntradaIncorrectos 
     * @throws FileNotFoundException 
     * @throws ExcepcionDuracionLimiteSuperada 
     */
    public void aniadirCancion(Cancion cancion) throws ExcepcionParametrosDeEntradaIncorrectos {
    	if (cancion == null ) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
        this.buscables.add(cancion);
    }

    /**
     * Borra una cancion de la aplicacion
     *
     * @param cancion Cancion que se pretende borrar
     * @throws ExcepcionParametrosDeEntradaIncorrectos 
     */
    public void borrarCancion(Cancion cancion) throws ExcepcionParametrosDeEntradaIncorrectos {
    	if (cancion == null ) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
        this.buscables.remove(cancion);
    }

    /**
     * Aniade un album a la aplicacion
     *
     * @param titulo Titulo del album
     * @param anio Anio del album
     * @param canciones Canciones que forman el album
     * @throws ExcepcionErrorCreandoAlbum 
     * @throws ExcepcionParametrosDeEntradaIncorrectos 
     */
    public void aniadirAlbum(String titulo, LocalDate anio, ArrayList <Cancion> canciones) throws ExcepcionErrorCreandoAlbum, ExcepcionParametrosDeEntradaIncorrectos {
    	if (titulo == null || anio == null || canciones==null) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
    	for (Cancion cancion : canciones) {
    		if (cancion.getAutor() != usuarioLogeado) {
    			throw new ExcepcionErrorCreandoAlbum();
    		}
    	}
    	Album album = new Album(titulo,anio,canciones);
        this.buscables.add(album);
        this.usuarioLogeado.aniadirBuscable(album);
    }

    /**
     * Borra un album de la aplicacion
     *
     * @param album album que se pretende borrar
     * @throws ExcepcionParametrosDeEntradaIncorrectos 
     */
    public void borrarAlbum(Album album) throws ExcepcionParametrosDeEntradaIncorrectos {
    	if (album == null) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
        this.buscables.remove(album);
    }

    /**
     * Actualiza el usuario logeado
     *
     * @param nombreUsuario Nombre del usuario que inicia sesion
     * @param contrasenia Contrasenia del usuario que inicia sesion
     * @return true si se inicia sesion correctamente
     * @throws ExcepcionParametrosDeEntradaIncorrectos 
     * @throws NoSuchAlgorithmException 
     */
    public void login(String nombreUsuario, String contrasenia) throws ExcepcionLoginErrorCredenciales, ExcepcionLoginBloqueado, ExcepcionParametrosDeEntradaIncorrectos, NoSuchAlgorithmException {
    	if (nombreUsuario == null || contrasenia == null) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
    	if (nombreUsuario == nombreAdministrador && contrasenia == contraseniaAdministrador) {
    		administradorLogeado = true;
    		return;
    	}
    	
    	/* Intentamos un login normal de un usuario registrado */
        for (UsuarioRegistrado u : usuarios){
            if (u.getNombreUsuario() == nombreUsuario &&
                u.getContrasenia().equals(this.hashContrasenia(contrasenia))) {
                usuarioLogeado = u;
                
                /* Si el usuario esta bloqueado, login falla */
                if (u.getBloqueadoHasta()!= null && u.getBloqueadoHasta().isAfter(LocalDate.now())) {
                	usuarioLogeado = null;
                	throw(new ExcepcionLoginBloqueado());
                }
                u.setBloqueadoHasta(null);
                
                /* Si es la primera vez que inicia sesio este mes... */
                if (u.getUltimoLogin()!=null &&
                    u.getUltimoLogin().getMonth() == LocalDate.now().minusMonths(1).getMonth()) {
                	
                	/* Ponemos el contador de reproducidas a cero */
                	u.setReproducidas(0);
                	
                	/* Comprobamos si en el mes anterior obtuvo reproducciones suficientes 
                	 * como para pasar a ser premium */
                    if (u.reproduccionesUltimoMes() >= reproduccionesPremium) {
                        u.setPremiumHasta(LocalDate.now().plusMonths(1).withDayOfMonth(1).minusDays(1));
                        for (Lista l : u.getListas()) {
                    		l.setEstado(Estado.NOBLOQUEADO);
                    	}
                    }
                }
                
                /* Comprobamos si debe dejar de ser premium */
                if (u.getPremiumHasta() != null && u.getPremiumHasta().isBefore(LocalDate.now())) {
                	u.setPremiumHasta(null);
                	for (Lista l : u.getListas()) {
                		l.setEstado(Estado.BLOQUEADO);
                	}
                }
                
                return;
            }
        }
        /* Si no se consigue iniciar sesion, login falla */
        throw(new ExcepcionLoginErrorCredenciales());
    }

    /**
     * Cierra la sesion del usuario que esta logeado
     */
    public void logout() {
    	actualizarCanciones();
    	if (administradorLogeado == false && usuarioLogeado ==null) {
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
     * @throws ExcepcionParametrosDeEntradaIncorrectos 
     */
    public ArrayList<Buscable> buscarPorTitulo(String s) throws ExcepcionParametrosDeEntradaIncorrectos {
    	if (s == null) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
        ArrayList<Buscable> coincidencias = new ArrayList<>();
        for (Buscable buscable : this.buscables) {
            if (buscable.getEstado() == Estado.NOBLOQUEADO && buscable.getTitulo().startsWith(s)) {
                coincidencias.add(buscable);
            }
        }
        return coincidencias;
    }

    /**
     * Implementa la busqueda por autor
     *
     * @param s Parametro de la busqueda
     * @return Devuelve la lista de canciones y albumes cuyo autor tiene un
     * nombre que empieza por s
     * @throws ExcepcionParametrosDeEntradaIncorrectos 
     */
    public ArrayList<Buscable> buscarPorAutor(String s) throws ExcepcionParametrosDeEntradaIncorrectos {
    	if (s == null) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
        ArrayList<Buscable> coincidencias = new ArrayList<>();
        for (UsuarioRegistrado autor : this.usuarios) {
            if (autor.getNombreUsuario().startsWith(s) || autor.getNombre().startsWith(s)) {
            	for (Buscable b : autor.getBuscables()) {
            		if (b.getEstado() == Estado.NOBLOQUEADO) {
            			coincidencias.add(b);
            		}
            	}
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
     * @throws ExcepcionParametrosDeEntradaIncorrectos 
     * @throws ExcepcionReproducirProhibido 
     */
    public void reproducirReproducible(Reproducible reproducible) throws FileNotFoundException, Mp3PlayerException, ExcepcionLimiteReproducidasAlcanzado, ExcepcionNoAptoParaMenores, ExcepcionParametrosDeEntradaIncorrectos, ExcepcionReproducirProhibido {
    	
    	if (reproducible == null) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
    	this.cola.stop();
    	this.cola = new Mp3Player();
        if (usuarioLogeado != null && 
        		   usuarioLogeado.getReproducidas() >= limiteReproducciones && 
        		   usuarioLogeado.getPremiumHasta() == null) {
        	throw new ExcepcionLimiteReproducidasAlcanzado();
        }
        
        if (usuarioLogeado != null && usuarioLogeado.esMenor() && reproducible.esAptoParaMenores() == false) {
        	throw new ExcepcionNoAptoParaMenores();
        }
        
        if (usuarioLogeado == null && administradorLogeado == false && reproducible.esAptoParaMenores() == false) {
        	throw new ExcepcionNoAptoParaMenores();
        }
        
        if (reproducible.getEstado() != Estado.NOBLOQUEADO) {
        	throw new ExcepcionReproducirProhibido();
        }
        
        int reproducidas = reproducible.reproducir(cola);
        if (usuarioLogeado != null) {
        	usuarioLogeado.setReproducidas(usuarioLogeado.getReproducidas() + reproducidas);
        }
        this.cola.play();
    }
    
    public void aniadirALaCola(Reproducible reproducible) throws ExcepcionParametrosDeEntradaIncorrectos, ExcepcionLimiteReproducidasAlcanzado, ExcepcionNoAptoParaMenores, ExcepcionReproducirProhibido {
    	if (reproducible == null) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
    	if (usuarioLogeado != null && 
    			usuarioLogeado.getReproducidas() >= limiteReproducciones && 
    			usuarioLogeado.getPremiumHasta() == null) {
    		throw new ExcepcionLimiteReproducidasAlcanzado();
    	}
    	if (usuarioLogeado != null && usuarioLogeado.esMenor() && reproducible.esAptoParaMenores() == false) {
        	throw new ExcepcionNoAptoParaMenores();
        }
    	int reproducidas = reproducible.reproducir(cola);
        if (usuarioLogeado != null) {
        	usuarioLogeado.setReproducidas(usuarioLogeado.getReproducidas() + reproducidas);
        }
    }

    /**
     * Bloquea la cancion denunciada y crea una denuncia que sera
     * tramitada por el administrador
     * Bloquea la cancion y todos los albumes en los que aparezca
     *
     * @param cancion Cancion que se denuncia
     * @throws ExcepcionParametrosDeEntradaIncorrectos 
     */
    public void denunciarPlagio(Cancion cancion, String comentario) throws ExcepcionParametrosDeEntradaIncorrectos {
    	if (cancion == null || comentario == null) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
        for (Buscable buscable : cancion.getAutor().getBuscables()) {
            if (buscable.contieneReproducible(cancion)) {
                buscable.setEstado(Estado.BLOQUEADO);
            }
        }
        this.administrador.aniadirDenuncia(new Denuncia(this.usuarioLogeado,cancion, comentario));
        cancion.getAutor().aniadirNotificacion(new NotificacionDenuncia(cancion));
    }

    /**
     * Actualiza la base de datos de canciones de la aplicacion, añadiendo las
     * las nuevas canciones que hayan sido validadas por el administrador
     *
     * Borra las canciones no validadas por el administrador que han excedido
     * el tiempo establecido para su modificacion
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
            cancion.getAutor().aniadirBuscable(cancion);
            NotificacionCancion notificacion = new NotificacionCancion(cancion);
            for (UsuarioRegistrado seguidor : cancion.getAutor().getSeguidores()) {
            	seguidor.aniadirNotificacion(notificacion);
            }
        }
        administrador.getCancionesNuevas().removeAll(validadas);
        for (Cancion cancion : validadas) {
            cancion.getAutor().getCancionesNuevas().removeAll(validadas);
        }


        for (Cancion cancion : administrador.getCancionesNuevas()) {
            if (cancion.getModificableHasta() != null && cancion.getModificableHasta().isBefore(LocalDate.now())) {
                noValidadas.add(cancion);
            }
        }
        administrador.getCancionesNuevas().removeAll(noValidadas);
    }

    /**
     * Guarda los datos de la aplicacion
     */
    public void guardarDatos() throws IOException {
        ObjectOutputStream salidaObjetos = 
            new ObjectOutputStream(
                new FileOutputStream( "aplicacion.objectData" ) );

        salidaObjetos.writeObject(this);
        salidaObjetos.close();
    }

    /**
     * Carga los datos de la aplicacion
     * @throws IOException 
     * @throws FileNotFoundException 
     * @throws ClassNotFoundException 
     */
    public void cargarDatos() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream entradaObjetos = null;
        entradaObjetos =
        		new ObjectInputStream(
        				new FileInputStream( "aplicacion.objectData" ) );
        INSTANCE = (Aplicacion) entradaObjetos.readObject();
        entradaObjetos.close();
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
	 * @throws OrderRejectedException 
	 * @throws FailedInternetConnectionException 
	 * @throws InvalidCardNumberException 
	 * @throws ExcepcionParametrosDeEntradaIncorrectos 
	 */
	public void pagarPremium(String cardNumStr, String subject) throws InvalidCardNumberException, FailedInternetConnectionException, OrderRejectedException, ExcepcionParametrosDeEntradaIncorrectos {
		if (cardNumStr == null || subject == null) {
    		throw new ExcepcionParametrosDeEntradaIncorrectos();
    	}
		if (usuarioLogeado == null) {
			return;
		}
		TeleChargeAndPaySystem.charge(cardNumStr, subject, precioPremium, true);
		usuarioLogeado.setPremiumHasta(LocalDate.now().plusDays(30));
		for (Lista l : usuarioLogeado.getListas()) {
    		l.setEstado(Estado.NOBLOQUEADO);
    	}

	}
	
	/**
	 * Pasa una contrasenia a su hash en MD5
	 * @param contrasenia sin hashear
	 * @return String: contrasenia haseada
	 * @throws NoSuchAlgorithmException 
	 */
	public String hashContrasenia(String contrasenia) throws NoSuchAlgorithmException {
		byte[] hashBytes;
		String hash;
		MessageDigest mD = MessageDigest.getInstance("MD5");
		mD.update(contrasenia.getBytes());
		hashBytes = mD.digest();
		hash = new String(hashBytes);
		return hash;
	}
}
