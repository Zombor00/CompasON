
import java.util.*;

/**
* Esta clase contiene la informacion comun de los usuarios
* registrados en la aplicacion (sin incluir al administrador)
*
* @author Alvaro Zaera alvaro.zaeradela@estudiante.uam.es
* @version 1.0 (05-03-2019)
*
*/
public class UsuarioRegistrado extends UsuarioConCuenta{
    private String nombre;
    private LocalDate fechaNacimiento;
    private LocalDate premiumHasta;
    private LocalDate bloqueadoHasta;
    private LocalDate ultimoLogin;
    private ArrayList<LocalDate> reproducciones = new ArrayList<>();
    private boolean limiteReproduccionesAlcanzado;
    private int reproducidas;
    private Cola = new Cola();
    private ArrayList<Elemento> musica = new ArrayList<>();
    private ArrayList<Notificacion> notificaciones = new ArrayList<>();
    private ArrayList<UsuarioRegistrado> seguidos = new ArrayList<>();
    private ArrayList<UsuarioRegistrado> seguidores = new ArrayList<>();

    /**
    * Constructor, con el nombre de usuario, contrasenia, nombre y
    * fecha de nacimiento
    *
    * @param nombreUsuario
    * @param contrasenia del usuario
    * @param nombre real del usuario
    * @param fechaNacimiento
    */
    public UsuarioRegistrado(String nombreUsuario, String contrasenia,
                             String nombre, LocalDate fechaNacimiento){
        super(nombreUsuario,contrasenia);
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public ArrayList<UsuarioRegistrado> getSeguidores(){
        return this.seguidores;
    }

    public ArrayList<Elemento> getMusica(){
        return this.musica;
    }

    /**
    * Este metodo hace Premium a un usuario
    *
    */
    public void hacerPremium(){
        //to do: pago
        if (premiumHasta == null){
            this.premiumHasta = LocalDate.now().plusDays(30);
        }
    }

    /**
    * Este metodo se usa para aniadir una cancion al usuario
    *
    * @param c cancion a aniadir
    * @return boolean sobre si se hace correctamente
    */
    public boolean aniadirCancion(Cancion c){
        return this.musica.add(c);
    }

    /**
    * Este metodo almacena el usuario que se recibe en lista de seguidos y guarda
    * este objeto en la lista de seguidores del que se recibe como argumento
    *
    * @param u usuario a seguir
    */
    public void seguirUsuario(UsuarioRegistrado u){
        if (this.seguidos.contains(u) == false){
            this.seguidos.add(u);
        }
        u.getSeguidores().add(this);
    }

    /**
    * Este metodo quita al usuario que se recibe de la lista de seguidos y quita
    * este objeto de la lista de seguidores del que se recibe como argumento
    *
    * @param u usuario a dejar de seguir
    */
    public void dejarSeguirUsuario(UsuarioRegistrado u){
        int indice;
        indice = this.seguidos.indexOf(u);
        if (indice != -1){
            this.seguidos.remove(indice);
        }
        indice = u.getSeguidores().indexOf(this);
        if (indice != -1){
            u.getSeguidores().remove(indice);
        }
    }

    /**
    * Este metodo se usa para crear una lista
    *
    * @param titulo de la lista
    * @param e elementos que contiene la lista inicialmente
    * @return boolean sobre si se hace correctamente
    */
    public boolean crearLista(String titulo, ArrayList<Elemento> e){
        if (this.premiumHasta == null){
            return false;
        }
        Lista list = new Lista(titulo,e);
        return this.musica.add(list);
    }

    /**
    * Este metodo se usa para crear un album
    *
    * @param titulo de la lista
    * @param c canciones que contiene el album inicialmente
    * @return boolean sobre si se hace correctamente
    */
    public boolean crearAlbum(String titulo, ArrayList<Cancion> c){
        Album album = new Album(titulo,c);
        return this.musica.add(album);
    }

    /**
    * Este metodo se usa para aniadir una cancion a la cola
    *
    * @param e elemento a aniadir a la cola
    * @return boolean sobre si se hace correctamente
    */
    public boolean aniadirALaCola(Elemento e){
        return this.cola.aniadirElemento(e);
    }

    /**
    * Este metodo se usa para aniadir una notificacion al usuario
    *
    * @param n notificacion a aniadir
    * @return boolean sobre si se hace correctamente
    */
    public boolean aniadirNotificacion(Notificacion n){
        return this.notificaciones.add(n);
    }





}
