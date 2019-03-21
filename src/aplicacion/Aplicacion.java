package aplicacion;

import java.io.*;
import java.time.*;
import java.util.*;
import media.*;
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
     */
    private Aplicacion(int limiteReproducciones, int precioPremium,
                       int reproduccionesPremium) {
        this.limiteReproducciones = limiteReproducciones;
        this.precioPremium = precioPremium;
        this.reproduccionesPremium = reproduccionesPremium;
        this.usuarios = new ArrayList<UsuarioRegistrado>();
        this.buscables = new ArrayList<Buscable>();
        this.administrador = new Administrador("admin","admin");
        this.usuarioLogeado = null;
        try {
			this.cola = new Mp3Player();
		} catch (FileNotFoundException e) {
			
		} catch (Mp3PlayerException e) {
			
		}
    }

    /**
     * Getter de la instancia de Aplicacion
     *
     * @return la instancia de Aplicacion 
     */
    public static Aplicacion getInstance() {
        if (INSTANCE==null) INSTANCE = new Aplicacion(10,10,10);
        return INSTANCE; 
    }





    /**
     * Aniade un usario a la aplicacion
     *
     * @param nombreUsuario Nombre del usuario
     * @param contraseña Contrasenia del usuario
     * @param nombreCompleto Nombre completo del usuario
     * @param fechaNacimiento Fecha de nacimiento del usuario
     */
    public void aniadirUsuario(String nombreUsuario,
                               String contraseña,
                               String nombreCompleto,
                               LocalDate fechaNacimiento) {
        this.usuarios.add(new UsuarioRegistrado(nombreUsuario,
                                                contraseña,
                                                nombreCompleto,
                                                fechaNacimiento));
    }

    /**
     * Aniade una cancion a la aplicacion
     *
     * @param titulo Titulo de la cancion
     * @param fichero Fichero de audio de la cancion
     */
    public void aniadirCancion(String titulo, String fichero) {
        this.buscables.add(new Cancion(titulo,fichero,this.usuarioLogeado));
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
     * @param titulo Titulo del album
     * @param anio Anio del album
     * @param canciones Canciones que forman el album
     */
    public void aniadirAlbum(String titulo, LocalDate anio, ArrayList <Cancion> canciones) {
        this.buscables.add(new Album(titulo,anio,canciones));
    }

    /**
     * Borra un album de la aplicacion
     *
     * @param Album album que se pretende borrar
     */
    public void borrarAlbum(Album album) {
        this.buscables.remove(album);
    }

    /**
     * Actualiza el usuario logeado
     * Cuando el usuario inicia sesion por primera vez en un mes,
     * se comprueba si en el mes anterior obtuvo suficientes
     * reproducciones como para pasar a ser premium
     *
     * @param nombreUsuario Nombre del usuario que inicia sesion
     * @param contrasenia Contrasenia del usuario que inicia sesion
     * @return true si se inicia sesion correctamente
     */
    public boolean login(String nombreUsuario, String contrasenia) {
        for (UsuarioConCuenta u : usuarios){
            if (u.getNombreUsuario() == nombreUsuario &&
                u.getContrasenia() == contrasenia) {
                this.usuarioLogeado = u;
                if (u.getUltimoLogin().getMonth() == LocalDate.now().minusMonths(1).getMonth()) {
                    if (u.tieneSuficientesReproducciones()) {
                        u.setPremiumHasta(LocalDate.now().plusMonths(1));
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Cierra la sesion del usuario que esta logeado
     */
    public void logout() {
        this.usuarioLogeado.setUltimoLogin(LocalDate.now());
        actualizarCanciones();
        this.usuarioLogeado = null;
    }

    /**
     * Implementa la busqueda por titulo
     *
     * @param s Parametro de la busqueda
     * @return Devuelve la lista de canciones que empiezan por s
     */
    public ArrayList<Reproducible> buscarPorTitulo(String s) {
        ArrayList<Reproducible> coincidencias = new ArrayList<>();
        for (Reproducible reproducible : this.reproducibles) {
            if (reproducible.getTitulo().startsWith(s)) {
                coincidencias.add(reproducible);
            }
        }
    }

    /**
     * Implementa la busqueda por autor
     *
     * @param s Parametro de la busqueda
     * @return Devuelve la lista de canciones cuyo autor tiene un
     * nombre que empieza por s
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
     * @param elemento Elemento que se pretende reproducir
     */
    public void reproducirElemento(Elemento elemento) {
        if (usuarioLogeado == null) {
            this.cola = new Mp3Player();
        }
        elemento.reproducir(cola);
    }

    /**
     * Bloquea la cancion denunciada y crea una denuncia que sera
     * tramitada por el administrador
     * Bloquea la cancion y todos los albumes en los que aparezca
     *
     * @param cancion Cancion que se denuncia
     */
    public void denunciarPlagio(Cancion cancion) {
        for (Buscable buscable : cancion.getAutor().getBuscables()) {
            if (buscable.contiene(cancion)) {
                buscable.setEstado(Estado.BLOQUEADO);
            }
        }
        this.administrador.aniadirDenuncia(new Denuncia(this.usuarioLogeado,cancion));
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
    public static void guardarDatos() throws IOException {
        ObjectOutputStream salidaObjetos = 
            new ObjectOutputStream(
                new FileOutputStream( "aplicacion.objectData" ) );

        salidaObjetos.writeObject(this);
        salidaObjetos.close();
    }

    /**
     * Carga los datos de la aplicacion
     */
    public static void cargarDatos() {
        ObjectInputStream entradaObjetos =
            new ObjectInputStream(
                new FileInputStream( "puntos.objectData" ) );

    INSTANCE = (Aplicacion) entradaObjetos.readObject();
    entradaObjetos.close();
    }


















}
